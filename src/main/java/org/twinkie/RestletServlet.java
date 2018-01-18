package org.twinkie;

import org.restlet.ext.spring.SpringServerServlet;

public class RestletServlet extends SpringServerServlet {

  @Override
  public String getInitParameter(String name, String defaultValue) {

    if (Component_BEAN_PARAM_NAME.equals(name)) {
      return "restletComponent";
    }

    return super.getInitParameter(name, defaultValue);
  }
}
