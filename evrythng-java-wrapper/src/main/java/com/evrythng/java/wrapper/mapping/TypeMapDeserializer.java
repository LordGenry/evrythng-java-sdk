/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.mapping;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Base class for deserializers with a type map.
 * 
 * @author Michel Yerly (my)
 **/
public abstract class TypeMapDeserializer<T> extends StdDeserializer<T> {

	private static final long serialVersionUID = 1L;

	private Map<String, Class<? extends T>> registry = new HashMap<>();
	private Map<Class<? extends T>, String> reverseRegistry = new HashMap<>();
	private String typeFieldName;

	protected TypeMapDeserializer(final Class<?> cls, final String fieldName) {
		super(cls);
		this.typeFieldName = fieldName;
	}

	public void registerType(final String type, final Class<? extends T> objectClass) {
		registry.put(type, objectClass);
		reverseRegistry.put(objectClass, type);
	}

	@Override
	public T deserialize(final JsonParser jp, final DeserializationContext ctxt) throws IOException {
		ObjectCodec codec = jp.getCodec();
		ObjectMapper mapper = (ObjectMapper) codec;
		ObjectNode root = mapper.readTree(jp);
		JsonNode type = root.get(typeFieldName);
		if (type == null) {
			throw new IllegalArgumentException(this.getValueClass().getSimpleName() + " type cannot be empty.");
		}
		String sType = type.textValue();
		if ((sType == null) || sType.isEmpty()) {
			throw new IllegalArgumentException(this.getValueClass().getSimpleName() + " type cannot be empty.");
		}

		Class<? extends T> clazz = resolveClass(sType);

		//if (codec instanceof ObjectMapper) {
		//	return ((ObjectMapper) codec).reader(clazz).readValue(root);
		//} else {
		return codec.treeToValue(root, clazz);
		//}
	}

	protected Class<? extends T> resolveClass(final String type) {

		Class<? extends T> clazz = registry.get(type);
		if (clazz == null) {
			throw new IllegalArgumentException(this.getValueClass().getSimpleName() + " type '" + type + "' is not recognized.");
		}
		return clazz;
	}

	public <U extends T> String getObjectType(final Class<U> objectClass) {
		return reverseRegistry.get(objectClass);
	}

	public String getTypeFieldName() {
		return typeFieldName;
	}
}
