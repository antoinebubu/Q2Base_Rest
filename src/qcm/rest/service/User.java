package qcm.rest.service;

import java.math.BigInteger;
import java.security.SecureRandom;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.ko.framework.Ko;
import net.ko.framework.KoSession;
import net.ko.kobject.KListObject;
import qcm.models.KGroupe;
import qcm.models.KQuestionnaire;
import qcm.models.KUtilisateur;

@Path("/user")
public class User extends CrudRestBase {

	public User() {
		super();
		kobjectClass = KUtilisateur.class;
		displayName = "utilisateur";
	}

	@GET
	@Path("/{id}/groupes")
	@Produces(MediaType.APPLICATION_JSON)
	public String getGroupes(@PathParam("id") int id) {
		KUtilisateur user = KoSession.kloadOne(KUtilisateur.class, id);
		String result = gson.toJson(user.getGroupes().asAL());
		return result;
	}

	@GET
	@Path("/{id}/quiz")
	@Produces(MediaType.APPLICATION_JSON)
	public String getQuiz(@PathParam("id") int id) {
		Ko.setTempConstraintDeph(2);
		KUtilisateur user = KoSession.kloadOne(KUtilisateur.class, id);
		KListObject<KQuestionnaire> quizes = new KListObject<>(KQuestionnaire.class);
		KListObject<KGroupe> groupes = user.getGroupes();
		for (KGroupe gr : groupes) {
			quizes.addAll(gr.getQuestionnaires());
		}
		String result = gson.toJson(quizes.asAL());
		Ko.restoreConstraintDeph();
		return result;
	}

	@GET
	@Path("/{id}/realisations")
	@Produces(MediaType.APPLICATION_JSON)
	public String getRealisations(@PathParam("id") int id) {
		KUtilisateur user = KoSession.kloadOne(KUtilisateur.class, id);
		String result = gson.toJson(user.getRealisations().asAL());
		return result;
	}

	protected SecureRandom random;

	@POST
	@Path("/connect")
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.APPLICATION_JSON)
	public String connect(@FormParam("mail") String login, @FormParam("password") String password) {
		KUtilisateur user = KoSession.kloadOne(KUtilisateur.class, "mail='" + login + "'");
		String result = returnMessage("Login ou mot de passe invalides", true);
		if (user.isLoaded()) {
			if (user.getPassword().equals(password)) {
				random = new SecureRandom();
				String bi = new BigInteger(130, random).toString(32);
				request.getSession().setAttribute("token", bi);
				result = returnValue("Connexion réussie de " + user, "utilisateur", user, "\"connected\":true");
			}
		}
		return result;
	}

	@POST
	@Path("/recovery")
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.APPLICATION_JSON)
	public String recovery(@FormParam("mail") String mail) {
		String message = returnMessage("Mail invalide", false);
		KUtilisateur user = KoSession.kloadOne(KUtilisateur.class, "mail='" + mail + "'");
		if (user.isLoaded()) {
			message = returnMessage("Mail envoyé !", true);
		}
		return message;
	}

	@GET
	@Path("/checkConnected")
	@Produces(MediaType.APPLICATION_JSON)
	public String checkConnected() {
		String result = "false";
		Object token = request.getSession().getAttribute("token");
		if (token != null) {
			result = "true";
		}
		return result;
	}

}
