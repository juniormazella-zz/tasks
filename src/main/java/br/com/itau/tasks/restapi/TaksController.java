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
 * Class comments go here...
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 01/07/2019
 */
@RestController
@AllArgsConstructor
public class TaksController {
	
	private final TaskApplicationService applicationService;
	
	/**
	 * @param pageable
	 *
	 * @return
	 */
	@GetMapping("/tasks")
	public PageWrapper getTasks(@PageableDefault(sort = {"creationDate"}, direction = Sort.Direction.ASC) final Pageable pageable) {
		final Page pageOfTasks = applicationService.getPageOfTasks(pageable);
		return new PageWrapper(pageOfTasks);
	}
	
	/**
	 * @param taskWrapper
	 *
	 * @return
	 */
	@PostMapping("/tasks")
	@ResponseStatus(HttpStatus.CREATED)
	public TaskWrapper addTask(@RequestBody final TaskRaw taskRaw) {
		final Task task = applicationService.addTask(taskRaw);
		return new TaskWrapper(task);
	}
	
	/**
	 * @param taskWrapper
	 *
	 * @return
	 */
	@PutMapping("/tasks/{id}")
	public TaskWrapper changeTask(@PathVariable("id") final Long id, @RequestBody final TaskWrapper taskWrapper) {
		final Task task = applicationService.changeTask(id, taskWrapper.getTask());
		return new TaskWrapper(task);
	}
	
	
	/**
	 * @param id
	 *
	 * @return
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
