package com.stargazerproject.bus.resources;

import com.stargazerproject.analysis.EventResultAnalysis;
import com.stargazerproject.log.LogMethod;
import net.engio.mbassy.bus.error.IPublicationErrorHandler;
import net.engio.mbassy.bus.error.PublicationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component(value="eventBusIPublicationErrorHandler")
@Qualifier("eventBusIPublicationErrorHandler")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class EventBusIPublicationErrorHandler implements IPublicationErrorHandler {

    @Autowired
    @Qualifier("logRecord")
    protected LogMethod log;

    @Autowired
    @Qualifier("eventResultAnalysisImpl")
    private EventResultAnalysis eventResultAnalysis;

    @Override
    public void handleError(PublicationError error) {
//        Event event = (Event) error.getPublishedMessage();
//        event.skipEvent(Optional.of("Event Skip , Skip Cause: "));
        log.WARN("eventBusIPublicationErrorHandler", error.getCause().toString());
    }
}
