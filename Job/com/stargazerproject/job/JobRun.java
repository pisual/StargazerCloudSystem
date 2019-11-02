package com.stargazerproject.job;

import com.google.common.base.Optional;

import java.util.function.Function;

public interface JobRun<Job, Return> {

    public Optional<Return> AddJob(Optional<String> JobNameID, Function<Job, Return> function);

}
