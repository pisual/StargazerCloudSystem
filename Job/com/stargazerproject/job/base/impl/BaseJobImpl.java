package com.stargazerproject.job.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.job.Job;

import java.util.function.Function;

public class BaseJobImpl<jobs, Return> implements Job<jobs, Return> {

    protected Job<jobs, Return> job;

    @Override
    public void startJobEngine() {
        job.startJobEngine();
    }

    @Override
    public void stopJobEngine() {
        job.stopJobEngine();
    }

    @Override
    public Optional<Return> AddJob(Optional<String> JobNameID, Function<jobs, Return> function) {
        return job.AddJob(JobNameID, function);
    }
}
