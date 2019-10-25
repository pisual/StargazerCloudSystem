package com.stargazerproject.bus.resources;

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

    @Override
    public void handleError(PublicationError error) {
        System.out.println(error.getMessage()); // An error message to describe what went wrong
        System.out.println(error.getCause()); // The underlying exception
        System.out.println(error.getPublishedMessage()); // The message that was published (can be null)
        System.out.println(error.getListener()); // The listener that was invoked when the execption was thrown (can be null)
        System.out.println(error.getHandler()); // The message handler (Method) that was invoked when the execption was thrown (can be null)
    }
}
