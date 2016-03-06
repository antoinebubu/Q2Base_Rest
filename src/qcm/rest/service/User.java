package qcm.rest.service;

import java.lang.reflect.InvocationTargetException;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;

import com.google.gson.Gson;

import net.ko.framework.KoHttp;
import net.ko.kobject.KListObject;
import qcm.models.KUtilisateur;

@Path("/user")
public class User extends RestBase {

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAll() {
		KListObject<KUtilisateur> users = KoHttp.getDao(KUtilisateur.class).readAll();
		Gson gson = new Gson();
		return gson.toJson(users.asAL());
	}
	
	@PUT
	@Path("/add")
	@Consumes("application/x-www-form-urlencoded")
	public String addOne(MultivaluedHashMap<String, String> formParams) {
		KUtilisateur user = new KUtilisateur();
		String message = "{\"message\":\"Insertion réussie\"}";
		for(String param:formParams.keySet()) {
			try {
				String value = formParams.getFirst(param) + "";
				value = value.replaceFirst("^\\[(.*)\\]$", "$1");
				user.setAttribute(param, value, false);
			} catch (SecurityException | IllegalArgumentException | NoSuchFieldException | IllegalAccessException
					| InvocationTargetException e) {
			}
		}
		
		try {
			KoHttp.getDao(KUtilisateur.class).create(user);
		} catch (Exception e) {
			message = "{\"erreur\":\"" + e.getMessage() + "\"}";
		}
		return message;
	}
}
