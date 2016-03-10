package qcm.rest.service;

import java.lang.reflect.InvocationTargetException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;

import com.google.gson.Gson;

import net.ko.framework.KoHttp;
import net.ko.framework.KoSession;
import net.ko.kobject.KListObject;
import qcm.models.KGroupe;
import qcm.models.KUtilisateur;

@Path("/groupe")
public class Groupe extends RestBase {

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getOne(@PathParam("id") int id) {
		KGroupe groupe = KoSession.kloadOne(KGroupe.class, id);
		Gson gson = new Gson();
		return gson.toJson(groupe);
	}
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAll() {
		KListObject<KGroupe> groupes = KoHttp.getDao(KGroupe.class).readAll();
		Gson gson = new Gson();
		return gson.toJson(groupes.asAL());
	}
	
	/*@PUT
	@Path("/add")
	@Consumes("application/x-www-form-urlencoded")
	public String addOne(MultivaluedHashMap<String, String> formParams) {
		KGroupe groupe = new KGroupe();
		String message =  "{\"message\":\"Insertion réussie\"}";
		for(String param:formParams.keySet()) {
			try {
				String value = formParams.getFirst(param) + "";
				value = value.replaceFirst("^\\[(.*)\\]$", "$1");
				groupe.setAttribute(param, value, false);
			} catch (SecurityException | IllegalArgumentException | NoSuchFieldException | IllegalAccessException
					| InvocationTargetException e) {
			}
		}
		

		try {
			KoHttp.getDao(KGroupe.class).create(groupe);
		} catch (Exception e) {
			message = "{\"erreur\":\"" + e.getMessage() + "\"}";
		}
		
		return message;
	}*/
	
}
