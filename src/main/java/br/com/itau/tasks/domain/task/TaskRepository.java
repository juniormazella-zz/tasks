package br.com.itau.tasks.domain.task;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface is the way to find and persist information in the database
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 01/07/2019
 */
public interface TaskRepository extends JpaRepository<Task, Long> {

}
