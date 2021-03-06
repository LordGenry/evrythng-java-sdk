/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store;

import com.evrythng.thng.resource.model.core.DurableResourceModel;
import com.evrythng.thng.resource.model.core.Identifiable;
import org.apache.commons.collections.list.SetUniqueList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Model representation for long <em>tasks</em> to distribute between machines.
 */
public class Task extends DurableResourceModel implements Identifiable {

	private static final long serialVersionUID = -9117128840439882175L;
	// private ResourceLocation resourceLocation;
	private Notification.Policy notificationPolicy;
	public static final String FIELD_NOTIFICATION_POLICY = "notificationPolicy";
	private Long completedAt;
	public static final String FIELD_COMPLETED_AT = "completedAt";
	private Map<String, String> identifiers;

	public static final class Notification {

		public static class Policy {

			public Policy() {

			}

			public Policy(final Way singleWay) {

				this.completion = Collections.singletonList(singleWay);
			}

			private List<Notification.Way> completion;
			public static final String FIELD_COMPLETION = "completion";

			public List<Way> getCompletion() {

				return completion;
			}

			public void setCompletion(final List<Way> completion) {

				this.completion = completion != null ? SetUniqueList.decorate(new ArrayList(completion)) : null;
			}

			@Override
			public boolean equals(final Object o) {

				if (this == o) {
					return true;
				}
				if (o == null || getClass() != o.getClass()) {
					return false;
				}

				Policy policy = (Policy) o;

				if (completion != null ? !completion.equals(policy.completion) : policy.completion != null) {
					return false;
				}

				return true;
			}

			@Override
			public int hashCode() {

				return completion != null ? completion.hashCode() : 0;
			}
		}

		public static interface Way {

			Way.Type getType();

			void setType(Way.Type type);

			enum Type {

				EMAIL
			}
		}
	}

	public Notification.Policy getNotificationPolicy() {

		return notificationPolicy;
	}

	public void setNotificationPolicy(final Notification.Policy notificationPolicy) {

		this.notificationPolicy = notificationPolicy;
	}

	public Long getCompletedAt() {

		return completedAt;
	}

	public void setCompletedAt(final Long completedAt) {

		this.completedAt = completedAt;
	}

	@Override
	public void addIdentifier(final String type, final String value) {

		if (identifiers == null) {
			identifiers = new HashMap<>();
		}
		identifiers.put(type, value);
	}

	@Override
	public Map<String, String> getIdentifiers() {

		return identifiers;
	}

	@Override
	public void setIdentifiers(final Map<String, String> identifiers) {

		this.identifiers = identifiers;
	}

	@Override
	public String firstIdentifier() {

		return identifiers.values().iterator().next();
	}
}
