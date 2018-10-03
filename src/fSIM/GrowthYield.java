package fSIM;

import java.util.ArrayList;

public class GrowthYield implements YieldAdapter {
	
	private String species;
	private int productivity;
	private String thin;
	private ArrayList<GrowthAge> yAges = new ArrayList<GrowthAge>();
	
	public GrowthYield(String s, int p, String thin) {
		this.species = s;
		this.productivity = p;
		this.thin = thin;	
	}

	public void addGrowthAge(GrowthAge gA) {
		yAges.add(gA);	
	}
	
	public String getYieldIdentifier() {
		return species + productivity + thin;
		
	}

	public double getCFVol(int standAge) {
		double vol = 0;
		for (GrowthAge a: yAges) {
			if (standAge == a.getAge()) {
				vol = a.getCFVol();
				break;
			} 
		}
		return vol;	
	}

}
