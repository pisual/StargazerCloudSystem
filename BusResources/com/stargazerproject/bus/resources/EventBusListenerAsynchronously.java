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

@Component(value="eventBusListenerAsynchronously")
@Qualifier("eventBusListenerAsynchronously")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Listener(references = References.Strong)
public class EventBusListenerAsynchronously implements BusListener<Optional<Event>>{

    @Autowired
    @Qualifier("eventExecuteAnalysisImpl")
    private EventExecuteAnalysis eventExecuteAnalysis;

    @Handler(delivery = Invoke.Asynchronously)
    @Override
    public void handler(Optional<Event> busEvent) {
        try {
            busEvent.get().eventExecute(Optional.of(eventExecuteAnalysis)).get().run();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
