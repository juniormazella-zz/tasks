package br.com.itau.tasks.infra.deserializer;

import br.com.itau.tasks.domain.task.Task;
import br.com.itau.tasks.infra.util.DefaultApplicationContextHolder;
import br.com.itau.tasks.infra.wrapper.TaskWrapper;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

/**
 * This class is the deserializer of the class {@link TaskWrapper}
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 01/07/2019
 */
public class TaskWrapperDeserializer extends AbstractDeserializer<TaskWrapper> {
	
	private final TaskDeserializer taskDeserializer = DefaultApplicationContextHolder.getBean(TaskDeserializer.class);
	
	@Override
	public TaskWrapper deserialize(final JsonNode jsonNode) throws IOException {
		final Task task = taskDeserializer.deserialize(jsonNode);
		return new TaskWrapper(task);
	}
	
}
