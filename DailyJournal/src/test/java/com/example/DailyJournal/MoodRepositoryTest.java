package com.example.DailyJournal;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.DailyJournal.domain.Mood;
import com.example.DailyJournal.domain.MoodRepository;

@SpringBootTest(classes = DailyJournalApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MoodRepositoryTest {
	@Autowired
	private MoodRepository mrepository;

	@Test
	public void createNewMood() {
		Mood mood = new Mood("Sleepy");
		mrepository.save(mood);

		Mood savedMood = mrepository.findByName("Sleepy").get(0);

		assertThat(savedMood).isNotNull();
		assertThat(savedMood.getName()).isEqualTo("Sleepy");
	}

	@Test
	public void deleteNewMood() {
		Mood mood = new Mood("Blissful");
		mrepository.save(mood);
		mrepository.delete(mood);

		List<Mood> deletedMood = mrepository.findByName("Blissful");

		assertThat(deletedMood).isEmpty();
	}

	@Test
	public void findMoodByName() {
		List<Mood> mood = mrepository.findByName("Angry 👿");

		assertThat(mood).hasSize(1);
		assertThat(mood.get(0).getName()).isEqualTo("Angry 👿");
	}

}
