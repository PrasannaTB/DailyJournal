package com.example.DailyJournal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.DailyJournal.domain.Journal;
import com.example.DailyJournal.domain.JournalRepository;
import com.example.DailyJournal.domain.Mood;
import com.example.DailyJournal.domain.MoodRepository;


@SpringBootApplication
public class DailyJournalApplication {
	private static final Logger log = LoggerFactory.getLogger(DailyJournalApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DailyJournalApplication.class, args);
	}

	@Bean
	public CommandLineRunner journalDemo(JournalRepository repository, MoodRepository mrepository) {
		return (args) -> {
			log.info("save a couple of journals");

			mrepository.save(new Mood("Happy"));
			mrepository.save(new Mood("Sad"));
			mrepository.save(new Mood("Excited"));
			mrepository.save(new Mood("Angry"));
			mrepository.save(new Mood("Peaceful"));

			repository.save(new Journal("Nov 8", "Today is my birthday.", "I am 20 years old.",
					mrepository.findByName("Excited").get(0)));
			repository.save(new Journal("Jan 1", "Today is new year.", "I had tasty food.",
					mrepository.findByName("Happy").get(0)));
			repository.save(new Journal("Feb 14", "Today is valentines.", "I am wearing red.",
					mrepository.findByName("Excited").get(0)));

			log.info("fetch all journals");
			for (Journal journal : repository.findAll()) {
				log.info(journal.toString());
			}
		};

	}

}
