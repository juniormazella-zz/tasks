package br.com.itau.tasks.domain.task.factory;

import br.com.itau.tasks.domain.task.Task;
import br.com.itau.tasks.infra.factory.AbstractFactory;
import org.springframework.stereotype.Component;

/**
 * This class is an implementation of {@link AbstractFactory} to factory a {@link Task} object
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 04/07/2019
 */
@Component
public class TaskFactory implements AbstractFactory<Task, TaskRaw> {
	
	@Override
	public Task create(final TaskRaw taskRaw) {
		return new Task(taskRaw.getDescription(), taskRaw.getPoints());
	}
	
}
