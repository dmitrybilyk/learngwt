package com.manning.gwtia.ch03.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiBinderUtil;
import com.google.gwt.user.client.ui.Widget;

public class ExamplePanel_ExamplePanelUiBinderImpl implements UiBinder<com.google.gwt.user.client.ui.Widget, com.manning.gwtia.ch03.client.ExamplePanel>, com.manning.gwtia.ch03.client.ExamplePanel.ExamplePanelUiBinder {

  interface Template extends SafeHtmlTemplates {
    @Template("<a href='http://manning.com/tacy/'><span id='{0}'></span></a>")
    SafeHtml html1(String arg0);
     
    @Template("Introduction")
    SafeHtml html2();
     
    @Template("History Example")
    SafeHtml html3();
     
    @Template("<a href='http://manning.com/tacy/'>Click here to<br><b>buy</b> the book,<br><b>read</b> the forums,<br><b>download</b> the source,<br>and more!</a>")
    SafeHtml html4();
     
  }

  Template template = GWT.create(Template.class);

  public com.google.gwt.user.client.ui.Widget createAndBindUi(final com.manning.gwtia.ch03.client.ExamplePanel owner) {

    com.manning.gwtia.ch03.client.ExamplePanel_ExamplePanelUiBinderImpl_GenBundle clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay = (com.manning.gwtia.ch03.client.ExamplePanel_ExamplePanelUiBinderImpl_GenBundle) GWT.create(com.manning.gwtia.ch03.client.ExamplePanel_ExamplePanelUiBinderImpl_GenBundle.class);
    com.manning.gwtia.ch03.client.ExamplePanel.Resources resources = (com.manning.gwtia.ch03.client.ExamplePanel.Resources) GWT.create(com.manning.gwtia.ch03.client.ExamplePanel.Resources.class);
    com.manning.gwtia.ch03.client.ExamplePanel_ExamplePanelUiBinderImpl_GenCss_style style = clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay.style();
    java.lang.String domId0 = com.google.gwt.dom.client.Document.get().createUniqueId();
    com.google.gwt.user.client.ui.Image f_Image5 = new com.google.gwt.user.client.ui.Image(resources.logo());
    com.google.gwt.user.client.ui.HTMLPanel f_HTMLPanel4 = new com.google.gwt.user.client.ui.HTMLPanel(template.html1(domId0).asString());
    com.google.gwt.user.client.ui.Label f_Label6 = (com.google.gwt.user.client.ui.Label) GWT.create(com.google.gwt.user.client.ui.Label.class);
    com.google.gwt.user.client.ui.HTML f_HTML7 = (com.google.gwt.user.client.ui.HTML) GWT.create(com.google.gwt.user.client.ui.HTML.class);
    com.google.gwt.user.client.ui.Button introPanel = owner.introPanel;
    com.google.gwt.user.client.ui.HTML f_HTML8 = (com.google.gwt.user.client.ui.HTML) GWT.create(com.google.gwt.user.client.ui.HTML.class);
    com.google.gwt.user.client.ui.Button history = (com.google.gwt.user.client.ui.Button) GWT.create(com.google.gwt.user.client.ui.Button.class);
    com.google.gwt.user.client.ui.HTML f_HTML9 = (com.google.gwt.user.client.ui.HTML) GWT.create(com.google.gwt.user.client.ui.HTML.class);
    com.google.gwt.user.client.ui.HTML f_HTML10 = (com.google.gwt.user.client.ui.HTML) GWT.create(com.google.gwt.user.client.ui.HTML.class);
    com.google.gwt.user.client.ui.HTMLPanel f_HTMLPanel11 = new com.google.gwt.user.client.ui.HTMLPanel(template.html4().asString());
    com.google.gwt.user.client.ui.FlowPanel f_FlowPanel3 = (com.google.gwt.user.client.ui.FlowPanel) GWT.create(com.google.gwt.user.client.ui.FlowPanel.class);
    com.google.gwt.user.client.ui.CustomScrollPanel f_CustomScrollPanel2 = (com.google.gwt.user.client.ui.CustomScrollPanel) GWT.create(com.google.gwt.user.client.ui.CustomScrollPanel.class);
    com.google.gwt.user.client.ui.SimplePanel exampleArea = (com.google.gwt.user.client.ui.SimplePanel) GWT.create(com.google.gwt.user.client.ui.SimplePanel.class);
    com.google.gwt.user.client.ui.DockLayoutPanel f_DockLayoutPanel1 = new com.google.gwt.user.client.ui.DockLayoutPanel(com.google.gwt.dom.client.Style.Unit.PX);

    f_HTMLPanel4.setStyleName("" + style.logo() + "");
    f_FlowPanel3.add(f_HTMLPanel4);
    f_Label6.setText("Examples");
    f_Label6.addStyleName("" + style.title() + "");
    f_FlowPanel3.add(f_Label6);
    f_HTML7.setStyleName("" + style.divider() + "");
    f_FlowPanel3.add(f_HTML7);
    introPanel.setHTML(template.html2().asString());
    introPanel.addStyleName("" + style.button() + "");
    f_FlowPanel3.add(introPanel);
    f_HTML8.setStyleName("" + style.divider() + "");
    f_FlowPanel3.add(f_HTML8);
    history.setHTML(template.html3().asString());
    history.addStyleName("" + style.button() + "");
    f_FlowPanel3.add(history);
    f_HTML9.setStyleName("" + style.divider() + "");
    f_FlowPanel3.add(f_HTML9);
    f_HTML10.setStyleName("" + style.divider() + "");
    f_FlowPanel3.add(f_HTML10);
    f_HTMLPanel11.setStyleName("" + style.website() + "");
    f_FlowPanel3.add(f_HTMLPanel11);
    f_FlowPanel3.addStyleName("" + style.navPanel() + "");
    f_CustomScrollPanel2.add(f_FlowPanel3);
    f_DockLayoutPanel1.addWest(f_CustomScrollPanel2, 180);
    exampleArea.addStyleName("" + style.exampleArea() + "");
    f_DockLayoutPanel1.add(exampleArea);

    UiBinderUtil.TempAttachment attachRecord0 = UiBinderUtil.attachToDom(f_HTMLPanel4.getElement());
    com.google.gwt.user.client.Element domId0Element = com.google.gwt.dom.client.Document.get().getElementById(domId0).cast();
    attachRecord0.detach();
    f_HTMLPanel4.addAndReplaceElement(f_Image5, domId0Element);


    final com.google.gwt.event.dom.client.ClickHandler handlerMethodWithNameVeryUnlikelyToCollideWithUserFieldNames1 = new com.google.gwt.event.dom.client.ClickHandler() {
      public void onClick(com.google.gwt.event.dom.client.ClickEvent event) {
        owner.showHistory(event);
      }
    };
    history.addClickHandler(handlerMethodWithNameVeryUnlikelyToCollideWithUserFieldNames1);

    final com.google.gwt.event.dom.client.ClickHandler handlerMethodWithNameVeryUnlikelyToCollideWithUserFieldNames2 = new com.google.gwt.event.dom.client.ClickHandler() {
      public void onClick(com.google.gwt.event.dom.client.ClickEvent event) {
        owner.showInstructionsPanel(event);
      }
    };
    introPanel.addClickHandler(handlerMethodWithNameVeryUnlikelyToCollideWithUserFieldNames2);

    owner.exampleArea = exampleArea;
    clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay.style().ensureInjected();

    return f_DockLayoutPanel1;
  }
}
