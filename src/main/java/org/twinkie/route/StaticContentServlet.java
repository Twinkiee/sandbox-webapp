package org.twinkie.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class StaticContentServlet extends RouteBuilder {

  @Override
  public void configure() throws Exception {

    from("servlet:staticContent")
        .to("language:constant:resource:classpath:/webjars/bootstrap/3.3.6/css/bootstrap.min.css");
  }
}
