package com.stargazerproject.bus;

public interface BusListener<T> {

    public void handler(T t);

}
