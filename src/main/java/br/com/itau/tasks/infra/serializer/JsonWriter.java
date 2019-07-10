package br.com.itau.tasks.infra.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import lombok.AllArgsConstructor;

import java.io.IOException;

/**
 * This class wraps the {@link JsonGenerator} class to aid use in the context of serialization
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 01/07/2019
 */
@AllArgsConstructor
public class JsonWriter {
	
	private final JsonGenerator jsonGenerator;
	
	/**
	 * This method write to json the command that represents start o array object
	 *
	 * @param fieldName
	 * 		to value object field that will be printed
	 *
	 * @throws IOException
	 * 		is the type of exception that can be thrown in the event of an error in the serialization process
	 */
	public void writeArrayFieldStart(final Enum<?> fieldName) throws IOException {
		jsonGenerator.writeArrayFieldStart(fieldName.toString());
	}
	
	/**
	 * This method will send the command to start a object without name field
	 *
	 * @throws IOException
	 * 		is the type of exception that can be thrown in the event of an error in the serialization process
	 */
	public void writeStartObject() throws IOException {
		jsonGenerator.writeStartObject();
	}
	
	/**
	 * This method send the command to write a {@link Long} field
	 *
	 * @param fieldName
	 * 		name of field
	 * @param number
	 * 		value of field
	 *
	 * @throws IOException
	 * 		is the type of exception that can be thrown in the event of an error in the serialization process
	 */
	public void writeNumberField(final Enum<?> fieldName, final Long number) throws IOException {
		jsonGenerator.writeNumberField(fieldName.toString(), number);
	}
	
	/**
	 * This method send the command to write an {@link Integer} field
	 *
	 * @param fieldName
	 * 		name of field
	 * @param number
	 * 		value of field
	 *
	 * @throws IOException
	 * 		is the type of exception that can be thrown in the event of an error in the serialization process
	 */
	public void writeNumberField(final Enum<?> fieldName, final Integer number) throws IOException {
		jsonGenerator.writeNumberField(fieldName.toString(), number);
	}
	
	/**
	 * This method will send the command to print a {@link String} field
	 *
	 * @param fieldName
	 * 		name of field
	 * @param string
	 * 		value of field
	 *
	 * @throws IOException
	 * 		is the type of exception that can be thrown in the event of an error in the serialization process
	 */
	public void writeStringField(final Enum<?> fieldName, final String string) throws IOException {
		jsonGenerator.writeStringField(fieldName.toString(), string);
	}
	
	/**
	 * This method will send the command to print "close" of object
	 *
	 * @throws IOException
	 * 		is the type of exception that can be thrown in the event of an error in the serialization process
	 */
	public void writeEndObject() throws IOException {
		jsonGenerator.writeEndObject();
	}
	
	/**
	 * This method will send the command to print "close" of array
	 *
	 * @throws IOException
	 * 		is the type of exception that can be thrown in the event of an error in the serialization process
	 */
	public void writeEndArray() throws IOException {
		jsonGenerator.writeEndArray();
	}
	
}
