package com.redhat.ejb.jaxws;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.redhat.ejb.jaxws.model.User;

@WebService
@Stateless
@LocalBean
public class SimpleJaxWS {

    @PersistenceContext
    EntityManager entityManager;

    @WebMethod
    public String simpleWebService(@WebParam(name = "name") String name) {
	StringBuilder builder = new StringBuilder("Resultado do processamento para [");
	builder.append(name);
	builder.append("]");

	User u = new User();
	u.setName(name);
	entityManager.persist(u);

	builder.append("ID: ");
	builder.append(u.getId());

	return builder.toString();
    }

}
