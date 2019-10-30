package com.stargazerproject.analysis.resources.handle;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventExecuteAnalysis;
import com.stargazerproject.analysis.handle.TransactionExecuteAnalysisHandle;
import com.stargazerproject.analysis.handle.TransactionResultsExecuteAnalysisHandle;
import com.stargazerproject.bus.Bus;
import com.stargazerproject.bus.BusObserver;
import com.stargazerproject.bus.exception.BusEventTimeoutException;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.transaction.Event;
import com.stargazerproject.transaction.TransactionResultState;
import com.stargazerproject.transaction.date.TransactionDate;
import com.stargazerproject.transaction.date.TransactionRunWayDate;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 *  @name Transaction Execute Analysis Handle 的具体实现
 *  @illustrate Transaction Execute Analysis Handle 的具体实现
 *  @author Felixerio
 *  @version 1.1.0
 *  **/
public class TransactionExecuteAnalysisHandleResoources implements TransactionExecuteAnalysisHandle {

    /** @illustrate Transaction参数缓存 **/
    public Cache<String, String> cache;

    /** @illustrate Transaction包含的Event列表 **/
    private Collection<Event> eventList;

    /** @illustrate Event运行处理器 **/
    private EventExecuteAnalysis eventExecuteAnalysis;

    /** @illustrate EventBus接口 **/
    private static Bus<Event, BusEventTimeoutException> eventBus;

    private TransactionResultsExecuteAnalysisHandle transactionResultsExecuteAnalysisHandle;

    private ScheduledExecutorService scheduler;

    private ScheduledFuture<?> scheduledFuture;

    public TransactionExecuteAnalysisHandleResoources(Optional<Collection<Event>> eventListArg,
                                                      Optional<Cache<String, String>> transactionInteractionCacheArg,
                                                      Optional<TransactionResultsExecuteAnalysisHandle> transactionResultsExecuteAnalysisHandleArg,
                                                      Optional<Bus<Event, BusEventTimeoutException>> eventBusArg){
        eventBus = eventBusArg.get();
        eventList = eventListArg.get();
        cache = transactionInteractionCacheArg.get();
        transactionResultsExecuteAnalysisHandle = transactionResultsExecuteAnalysisHandleArg.get();
    }

    public void initScheduledFuture(List<BusObserver<Event, BusEventTimeoutException>> busObserverList){
        scheduledFuture= scheduler.scheduleAtFixedRate(() ->{

            Boolean result = busObserverList.stream().map(busObserver -> busObserver.testFinish().get()).reduce(Boolean.TRUE,(acc, element) -> acc&element);

            if(result.equals(Boolean.TRUE)){
                scheduledFuture.cancel(true);
            }
            else{ }

        }, 10, 10, TimeUnit.SECONDS);
    }

    /**
     * @name 启动Transaction序列事务方法
     * @illustrate 序列方法包括并行（乱序）非阻塞序列，标准顺序执行非阻塞序列，提供聚合跟功能的顺序执行非阻塞序列
     * **/
    @Override
    public void startTransaction() {
        TransactionRunWayDate transactionRunWayDate = transactionRunWayDate().get();

        if(transactionRunWayDate.equals(TransactionRunWayDate.Parallel.toString())){
            List<BusObserver<Event, BusEventTimeoutException>> busObserverList =  eventList.parallelStream().map(event -> Optional.of(event)).map(OptionalEvent -> eventBus.pushAsync(OptionalEvent).get()).collect(Collectors.toList());
            initScheduledFuture(busObserverList);
            try {
                scheduledFuture.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        else if(transactionRunWayDate.equals(TransactionRunWayDate.Sequence.toString())){
        }
        else if(transactionRunWayDate.equals(TransactionRunWayDate.AggregateRootSequence.toString())){
        }
        throw new IllegalArgumentException("TransactionRunWayDate参数错误，错误的参数为 : " + transactionRunWayDate);
    }

    @Override
    public Optional<TimeUnit> waitTimeoutUnit(){
        String waitTimeoutUnit = cache.get(Optional.of(TransactionDate.WaitTimeoutUnit.toString())).get();
        return conversionTimeUnit(waitTimeoutUnit);
    }

    @Override
    public Optional<Integer> waitTimeout(){
        String waitTimeout = cache.get(Optional.of(TransactionDate.WaitTimeout.toString())).get();
        return Optional.of(Integer.parseInt(waitTimeout));
    }

    @Override
    public Optional<TimeUnit> runTimeoutUnit() {
        String waitTimeoutUnit = cache.get(Optional.of(TransactionDate.RunTimeoutUnit.toString())).get();
        return conversionTimeUnit(waitTimeoutUnit);
    }

    @Override
    public Optional<Integer> runTimeout() {
        String waitTimeout = cache.get(Optional.of(TransactionDate.RunTimeout.toString())).get();
        return Optional.of(Integer.parseInt(waitTimeout));
    }

    private Optional<TimeUnit> conversionTimeUnit(String waitTimeoutUnit){
        switch (waitTimeoutUnit){
            case "NANOSECONDS":
                return Optional.of(TimeUnit.NANOSECONDS);
            case "MICROSECONDS":
                return Optional.of(TimeUnit.MICROSECONDS);
            case "MILLISECONDS":
                return Optional.of(TimeUnit.MILLISECONDS);
            case "SECONDS":
                return Optional.of(TimeUnit.SECONDS);
            case "HOURS":
                return Optional.of(TimeUnit.HOURS);
            case "DAYS":
                return Optional.of(TimeUnit.DAYS);
            default:
                throw new IllegalArgumentException("waitTimeoutUnit Error");
        }
    }

    private Optional<TransactionResultState> conversionResultState(String result){
        TransactionResultState resultState;
        switch (result){
            case "SUCCESS":
                resultState = TransactionResultState.SUCCESS;
                break;
            case "FAULT":
                resultState = TransactionResultState.FAULT;
                break;
            case "WAIT":
                resultState = TransactionResultState.WAIT;
                break;
            default:
                throw new IllegalArgumentException("TransactionResultState Error , TransactionResultState : " + result);
        }
        return Optional.of(resultState);
    }

    private Optional<TransactionRunWayDate> transactionRunWayDate(){
        String runWay = cache.get(Optional.of(TransactionDate.RunWay.toString())).get();
        return conversionRunWayState(runWay);
    }

    private Optional<TransactionRunWayDate> conversionRunWayState(String runWay){
        TransactionRunWayDate transactionRunWayDate;
        switch (runWay){
            case "Parallel":
                transactionRunWayDate = TransactionRunWayDate.Parallel;
                break;
            case "Sequence":
                transactionRunWayDate = TransactionRunWayDate.Sequence;
                break;
            case "AggregateRootSequence":
                transactionRunWayDate = TransactionRunWayDate.AggregateRootSequence;
                break;
            default:
                throw new IllegalArgumentException("TransactionRunWayDate Error , TransactionRunWayDate : " + runWay);
        }
        return Optional.of(transactionRunWayDate);
    }

}
