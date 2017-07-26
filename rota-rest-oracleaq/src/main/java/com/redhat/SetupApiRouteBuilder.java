package com.redhat;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class SetupApiRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {

	restConfiguration().bindingMode(RestBindingMode.json).dataFormatProperty("prettyPrint", "true")
		.apiContextPath(simple("{{camel.handler.api.path}}").getText()).component("servlet").enableCORS(true)
		.apiProperty("api.title", simple("{{camel.handler.api.title}}").getText())
		.host(simple("{{camel.handler.api.address}}").getText()).port(simple("{{camel.handler.api.port}}").getText())
		.apiProperty("api.description", simple("{{camel.handler.api.description}}").getText())
		.apiProperty("api.version", simple("{{camel.handler.api.version}}").getText())
		.contextPath(simple("{{camel.context.path}}").getText())
		.apiProperty("api.contact.name", simple("{{camel.handler.api.contact}}").getText()).apiProperty("cors", "true");

    }
}
