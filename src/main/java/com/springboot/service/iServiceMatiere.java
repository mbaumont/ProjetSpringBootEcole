package com.springboot.service;

import java.util.List;

import com.springboot.bean.Matiere;

public interface iServiceMatiere {
	public List<Matiere> rechercheMatiere();
	public Matiere rechercheMatiereId(final int id);
	public void creerMatiere(final Matiere pMatiere);
	public void modifierMatiere(final Matiere pMatiere);
	public void supprimerMatiere(final Matiere pMatiere);
}
