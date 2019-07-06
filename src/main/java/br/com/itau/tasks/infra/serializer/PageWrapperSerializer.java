package br.com.itau.tasks.infra.serializer;

import br.com.itau.tasks.infra.wrapper.PageWrapper;

import java.io.IOException;
import java.util.List;

/**
 * Class comments go here...
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 02/07/2019
 */
public class PageWrapperSerializer<E> extends AbstractSerializer<PageWrapper<E>> {
	
	@Override
	public void serialize(final PageWrapper<E> pageWrapper, final JsonWriter jsonWriter) throws IOException {
		jsonWriter.writeStartObject();
		serializeContentInformation(pageWrapper, jsonWriter);
		jsonWriter.writeNumberField(Field.CURRENT_PAGE, pageWrapper.getNumber());
		jsonWriter.writeNumberField(Field.TOTAL_OF_PAGES, pageWrapper.getTotalOfPages());
		jsonWriter.writeEndObject();
	}
	
	private void serializeContentInformation(final PageWrapper<E> pageWrapper, final JsonWriter jsonWriter) throws IOException {
		jsonWriter.writeArrayFieldStart(Field.CONTENT);
		final List<E> collection = pageWrapper.getContent();
		for (final E element : collection) {
			final AbstractSerializer serializer = getSerializerFor(element.getClass());
			serializer.serialize(element, jsonWriter);
		}
		jsonWriter.writeEndArray();
	}
	
}
