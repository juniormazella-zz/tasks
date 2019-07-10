package br.com.itau.tasks.infra.controlleradvice.error;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is a value object of a specific error
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 05/07/2019
 */
public class ConstraintValidationErrorData extends ErrorData {
	
	private final List<String> messages = new ArrayList<>();
	
	/**
	 * Constructor for {@link ConstraintValidationErrorData}
	 *
	 * @param httpStatus http status of error
	 * @param messages text that describes the error that occurred
	 */
	public ConstraintValidationErrorData(final HttpStatus httpStatus, final List<String> messages) {
		super(httpStatus);
		this.messages.addAll(messages);
	}
	
	@Override
	public String getMessage() {
		return messages.stream().collect(Collectors.joining(", "));
	}
	
}
