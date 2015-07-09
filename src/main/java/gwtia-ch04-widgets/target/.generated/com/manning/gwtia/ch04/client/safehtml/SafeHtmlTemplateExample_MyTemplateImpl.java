package com.manning.gwtia.ch04.client.safehtml;

public class SafeHtmlTemplateExample_MyTemplateImpl implements com.manning.gwtia.ch04.client.safehtml.SafeHtmlTemplateExample.MyTemplate {
  
  public com.google.gwt.safehtml.shared.SafeHtml message(java.lang.String arg0) {
    StringBuilder sb = new java.lang.StringBuilder();
    sb.append("<b>");
    sb.append(com.google.gwt.safehtml.shared.SafeHtmlUtils.htmlEscape(arg0));
    sb.append("</b>");
return new com.google.gwt.safehtml.shared.OnlyToBeUsedInGeneratedCodeStringBlessedAsSafeHtml(sb.toString());
}
}
