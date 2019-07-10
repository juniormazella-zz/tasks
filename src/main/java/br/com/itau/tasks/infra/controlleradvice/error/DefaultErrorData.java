package br.com.itau.tasks.infra.controlleradvice.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * This class is a value object of a specific error
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 05/07/2019
 */
@Getter
public class DefaultErrorData extends ErrorData {
	
	private final String message;
	
	/**
	 * Constructor for {@link DefaultErrorData}
	 *
	 * @param httpStatus
	 * 		http status of error
	 * @param message
	 * 		text that describes the error that occurred
	 */
	public DefaultErrorData(final HttpStatus httpStatus, final String message) {
		super(httpStatus);
		this.message = message;
	}
	
}
