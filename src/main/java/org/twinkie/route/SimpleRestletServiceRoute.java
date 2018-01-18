package org.twinkie.route;

/**
 * Created by a.cirasole on 05/07/2017.
 */

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * A simple Camel route that triggers from a timer and calls a bean and prints to system out.
 * <p/>
 * Use <tt>@Component</tt> to make Camel auto detect this route when starting.
 */
@Component
public class SimpleRestletServiceRoute extends RouteBuilder {


  @Override
  public void configure() throws Exception {

    from("restlet:/simple-restlet-service?restletMethod=post")
        .process(
            exchange -> {
              StringBuilder queryStringHeaders = new StringBuilder(" [");

              exchange.getIn().getHeaders().forEach(
                  (key, value) -> queryStringHeaders.append("key: " + key + "; value: " + value + "\n")
              );

              queryStringHeaders.append(" ]");

              exchange.getOut().setBody(
                  "Received [ " + exchange.getIn().getBody(String.class)
                      + " ] as body content from the forwarding REST service with query string headers "
                      + queryStringHeaders);


            }
        );
  }

}
