package com.example.DailyJournal;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.DailyJournal.domain.Journal;
import com.example.DailyJournal.domain.JournalRepository;
import com.example.DailyJournal.domain.Mood;
import com.example.DailyJournal.domain.MoodRepository;

@SpringBootTest(classes = DailyJournalApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JournalRepositoryTest {
	@Autowired
	private JournalRepository repository;

	@Autowired
	private MoodRepository mrepository;

	@Test
	public void createNewJournal() {
		Mood mood = new Mood("Happy 😊");
		mrepository.save(mood);
		Journal journal = new Journal(LocalDate.of(2022, 11, 23), "Today is Baba's birthday.", "I miss him.", mood);
		repository.save(journal);
		assertThat(journal.getId()).isNotNull();
	}

	@Test
	public void deleteNewJournal() {
		List<Journal> journals = repository.findByNote("I miss him.");
		Journal journal = journals.get(0);
		repository.delete(journal);
		List<Journal> newJournal = repository.findByNote("I miss him.");
		assertThat(newJournal).hasSize(0);

	}

	@Test
	public void findByNoteShouldReturnJournal() {

		List<Journal> journals = repository.findByNote("I am 20 years old.");

		assertThat(journals).hasSize(1);
		assertThat(journals.get(0).getDescription()).isEqualTo("Today is my birthday.");
	}

	@Test
	public void findByDescription() {

		List<Journal> journals = repository.findByDescription("Today is my birthday.");

		assertThat(journals).hasSize(1);
		assertThat(journals.get(0).getNote()).isEqualTo("I am 20 years old.");
	}

}
