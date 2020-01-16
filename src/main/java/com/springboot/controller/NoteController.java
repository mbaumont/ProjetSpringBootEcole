package com.springboot.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.springboot.bean.Classe;
import com.springboot.bean.Eleve;
import com.springboot.bean.Matiere;
import com.springboot.bean.Note;
import com.springboot.bean.Professeur;
import com.springboot.bean.Trimestre;
import com.springboot.controller.form.ClasseForm;
import com.springboot.controller.form.NoteForm;
import com.springboot.service.iServiceClasse;
import com.springboot.service.iServiceEleve;
import com.springboot.service.iServiceMatiere;
import com.springboot.service.iServiceNote;
import com.springboot.service.iServiceNote;
import com.springboot.service.iServiceProfesseur;
import com.springboot.service.iServiceTrimestre;

@Controller
public class NoteController {
	@Autowired
	private iServiceClasse serviceclasse;
	@Autowired
	private iServiceProfesseur serviceprof;
	@Autowired
	private iServiceNote servicenote;
	@Autowired
	private iServiceMatiere servicematiere;
	@Autowired
	private iServiceTrimestre servicetrimestre;
	@Autowired
	private iServiceEleve serviceeleve;
		
	private Note convertForm(NoteForm noteform){
		Note pnote = new Note();
		Professeur prof = serviceprof.rechercheProfId(Integer.valueOf(noteform.getIdprof()));
		Matiere matiere = servicematiere.rechercheMatiereId(Integer.valueOf(noteform.getIdmatiere()));
		Classe classe = serviceclasse.rechercheClasseId(Integer.valueOf(noteform.getIdclasse()));
		Trimestre trimestre = servicetrimestre.rechercheTrimestreId(Integer.valueOf(noteform.getIdtrimestre()));
		Eleve eleve = serviceeleve.rechercheEleveId(Integer.valueOf(noteform.getIdeleve()));
		pnote.setId(noteform.getId());	
		pnote.setAvancement(Float.parseFloat(noteform.getAvancement()));
		pnote.setAvis(noteform.getAvis());
		pnote.setClasse(classe);
		pnote.setEleve(eleve);
		pnote.setMatiere(matiere);
		pnote.setNote(Integer.parseInt(noteform.getNote()));
		pnote.setTrimestre(trimestre);
		pnote.setProf(prof);
		try {
			pnote.setDateSaisie(convertDate(noteform.getDateSaisie()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pnote;
	}
	
	@GetMapping("/SupprimerNotes/{id}")
	public String getSupprimer(@PathVariable final Integer id,Model pmodel) {
		Note note = servicenote.rechercheNoteId(id);
		if(note != null) {
			servicenote.supprimerNote(note);
		}
		return this.getAffiche(pmodel);
	}
	
	@GetMapping("/afficherCreerNotes")
	public String getAffiche(Model pmodel) {
		List<Note> lnote = servicenote.rechercheNote();
		List<Professeur> lprof = serviceprof.rechercheProf();
		List<Matiere> lmatiere = servicematiere.rechercheMatiere();
		List<Trimestre> ltrimestre = servicetrimestre.rechercheTrimestre();
		List<Classe> lclasse = serviceclasse.rechercheClasse();
		List<Eleve> leleve = serviceeleve.rechercheEleve();
		pmodel.addAttribute("listematiere", lmatiere);
		pmodel.addAttribute("listeprof", lprof);		
		pmodel.addAttribute("listeclasse", lclasse);
		pmodel.addAttribute("listetrimestre", ltrimestre);
		pmodel.addAttribute("listeeleve", leleve);
		pmodel.addAttribute("listenote",lnote);
		pmodel.addAttribute("action", "CreerNotes");	
		NoteForm noteform = new NoteForm();
		noteform.setId(0);
		pmodel.addAttribute("noteform",noteform);

		return "notes";
		}
		
	@GetMapping("/afficherModifierNotes/{id}")
	public String getAfficheMod(@PathVariable final Integer id,Model pmodel) {
		Note pnote = servicenote.rechercheNoteId(id);
		List<Professeur> lprof = serviceprof.rechercheProf();
		List<Matiere> lmatiere = servicematiere.rechercheMatiere();
		List<Trimestre> ltrimestre = servicetrimestre.rechercheTrimestre();
		List<Classe> lclasse = serviceclasse.rechercheClasse();
		List<Eleve> leleve = serviceeleve.rechercheEleve();
		List<Note> lnote = servicenote.rechercheNote();
		pmodel.addAttribute("listematiere", lmatiere);
		pmodel.addAttribute("listeprof", lprof);		
		pmodel.addAttribute("listeclasse", lclasse);
		pmodel.addAttribute("listetrimestre", ltrimestre);
		pmodel.addAttribute("listeeleve", leleve);
		pmodel.addAttribute("listenote",lnote);
		pmodel.addAttribute("action", "ModifierNotes");
		if(pmodel.containsAttribute("noteform") == false) {
			NoteForm noteform = new NoteForm();
			noteform.setId(pnote.getId());
			noteform.setIdprof(pnote.getProf().getId());
			noteform.setIdmatiere(pnote.getMatiere().getId());
			noteform.setIdclasse(pnote.getClasse().getId());
			noteform.setIdtrimestre(pnote.getTrimestre().getId());
			noteform.setIdeleve(pnote.getEleve().getId());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String dateSaisie = sdf.format(pnote.getDateSaisie());  
			noteform.setDateSaisie(dateSaisie);
			noteform.setNote(String.valueOf(pnote.getNote()));
			noteform.setAvis(pnote.getAvis());
			noteform.setAvancement(String.valueOf(pnote.getAvancement()));		
			pmodel.addAttribute("noteform", noteform);
		}
		return "notes";
	}
	@PostMapping("/CreerNotes")
	public String ajoutNote( 
			@Valid @ModelAttribute(name = "noteform") NoteForm noteform,
			BindingResult presult,
			Model pmodel)
	{
		System.err.println(presult);
		if(!presult.hasErrors()) {
			try
			{
				Note pnote = convertForm(noteform);
				servicenote.creerNote(pnote);
			}
			catch(Exception e) {
				System.err.println(e.getMessage());
			}
		}
		return this.getAffiche(pmodel);
	}

	@PostMapping("/ModifierNotes")
	public String modifieNote( 
			@Valid @ModelAttribute(name = "noteform") NoteForm noteform,
			 BindingResult presult,
			Model pmodel)
	{
		if(!presult.hasErrors()) {
			try
			{
				Note pnote= convertForm(noteform);
				servicenote.creerNote(pnote);
			}
			catch(Exception e) {
				System.err.println(e.getMessage());
			}
		}
		return this.getAffiche(pmodel);
	}	
	public Date convertDate(String date) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dateNaissance = sdf.parse(date);
		return dateNaissance;
	}
}
