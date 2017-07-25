package com.redhat.ejb.jaxws;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.ejb.jaxws.model.User;

@WebService
@Stateless
@LocalBean
public class SimpleJaxWS {

    @PersistenceContext
    EntityManager entityManager;

    Logger logger = LoggerFactory.getLogger(getClass());

    @WebMethod
    public User addUser(@WebParam(name = "name") String name) {
	logger.info("received request to create user for name [" + name + "]");

	User u = new User();
	u.setName(name);
	entityManager.persist(u);

	return u;
    }

    @SuppressWarnings("unchecked")
    @WebMethod
    public List<User> getUsers() {
	logger.info("received request to list all users");

	Query query = entityManager.createQuery("select u from User u order by u.id");
	List<User> usersList = query.getResultList();
	return usersList;
    }

    @WebMethod
    public String deleteUser(@WebParam(name = "ocd") String cod) {
	logger.info("received request to delete user for id [" + cod + "]");

	User u = entityManager.find(User.class, new Long(cod));

	entityManager.remove(u);
	entityManager.flush();

	StringBuilder sb = new StringBuilder("User ");
	sb.append(u.getName());
	sb.append(" removed");

	return sb.toString();
    }

}
