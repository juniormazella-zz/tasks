package br.com.itau.tasks.infra.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import lombok.AllArgsConstructor;

import java.io.IOException;

/**
 * Class comments go here...
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 01/07/2019
 */
@AllArgsConstructor
public class JsonWriter {
	
	private final JsonGenerator jsonGenerator;
	
	public void writeArrayFieldStart(final Enum<?> fieldName) throws IOException {
		jsonGenerator.writeArrayFieldStart(fieldName.toString());
	}
	
	public void writeStartObject() throws IOException {
		jsonGenerator.writeStartObject();
	}
	
	public void writeNumberField(final Enum<?> fieldName, final Long number) throws IOException {
		jsonGenerator.writeNumberField(fieldName.toString(), number);
	}
	
	public void writeNumberField(final Enum<?> fieldName, final Integer number) throws IOException {
		jsonGenerator.writeNumberField(fieldName.toString(), number);
	}
	
	public void writeStringField(final Enum<?> fieldName, final String string) throws IOException {
		jsonGenerator.writeStringField(fieldName.toString(), string);
	}
	
	public void writeEndObject() throws IOException {
		jsonGenerator.writeEndObject();
	}
	
	public void writeEndArray() throws IOException {
		jsonGenerator.writeEndArray();
	}
	
}
