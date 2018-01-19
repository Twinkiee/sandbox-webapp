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
import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.restlet.Component;
import org.restlet.ext.spring.SpringServerServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by a.cirasole on 05/07/2017.
 */
@SpringBootApplication
public class SampleApplication extends WebMvcConfigurerAdapter {

  private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
      "classpath:/META-INF/resources/", "classpath:/resources/",
      "classpath:/static/", "classpath:/public/"};

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
  public JmsTransactionManager jmsTransactionManager(ConnectionFactory jmsConnectionFactory) {
    return new JmsTransactionManager(jmsConnectionFactory);
  }

  @Bean
  public ServletRegistrationBean restletRegistration(SpringServerServlet restletServlet) {
    ServletRegistrationBean registration = new ServletRegistrationBean(restletServlet);
    registration.addUrlMappings("/*");
    return registration;
  }

  @Bean
  public ServletRegistrationBean mainServletRegistration() {
    ServletRegistrationBean registration = new ServletRegistrationBean
        (new CamelHttpTransportServlet(), "/camel/*");
    registration.setName("CamelServlet");
    return registration;
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    if (!registry.hasMappingForPattern("/webjars/**")) {
      registry.addResourceHandler("/webjars/**").addResourceLocations(
          "classpath:/META-INF/resources/webjars/");
    }
    if (!registry.hasMappingForPattern("/**")) {
      registry.addResourceHandler("/**").addResourceLocations(
          CLASSPATH_RESOURCE_LOCATIONS);
    }
  }

}
