package br.com.itau.tasks.infra.factory;

/**
 * Class comments go here...
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 04/07/2019
 */
public interface AbstractFactory<T, D> {
	
	T create(final D d);
	
}
