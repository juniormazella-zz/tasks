package br.com.itau.tasks.infra.serializer;

import br.com.itau.tasks.infra.util.DefaultApplicationContextHolder;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.AllArgsConstructor;
import org.springframework.core.GenericTypeResolver;

import java.io.IOException;
import java.util.List;

/**
 * This class is the base class of what we call the anti-corruption layer in the serialization context of the data that will be returned from the
 * controller
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 01/07/2019
 */
public abstract class AbstractSerializer<T> extends JsonSerializer<T> {
	
	@Override
	public void serialize(final T t, final JsonGenerator jsonGenerator, final SerializerProvider serializerProvider) throws IOException {
		final JsonWriter jsonWriter = new JsonWriter(jsonGenerator);
		serialize(t, jsonWriter);
	}
	
	/**
	 * Any concrete implementation of this method must contain the writing rule of the object being handling in question
	 *
	 * @param t
	 * 		the object being handling
	 * @param jsonWriter
	 * 		an instance of the class that will aid in serialization
	 *
	 * @throws IOException
	 * 		is the type of exception that can be thrown in the event of an error in the serialization process
	 */
	public abstract void serialize(final T t, final JsonWriter jsonWriter) throws IOException;
	
	/**
	 * @param clazz
	 * @param <T>
	 *
	 * @return
	 */
	protected <T extends AbstractSerializer> T getBean(final Class<?> clazz) {
		return (T) DefaultApplicationContextHolder.getBean(clazz);
	}
	
	/**
	 * This method will return which is the serializer class of the passed class as parameter
	 *
	 * @param clazz
	 * 		class that wants to know which serializer
	 *
	 * @return an {@link AbstractSerializer} implementation that serializes the passed class as a parameter
	 */
	protected AbstractSerializer getSerializerFor(final Class<?> clazz) {
		final List<AbstractSerializer> serializers = DefaultApplicationContextHolder.getBeans(AbstractSerializer.class);
		return serializers.stream().filter(s -> s.getTypedParameter().equals(clazz)).findFirst().orElseThrow(() -> new RuntimeException(""));
	}
	
	/**
	 * This method returns which object is passed as type
	 *
	 * @return the object passed as type
	 */
	public Class<?> getTypedParameter() {
		return GenericTypeResolver.resolveTypeArgument(getClass(), AbstractSerializer.class);
	}
	
	@AllArgsConstructor
	protected enum Field {
		
		CODE("code"),
		CONTENT("content"),
		CREATION_DATE("creationDate"),
		CURRENT_PAGE("currentPage"),
		DESCRIPTION("description"),
		ID("id"),
		MESSAGE("message"),
		POINTS("points"),
		STATUS("status"),
		TOTAL_OF_PAGES("totalOfPages");
		
		private final String label;
		
		@Override
		public String toString() {
			return this.label;
		}
		
	}
	
}
