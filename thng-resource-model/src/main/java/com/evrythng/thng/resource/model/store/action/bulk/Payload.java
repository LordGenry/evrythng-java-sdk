/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store.action.bulk;

import java.io.Serializable;

public abstract class Payload implements Serializable {

	private static final long serialVersionUID = -153289055582546641L;
	public static final String FIELD_TYPE = "type";
	private Type type;

	public enum Type {
		LIST, FILE
	}

	public Type getType() {

		return type;
	}

	public void setType(final Type type) {

		this.type = type;
	}
}
