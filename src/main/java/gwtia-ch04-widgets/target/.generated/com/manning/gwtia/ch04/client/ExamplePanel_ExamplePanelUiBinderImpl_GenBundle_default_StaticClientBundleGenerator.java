package com.manning.gwtia.ch04.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ResourcePrototype;

public class ExamplePanel_ExamplePanelUiBinderImpl_GenBundle_default_StaticClientBundleGenerator implements com.manning.gwtia.ch04.client.ExamplePanel_ExamplePanelUiBinderImpl_GenBundle {
  private static ExamplePanel_ExamplePanelUiBinderImpl_GenBundle_default_StaticClientBundleGenerator _instance0 = new ExamplePanel_ExamplePanelUiBinderImpl_GenBundle_default_StaticClientBundleGenerator();
  private void styleInitializer() {
    style = new com.manning.gwtia.ch04.client.ExamplePanel_ExamplePanelUiBinderImpl_GenCss_style() {
      private boolean injected;
      public boolean ensureInjected() {
        if (!injected) {
          injected = true;
          com.google.gwt.dom.client.StyleInjector.inject(getText());
          return true;
        }
        return false;
      }
      public String getName() {
        return "style";
      }
      public String getText() {
        return com.google.gwt.i18n.client.LocaleInfo.getCurrentLocale().isRTL() ? ((".GNVYFPTBEI{width:" + ("100%")  + ";}.GNVYFPTBGI{border-right:" + ("1px"+ " " +"solid"+ " " +"#333")  + ";padding:" + ("10px")  + ";}.GNVYFPTBJI{font-family:" + ("Verdana"+ ","+ " " +"sans-serif")  + ";color:" + ("#fff")  + ";font-weight:" + ("bold")  + ";font-size:" + ("16px")  + ";text-align:" + ("center")  + ";padding:" + ("5px"+ " " +"0")  + ";background-color:" + ("#323b38")  + ";}.GNVYFPTBII{background:") + (("#eee")  + ";}.GNVYFPTBFI{height:" + ("10px")  + ";background:" + ("#999")  + ";}.GNVYFPTBKI{background-color:" + ("#df1b1b")  + ";font-family:" + ("Verdana"+ ","+ " " +"sans-serif")  + ";font-size:" + ("12px")  + ";text-align:" + ("center")  + ";padding:" + ("10px"+ " " +"0")  + ";}.GNVYFPTBHI{background-color:" + ("#fff")  + ";height:" + ("93px")  + ";}")) : ((".GNVYFPTBEI{width:" + ("100%")  + ";}.GNVYFPTBGI{border-left:" + ("1px"+ " " +"solid"+ " " +"#333")  + ";padding:" + ("10px")  + ";}.GNVYFPTBJI{font-family:" + ("Verdana"+ ","+ " " +"sans-serif")  + ";color:" + ("#fff")  + ";font-weight:" + ("bold")  + ";font-size:" + ("16px")  + ";text-align:" + ("center")  + ";padding:" + ("5px"+ " " +"0")  + ";background-color:" + ("#323b38")  + ";}.GNVYFPTBII{background:") + (("#eee")  + ";}.GNVYFPTBFI{height:" + ("10px")  + ";background:" + ("#999")  + ";}.GNVYFPTBKI{background-color:" + ("#df1b1b")  + ";font-family:" + ("Verdana"+ ","+ " " +"sans-serif")  + ";font-size:" + ("12px")  + ";text-align:" + ("center")  + ";padding:" + ("10px"+ " " +"0")  + ";}.GNVYFPTBHI{background-color:" + ("#fff")  + ";height:" + ("93px")  + ";}"));
      }
      public java.lang.String button(){
        return "GNVYFPTBEI";
      }
      public java.lang.String divider(){
        return "GNVYFPTBFI";
      }
      public java.lang.String exampleArea(){
        return "GNVYFPTBGI";
      }
      public java.lang.String logo(){
        return "GNVYFPTBHI";
      }
      public java.lang.String navPanel(){
        return "GNVYFPTBII";
      }
      public java.lang.String title(){
        return "GNVYFPTBJI";
      }
      public java.lang.String website(){
        return "GNVYFPTBKI";
      }
    }
    ;
  }
  private static class styleInitializer {
    static {
      _instance0.styleInitializer();
    }
    static com.manning.gwtia.ch04.client.ExamplePanel_ExamplePanelUiBinderImpl_GenCss_style get() {
      return style;
    }
  }
  public com.manning.gwtia.ch04.client.ExamplePanel_ExamplePanelUiBinderImpl_GenCss_style style() {
    return styleInitializer.get();
  }
  private static java.util.HashMap<java.lang.String, com.google.gwt.resources.client.ResourcePrototype> resourceMap;
  private static com.manning.gwtia.ch04.client.ExamplePanel_ExamplePanelUiBinderImpl_GenCss_style style;
  
  public ResourcePrototype[] getResources() {
    return new ResourcePrototype[] {
      style(), 
    };
  }
  public ResourcePrototype getResource(String name) {
    if (GWT.isScript()) {
      return getResourceNative(name);
    } else {
      if (resourceMap == null) {
        resourceMap = new java.util.HashMap<java.lang.String, com.google.gwt.resources.client.ResourcePrototype>();
        resourceMap.put("style", style());
      }
      return resourceMap.get(name);
    }
  }
  private native ResourcePrototype getResourceNative(String name) /*-{
    switch (name) {
      case 'style': return this.@com.manning.gwtia.ch04.client.ExamplePanel_ExamplePanelUiBinderImpl_GenBundle::style()();
    }
    return null;
  }-*/;
}
