package br.com.itau.tasks.domain.task.factory;

import br.com.itau.tasks.infra.deserializer.TaskRawDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Class comments go here...
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 04/07/2019
 */
@JsonDeserialize(using = TaskRawDeserializer.class)
@AllArgsConstructor
@Getter
public class TaskRaw {
	
	private final String description;
	private final Integer points;
	
}
