package br.com.itau.tasks.infra.controlleradvice.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Class comments go here...
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 05/07/2019
 */
@Getter
public class DefaultErrorData extends ErrorData {
	
	private final String message;
	
	/**
	 *
	 * @param httpStatus
	 * @param message
	 */
	public DefaultErrorData(final HttpStatus httpStatus, final String message) {
		super(httpStatus);
		this.message = message;
	}
	
}
