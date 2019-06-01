package com.stargazerproject.interfaces.characteristic.shell;

@FunctionalInterface
public interface ActionFunction<T> {
    /**
     * Runs the action and optionally throws a checked exception.
     * @throws Exception if the implementation wishes to throw a checked exception
     */
    void run(T t) throws Exception;
}