package org.twinkie;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.jms.ConnectionFactory;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.CamelContext;
import org.apache.camel.component.restlet.RestletComponent;
import org.restlet.Component;
import org.restlet.ext.spring.SpringServerServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Created by a.cirasole on 05/07/2017.
 */
@SpringBootApplication
public class SampleApplication {

  /**
   * A main method to start this application.
   */
  public static void main(String[] args) {
    System.out.println("Ciao");

    SpringApplication.run(SampleApplication.class, args);

    System.out.println("Ciao");

  }

  @Bean
  SpringServerServlet restletServlet() throws ServletException {
    return new RestletServlet();
  }

  @Bean
  Component restletComponent() {
    return new Component();
  }

//  @Bean
//  RestletComponent restletComponentService(Component restletComponent) {
//    return new RestletComponent(restletComponent);
//  }
//
//  @Bean
//  ActiveMQComponent activemq(CamelContext camelContext) {
//    return new ActiveMQComponent(camelContext);
//  }

  @Bean
  public JmsTransactionManager jmsTransactionManager (ConnectionFactory jmsConnectionFactory) {
    return new JmsTransactionManager(jmsConnectionFactory);
  }

  @Bean
  public ServletRegistrationBean restletRegistration(SpringServerServlet restletServlet) {
    ServletRegistrationBean registration = new ServletRegistrationBean(restletServlet);
    registration.addUrlMappings("/*");
    return registration;
  }

}
