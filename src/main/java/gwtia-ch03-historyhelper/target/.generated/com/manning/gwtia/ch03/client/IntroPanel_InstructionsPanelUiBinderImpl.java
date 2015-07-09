package com.manning.gwtia.ch03.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiBinderUtil;
import com.google.gwt.user.client.ui.Widget;

public class IntroPanel_InstructionsPanelUiBinderImpl implements UiBinder<com.google.gwt.user.client.ui.Widget, com.manning.gwtia.ch03.client.IntroPanel>, com.manning.gwtia.ch03.client.IntroPanel.InstructionsPanelUiBinder {

  interface Template extends SafeHtmlTemplates {
    @Template("<h1 style='font-face:Arial;'>Introduction</h1> <p>Dear reader,</p> <p>Chapter 3 discusses layout panels and history management as part of the overall application. It mentions two helper applications that give simple examples of these in action.</p> <p>This is those two examples, and we hope that you learn as much from them application as you do from each of the examples.</p> <p> <b>Layout Example</b> - This shows a layout panel in action. You can click the animate button to see layout constraints animate. </p> <p> <b>SplitPanelLayout Example</b> - This shows a split layout panel in action. You can move the sliders around to resize the panels, and click the animate button to see layout constraints animate back to original sizes. In addition, the west panel has a minimum size constraint. </p> <p> <b>History Example</b> - This shows the history management in action. </p>")
    SafeHtml html1();
     
  }

  Template template = GWT.create(Template.class);

  public com.google.gwt.user.client.ui.Widget createAndBindUi(final com.manning.gwtia.ch03.client.IntroPanel owner) {

    com.manning.gwtia.ch03.client.IntroPanel_InstructionsPanelUiBinderImpl_GenBundle clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay = (com.manning.gwtia.ch03.client.IntroPanel_InstructionsPanelUiBinderImpl_GenBundle) GWT.create(com.manning.gwtia.ch03.client.IntroPanel_InstructionsPanelUiBinderImpl_GenBundle.class);
    com.google.gwt.user.client.ui.HTMLPanel f_HTMLPanel1 = new com.google.gwt.user.client.ui.HTMLPanel(template.html1().asString());
    com.google.gwt.user.client.ui.ScrollPanel scrollPanel = (com.google.gwt.user.client.ui.ScrollPanel) GWT.create(com.google.gwt.user.client.ui.ScrollPanel.class);

    scrollPanel.add(f_HTMLPanel1);



    owner.scrollPanel = scrollPanel;

    return scrollPanel;
  }
}
