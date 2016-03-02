package com.epam.minsk.exception;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class MongoDBExceptionTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	 public void testException() throws MongoDBException {
		expectedException.expect(MongoDBException.class);
		expectedException.expectMessage("Exception message");
		
		throw new MongoDBException("Exception message", new Throwable());
	 }
}
