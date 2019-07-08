package br.com.itau.tasks.infra.controlleradvice;

import br.com.itau.tasks.infra.controlleradvice.error.ConstraintValidationErrorData;
import br.com.itau.tasks.infra.controlleradvice.error.DefaultErrorData;
import br.com.itau.tasks.infra.controlleradvice.error.ErrorData;
import br.com.itau.tasks.infra.exception.ApplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Class comments go here...
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 02/07/2019
 */
@RestControllerAdvice
public class DefaultRestControllerAdvice {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * @param exception
	 *
	 * @return
	 */
	@ExceptionHandler({ApplicationException.class})
	public ResponseEntity handleApplicationException(final ApplicationException exception) {
		logger.error(exception.getMessage(), exception);
		final ResponseStatus annotation = exception.getClass().getAnnotation(ResponseStatus.class);
		final ErrorData errorData = new DefaultErrorData(annotation.code(), exception.getMessage());
		return new ResponseEntity(errorData, errorData.getHttpStatus());
	}
	
	/**
	 * @param exception
	 *
	 * @return
	 */
	@ExceptionHandler({ConstraintViolationException.class})
	public ResponseEntity handleConstraintViolationException(final ConstraintViolationException exception) {
		logger.error(exception.getMessage(), exception);
		final Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
		final List<String> messages =
				violations.stream().map(v -> String.format("field: '%s' | message: '%s'", v.getPropertyPath().toString(), v.getMessage()))
						.collect(Collectors.toList());
		final ConstraintValidationErrorData errorData = new ConstraintValidationErrorData(HttpStatus.BAD_REQUEST, messages);
		return new ResponseEntity(errorData, errorData.getHttpStatus());
	}
	
}
