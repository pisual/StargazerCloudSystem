package com.stargazerproject.job;

import com.google.common.base.Optional;
import com.stargazerproject.job.exception.JobTimeoutException;

import java.util.concurrent.TimeUnit;

public interface JobObserver{

    public Optional<Boolean> isComplete();

    public Optional<Boolean> isStop();

    public Optional<JobObserver> waitFinish(Optional<Long> timeout, Optional<TimeUnit> unit) throws JobTimeoutException;

    public Optional<JobObserver> waitFinish() throws JobTimeoutException;

    public void JobStop(Optional JobNameID);
}
