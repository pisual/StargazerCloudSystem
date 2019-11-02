package com.stargazerproject.job.shell;

import com.google.common.base.Optional;
import com.stargazerproject.job.JobObserver;
import com.stargazerproject.job.exception.JobTimeoutException;

import java.util.concurrent.*;

public class CycleJobObserver implements JobObserver{

    private ScheduledFuture<?> scheduledFuture;

    public CycleJobObserver(Optional<ScheduledFuture<?>> scheduledFutureArg){
        scheduledFuture = scheduledFutureArg.get();
    }


    @Override
    public Optional<Boolean> isComplete() {
        return Optional.of(scheduledFuture.isDone());
    }

    @Override
    public Optional<Boolean> isStop() {
        return Optional.of(scheduledFuture.isCancelled());
    }

    @Override
    public Optional<JobObserver> waitFinish(Optional<Long> timeout, Optional<TimeUnit> unit) throws JobTimeoutException {
        try {
            scheduledFuture.get(timeout.get(), unit.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.out.println("scheduledFutureJob已经结束");
        } catch (TimeoutException e) {
            throw new JobTimeoutException(e.getMessage());
        }

        return Optional.of(this);
    }

    @Override
    public Optional<JobObserver> waitFinish() throws JobTimeoutException {
        try {
            scheduledFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        catch (CancellationException e){
            System.out.println("scheduledFutureJob已经取消");
        }
        return Optional.of(this);
    }

    @Override
    public void JobStop(Optional JobNameID) {
        scheduledFuture.cancel(Boolean.TRUE);
    }
}
