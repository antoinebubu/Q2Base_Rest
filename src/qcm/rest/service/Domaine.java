package qcm.rest.service;

import javax.ws.rs.Path;

import qcm.models.KDomaine;

@Path("/domaine")
public class Domaine extends CrudRestBase {

	public Domaine() {
		super();
		kobjectClass = KDomaine.class;
		displayName = "Domaine";
	}
}