package br.com.itau.tasks.infra.serializer;

import br.com.itau.tasks.infra.controlleradvice.error.ErrorData;

import java.io.IOException;

/**
 * This class is responsible for serializing the class {@link ErrorData}
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 02/07/2019
 */
public class ErrorDataSerializer extends AbstractSerializer<ErrorData> {
	
	@Override
	public void serialize(final ErrorData errorData, final JsonWriter jsonWriter) throws IOException {
		jsonWriter.writeStartObject();
		jsonWriter.writeNumberField(Field.CODE, errorData.getHttpStatusCode());
		jsonWriter.writeStringField(Field.MESSAGE, errorData.getMessage());
		jsonWriter.writeEndObject();
	}
	
}
