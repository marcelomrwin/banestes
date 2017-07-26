package com.redhat.route.monitor;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class PingMonitor extends RouteBuilder {

    @Override
    public void configure() throws Exception {
	from("direct:pingTransferenciaRoute").setBody(constant("PONG!")).convertBodyTo(String.class);
    }

}
