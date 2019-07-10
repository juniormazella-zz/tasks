package br.com.itau.tasks.infra.exception;

/**
 * This exception is the basis of the exceptions of the application, in the implementation any exception that can be released should extend this, to
 * favor the composition
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 02/07/2019
 */
public abstract class ApplicationException extends RuntimeException {
	
	/**
	 * Constructor for {@link ApplicationException}
	 *
	 * @param message
	 * 		text that describes the error that occurred in the application
	 */
	protected ApplicationException(final String message) {
		super(message);
	}
	
}
