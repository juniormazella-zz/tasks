package br.com.itau.tasks.infra.deserializer;

import br.com.itau.tasks.domain.task.Status;
import br.com.itau.tasks.domain.task.Task;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * This class is the deserializer of the class {@link Task}
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 01/07/2019
 */
@Component
public class TaskDeserializer extends AbstractDeserializer<Task> {
	
	@Override
	public Task deserialize(final JsonNode jsonNode) throws IOException {
		final Status status = getStatus(jsonNode);
		final String description = getFieldTextValue(jsonNode, Field.DESCRIPTION);
		final Integer points = getFieldIntegerValue(jsonNode, Field.POINTS);
		return new Task(status, description, points);
	}
	
	private Status getStatus(final JsonNode jsonNode) {
		final String statusText = getFieldTextValue(jsonNode, Field.STATUS);
		try {
			return Status.valueOf(statusText);
		} catch (final IllegalArgumentException e) {
			throw new InvalidArgumentException(String.format("Invalid status for string '%s'", statusText));
		}
	}
	
}
