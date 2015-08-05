/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

public abstract class TaskOnBatch extends Task {

	private static final long serialVersionUID = 8091207835379916024L;
	private String batch;
	private Type type;
	private Status status;
	public static final String FIELD_STATUS = "status";
	public static final String FIELD_TYPE = "type";
	public static final String FIELD_BATCH = "batch";

	// Might be promoted to a super class !
	public static enum Status {
		PENDING, EXECUTING, EXECUTED, CANCELING
	}

	public Status getStatus() {

		return status;
	}

	public void setStatus(final Status status) {

		this.status = status;
	}

	public Type getType() {

		return type;
	}

	public void setType(final Type type) {

		this.type = type;
	}

	public String getBatch() {

		return batch;
	}

	public void setBatch(final String batch) {

		this.batch = batch;
	}

	public static enum Type {

		POPULATING, SHORT_ID_GENERATION;
	}

	public abstract static class BaseTaskResult {
		
		public static String FIELD_TYPE = "type";
		private Type type;
		private String error;

		public Type getType() {

			return type;
		}

		public void setType(final Type type) {

			this.type = type;
		}

		public String getError() {

			return error;
		}

		public void setError(final String error) {

			this.error = error;
		}
	}
}
