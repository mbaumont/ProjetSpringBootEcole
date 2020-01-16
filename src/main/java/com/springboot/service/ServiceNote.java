package com.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.bean.Note;
import com.springboot.dao.DaoNote;

@Service
public class ServiceNote implements iServiceNote {

	@Autowired
	DaoNote dao;
	
	@Transactional
	@Override
	public List<Note> rechercheNote() {
		return dao.findAll();
	}

	@Transactional
	@Override
	public Note rechercheNoteId(int id) {
		return dao.findById(id).get();
	}
	@Transactional
	@Override
	public void creerNote(Note pNote) {
		dao.save(pNote);
		
	}
	@Transactional
	@Override
	public void modifierNote(Note pNote) {
		dao.save(pNote);
		
	}
	@Transactional
	@Override
	public void supprimerNote(Note pNote) {
		dao.deleteById(pNote.getId());
		
	}

}
