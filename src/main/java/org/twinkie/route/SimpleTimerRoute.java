package org.twinkie.route;

/**
 * Created by a.cirasole on 05/07/2017.
 */

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * A simple Camel route that triggers from a timer, calls a bean and prints to system out.
 * <p/>
 * Use <tt>@Component</tt> to make Camel auto detect this route when starting.
 */
@Component
public class SimpleTimerRoute extends RouteBuilder {


  @Override
  public void configure() throws Exception {
    from("timer:hello?period={{timer.period}}")
        .transform(method("myBean", "saySomething"))
        .to("stream:out");
  }

}
