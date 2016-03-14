package qcm.rest.service;

import javax.ws.rs.Path;

import qcm.models.KQuestionnaire;

@Path("/quiz")
public class Questionnaire extends CrudRestBase {

	public Questionnaire() {
		super();
		kobjectClass = KQuestionnaire.class;
		displayName = "questionnaire";
	}

}