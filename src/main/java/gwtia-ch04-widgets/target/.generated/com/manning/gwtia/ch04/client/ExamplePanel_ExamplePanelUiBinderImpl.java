package com.manning.gwtia.ch04.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiBinderUtil;
import com.google.gwt.user.client.ui.Widget;

public class ExamplePanel_ExamplePanelUiBinderImpl implements UiBinder<com.google.gwt.user.client.ui.Widget, com.manning.gwtia.ch04.client.ExamplePanel>, com.manning.gwtia.ch04.client.ExamplePanel.ExamplePanelUiBinder {

  interface Template extends SafeHtmlTemplates {
    @Template("<a href='http://manning.com/tacy/'><span id='{0}'></span></a>")
    SafeHtml html1(String arg0);
     
    @Template("Introduction")
    SafeHtml html2();
     
    @Template("Creating a Widget")
    SafeHtml html3();
     
    @Template("Extending a Widget")
    SafeHtml html4();
     
    @Template("Composite Widget")
    SafeHtml html5();
     
    @Template("SafeHTML")
    SafeHtml html6();
     
    @Template("Animation")
    SafeHtml html7();
     
    @Template("Layout Panel")
    SafeHtml html8();
     
    @Template("Split Layout Panel")
    SafeHtml html9();
     
    @Template("Dock Layout Panel")
    SafeHtml html10();
     
    @Template("Stack Layout Panel")
    SafeHtml html11();
     
    @Template("<a href='http://manning.com/tacy/'>Click here to<br><b>buy</b> the book,<br><b>read</b> the forums,<br><b>download</b> the source,<br>and more!</a>")
    SafeHtml html12();
     
  }

  Template template = GWT.create(Template.class);

