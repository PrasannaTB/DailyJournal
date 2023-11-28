package com.example.DailyJournal;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.DailyJournal.web.JournalController;

@SpringBootTest
class DailyJournalApplicationTests {

	@Autowired
	private JournalController controller;

	@Test
	void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
		{
		}
	}

}
