package com.manning.gwtia.ch04.client.intro;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiBinderUtil;
import com.google.gwt.user.client.ui.Widget;

public class IntroPanel_InstructionsPanelUiBinderImpl implements UiBinder<com.google.gwt.user.client.ui.Widget, com.manning.gwtia.ch04.client.intro.IntroPanel>, com.manning.gwtia.ch04.client.intro.IntroPanel.InstructionsPanelUiBinder {

  interface Template extends SafeHtmlTemplates {
    @Template("<h1 style='font-face:Arial;'>Introduction</h1> <p>Dear reader,</p> <p>Chapter 4 introduces widgets (and panels).</p> <p>The examples in this application are the three that are discussed in the chapter, namely:</p> <p> <b>Create Example</b> - A new widget is created from the DOM, in this case a Canvas widget, and we see how to use it as well as how to get it to handle events. </p> <p> <b>Extend Example</b> - We see how to extend an existing widget and overide functionality on it to create a new widget. In this case, the InlineLabel widget is extended so that it reports its size when added to the DOM and to stop ClickHandlers being added to it. </p> <p> <b>Composite Example</b> - We create a composite widget made up of a question label and answer text box both added to a FlowPanel. The code shows how functionalities, such as getText, are delegated to components of the composite widget, but that the composite itself acts as a brand new widget. </p>")
    SafeHtml html1();
     
  }

  Template template = GWT.create(Template.class);

  public com.google.gwt.user.client.ui.Widget createAndBindUi(final com.manning.gwtia.ch04.client.intro.IntroPanel owner) {

    com.manning.gwtia.ch04.client.intro.IntroPanel_InstructionsPanelUiBinderImpl_GenBundle clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay = (com.manning.gwtia.ch04.client.intro.IntroPanel_InstructionsPanelUiBinderImpl_GenBundle) GWT.create(com.manning.gwtia.ch04.client.intro.IntroPanel_InstructionsPanelUiBinderImpl_GenBundle.class);
    com.google.gwt.user.client.ui.HTMLPanel f_HTMLPanel1 = new com.google.gwt.user.client.ui.HTMLPanel(template.html1().asString());
    com.google.gwt.user.client.ui.ScrollPanel scrollPanel = (com.google.gwt.user.client.ui.ScrollPanel) GWT.create(com.google.gwt.user.client.ui.ScrollPanel.class);

    scrollPanel.add(f_HTMLPanel1);



    owner.scrollPanel = scrollPanel;

    return scrollPanel;
  }
}
