package com.example.DailyJournal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class WebLayerTest {
	@Autowired
	private MockMvc mockMvc;

	@Test
	@WithMockUser(roles = "USER")
	public void testGetJournalEndpoint() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/diary"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("diary"))
				.andDo(MockMvcResultHandlers.print());
		;

	}

	@Test
	public void testLoginPage() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/login"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("login"))
				.andDo(MockMvcResultHandlers.print());
	}

}
