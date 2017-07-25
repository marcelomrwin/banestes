package com.redhat.ejb.jaxws;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ejb.LocalBean;

@WebService
@Stateless
@LocalBean
public class SimpleJaxWS {
    
    @WebMethod
    public String simpleWebService(@WebParam(name = "param") String param) {
	StringBuilder builder = new StringBuilder("Resultado do processamento para [");
	builder.append(param);
	builder.append("]");
	return builder.toString();
    }

}
