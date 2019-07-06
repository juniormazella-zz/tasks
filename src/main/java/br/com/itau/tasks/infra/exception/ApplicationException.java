package br.com.itau.tasks.infra.exception;

/**
 * Class comments go here...
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 02/07/2019
 */
public abstract class ApplicationException extends RuntimeException {

    /**
     * @param message
     */
    protected ApplicationException(final String message) {
        super(message);
    }

}
