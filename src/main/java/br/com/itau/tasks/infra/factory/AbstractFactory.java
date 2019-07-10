package br.com.itau.tasks.infra.factory;

/**
 * This interface is a definition of a contract to be used in every class that uses the Factory pattern
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 04/07/2019
 */
public interface AbstractFactory<T, D> {
	
	/**
	 * Any concrete implementation of this method must be able to construct an object, the information that will be used to make this happen must be
	 * passed as method argument
	 *
	 * @param d
	 * 		data object with the information to be used for the construction of the object in question
	 *
	 * @return an instance of T
	 */
	T create(final D d);
	
}
