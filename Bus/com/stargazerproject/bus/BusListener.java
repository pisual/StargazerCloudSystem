package com.stargazerproject.bus;

import net.engio.mbassy.listener.Handler;
import net.engio.mbassy.listener.Invoke;

public interface BusListener<T> {

    @Handler(delivery = Invoke.Asynchronously)
    public void handler(T t);

}
