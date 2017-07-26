package com.redhat.route.create;

import javax.ws.rs.core.Response;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.gson.GsonDataFormat;
import org.springframework.stereotype.Component;

import com.redhat.dto.Transferencia;
import com.redhat.dto.TransferenciaResponse;
@Component
public class CriarTransferenciaRoute extends RouteBuilder {

    protected GsonDataFormat gson = new GsonDataFormat();

    @Override
    public void configure() throws Exception {
	from("direct:criarTransferenciaRoute").routeId("criarConviteRoute").process(new Processor() {

	    @Override
	    public void process(Exchange exchange) throws Exception {
		Transferencia body = exchange.getIn().getBody(Transferencia.class);
		TransferenciaResponse tf = new TransferenciaResponse(body);
		exchange.getOut().setBody(tf);
	    }
	}).multicast().stopOnException().to("direct:notificarOracleAQ", "direct:criarTransferenciaOutputRoute").end();

	from("direct:criarTransferenciaOutputRoute").routeId("criarTransferenciaOutputRoute").marshal(gson)
		.convertBodyTo(String.class, "UTF-8").log("${body}")
		.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(Response.Status.CREATED.getStatusCode()));
    }

}
