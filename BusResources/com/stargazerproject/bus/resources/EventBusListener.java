package com.stargazerproject.bus.resources;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventExecuteAnalysis;
import com.stargazerproject.bus.BusListener;
import com.stargazerproject.transaction.Event;
import net.engio.mbassy.listener.Handler;
import net.engio.mbassy.listener.Invoke;
import net.engio.mbassy.listener.Listener;
import net.engio.mbassy.listener.References;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="eventBusListener")
@Qualifier("eventBusListener")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Listener(references = References.Strong)
public class EventBusListener implements BusListener<Optional<Event>>{

    @Autowired
    @Qualifier("eventExecuteAnalysisImpl")
    private EventExecuteAnalysis eventExecuteAnalysis;

    @Handler(delivery = Invoke.Asynchronously)
    @Override
    public void handler(Optional<Event> busEvent) {
        busEvent.get().eventExecute(Optional.of(eventExecuteAnalysis));
    }

}
