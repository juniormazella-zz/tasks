package br.com.itau.tasks.infra.serializer;

import br.com.itau.tasks.domain.task.Task;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Class comments go here...
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 01/07/2019
 */
@Component
public class TaskSerializer extends AbstractSerializer<Task> {
	
	@Override
	public void serialize(final Task task, final JsonWriter jsonWriter) throws IOException {
		jsonWriter.writeStartObject();
		jsonWriter.writeNumberField(Field.ID, task.getId());
		jsonWriter.writeNumberField(Field.CREATION_DATE, task.getCreationDateAsTimestamp());
		jsonWriter.writeStringField(Field.STATUS, task.getStatusAsString());
		jsonWriter.writeStringField(Field.DESCRIPTION, task.getDescription());
		jsonWriter.writeNumberField(Field.POINTS, task.getPoints());
		jsonWriter.writeEndObject();
	}
	
}
