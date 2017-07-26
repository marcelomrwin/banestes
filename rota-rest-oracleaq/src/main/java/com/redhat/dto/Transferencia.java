package com.redhat.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Transferencia {

    @XmlElement
    private String contaDebito;
    @XmlElement
    private String contaCredito;
    @XmlElement
    private Double valor;
    @XmlElement
    private Date data;

    public String getContaDebito() {
	return contaDebito;
    }

    public void setContaDebito(String contaDebito) {
	this.contaDebito = contaDebito;
    }

    public String getContaCredito() {
	return contaCredito;
    }

    public void setContaCredito(String contaCredito) {
	this.contaCredito = contaCredito;
    }

    public Double getValor() {
	return valor;
    }

    public void setValor(Double valor) {
	this.valor = valor;
    }

    public Date getData() {
	return data;
    }

    public void setData(Date data) {
	this.data = data;
    }

    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("Transferencia [");
	if (contaDebito != null)
	    builder.append("contaDebito=").append(contaDebito).append(", ");
	if (contaCredito != null)
	    builder.append("contaCredito=").append(contaCredito).append(", ");
	if (valor != null)
	    builder.append("valor=").append(valor).append(", ");
	if (data != null)
	    builder.append("data=").append(data);
	builder.append("]");
	return builder.toString();
    }

}
