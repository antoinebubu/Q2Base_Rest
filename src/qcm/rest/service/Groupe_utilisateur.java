package qcm.rest.service;

import javax.ws.rs.Path;

import qcm.models.KGroupe_utilisateur;

@Path("/GroupeUtilisateur")
public class Groupe_utilisateur extends CrudRestBase {

	public Groupe_utilisateur() {
		super();
		kobjectClass = KGroupe_utilisateur.class;
		displayName = "GroupeUtilisateur";
	}

}