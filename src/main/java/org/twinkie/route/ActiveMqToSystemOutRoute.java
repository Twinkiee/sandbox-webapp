package org.twinkie.route;

/**
 * Created by a.cirasole on 05/07/2017.
 */

import org.apache.camel.Exchange;
import org.apache.camel.Expression;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

/**
 * A simple Camel route that triggers from a timer and calls a bean and prints to system out.
 * <p/>
 * Use <tt>@Component</tt> to make Camel auto detect this route when starting.
 */
@Component
public class ActiveMqToSystemOutRoute extends RouteBuilder {


  @Override
  public void configure() throws Exception {

//    errorHandler(transactionErrorHandler().maximumRedeliveries(0));

    onException(RuntimeException.class).log(">>>>>>>>>> onException <<<<<<<<<<<");
//        .maximumRedeliveries(3)
//        .retryAttemptedLogLevel(LoggingLevel.INFO);

    from("activemq:queue:inbound")
        .log("---------- Dequeued message ${body}")
        .transacted()
        .transform(
            simple("Inbound queue with body [ ${body} ]")
        )
        .throwException(new RuntimeException("Test!!!"))
        .to("stream:out");
  }
}
