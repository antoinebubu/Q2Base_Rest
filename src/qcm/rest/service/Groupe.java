package qcm.rest.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.ko.framework.KoSession;
import qcm.models.KGroupe;

@Path("/groupe")
public class Groupe extends CrudRestBase {

	public Groupe() {
		super();
		kobjectClass = KGroupe.class;
		displayName = "groupe";
	}

	@GET
	@Path("/{id}/users")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUsers(@PathParam("id") int id) {
		KGroupe groupe = KoSession.kloadOne(KGroupe.class, id);
		String result = gson.toJson(groupe.getUsers().asAL());
		return result;
	}

	@GET
	@Path("/{id}/quiz")
	@Produces(MediaType.APPLICATION_JSON)
	public String getQuiz(@PathParam("id") int id) {
		KGroupe groupe = KoSession.kloadOne(KGroupe.class, id);
		String result = gson.toJson(groupe.getQuestionnaires().asAL());
		return result;
	}

}
