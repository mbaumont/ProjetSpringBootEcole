package com.springboot.controller.form;

import javax.validation.constraints.NotEmpty;

public class NoteForm {
	private Integer id;
	@NotEmpty
	private String note;
	
	@NotEmpty
	private String avis;	
	
	@NotEmpty
	private String avancement;
	
	private Integer idprof;
	private Integer ideleve;
	private Integer idmatiere;
	private Integer idtrimestre;
	private Integer idclasse;
	@NotEmpty
	private String dateSaisie;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getAvis() {
		return avis;
	}
	public void setAvis(String avis) {
		this.avis = avis;
	}
	public String getAvancement() {
		return avancement;
	}
	public void setAvancement(String avancement) {
		this.avancement = avancement;
	}
	public Integer getIdprof() {
		return idprof;
	}
	public void setIdprof(Integer idprof) {
		this.idprof = idprof;
	}
	public Integer getIdeleve() {
		return ideleve;
	}
	public void setIdeleve(Integer ideleve) {
		this.ideleve = ideleve;
	}
	public Integer getIdmatiere() {
		return idmatiere;
	}
	public void setIdmatiere(Integer idmatiere) {
		this.idmatiere = idmatiere;
	}
	public Integer getIdtrimestre() {
		return idtrimestre;
	}
	public void setIdtrimestre(Integer idtrimestre) {
		this.idtrimestre = idtrimestre;
	}
	public Integer getIdclasse() {
		return idclasse;
	}
	public void setIdclasse(Integer idclasse) {
		this.idclasse = idclasse;
	}
	public String getDateSaisie() {
		return dateSaisie;
	}
	public void setDateSaisie(String dateSaisie) {
		this.dateSaisie = dateSaisie;
	}
}
