package com.epam.minsk.xml;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class WrapperXMLTest {
	
	@Test
	public void test_getItems() {
		WrapperXML wrapper = new WrapperXML();
		
		assertNotNull(wrapper.getItems());
	}

}
