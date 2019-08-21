package com.stargazerproject.serializable;


import com.google.common.base.Optional;

import java.io.IOException;

public interface Serializables<SourceType, TransmissionType> {

	public Optional<TransmissionType> serialize(Optional<SourceType> source) throws IOException;
	
	public Optional<SourceType> deserialize(Optional<TransmissionType> source) throws ClassNotFoundException, IOException;
}
