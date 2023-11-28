package com.example.DailyJournal.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface JournalRepository extends CrudRepository<Journal, Long> {
	List<Journal> findAllByOrderByDateDesc();

	List<Journal> findByNote(String note);

	List<Journal> findByDescription(String description);
}
