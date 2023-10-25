package com.example.DailyJournal.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Mood {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long moodId;
	private String name;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "mood")
	private List <Journal> journals;
	
	public Mood() {}

	public Mood(String name) {
		super();
		this.name = name;
	}

	public Long getMoodId() {
		return moodId;
	}

	public void setMoodId(Long moodId) {
		this.moodId = moodId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Journal> getJournals() {
		return journals;
	}

	public void setJournals(List<Journal> journals) {
		this.journals = journals;
	}
	
	
	
	

}
