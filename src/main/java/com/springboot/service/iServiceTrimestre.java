package com.springboot.service;

import java.util.List;

import com.springboot.bean.Trimestre;

public interface iServiceTrimestre {
	public List<Trimestre> rechercheTrimestre();
	public Trimestre rechercheTrimestreId(final int id);
	public void creerTrimestre(final Trimestre Trimestre);
	public void modifierTrimestre(final Trimestre Trimestre);
	public void supprimerTrimestre(final Trimestre Trimestre);
}
