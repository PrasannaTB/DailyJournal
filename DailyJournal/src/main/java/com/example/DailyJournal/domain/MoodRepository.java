package com.example.DailyJournal.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface MoodRepository extends CrudRepository<Mood, Long>{
	List<Mood> findByName(String name);
}
