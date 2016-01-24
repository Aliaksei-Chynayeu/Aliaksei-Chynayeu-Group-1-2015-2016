package com.epam.minsk.creators;

import java.util.ArrayList;
import java.util.List;

/**Creates List with objects 
 */
public class ObjectCreator {
	
	/** Time for creating objects */
	public static final int WORK_TIME = 30000;
		
	public static void create() {
		while(true) {
			List<Object> objectList = new ArrayList<Object>(); 
			for (int i = 0; i < WORK_TIME; i++) {
				objectList.add(new Object());
			}
			objectList = null;
		}
	}
}
