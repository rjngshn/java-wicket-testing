/*
 * Copyright 2021 China kadoorie biobank.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.ac.ox.ndph.ckb.scratch;

import org.apache.wicket.Session;
import org.apache.wicket.core.request.handler.PageProvider;
import org.apache.wicket.core.request.handler.RenderPageRequestHandler;
import org.apache.wicket.core.request.mapper.StalePageException;
import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.cycle.IRequestCycleListener;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 *
 * @author rajanis
 */
class MyExceptionListener implements IRequestCycleListener {

    @Override
    public void onExceptionRequestHandlerResolved(RequestCycle cycle, IRequestHandler handler, Exception exception) {
        System.out.println("MyExceptionListener:onExceptionRequestHandlerResolved");
    }
    /**
     * Overridden method to return IRequestHandler, when exception is thrown.
     * @param cycle
     * @param e
     * @return ExceptionPage class extending BasePage.class
     */
    @Override
    public IRequestHandler onException(RequestCycle cycle, Exception e) {
        PageParameters params = new PageParameters();

        if (e instanceof StalePageException) {
            System.out.println("Received:StalePageException");
            params.add("error", "StalePageException");
        } else {
            params.add("error", "other_error");
        }
        Session.get().invalidateNow();
        return new RenderPageRequestHandler(new PageProvider(
            ExceptionPage.class, params));
    }
}
