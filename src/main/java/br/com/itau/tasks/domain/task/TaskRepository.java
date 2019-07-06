package br.com.itau.tasks.domain.task;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Class comments go here...
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 01/07/2019
 */
public interface TaskRepository extends JpaRepository<Task, Long> {

}
