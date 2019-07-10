package br.com.itau.tasks.infra.controlleradvice.error;

import br.com.itau.tasks.infra.serializer.ErrorDataSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * This class is only a value object that knows the error data that occurred in the application
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 02/07/2019
 */
@AllArgsConstructor
@Getter
@JsonSerialize(using = ErrorDataSerializer.class)
public abstract class ErrorData {
	
	private final HttpStatus httpStatus;
	
	/**
	 * This method will return numeric value that corresponds to HttpStatus
	 *
	 * @return error code as number
	 */
	public Integer getHttpStatusCode() {
		return httpStatus.value();
	}
	
	/**
	 * Every implementation of this method should return the error message that will be shown inside the "message" field in the api return when an
	 * exception occurs
	 *
	 * @return value of the variable describing the error occurred
	 */
	public abstract String getMessage();
	
}
