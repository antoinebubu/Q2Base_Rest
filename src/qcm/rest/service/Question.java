package qcm.rest.service;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import com.google.gson.Gson;

import net.ko.framework.KoHttp;
import net.ko.framework.KoSession;
import net.ko.kobject.KListObject;
import qcm.models.KQuestion;
import qcm.models.KQuestionnaire;

@Path("/question")
public class Question extends RestBase {
	
	/**
	 * Get question/user/ :id
	 */
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getOne(@PathParam("id") int id){
		KQuestion question=KoSession.kloadOne(KQuestion.class, id);
		Gson gson = new Gson();
		return gson.toJson(question);
	}
	
//	 Get question/all
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAll() {
		KListObject<KQuestion> questions = KoSession.kloadMany(KQuestion.class);
		String result = gson.toJson(questions.asAL());
		return result;
	}	
	
//	 Get questionnaire/id
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAll() {
		KListObject<KQuestion> questions = KoSession.kloadMany(KQuestion.class);
		String result = gson.toJson(questions.asAL());
		return result;
	}	
	
	
//	Put question
	
	@PUT
	@Path("/")
	@Consumes("application/x-www-form-urlencoded")
	public String addOne(MultivaluedMap<String, Integer> formParams)
			throws SQLException {
		KQuestion question = new KQuestion();
		
		try {
			KoHttp.getDao(KQuestion.class).create(question);
			return "{\"message\": \"Insert\"}";
		} catch (Exception e) {
			return "erreur";
		}
		
		
		
	}
	
	
//	 Post question/ :id
	@POST
	@Path("/update")
	@Consumes("application/x-www-form-urlencoded")
	public String updateQuestion(MultivaluedMap<String, String> formParams)
			throws SQLException {
		int id = Integer.valueOf(formParams.get("id").get(0));
		KQuestion question = KoHttp.getDao(KQuestion.class).readById(id);
		
		if (!question.isLoaded())
			return "{\"message\": \"erreur au chargement de l'id " + String.valueOf(id) + "\"}";
		
		
		KoHttp.getDao(KQuestion.class).update(question);
		
		return "{\"message\": \"Update\"}";
	}
	
	
//	Delete question/ :id
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public String deleteQuestion(@PathParam("id") int id){
		KQuestion question = KoHttp.getDao(KQuestion.class).readById(id);
		if (!question.isLoaded())
			return "{\"message\": \"erreur au chargement de l'id " + String.valueOf(id) + "\"}";
		
		try {
			KoHttp.getDao(KQuestion.class).delete(question);
		} catch (SQLException e) {
			return "{\"message\": \" "+e.getMessage()+"\"}";
		}
		
		return "{\"message\": \"Delete\"}";
	}

//	Get question/ :id/reponses
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}/reponses")
	public String getAnswers(@PathParam("id") int id) {
		KQuestion question = KoSession.kloadOne(KQuestion.class, id);
		if(!question.isLoaded())
			return "{\"message\": \"erreur au chargement de l'id " + String.valueOf(id) + "\"}";
		
		return gson.toJson(question.getReponses());
	}
	
	
	
	
	
	

	
}