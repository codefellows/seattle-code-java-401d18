package com.codefellows.salmonCookies;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SalmonCookiesApplicationTests {

	@Autowired
	MockMvc mockMvc;

	@Test
	void testHomePage() throws Exception {
		mockMvc.perform(get("/"))
				.andDo(print())
				.andExpect(content().string(containsString("<h1>Cookie Stands</h1>")))
				.andExpect(status().isOk());
	}

	@Disabled
	@Test
	void testPostCookieStand() throws Exception {
		mockMvc.perform(
						post("/create")
								.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
								.param("name", "Indi's")
								.param("averageCookiesPerDay", String.valueOf(500))
				)
				.andExpect(redirectedUrl("/"))
				.andExpect(status().isFound());
	}

	@Test
	void contextLoads() {
	}

}
