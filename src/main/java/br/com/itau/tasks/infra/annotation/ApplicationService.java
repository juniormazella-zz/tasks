package br.com.itau.tasks.infra.annotation;

import org.springframework.stereotype.Service;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation should be used to mark any and every application layer
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 01/07/2019
 */
@Service
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApplicationService {

}
