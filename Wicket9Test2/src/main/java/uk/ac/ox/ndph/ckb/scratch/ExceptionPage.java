/*
 * Copyright 2021 rajanis.
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 */

package uk.ac.ox.ndph.ckb.scratch;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * @date 2021-08-10
 * @author rajanis
 */
public class ExceptionPage extends WebPage {
//https://www.javatips.net/api/nextreports-server-master/src/ro/nextreports/server/web/core/ErrorPage.java
	private static final long serialVersionUID = 1L;

	public ExceptionPage() {
		super();
	}

	public ExceptionPage(PageParameters parameters) {
		super(parameters);
		String errorCode = parameters.get("error").toString();
		add(new Label("errorDescr", errorCode));	
        }

}
