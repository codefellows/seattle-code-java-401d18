package com.codefellows.salmonCookies2;

import com.codefellows.salmonCookies2.models.SalmonCookiesStore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

// Comment this out for now since it is an integration test and we'll be talking about that later
@SpringBootTest
@AutoConfigureMockMvc
class SalmonCookies2ApplicationTests {

	@Autowired
    MockMvc mockMvc;

	@Test
	public void Test_salmon_cookies_store_unit_test()
	{
		SalmonCookiesStore sut = new SalmonCookiesStore("Test Store Name", 10);

		assertEquals("Test Store Name", sut.getName());
		assertEquals(10, sut.getAverageCookiesPerDay());
	}

	@Test
	public void Test_salmon_cookie_app() throws Exception
	{
		mockMvc.perform(get("/"))
				.andDo(print())  // shows output on server console for debugging
				.andExpect(content().string(containsString("<h1>Salmon Cookie Store Manager</h1>")))
				.andExpect(status().isOk());
		// Stretch goal: test POST, DELETE, etc. with something that looks like this
		/*mockMvc.perform(post("/")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
				.param("title", "Album Title"))
				.andExpect(content().string(containsString("the response text")));*/
	}
}
