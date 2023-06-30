package com.codefellows.salmonCookies2;

import com.codefellows.salmonCookies2.models.SalmonCookiesStore;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Comment this out for now since it is an integration test and we'll be talking about that later
//@SpringBootTest
class SalmonCookies2ApplicationTests {

	@Test
	public void Test_salmon_cookies_store()
	{
		SalmonCookiesStore sut = new SalmonCookiesStore("Test Store Name", 10);

		assertEquals("Test Store Name", sut.getName());
		assertEquals(10, sut.getAverageCookiesPerDay());
	}
}
