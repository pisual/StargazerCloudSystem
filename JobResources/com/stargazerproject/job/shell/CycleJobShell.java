package com.stargazerproject.job.shell;

import com.google.common.base.Optional;
import com.stargazerproject.annotation.description.NeedInject;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.job.Job;
import com.stargazerproject.job.JobObserver;
import com.stargazerproject.log.LogMethod;
import com.stargazerproject.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.function.Function;

@Component(value="cycleJobShell")
@Qualifier("cycleJobShell")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CycleJobShell implements Job<ScheduledExecutorService, JobObserver>, BaseCharacteristic<Job<ScheduledExecutorService, JobObserver>> {

    /** @name Job线程池中线程的数量 **/
    @NeedInject(type="SystemParametersCache")
    private static String Parameters_Module_Kernel_Job_ThreadPool_Size;

    /** @name 结束Job的时候等待的最长时间 **/
    @NeedInject(type="SystemParametersCache")
    private static String Parameters_Module_Kernel_Job_AwaitTermination_Time;

    /** @name 结束Job的时候等待的最长时间Unit **/
    @NeedInject(type="SystemParametersCache")
    private static String Parameters_Module_Kernel_Job_AwaitTermination_TimeUnit;

    @Autowired
    @Qualifier("logRecord")
    protected LogMethod log;

    private ScheduledExecutorService scheduler;

    public CycleJobShell(){ }

    @Override
    public Optional<Job<ScheduledExecutorService, JobObserver>> characteristic() {
        return Optional.of(this);
    }

    @Override
    public void startJobEngine() {
        scheduler = Executors.newScheduledThreadPool(Integer.parseInt(Parameters_Module_Kernel_Job_ThreadPool_Size));
    }

    @Override
    public void stopJobEngine() {
        Optional.of(scheduler);
        try {
            scheduler.awaitTermination(Integer.parseInt(Parameters_Module_Kernel_Job_AwaitTermination_Time), TimeUtil.conversionTimeUnit(Parameters_Module_Kernel_Job_AwaitTermination_TimeUnit).get());
        } catch (InterruptedException e) {
            log.WARN("cycleJobShell", "Job队列终止错误 ： " + e.getMessage());
        }
    }

    @Override
    public Optional<JobObserver> AddJob(Optional<String> JobNameID, Function<ScheduledExecutorService, JobObserver> function) {
        Optional.of(scheduler);
        return Optional.of(function.apply(scheduler));
    }

}
