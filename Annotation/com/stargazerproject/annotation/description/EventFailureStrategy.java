package com.stargazerproject.annotation.description;

public enum EventFailureStrategy {
    Idempotent,//幂等
    Rollback,
}
