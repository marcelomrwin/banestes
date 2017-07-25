package com.redhat;

import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath:spring/camel-context.xml"})
public class Application {

    /**
     * A main method to start this application.
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Bean
    ServletRegistrationBean servletRegistrationBean() {
	ServletRegistrationBean servlet = new ServletRegistrationBean(new CamelHttpTransportServlet(), "/camel/*");
	servlet.setName("CamelServlet");
	return servlet;
    }

}
