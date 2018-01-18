package org.twinkie.route;

/**
 * Created by a.cirasole on 05/07/2017.
 */

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * A simple Camel route that triggers from a timer and calls a bean and prints to system out.
 * <p/>
 * Use <tt>@Component</tt> to make Camel auto detect this route when starting.
 */
@Component
public class RestToActiveMqRoute extends RouteBuilder {


  @Override
  public void configure() throws Exception {

    from("restlet:/rest-to-activemq/{body}?restletMethod=get")
        .process(
            exchange ->
              exchange.getOut().setBody(exchange.getIn().getHeader("body"))
        )
    .inOnly("activemq:queue:inbound");
  }
}
