package br.com.itau.tasks.restapi;

import br.com.itau.tasks.application.TaskApplicationService;
import br.com.itau.tasks.domain.task.Task;
import br.com.itau.tasks.domain.task.factory.TaskRaw;
import br.com.itau.tasks.infra.wrapper.PageWrapper;
import br.com.itau.tasks.infra.wrapper.TaskWrapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


/**
 * This class exposes class services {@link Task}
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 01/07/2019
 */
@RestController
@AllArgsConstructor
public class TaskController {
	
	private final TaskApplicationService applicationService;
	
	/**
	 * This method will return a page of the query performed
	 *
	 * @param pageable
	 * 		is a Spring object with the information that will serve as a query condition, the Sort.Direction.ASC ordering in the creationDate field is
	 * 		set as default
	 *
	 * @return a value object with the information that will be serialized in return
	 */
	@GetMapping("/tasks")
	public PageWrapper getTasks(@PageableDefault(sort = {"creationDate"}, direction = Sort.Direction.ASC) final Pageable pageable) {
		final Page pageOfTasks = applicationService.getPageOfTasks(pageable);
		return new PageWrapper(pageOfTasks);
	}
	
	/**
	 * This method will perform the registration of a {@link Task} in the application, the http status code of return of this method is 202 - Created
	 *
	 * @param taskRaw
	 * 		a raw object that will give rise to an object of the {@link Task}
	 *
	 * @return a value object with the information that will be serialized in return
	 */
	@PostMapping("/tasks")
	@ResponseStatus(HttpStatus.CREATED)
	public TaskWrapper addTask(@RequestBody final TaskRaw taskRaw) {
		final Task task = applicationService.addTask(taskRaw);
		return new TaskWrapper(task);
	}
	
	/**
	 * This method will perform an update on an existing {@link Task} in the database
	 *
	 * @param id
	 * 		information of {@link Task} that will be update
	 * @param taskWrapper
	 * 		a value object with information to update
	 *
	 * @return a value object with the information that will be serialized in return
	 */
	@PutMapping("/tasks/{id}")
	public TaskWrapper changeTask(@PathVariable("id") final Long id, @RequestBody final TaskWrapper taskWrapper) {
		final Task task = applicationService.changeTask(id, taskWrapper.getTask());
		return new TaskWrapper(task);
	}
	
	
	/**
	 * @param id
	 *
	 * @return a value object with the information that will be serialized in return
	 */
	@GetMapping("/tasks/{id}")
	public TaskWrapper getTaskById(@PathVariable("id") final Long id) {
		final Task task = applicationService.getTaskById(id);
		return new TaskWrapper(task);
	}
	
	/**
	 * @param id
	 */
	@DeleteMapping("/tasks/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteTaskById(@PathVariable("id") final Long id) {
		applicationService.deleteTaskId(id);
	}
	
}
