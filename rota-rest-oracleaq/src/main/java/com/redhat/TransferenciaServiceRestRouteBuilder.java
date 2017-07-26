package com.redhat;

import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.Status.OK;

import javax.ws.rs.core.MediaType;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.stereotype.Component;

import com.redhat.dto.Transferencia;
import com.redhat.dto.TransferenciaResponse;

@Component
public class TransferenciaServiceRestRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
	rest("/transferencia").description("API de serviços para transferência").consumes(MediaType.APPLICATION_JSON)
		.produces(MediaType.APPLICATION_JSON)
		// post - realizar transferencia
		.post().description("Cria uma nova transferência").type(Transferencia.class).param().name("body").type(RestParamType.body)
		.description("Transferência para realizar").endParam().responseMessage().code(CREATED.getStatusCode())
		.message("Transferência realizada e notificada").responseModel(TransferenciaResponse.class).endResponseMessage()
		.to("direct:criarTransferenciaRoute")
		// delete - cancelar transferencia programada
		.delete().description("Cancelar uma transferência programada").type(TransferenciaResponse.class).param().name("body")
		.type(RestParamType.body).description("Transferência para cancelar").endParam().responseMessage().code(OK.getStatusCode())
		.message("Transferência cancelada e notificada").endResponseMessage().to("direct:cancelarTransferenciaRoute")
		// get - heath
		.get().description("Monitora a saúde do serviço").responseMessage().code(OK.getStatusCode()).message("Serviço funcionando")
		.endResponseMessage().to("direct:pingTransferenciaRoute");

    }

}
