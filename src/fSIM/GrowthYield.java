package fSIM;

import java.util.LinkedList;

public class GrowthYield implements YieldAdapter {
	
	private String species;
	private String productivity;
	private String thin;
	private LinkedList<GrowthAge> yAges = new LinkedList<GrowthAge>();
	private LinkedList<GrowthMetric> growthMetrics = new LinkedList<GrowthMetric>();
	
	public GrowthYield(String s, String p, String thin) {
		this.species = s;
		this.productivity = p;
		this.thin = thin;
	}

	public void addGrowthAge(GrowthAge gA) {
		yAges.add(gA);	
	}
	
	//new growth metric object created based on name
	public void createGrowthMetric(String name) {
		this.growthMetrics.add(new GrowthMetric(name)); 
	}
	
	//adding metric value to growth metric based on metric name
	public void createUpdateMetricValue(double d, String gM) {
		
		boolean createdAlready = false;
		
		for (GrowthMetric g: growthMetrics) {
			if (g.getName().equals(gM)) { // adding growth metric to object if one has been created
				g.addMetricValue(d);
				createdAlready = true;
				break;
			}
		}
					
		if (createdAlready == false) {	
			this.growthMetrics.add(new GrowthMetric(gM));  // create growth Metric object if one hasn't been created 
		}
	}
		

	//adding metric value to growth metric based on metric name
	public void printMetricNames() {
		System.out.println("There are " + growthMetrics.size() + " growth metric objects and " + yAges.size() + " age objects in " + this.getYieldIdentifier());
	}
	
	
	public String getYieldIdentifier() {
		return species + productivity + thin;	
	}

	//retrieving metric from array of the corresponding yield age index to stand age 
	public double getMetric(int standAge, String metricName) { 
		double vol = 0;	
		for (int i = 0; i< yAges.size(); i++) {			
			if ((yAges.get(i).getAge()) == standAge) {
				for (GrowthMetric g: growthMetrics) {
					if (g.getName().equals(metricName)) {
						vol = g.getMetricValue(i);
					}
				}
				break;
			} 
		}
		return vol;	
	}

}
