package br.com.itau.tasks.domain.task.factory;

import br.com.itau.tasks.domain.task.Task;
import br.com.itau.tasks.infra.factory.AbstractFactory;
import org.springframework.stereotype.Component;

/**
 * Class comments go here...
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
