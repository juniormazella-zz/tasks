package br.com.itau.tasks.infra.deserializer;

import br.com.itau.tasks.infra.exception.ApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Class comments go here...
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 04/07/2019
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidArgumentException extends ApplicationException {
	
	/**
	 * @param message
	 */
	public InvalidArgumentException(final String message) {
		super(message);
	}
	
}
