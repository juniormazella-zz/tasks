package br.com.itau.tasks.application;

import br.com.itau.tasks.domain.task.Task;
import br.com.itau.tasks.domain.task.TaskNotFoundException;
import br.com.itau.tasks.domain.task.TaskRepository;
import br.com.itau.tasks.domain.task.factory.TaskFactory;
import br.com.itau.tasks.domain.task.factory.TaskRaw;
import br.com.itau.tasks.infra.annotation.ApplicationService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Class comments go here...
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 01/07/2019
 */
@ApplicationService
@AllArgsConstructor
public class TaskApplicationService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private final TaskRepository repository;
	private final TaskFactory factory;
	
	/**
	 * @param pageable
	 *
	 * @return
	 */
	public Page getPageOfTasks(final Pageable pageable) {
		final Page<Task> pageOfTasks = repository.findAll(pageable);
		if (!pageOfTasks.isEmpty()) {
			return pageOfTasks;
		}
		throw new TaskNotFoundException(String.format("Not found tasks to condition '%s'", pageable));
	}
	
	@Transactional
	public Task addTask(final TaskRaw taskRaw) {
		final Task data = factory.create(taskRaw);
		final Task task = repository.save(data);
		if (logger.isDebugEnabled()) {
			logger.debug("Saved task: {}", data);
		}
		return task;
	}
	
	/**
	 * @param id
	 */
	public void deleteTaskId(final Long id) {
		final Task task = getTaskById(id);
		repository.delete(task);
		if (logger.isDebugEnabled()) {
			logger.debug("Deleted task: {}", task);
		}
	}
	
	/**
	 * @param id
	 *
	 * @return
	 */
	public Task getTaskById(final Long id) {
		final Optional<Task> optional = repository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		throw new TaskNotFoundException(String.format("Task with id '%s' not found", id));
	}
	
	/**
	 * @param id
	 * @param toUpdate
	 *
	 * @return
	 */
	@Transactional
	public Task changeTask(final Long id, final Task toUpdate) {
		final Task task = getTaskById(id);
		task.update(toUpdate);
		return repository.save(task);
	}
	
}
