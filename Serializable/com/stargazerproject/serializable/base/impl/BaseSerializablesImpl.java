package com.stargazerproject.serializable.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.serializable.Serializables;

import java.io.IOException;

public class BaseSerializablesImpl<SourceType, TransmissionType> implements Serializables<SourceType, TransmissionType>{

	protected Serializables serializables;
	
	@Override
	public Optional<TransmissionType> serialize(Optional<SourceType> source) throws IOException {
		return serializables.serialize(source);
	}

	@Override
	public Optional<SourceType> deserialize(Optional<TransmissionType> source) throws ClassNotFoundException, IOException{
		return serializables.deserialize(source);
	}

}
