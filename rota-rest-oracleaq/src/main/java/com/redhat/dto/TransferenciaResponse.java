package com.redhat.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.RandomStringUtils;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TransferenciaResponse {

    public TransferenciaResponse() {
	super();
	this.data = new Date();
	this.authenticacao = RandomStringUtils.randomAscii(16);
    }

    public TransferenciaResponse(Transferencia transferencia) {
	this();
	this.transferencia = transferencia;	
    }
    
    @XmlElement
    private String authenticacao;
    @XmlElement
    private Date data;

    @XmlElement
    private Transferencia transferencia;

    public String getAuthenticacao() {
	return authenticacao;
    }

    public void setAuthenticacao(String authenticacao) {
	this.authenticacao = authenticacao;
    }

    public Date getData() {
	return data;
    }

    public void setData(Date data) {
	this.data = data;
    }

    public Transferencia getTransferencia() {
	return transferencia;
    }

    public void setTransferencia(Transferencia transferencia) {
	this.transferencia = transferencia;
    }

    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("TransferenciaResponse [");
	if (authenticacao != null)
	    builder.append("authenticacao=").append(authenticacao).append(", ");
	if (data != null)
	    builder.append("data=").append(data).append(", ");
	if (transferencia != null)
	    builder.append("transferencia=").append(transferencia);
	builder.append("]");
	return builder.toString();
    }
}
