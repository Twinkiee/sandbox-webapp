package org.twinkie;

import org.restlet.Component;
import org.restlet.ext.spring.SpringServerServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.connection.JmsTransactionManager;

import javax.jms.ConnectionFactory;
import javax.servlet.ServletException;

/**
 * Created by a.cirasole on 05/07/2017.
 */
@SpringBootApplication
public class SampleApplication {

  /**
   * A main method to start this application.
   */
  public static void main(String[] args) {

    SpringApplication.run(SampleApplication.class, args);
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
