package org.twinkie.route;

/**
 * Created by a.cirasole on 05/07/2017.
 */

import java.util.concurrent.ConcurrentMap;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.restlet.RestletConstants;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.Header;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.util.Series;
import org.springframework.stereotype.Component;

/**
 * A simple Camel route that triggers from a timer and calls a bean and prints to system out.
 * <p/>
 * Use <tt>@Component</tt> to make Camel auto detect this route when starting.
 */
@Component
public class JsonRestletServiceRoute extends RouteBuilder {


  @Override
  @SuppressWarnings("unchecked")
  public void configure() throws Exception {

    from("restlet:/json-restlet-service?restletMethod=post").process(exchange ->
    {
      Request request = exchange.getIn().getHeader(RestletConstants.RESTLET_REQUEST, Request.class);
      String param1 = request.getResourceRef().getQueryAsForm().getFirstValue("param1");

      // use Restlet API to create the response
      Response response = exchange.getIn().getHeader(RestletConstants.RESTLET_RESPONSE, Response.class);
      response.setStatus(Status.SUCCESS_OK);

      // This is what we have to do in order to have Restlet initialize some custom HTTP headers
      final ConcurrentMap<String, Object> attributes = response.getAttributes();

            Series<Header> headers = (Series<Header>) attributes.get("org.restlet.http.headers");
        if (headers == null) {
          headers = new Series<>(Header.class);
          Series<Header> prev = (Series<Header>) attributes.putIfAbsent("org.restlet.http.headers", headers);
        if (prev != null) {
          headers = prev;
        }
      }

      headers.add("new-header-id", exchange.getIn().getHeader("id", String.class));

      response.setEntity("{ \"param1\": \"" + param1 + "\" }", MediaType.APPLICATION_JSON);
      exchange.getOut().setBody(response);
    });
  }

}