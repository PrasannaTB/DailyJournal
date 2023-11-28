package com.example.DailyJournal;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.DailyJournal.domain.AppUser;
import com.example.DailyJournal.domain.AppUserRepository;

@SpringBootTest(classes = DailyJournalApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class AppUserRepositoryTest {

	@Autowired
	private AppUserRepository urepository;

	@Test
	public void testCreateAppUser() {

		AppUser newUser = new AppUser("testUser", "$2a$10$JgEUTPsMv6TwBLA5ox.youDJu90cKByVM1K9C5Dvc7xP.BUqc8MO.",
				"TUSER");
		urepository.save(newUser);
		// password: testuser
		// Verify that the user was saved and has an ID
		assertThat(newUser.getId()).isNotNull();
	}

	@Test
	public void testDeleteAppUser() {

		AppUser newUser1 = new AppUser("testUser1", "$2a$10$Ojz.ez8SSTB6Kf5eeFXKM..fKgJcKIrCzEXvvpvHuSGtHm/tVlAfq",
				"T1USER");
		urepository.save(newUser1);

		urepository.delete(newUser1);

		java.util.Optional<AppUser> deletedUser = urepository.findById(newUser1.getId());
		assertThat(deletedUser).isEmpty();

		// password: finaltest
		AppUser user1 = urepository.findByUsername("testUser1");
		assertThat(user1).isNull();
	}

	@Test
	public void testFindByUsername() {

		AppUser foundUser = urepository.findByUsername("user");

		// Verify that the user with the specified username was found
		assertThat(foundUser).isNotNull();
		assertThat(foundUser.getUsername()).isEqualTo("user");
	}

}
