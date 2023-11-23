package com.example.DailyJournal.domain;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OrderBy;

@Entity
public class Journal {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private Long id;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	private String description;
	private String note;

	@ManyToOne
	@JoinColumn(name = "mood")
	@OrderBy("date ASC")
	private Mood mood;

	public Journal() {
		super();

	}

	public Journal(LocalDate date, String description, String note, Mood mood) {
		super();
		this.date = date;
		this.description = description;
		this.note = note;
		this.mood = mood;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
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
