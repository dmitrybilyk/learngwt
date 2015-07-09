package com.manning.gwtia.ch03.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ResourcePrototype;

public class ExamplePanel_Resources_default_StaticClientBundleGenerator implements com.manning.gwtia.ch03.client.ExamplePanel.Resources {
  private static ExamplePanel_Resources_default_StaticClientBundleGenerator _instance0 = new ExamplePanel_Resources_default_StaticClientBundleGenerator();
  private void logoInitializer() {
    logo = new com.google.gwt.resources.client.impl.ImageResourcePrototype(
      "logo",
      com.google.gwt.safehtml.shared.UriUtils.fromTrustedString(externalImage),
      0, 0, 141, 93, false, true
    );
  }
  private static class logoInitializer {
    static {
      _instance0.logoInitializer();
    }
    static com.google.gwt.resources.client.ImageResource get() {
      return logo;
    }
  }
  public com.google.gwt.resources.client.ImageResource logo() {
    return logoInitializer.get();
  }
  private static java.util.HashMap<java.lang.String, com.google.gwt.resources.client.ResourcePrototype> resourceMap;
  private static final java.lang.String externalImage = GWT.getModuleBaseURL() + "986CB9BC2ACC3F147B76A5C661F49DD5.cache.jpg";
  private static com.google.gwt.resources.client.ImageResource logo;
  
  public ResourcePrototype[] getResources() {
    return new ResourcePrototype[] {
      logo(), 
    };
  }
  public ResourcePrototype getResource(String name) {
    if (GWT.isScript()) {
      return getResourceNative(name);
    } else {
      if (resourceMap == null) {
        resourceMap = new java.util.HashMap<java.lang.String, com.google.gwt.resources.client.ResourcePrototype>();
        resourceMap.put("logo", logo());
      }
      return resourceMap.get(name);
    }
  }
  private native ResourcePrototype getResourceNative(String name) /*-{
    switch (name) {
      case 'logo': return this.@com.manning.gwtia.ch03.client.ExamplePanel.Resources::logo()();
    }
    return null;
  }-*/;
}
