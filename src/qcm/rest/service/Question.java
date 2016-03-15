package qcm.rest.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import net.ko.framework.KoSession;
import net.ko.kobject.KListObject;
import qcm.models.KQuestion;

@Path("/question")
public class Question extends CrudRestBase {

	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getOne(@PathParam("id") int id){
		KQuestion question=KoSession.kloadOne(KQuestion.class, id);
		Gson gson = new Gson();
		return gson.toJson(question);
	}
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAll() {
		KListObject<KQuestion> questions = KoSession.kloadMany(KQuestion.class);
		String result = gson.toJson(questions.asAL());
		return result;
	}	
	
	public Question() {
		super();
		kobjectClass = KQuestion.class;
		displayName = "question";
	}

}