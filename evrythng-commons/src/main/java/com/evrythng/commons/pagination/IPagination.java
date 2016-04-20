/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.commons.pagination;

import com.evrythng.commons.domain.Size;
import com.evrythng.commons.domain.SortOrder;

public interface IPagination<TOKEN> {

	TOKEN token();

	Size size();

	SortOrder sortOrder();
}
