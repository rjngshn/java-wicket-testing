/*
 * Copyright 2021 rajanis.
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 */

package uk.ac.ox.ndph.ckb.scratch;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalDialog;
import org.apache.wicket.extensions.ajax.markup.html.modal.theme.DefaultTheme;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

/**
 * @date 2021-06-28
 * @author rajanis
 */
public class ButtonsPanel extends Panel {
    ModalFragment modalFragment;
    final Label lblCloseBy;

    public ButtonsPanel(String id, Label lblCloseBy) {
        super(id);
        this.lblCloseBy = lblCloseBy;
    }
    @Override
    protected void onInitialize() {
        super.onInitialize(); //To change body of generated methods, choose Tools | Templates.
        ModalDialog modalDialog = new ModalDialog("modalWindow"){
            @Override
            public void onEvent(IEvent<?> event) {
//**** Not really required, since we can't really do anything here without AjaxRequestTarget
                System.out.println(modalFragment.getRetValue());
            }
        };
        modalDialog.add(new DefaultTheme());
        modalDialog.trapFocus();
        modalDialog.closeOnEscape();
        queue(modalDialog);
        queue(new Link<Void>("cmdOpenDialog") {
            @Override
            public void onClick() {
                modalFragment = new ModalFragment(ModalDialog.CONTENT_ID, modalDialog, lblCloseBy);
                modalDialog.open(modalFragment, null);
            }
        });
        queue(new AjaxLink("cmdOpenAjaxDialog") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                modalFragment = new ModalFragment(ModalDialog.CONTENT_ID, modalDialog, lblCloseBy);
                modalDialog.open(modalFragment, target);
            }
        });
    }
    private class ModalFragment extends Fragment {
        private String retValue = "";
        ModalFragment(String id, final ModalDialog modalDialog, final Label lbl) {
            super(id, "fragment", ButtonsPanel.this);
            queue(new Label("titleLabel", "ModalWindowTitle"));
            AjaxLink<Void> btnSubmit = new AjaxLink<Void>("cmdSubmit") {
                @Override
                public void onClick(AjaxRequestTarget target) {
                    retValue = "Submit clicked";
                    lbl.setDefaultModel(Model.of(retValue));
                    target.add(lbl);
                    modalDialog.close(target);
                }
            };
            queue(btnSubmit);
            AjaxLink<Void> btnCancel = new AjaxLink<Void>("cmdCancel") {
                @Override
                public void onClick(AjaxRequestTarget target) {
                    retValue = "Cancel clicked";
                    lbl.setDefaultModel(Model.of(retValue));
                    target.add(lbl);
                    modalDialog.close(target);
                }
            };
            queue(btnCancel);
        }

        public String getRetValue() {
            return retValue;
        }
    }

}
