package com.stargazerproject.job;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.job.base.impl.BaseJobImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledExecutorService;

@Component(value="cycleJob")
@Qualifier("cycleJob")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CycleJob extends BaseJobImpl<ScheduledExecutorService, JobObserver> implements StanderCharacteristicShell<Job<ScheduledExecutorService, JobObserver>> {

    @Override
    public void initialize(Optional<Job<ScheduledExecutorService, JobObserver>> jobArg) {
        job = jobArg.get();
    }
}
