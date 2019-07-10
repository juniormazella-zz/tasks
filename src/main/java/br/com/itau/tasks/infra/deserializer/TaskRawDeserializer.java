package br.com.itau.tasks.infra.deserializer;

import br.com.itau.tasks.domain.task.factory.TaskRaw;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

/**
 * This class is the deserializer of the class {@link TaskRaw}
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 04/07/2019
 */
public class TaskRawDeserializer extends AbstractDeserializer<TaskRaw> {
	
	@Override
	public TaskRaw deserialize(final JsonNode jsonNode) throws IOException {
		final String description = getFieldTextValue(jsonNode, Field.DESCRIPTION);
		final Integer points = getFieldIntegerValue(jsonNode, Field.POINTS);
		return new TaskRaw(description, points);
	}
	
}
