package com.stargazerproject.job;

public interface Job<Job, Return> extends JobControl, JobRun<Job, Return> {
}
