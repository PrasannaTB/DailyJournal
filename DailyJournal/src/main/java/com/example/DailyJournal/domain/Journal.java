package com.example.DailyJournal.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Journal {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private Long id;
	private String date;
	private String description;
	private String note;
	
	@ManyToOne
	@JoinColumn(name = "mood")
	private Mood mood;

	public Journal() {
		super();
		
	}

	public Journal(String date, String description, String note, Mood mood) {
		super();
		this.date = date;
		this.description = description;
		this.note = note;
		this.mood = mood;
	}

	public String getDate() {
		return date;
	}
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Mood getMood() {
		return mood;
	}

	public void setMood(Mood mood) {
		this.mood = mood;
	}
	
	
	
	
	
	
	
}
