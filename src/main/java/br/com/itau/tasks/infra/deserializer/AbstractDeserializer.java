package br.com.itau.tasks.infra.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;

import java.io.IOException;

/**
 * Class comments go here...
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 01/07/2019
 */
public abstract class AbstractDeserializer<T> extends JsonDeserializer<T> {
	
	@Override
	public T deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext)
			throws IOException {
		final ObjectCodec codec = jsonParser.getCodec();
		final JsonNode jsonNode = codec.readTree(jsonParser);
		return deserialize(jsonNode);
	}
	
	/**
	 * @param jsonNode
	 *
	 * @return
	 *
	 * @throws IOException
	 */
	public abstract T deserialize(final JsonNode jsonNode) throws IOException;
	
	protected String getFieldTextValue(final JsonNode node, final Enum<?> fieldName) {
		return this.hasNonNullAndNonEmpty(node, fieldName) ? node.get(fieldName.toString()).textValue() : null;
	}
	
	protected Integer getFieldIntegerValue(final JsonNode node, final Enum<?> fieldName) {
		return this.hasNonNullAndNonEmpty(node, fieldName) ? node.get(fieldName.toString()).asInt() : null;
	}
	
	protected boolean hasNonNullAndNonEmpty(final JsonNode node, final Enum<?> fieldName) {
		return this.hasNonNull(node, fieldName) && node.get(fieldName.toString()).asText().length() > 0;
	}
	
	protected boolean hasNonNull(final JsonNode node, final Enum<?> fieldName) {
		return this.has(node, fieldName) && node.hasNonNull(fieldName.toString());
	}
	
	protected boolean has(final JsonNode node, final Enum<?> fieldName) {
		return node.has(fieldName.toString());
	}
	
	@AllArgsConstructor
	protected enum Field {
		
		DESCRIPTION("description"),
		POINTS("points"),
		STATUS("status");
		
		private final String label;
		
		@Override
		public String toString() {
			return this.label;
		}
	}
	
}
