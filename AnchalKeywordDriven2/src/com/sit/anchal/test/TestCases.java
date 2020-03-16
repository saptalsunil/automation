package com.sit.anchal.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.sit.anchal.keyword.engine.KeywordEngine;

public class TestCases {
	public KeywordEngine keywordEngine;

	@Test(priority=0)
	public void loginTest() throws IOException, Throwable {
		keywordEngine = new KeywordEngine();
		keywordEngine.startExecution("Login");
		Thread.sleep(5000);
	}

     @Test(priority=1)
	public void addBeneficiary() throws IOException {
				keywordEngine.startExecution("add beneficiary");
	}                            
}
