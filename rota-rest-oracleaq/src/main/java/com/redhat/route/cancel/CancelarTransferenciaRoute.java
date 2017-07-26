package com.redhat.route.cancel;

import javax.ws.rs.core.Response;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.gson.GsonDataFormat;
import org.springframework.stereotype.Component;

import com.redhat.dto.TransferenciaResponse;
@Component
public class CancelarTransferenciaRoute extends RouteBuilder {
    protected GsonDataFormat gson = new GsonDataFormat();
    @Override
    public void configure() throws Exception {
	from("direct:criarTransferenciaRoute").routeId("criarConviteRoute").process(new Processor() {

	    @Override
	    public void process(Exchange exchange) throws Exception {
		TransferenciaResponse body = exchange.getIn().getBody(TransferenciaResponse.class);
		
		StringBuilder sb = new StringBuilder("-- Cancelamento de transferência |");
		sb.append(body.toString());sb.append("| --");
		exchange.getOut().setBody(sb.toString());
	    }
	}).multicast().stopOnException().to("direct:notificarOracleAQ", "direct:cancelarTransferenciaOutputRoute").end();

	from("direct:cancelarTransferenciaOutputRoute").routeId("cancelarTransferenciaOutputRoute")
		.convertBodyTo(String.class, "UTF-8").log("${body}")
		.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(Response.Status.OK.getStatusCode()));

    }

}
