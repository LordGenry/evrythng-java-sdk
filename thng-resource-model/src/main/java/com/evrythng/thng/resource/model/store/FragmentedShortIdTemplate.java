/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store;

public abstract class FragmentedShortIdTemplate extends ShortIdTemplate {

	private String prefix;
	public static final String PREFIX = "prefix";
	private String suffix;
	public static final String SUFFIX = "suffix";
	private String separator;
	public static final String SEPARATOR = "separator";

	public String getPrefix() {

		return prefix;
	}

	public void setPrefix(final String prefix) {

		this.prefix = prefix;
	}

	public String getSuffix() {

		return suffix;
	}

	public void setSuffix(final String suffix) {

		this.suffix = suffix;
	}

	public String getSeparator() {

		return separator;
	}

	public void setSeparator(final String separator) {

		this.separator = separator;
	}

}
