package qcm.models;

import com.google.gson.annotations.Expose;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
import net.ko.persistence.annotation.Entity;
import net.ko.persistence.annotation.Table;


/**
* Classe KQuestion
*/
@SuppressWarnings("serial")
@Entity
@Table(name="question")
public class KQuestion extends KObject {
	@Expose
	private int idQuestionnaire;
	@Expose
	private String libelle;
	

	@Expose
	private KListObject<KQuestionnaire> questionnaires;
	@Expose
	private KListObject<KReponse> reponses;
	@Expose
	private KQuestionnaire questionnaire;

	public KQuestion() {
		super();

		hasMany(KReponse.class);
		belongsTo(KQuestionnaire.class);
		hasMany(KQuestion.class);

	}
	/**
	 * return the value of idQuestionnaire
	 * @return idQuestionnaire
	 */
	public int getIdQuestionnaire(){
		return this.idQuestionnaire;
	}
	/**
	 * return the value of libelle
	 * @return libelle
	 */
	public String getLibelle(){
		return this.libelle;
	}
	/**
	 * return the value of reponses
	 * @return reponses
	 */
	public KListObject<KReponse> getReponses(){
		return this.reponses;
	}
	/**
	 * return the value of questionnaire
	 * @return questionnaire
	 */
	public KQuestionnaire getQuestionnaire(){
		return this.questionnaire;
	}

	/**
	 * set the value of idQuestionnaire
	 * @param aIdQuestionnaire
	 */
	public void setIdQuestionnaire(int aIdQuestionnaire){
		this.idQuestionnaire=aIdQuestionnaire;
	}
	/**
	 * set the value of libelle
	 * @param aLibelle
	 */
	public void setLibelle(String aLibelle){
		this.libelle=aLibelle;
	}
	/**
	 * set the value of reponses
	 * @param aReponses
	 */
	public void setReponses(KListObject<KReponse> aReponses){
		this.reponses=aReponses;
	}
	/**
	 * set the value of questionnaire
	 * @param aQuestionnaire
	 */
	public void setQuestionnaire(KQuestionnaire aQuestionnaire){
		this.questionnaire=aQuestionnaire;
	}
	@Override
	public String toString() {
		return " [libelle] = " + libelle+" [idQuestionnaire] = " + idQuestionnaire;
	}
}