package com.stargazerproject.sequence.impl;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.sequence.Sequence;
import com.stargazerproject.sequence.base.impl.BaseSequence;
import com.stargazerproject.transaction.Event;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="standardSequence")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Qualifier("standardSequenceImpl")
public class StandardSequenceImpl extends BaseSequence<Event> implements StanderCharacteristicShell<Sequence<Event>>{

	@Override
	public void initialize(Optional<Sequence<Event>> sequenceArg) {
		sequence = sequenceArg.get();
	}

}
