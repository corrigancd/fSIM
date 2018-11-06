package fSIM;

import java.util.ArrayList;

public class Areas {
	private ArrayList<Stand> allStands = new ArrayList<Stand>();
	
	private static Areas a;
	
	public static Areas getInstance() { //Singleton returning just one areas object
		if (a == null) {
			a = new Areas();
		}
		return a;		
	}
	
	public void addStand(Stand s) {
		allStands.add(s);
	}

	public  ArrayList<Stand> getAllStands() {
		return allStands;
	}


	
	
	
} //close class
