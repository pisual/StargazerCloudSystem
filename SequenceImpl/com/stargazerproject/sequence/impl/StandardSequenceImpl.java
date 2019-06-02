package com.stargazerproject.sequence.impl;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.sequence.Sequence;
import com.stargazerproject.sequence.base.impl.BaseSequence;
import com.stargazerproject.transaction.Transaction;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="standardSequenceImpl")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Qualifier("standardSequenceImpl")
public class StandardSequenceImpl extends BaseSequence<Transaction> implements StanderCharacteristicShell<Sequence<Transaction>>{

	@Override
	public void initialize(Optional<Sequence<Transaction>> sequenceArg) {
		sequence = sequenceArg.get();
	}

}
