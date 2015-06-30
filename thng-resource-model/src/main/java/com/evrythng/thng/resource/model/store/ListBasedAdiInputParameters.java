/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.thng.resource.model.store;

import java.util.List;

public class ListBasedAdiInputParameters extends BaseAdiInputParameters {

	private ThngTemplate thngTemplate;
	private Boolean generateThngs;
	private Boolean generateRedirections;

	public static final String FIELD_REDIRECTIONS = "redirections";

	private List<RedirectionResource> redirections;

	public void setGenerateThngs(final Boolean generateThngs) {

		this.generateThngs = generateThngs;
	}

	public ThngTemplate getThngTemplate() {

		return thngTemplate;
	}

	public void setThngTemplate(final ThngTemplate thngTemplate) {

		this.thngTemplate = thngTemplate;
	}

	@Override
	public Boolean getGenerateThngs() {

		return generateThngs;
	}

	public void setGenerateRedirections(final Boolean generateRedirections) {

		this.generateRedirections = generateRedirections;
	}

	@Override
	public Boolean getGenerateRedirections() {

		return generateRedirections;
	}

	public List<RedirectionResource> getRedirections() {

		return redirections;
	}

	public void setRedirections(final List<RedirectionResource> redirections) {

		this.redirections = redirections;
	}
}
