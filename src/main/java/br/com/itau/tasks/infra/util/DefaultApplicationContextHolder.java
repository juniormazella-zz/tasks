package br.com.itau.tasks.infra.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * This class is able to retrieve objects that are managed by Spring outside the context of Spring
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 01/07/2019
 */
@Component
public class DefaultApplicationContextHolder implements ApplicationContextAware {
	
	private static ApplicationContext applicationContext;
	
	/**
	 * This method return a {@link org.springframework.context.annotation.Bean} from spring context by class type
	 *
	 * @param <T>
	 * 		type of class to return from spring context
	 * @param clazz
	 * 		of {@link org.springframework.context.annotation.Bean} from spring context
	 *
	 * @return a {@link org.springframework.context.annotation.Bean} of same type T from spring context
	 */
	public static <T> T getBean(final Class<T> clazz) {
		return applicationContext.getBean(clazz);
	}
	
	/**
	 * This method return a {@link List} of {@link org.springframework.context.annotation.Bean} from spring context by class type
	 *
	 * @param <T>
	 * 		type of class to return from spring context
	 * @param clazz
	 * 		of {@link org.springframework.context.annotation.Bean} from spring context
	 *
	 * @return a {@link org.springframework.context.annotation.Bean} of same type T from spring context
	 */
	public static <T> List<T> getBeans(final Class<T> clazz) {
		final Map<String, T> beansOfType = applicationContext.getBeansOfType(clazz);
		return Collections.unmodifiableList(new LinkedList<>(beansOfType.values()));
	}
	
	
	@Override
	public synchronized void setApplicationContext(final ApplicationContext applicationContext) {
		DefaultApplicationContextHolder.applicationContext = applicationContext;
	}
	
}
