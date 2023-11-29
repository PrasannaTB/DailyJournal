package com.example.DailyJournal;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.DailyJournal.domain.AppUser;
import com.example.DailyJournal.domain.AppUserRepository;
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
	public CommandLineRunner journalDemo(JournalRepository repository, MoodRepository mrepository,
			AppUserRepository urepository) {
		return (args) -> {
			log.info("save a couple of journals");

			mrepository.save(new Mood("Happy 😊"));
			mrepository.save(new Mood("Sad ☹️"));
			mrepository.save(new Mood("Excited 🤠"));
			mrepository.save(new Mood("Angry 👿"));
			mrepository.save(new Mood("Peaceful 🕊️"));

			repository.save(new Journal(LocalDate.of(2023, 12, 23), "Today is my birthday.", "I am 20 years old.",
					mrepository.findByName("Excited 🤠").get(0)));
			repository.save(new Journal(LocalDate.of(2023, 12, 8), "Today is new year.", "I had tasty food.",
					mrepository.findByName("Happy 😊").get(0)));
			repository.save(new Journal(LocalDate.of(2023, 12, 15), "Today is valentines.", "I am wearing red.",
					mrepository.findByName("Angry 👿").get(0)));

			AppUser user1 = new AppUser("user", "$2a$10$YM0ZZtUtkdGVbaVw/NgxtOIjSawkWmxkLzYgt.Mv2vNLrfr9GZBFe", "USER");
			AppUser user2 = new AppUser("admin", "$2a$10$FMVXp4WgAdUyS.2P2DdK/OYSRUvvK5CucWKCdAdfO5XxLu6pH7VCK",
					"ADMIN");

			//urepository.save(user1);
			//urepository.save(user2);

			log.info("fetch all journals");
			for (Journal journal : repository.findAll()) {
				log.info(journal.toString());
			}
		};

	}

}
