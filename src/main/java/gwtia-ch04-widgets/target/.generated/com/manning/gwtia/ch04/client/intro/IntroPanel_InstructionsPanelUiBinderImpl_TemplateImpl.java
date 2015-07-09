package com.manning.gwtia.ch04.client.intro;

public class IntroPanel_InstructionsPanelUiBinderImpl_TemplateImpl implements com.manning.gwtia.ch04.client.intro.IntroPanel_InstructionsPanelUiBinderImpl.Template {
  
  public com.google.gwt.safehtml.shared.SafeHtml html1() {
    StringBuilder sb = new java.lang.StringBuilder();
    sb.append("<h1 style='font-face:Arial;'>Introduction</h1> <p>Dear reader,</p> <p>Chapter 4 introduces widgets (and panels).</p> <p>The examples in this application are the three that are discussed in the chapter, namely:</p> <p> <b>Create Example</b> - A new widget is created from the DOM, in this case a Canvas widget, and we see how to use it as well as how to get it to handle events. </p> <p> <b>Extend Example</b> - We see how to extend an existing widget and overide functionality on it to create a new widget. In this case, the InlineLabel widget is extended so that it reports its size when added to the DOM and to stop ClickHandlers being added to it. </p> <p> <b>Composite Example</b> - We create a composite widget made up of a question label and answer text box both added to a FlowPanel. The code shows how functionalities, such as getText, are delegated to components of the composite widget, but that the composite itself acts as a brand new widget. </p>");
return new com.google.gwt.safehtml.shared.OnlyToBeUsedInGeneratedCodeStringBlessedAsSafeHtml(sb.toString());
}
}
