package com.manning.gwtia.ch03.client;

public class ExamplePanel_ExamplePanelUiBinderImpl_TemplateImpl implements com.manning.gwtia.ch03.client.ExamplePanel_ExamplePanelUiBinderImpl.Template {
  
  public com.google.gwt.safehtml.shared.SafeHtml html2() {
    StringBuilder sb = new java.lang.StringBuilder();
    sb.append("Introduction");
return new com.google.gwt.safehtml.shared.OnlyToBeUsedInGeneratedCodeStringBlessedAsSafeHtml(sb.toString());
}

public com.google.gwt.safehtml.shared.SafeHtml html1(java.lang.String arg0) {
StringBuilder sb = new java.lang.StringBuilder();
sb.append("<a href='http://manning.com/tacy/'><span id='");
sb.append(com.google.gwt.safehtml.shared.SafeHtmlUtils.htmlEscape(arg0));
sb.append("'></span></a>");
return new com.google.gwt.safehtml.shared.OnlyToBeUsedInGeneratedCodeStringBlessedAsSafeHtml(sb.toString());
}

public com.google.gwt.safehtml.shared.SafeHtml html3() {
StringBuilder sb = new java.lang.StringBuilder();
sb.append("History Example");
return new com.google.gwt.safehtml.shared.OnlyToBeUsedInGeneratedCodeStringBlessedAsSafeHtml(sb.toString());
}

public com.google.gwt.safehtml.shared.SafeHtml html4() {
StringBuilder sb = new java.lang.StringBuilder();
sb.append("<a href='http://manning.com/tacy/'>Click here to<br><b>buy</b> the book,<br><b>read</b> the forums,<br><b>download</b> the source,<br>and more!</a>");
return new com.google.gwt.safehtml.shared.OnlyToBeUsedInGeneratedCodeStringBlessedAsSafeHtml(sb.toString());
}
}
