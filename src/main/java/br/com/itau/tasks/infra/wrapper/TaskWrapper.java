package br.com.itau.tasks.infra.wrapper;

import br.com.itau.tasks.domain.task.Task;
import br.com.itau.tasks.infra.deserializer.TaskWrapperDeserializer;
import br.com.itau.tasks.infra.serializer.TaskWrapperSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * This class is a value object for {@link TaskWrapper}.
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 01/07/2019
 */
@AllArgsConstructor
@Getter
@JsonSerialize(using = TaskWrapperSerializer.class)
@JsonDeserialize(using = TaskWrapperDeserializer.class)
public class TaskWrapper {
	
	private final Task task;
	
}
