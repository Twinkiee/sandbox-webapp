package org.twinkie.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MainServlet extends RouteBuilder {

  @Override
  public void configure() throws Exception {

    from("servlet:hello")
        .to("language:constant:resource:classpath:/index.html");
  }
}
