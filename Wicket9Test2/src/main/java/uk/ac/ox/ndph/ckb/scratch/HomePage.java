/*
 * Copyright 2020 China kadoorie biobank.
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

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

/**
 *
 * @author rajanis
 */
public class HomePage extends WebPage {
    private static final long serialVersionUID = 1L;
    Label lblCloseBy;
    int currentCount;
    public HomePage() {
        System.out.println("HomePage:Constructor");
        lblCloseBy = new Label("closedBy", "");
        lblCloseBy.setOutputMarkupId(true);
        queue (lblCloseBy);
        queue(new ButtonsPanel("buttonsPanel", lblCloseBy));
        System.out.println("HomePage:isStateless:" + Boolean.toString(this.isStateless()));
        currentCount = 0;
        add(new AjaxNewWindowNotifyingBehavior(){
            @Override
            protected void onNewWindow(AjaxRequestTarget target) {
                System.out.println("HomePage:AjaxNewWindowNotifyingBehavior:onNewWindow" + "render count:" + currentCount);
                super.onNewWindow(target);
                setResponsePage(HomePage.class);
            }
        });
    }
    @Override
    protected void onConfigure() {
        super.onConfigure();
        currentCount = this.getRenderCount();
        System.out.println("HomePage:OnConfigure:Current count: " + currentCount);
    }
}