  public com.google.gwt.user.client.ui.Widget createAndBindUi(final com.manning.gwtia.ch04.client.ExamplePanel owner) {

    com.manning.gwtia.ch04.client.ExamplePanel_ExamplePanelUiBinderImpl_GenBundle clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay = (com.manning.gwtia.ch04.client.ExamplePanel_ExamplePanelUiBinderImpl_GenBundle) GWT.create(com.manning.gwtia.ch04.client.ExamplePanel_ExamplePanelUiBinderImpl_GenBundle.class);
    com.manning.gwtia.ch04.client.ExamplePanel.Resources resources = (com.manning.gwtia.ch04.client.ExamplePanel.Resources) GWT.create(com.manning.gwtia.ch04.client.ExamplePanel.Resources.class);
    com.manning.gwtia.ch04.client.ExamplePanel_ExamplePanelUiBinderImpl_GenCss_style style = clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay.style();
    java.lang.String domId0 = com.google.gwt.dom.client.Document.get().createUniqueId();
    com.google.gwt.user.client.ui.Image f_Image5 = new com.google.gwt.user.client.ui.Image(resources.logo());
    com.google.gwt.user.client.ui.HTMLPanel f_HTMLPanel4 = new com.google.gwt.user.client.ui.HTMLPanel(template.html1(domId0).asString());
    com.google.gwt.user.client.ui.Label f_Label6 = (com.google.gwt.user.client.ui.Label) GWT.create(com.google.gwt.user.client.ui.Label.class);
    com.google.gwt.user.client.ui.HTML f_HTML7 = (com.google.gwt.user.client.ui.HTML) GWT.create(com.google.gwt.user.client.ui.HTML.class);
    com.google.gwt.user.client.ui.Button introPanel = (com.google.gwt.user.client.ui.Button) GWT.create(com.google.gwt.user.client.ui.Button.class);
    com.google.gwt.user.client.ui.HTML f_HTML8 = (com.google.gwt.user.client.ui.HTML) GWT.create(com.google.gwt.user.client.ui.HTML.class);
    com.google.gwt.user.client.ui.Button create = (com.google.gwt.user.client.ui.Button) GWT.create(com.google.gwt.user.client.ui.Button.class);
    com.google.gwt.user.client.ui.Button extend = (com.google.gwt.user.client.ui.Button) GWT.create(com.google.gwt.user.client.ui.Button.class);
    com.google.gwt.user.client.ui.Button composite = (com.google.gwt.user.client.ui.Button) GWT.create(com.google.gwt.user.client.ui.Button.class);
    com.google.gwt.user.client.ui.HTML f_HTML9 = (com.google.gwt.user.client.ui.HTML) GWT.create(com.google.gwt.user.client.ui.HTML.class);
    com.google.gwt.user.client.ui.Button safehtml = (com.google.gwt.user.client.ui.Button) GWT.create(com.google.gwt.user.client.ui.Button.class);
    com.google.gwt.user.client.ui.HTML f_HTML10 = (com.google.gwt.user.client.ui.HTML) GWT.create(com.google.gwt.user.client.ui.HTML.class);
    com.google.gwt.user.client.ui.Button animation = (com.google.gwt.user.client.ui.Button) GWT.create(com.google.gwt.user.client.ui.Button.class);
    com.google.gwt.user.client.ui.HTML f_HTML11 = (com.google.gwt.user.client.ui.HTML) GWT.create(com.google.gwt.user.client.ui.HTML.class);
    com.google.gwt.user.client.ui.Button layout = (com.google.gwt.user.client.ui.Button) GWT.create(com.google.gwt.user.client.ui.Button.class);
    com.google.gwt.user.client.ui.Button splitlayout = (com.google.gwt.user.client.ui.Button) GWT.create(com.google.gwt.user.client.ui.Button.class);
    com.google.gwt.user.client.ui.Button docklayout = (com.google.gwt.user.client.ui.Button) GWT.create(com.google.gwt.user.client.ui.Button.class);
    com.google.gwt.user.client.ui.Button stacklayout = (com.google.gwt.user.client.ui.Button) GWT.create(com.google.gwt.user.client.ui.Button.class);
    com.google.gwt.user.client.ui.HTML f_HTML12 = (com.google.gwt.user.client.ui.HTML) GWT.create(com.google.gwt.user.client.ui.HTML.class);
    com.google.gwt.user.client.ui.HTML f_HTML13 = (com.google.gwt.user.client.ui.HTML) GWT.create(com.google.gwt.user.client.ui.HTML.class);
    com.google.gwt.user.client.ui.HTMLPanel f_HTMLPanel14 = new com.google.gwt.user.client.ui.HTMLPanel(template.html12().asString());
    com.google.gwt.user.client.ui.FlowPanel f_FlowPanel3 = (com.google.gwt.user.client.ui.FlowPanel) GWT.create(com.google.gwt.user.client.ui.FlowPanel.class);
    com.google.gwt.user.client.ui.ScrollPanel f_ScrollPanel2 = (com.google.gwt.user.client.ui.ScrollPanel) GWT.create(com.google.gwt.user.client.ui.ScrollPanel.class);
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
    create.setHTML(template.html3().asString());
    create.addStyleName("" + style.button() + "");
    f_FlowPanel3.add(create);
    extend.setHTML(template.html4().asString());
    extend.addStyleName("" + style.button() + "");
    f_FlowPanel3.add(extend);
    composite.setHTML(template.html5().asString());
    composite.addStyleName("" + style.button() + "");
    f_FlowPanel3.add(composite);
    f_HTML9.setStyleName("" + style.divider() + "");
    f_FlowPanel3.add(f_HTML9);
    safehtml.setHTML(template.html6().asString());
    safehtml.addStyleName("" + style.button() + "");
    f_FlowPanel3.add(safehtml);
    f_HTML10.setStyleName("" + style.divider() + "");
    f_FlowPanel3.add(f_HTML10);
    animation.setHTML(template.html7().asString());
    animation.addStyleName("" + style.button() + "");
    f_FlowPanel3.add(animation);
    f_HTML11.setStyleName("" + style.divider() + "");
    f_FlowPanel3.add(f_HTML11);
    layout.setHTML(template.html8().asString());
    layout.addStyleName("" + style.button() + "");
    f_FlowPanel3.add(layout);
    splitlayout.setHTML(template.html9().asString());
    splitlayout.addStyleName("" + style.button() + "");
    f_FlowPanel3.add(splitlayout);
    docklayout.setHTML(template.html10().asString());
    docklayout.addStyleName("" + style.button() + "");
    f_FlowPanel3.add(docklayout);
    stacklayout.setHTML(template.html11().asString());
    stacklayout.addStyleName("" + style.button() + "");
    f_FlowPanel3.add(stacklayout);
    f_HTML12.setStyleName("" + style.divider() + "");
    f_FlowPanel3.add(f_HTML12);
    f_HTML13.setStyleName("" + style.divider() + "");
    f_FlowPanel3.add(f_HTML13);
    f_HTMLPanel14.setStyleName("" + style.website() + "");
    f_FlowPanel3.add(f_HTMLPanel14);
    f_FlowPanel3.addStyleName("" + style.navPanel() + "");
    f_ScrollPanel2.add(f_FlowPanel3);
    f_DockLayoutPanel1.addWest(f_ScrollPanel2, 180);
    exampleArea.addStyleName("" + style.exampleArea() + "");
    f_DockLayoutPanel1.add(exampleArea);

    UiBinderUtil.TempAttachment attachRecord0 = UiBinderUtil.attachToDom(f_HTMLPanel4.getElement());
    com.google.gwt.user.client.Element domId0Element = com.google.gwt.dom.client.Document.get().getElementById(domId0).cast();
    attachRecord0.detach();
    f_HTMLPanel4.addAndReplaceElement(f_Image5, domId0Element);


    final com.google.gwt.event.dom.client.ClickHandler handlerMethodWithNameVeryUnlikelyToCollideWithUserFieldNames1 = new com.google.gwt.event.dom.client.ClickHandler() {
      public void onClick(com.google.gwt.event.dom.client.ClickEvent event) {
        owner.showCreate(event);
      }
    };
    create.addClickHandler(handlerMethodWithNameVeryUnlikelyToCollideWithUserFieldNames1);

    final com.google.gwt.event.dom.client.ClickHandler handlerMethodWithNameVeryUnlikelyToCollideWithUserFieldNames2 = new com.google.gwt.event.dom.client.ClickHandler() {
      public void onClick(com.google.gwt.event.dom.client.ClickEvent event) {
        owner.showExtend(event);
      }
    };
    extend.addClickHandler(handlerMethodWithNameVeryUnlikelyToCollideWithUserFieldNames2);

    final com.google.gwt.event.dom.client.ClickHandler handlerMethodWithNameVeryUnlikelyToCollideWithUserFieldNames3 = new com.google.gwt.event.dom.client.ClickHandler() {
      public void onClick(com.google.gwt.event.dom.client.ClickEvent event) {
        owner.showComposite(event);
      }
    };
    composite.addClickHandler(handlerMethodWithNameVeryUnlikelyToCollideWithUserFieldNames3);

    final com.google.gwt.event.dom.client.ClickHandler handlerMethodWithNameVeryUnlikelyToCollideWithUserFieldNames4 = new com.google.gwt.event.dom.client.ClickHandler() {
      public void onClick(com.google.gwt.event.dom.client.ClickEvent event) {
        owner.showSafeHtml(event);
      }
    };
    safehtml.addClickHandler(handlerMethodWithNameVeryUnlikelyToCollideWithUserFieldNames4);

    final com.google.gwt.event.dom.client.ClickHandler handlerMethodWithNameVeryUnlikelyToCollideWithUserFieldNames5 = new com.google.gwt.event.dom.client.ClickHandler() {
      public void onClick(com.google.gwt.event.dom.client.ClickEvent event) {
        owner.showInstructionsPanel(event);
      }
    };
    introPanel.addClickHandler(handlerMethodWithNameVeryUnlikelyToCollideWithUserFieldNames5);

    final com.google.gwt.event.dom.client.ClickHandler handlerMethodWithNameVeryUnlikelyToCollideWithUserFieldNames6 = new com.google.gwt.event.dom.client.ClickHandler() {
      public void onClick(com.google.gwt.event.dom.client.ClickEvent event) {
        owner.showAnimationPanel(event);
      }
    };
    animation.addClickHandler(handlerMethodWithNameVeryUnlikelyToCollideWithUserFieldNames6);

    final com.google.gwt.event.dom.client.ClickHandler handlerMethodWithNameVeryUnlikelyToCollideWithUserFieldNames7 = new com.google.gwt.event.dom.client.ClickHandler() {
      public void onClick(com.google.gwt.event.dom.client.ClickEvent event) {
        owner.showLayout(event);
      }
    };
    layout.addClickHandler(handlerMethodWithNameVeryUnlikelyToCollideWithUserFieldNames7);

    final com.google.gwt.event.dom.client.ClickHandler handlerMethodWithNameVeryUnlikelyToCollideWithUserFieldNames8 = new com.google.gwt.event.dom.client.ClickHandler() {
      public void onClick(com.google.gwt.event.dom.client.ClickEvent event) {
        owner.showSplitLayout(event);
      }
    };
    splitlayout.addClickHandler(handlerMethodWithNameVeryUnlikelyToCollideWithUserFieldNames8);

    final com.google.gwt.event.dom.client.ClickHandler handlerMethodWithNameVeryUnlikelyToCollideWithUserFieldNames9 = new com.google.gwt.event.dom.client.ClickHandler() {
      public void onClick(com.google.gwt.event.dom.client.ClickEvent event) {
        owner.showStackLayout(event);
      }
    };
    stacklayout.addClickHandler(handlerMethodWithNameVeryUnlikelyToCollideWithUserFieldNames9);

    final com.google.gwt.event.dom.client.ClickHandler handlerMethodWithNameVeryUnlikelyToCollideWithUserFieldNames10 = new com.google.gwt.event.dom.client.ClickHandler() {
      public void onClick(com.google.gwt.event.dom.client.ClickEvent event) {
        owner.showDockLayout(event);
      }
    };
    docklayout.addClickHandler(handlerMethodWithNameVeryUnlikelyToCollideWithUserFieldNames10);

    owner.exampleArea = exampleArea;
    clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay.style().ensureInjected();

    return f_DockLayoutPanel1;
  }
}
