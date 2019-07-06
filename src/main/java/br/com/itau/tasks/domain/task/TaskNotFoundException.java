package br.com.itau.tasks.domain.task;

import br.com.itau.tasks.infra.exception.ApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Class comments go here...
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 02/07/2019
 */
@ResponseStatus(code = HttpStatus.PRECONDITION_FAILED)
public class TaskNotFoundException extends ApplicationException {


    /**
     * @param message
     */
    public TaskNotFoundException(final String message) {
        super(message);
    }

}
