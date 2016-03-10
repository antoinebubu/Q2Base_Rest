package qcm.rest.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import net.ko.framework.KoSession;
import net.ko.kobject.KListObject;
import qcm.models.KQuestionnaire;

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
}