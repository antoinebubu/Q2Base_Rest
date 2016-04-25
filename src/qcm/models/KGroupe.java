package qcm.models;

import com.google.gson.annotations.Expose;

import net.ko.kobject.KListObject;
import net.ko.persistence.annotation.Entity;
import net.ko.persistence.annotation.Table;

/**
 * Classe KGroupe
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "groupe")
public class KGroupe extends KRestObject {
	@Expose
	private String code;
	@Expose
	private String libelle;
	@Expose
	private KListObject<KQuestionnaire> questionnaires;
	@Expose
	private KListObject<KUtilisateur> utilisateurs;
	@Expose
	private KListObject<KGroupe_questionnaire> groupe_questionnaires;
	@Expose
	private KListObject<KGroupe_utilisateur> groupe_utilisateurs;

	public KGroupe() {
		super();

		hasMany(KGroupe_utilisateur.class);
		hasMany(KGroupe_questionnaire.class);
		hasAndBelongsToMany(KGroupe_questionnaire.class, KQuestionnaire.class);
		hasAndBelongsToMany(KGroupe_utilisateur.class, KUtilisateur.class);
	}

	/**
	 * return the value of code
	 * 
	 * @return code
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 * return the value of libelle
	 * 
	 * @return libelle
	 */
	public String getLibelle() {
		return this.libelle;
	}

	/**
	 * return the value of groupe_questionnaires
	 * 
	 * @return groupe_questionnaires
	 */
	public KListObject<KGroupe_questionnaire> getGroupe_questionnaires() {
		return this.groupe_questionnaires;
	}

	/**
	 * return the value of groupe_utilisateurs
	 * 
	 * @return groupe_utilisateurs
	 */
	public KListObject<KGroupe_utilisateur> getGroupe_utilisateurs() {
		return this.groupe_utilisateurs;
	}

	/**
	 * set the value of code
	 * 
	 * @param aCode
	 */
	public void setCode(String aCode) {
		this.code = aCode;
	}

	/**
	 * set the value of libelle
	 * 
	 * @param aLibelle
	 */
	public void setLibelle(String aLibelle) {
		this.libelle = aLibelle;
	}

	/**
	 * set the value of groupe_questionnaires
	 * 
	 * @param aGroupe_questionnaires
	 */
	public void setGroupe_questionnaires(KListObject<KGroupe_questionnaire> aGroupe_questionnaires) {
		this.groupe_questionnaires = aGroupe_questionnaires;
	}

	/**
	 * set the value of groupe_utilisateurs
	 * 
	 * @param aGroupe_utilisateurs
	 */
	public void setGroupe_utilisateurs(KListObject<KGroupe_utilisateur> aGroupe_utilisateurs) {
		this.groupe_utilisateurs = aGroupe_utilisateurs;
	}

	public KListObject<KQuestionnaire> getQuestionnaires() {
		return questionnaires;
	}

	public KListObject<KUtilisateur> getUsers() {
		return utilisateurs;
	}

	@Override
	public String toString() {
		return " [libelle] = " + libelle + " [code] = " + code;
	}
}