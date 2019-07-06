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
 * Class comments go here...
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
	 * @param t
	 * @param jsonWriter
	 *
	 * @throws IOException
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
	
	protected AbstractSerializer getSerializerFor(final Class<?> clazz) {
		final List<AbstractSerializer> serializers = DefaultApplicationContextHolder.getBeans(AbstractSerializer.class);
		return serializers.stream().filter(s -> s.getTypedParameter().equals(clazz)).findFirst().orElseThrow(() -> new RuntimeException(""));
	}
	
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
