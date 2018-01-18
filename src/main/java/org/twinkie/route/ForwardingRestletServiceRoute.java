package org.twinkie.route;

/**
 * Created by a.cirasole on 05/07/2017.
 */


import java.util.Map;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.restlet.RestletConstants;
import org.restlet.Request;
import org.springframework.stereotype.Component;

/**
 * A simple Camel route that forwards a GET REST service call to another REST service
 * moving the uri param to body and query string to headers
 * <p/>
 * Use <tt>@Component</tt> to make Camel auto detect this route when starting.
 */
@Component
public class ForwardingRestletServiceRoute extends RouteBuilder {


  @Override
  public void configure() throws Exception {

    from("restlet:/forwarding-restlet-service/{id}?restletMethod=get")
        .process(exchange -> {

          Request request = exchange.getIn().getHeader(RestletConstants.RESTLET_REQUEST, Request.class);
          Map<String, String> queryStringMap = request.getResourceRef().getQueryAsForm().getValuesMap();

          queryStringMap.forEach(
              (key, value) -> exchange.getOut().setHeader(key, value)
          );

          exchange.getOut().setBody(exchange.getIn().getHeader("id"));
        })
        .to("restlet:http://localhost:8080/simple-restlet-service?restletMethod=post");
  }
}
