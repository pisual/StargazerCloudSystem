package com.stargazerproject.serializable.shell;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.TransactionAssembleAnalysis;
import com.stargazerproject.analysis.handle.TransactionAssembleAnalysisHandle;
import com.stargazerproject.annotation.description.NeedInject;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.serializable.Serializables;
import com.stargazerproject.spring.container.impl.BeanContainer;
import com.stargazerproject.transaction.Transaction;
import org.apache.avro.Schema;
import org.apache.avro.file.DataFileStream;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.*;

@Component(value = "transactionTransmissionSerializablesShell")
@Qualifier("transactionTransmissionSerializablesShell")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TransactionTransmissionSerializablesShell implements Serializables<Transaction, byte[]>, BaseCharacteristic<Serializables<Transaction, byte[]>> {

	/** @name Transaction的 schema**/
	@NeedInject(type="SystemParametersCache")
	private static String Parameters_Module_Kernel_Serializables_Transaction_Schema;

	@Autowired
	@Qualifier("transactionAssembleAnalysisImpl")
	private TransactionAssembleAnalysis transactionAssembleAnalysis;

	@Override
	public Optional<Serializables<Transaction, byte[]>> characteristic() {
		return Optional.of(this);
	}

	@Override
	public Optional<byte[]> serialize(Optional<Transaction> transaction) throws IOException {
		String transactionJsonData = transaction.get().toString();
		return Optional.of(jsonToAvro(transactionJsonData, Parameters_Module_Kernel_Serializables_Transaction_Schema));
	}

	@Override
	public Optional<Transaction> deserialize(Optional<byte[]> byteArray) throws ClassNotFoundException, IOException {
		String transactionJsonData = avroToJson(byteArray.get());
		Transaction transaction = BeanContainer.instance().getBean(Optional.of("standardTransaction"), Transaction.class);
		TransactionAssembleAnalysisHandle transactionAssembleAnalysisHandle = transaction.transactionAssemble(Optional.of(transactionAssembleAnalysis)).get();
		transactionAssembleAnalysisHandle.assembleFromJson(Optional.of(transactionJsonData));
		return Optional.of(transaction);
	}

	private byte[] jsonToAvro(String json, String schemaStr) throws IOException {
		InputStream input = null;
		DataFileWriter<GenericRecord> writer = null;
		Encoder encoder = null;
		ByteArrayOutputStream output = null;
		try {
			Schema schema = new Schema.Parser().parse(schemaStr);
			DatumReader<GenericRecord> reader = new GenericDatumReader<GenericRecord>(schema);
			input = new ByteArrayInputStream(json.getBytes());
			output = new ByteArrayOutputStream();
			DataInputStream din = new DataInputStream(input);
			writer = new DataFileWriter<GenericRecord>(new GenericDatumWriter<GenericRecord>());
			writer.create(schema, output);
			Decoder decoder = DecoderFactory.get().jsonDecoder(schema, din);
			GenericRecord datum;
			while (true) {
				try {
					datum = reader.read(null, decoder);
				} catch (EOFException eofe) {
					break;
				}
				writer.append(datum);
			}
			writer.flush();
			return output.toByteArray();
		} finally {
			try {
				input.close();
			} catch (Exception e) {

			}
		}
	}

	private String avroToJson(byte[] avro) throws IOException {
		boolean pretty = false;
		GenericDatumReader<GenericRecord> reader = null;
		JsonEncoder encoder = null;
		ByteArrayOutputStream output = null;
		try {
			reader = new GenericDatumReader<GenericRecord>();
			InputStream input = new ByteArrayInputStream(avro);
			DataFileStream<GenericRecord> streamReader = new DataFileStream<GenericRecord>(input, reader);
			output = new ByteArrayOutputStream();
			Schema schema = streamReader.getSchema();
			DatumWriter<GenericRecord> writer = new GenericDatumWriter<GenericRecord>(schema);
			encoder = EncoderFactory.get().jsonEncoder(schema, output, pretty);
			for (GenericRecord datum : streamReader) {
				writer.write(datum, encoder);
			}
			encoder.flush();
			output.flush();
			return new String(output.toByteArray());
		} finally {
			try {
				if (output != null){
					output.close();
				}
			} catch (Exception e) {

			}
		}
	}

}
