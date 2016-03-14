package qcm.rest.service;

import java.lang.reflect.InvocationTargetException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
import qcm.models.KQuestionnaire;
import qcm.models.KUtilisateur;

@Path("/quiz")
public class Questionnaire extends RestBase {
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getOne(@PathParam("id") int id){
		KQuestionnaire quiz=KoSession.kloadOne(KQuestionnaire.class, id);
		Gson gson = new Gson();
		return gson.toJson(quiz);
	}
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAll() {
		KListObject<KQuestionnaire> questionnaires = KoSession.kloadMany(KQuestionnaire.class);
		String result = gson.toJson(questionnaires.asAL());
		return result;
	}
	
	
	
	
	
	
	
	@PUT
	@Path("/add")
	@Consumes("application/x-www-form-urlencoded")
	public String addOne(MultivaluedHashMap<String, String> formParams) {
	KQuestionnaire QUIZ = new KQuestionnaire();
	String message = "{Insertion reussie}";
	
	for(String param:formParams.keySet()) {
		try {
			String value = formParams.getFirst(param) + "";
			QUIZ.setAttribute(param, value, false);
		} catch (SecurityException | IllegalArgumentException | NoSuchFieldException | IllegalAccessException
				| InvocationTargetException e) {
		}
	}
	
	try {
		KoHttp.getDao(KQuestionnaire.class).create(QUIZ);
	} catch (Exception e) {
		message = "{\"erreur\":\"" + e.getMessage() + "\"}";
	}
	return message;
	}
}