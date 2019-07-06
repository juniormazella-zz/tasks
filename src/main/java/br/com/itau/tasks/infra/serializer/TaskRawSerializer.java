package br.com.itau.tasks.infra.serializer;

import br.com.itau.tasks.domain.task.factory.TaskRaw;
import br.com.itau.tasks.infra.deserializer.AbstractDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

/**
 * Class comments go here...
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 04/07/2019
 */
public class TaskRawSerializer extends AbstractDeserializer<TaskRaw> {
	
	@Override
	public TaskRaw deserialize(final JsonNode jsonNode) throws IOException {
		final String description = getFieldTextValue(jsonNode, Field.DESCRIPTION);
		final Integer points = getFieldIntegerValue(jsonNode, Field.POINTS);
		return new TaskRaw(description, points);
	}
	
}
