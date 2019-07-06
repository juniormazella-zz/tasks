package br.com.itau.tasks.infra.controlleradvice.error;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class comments go here...
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 05/07/2019
 */
public class ConstraintValidationErrorData extends ErrorData {
	
	private final List<String> messages = new ArrayList<>();
	
	
	public ConstraintValidationErrorData(final HttpStatus httpStatus, final List<String> messages) {
		super(httpStatus);
		this.messages.addAll(messages);
	}
	
	
	@Override
	public String getMessage() {
		return messages.stream().collect(Collectors.joining(", "));
	}
	
}
