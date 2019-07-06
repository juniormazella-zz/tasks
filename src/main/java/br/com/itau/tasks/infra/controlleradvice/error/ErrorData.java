package br.com.itau.tasks.infra.controlleradvice.error;

import br.com.itau.tasks.infra.serializer.ErrorDataSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Class comments go here...
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
	 * @return
	 */
	public Integer getHttpStatusCode() {
		return httpStatus.value();
	}
	
	public abstract String getMessage();
	
}
