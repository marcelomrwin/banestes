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

import com.redhat.ejb.jaxws.model.User;

@WebService
@Stateless
@LocalBean
public class SimpleJaxWS {

    @PersistenceContext
    EntityManager entityManager;

    @WebMethod
    public User addUser(@WebParam(name = "name") String name) {

	User u = new User();
	u.setName(name);
	entityManager.persist(u);

	return u;
    }

    @SuppressWarnings("unchecked")
    @WebMethod
    public List<User> getUsers() {
	Query query = entityManager.createQuery("select u from User u");
	List<User> usersList = query.getResultList();
	return usersList;
    }

    @WebMethod
    public void deleteUser(Long id) {
	User u = entityManager.find(User.class, id);
	entityManager.remove(u);
	entityManager.flush();
    }

}
