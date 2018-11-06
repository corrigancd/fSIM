package fSIM;

import java.util.Collections;
import java.util.LinkedList;

public class GrowthYield implements java.io.Serializable {


	private static final long serialVersionUID = 5170141421229647397L;
	private String species, productivity, thin;

	private LinkedList<GrowthAge> yAges = new LinkedList<GrowthAge>();
	private LinkedList<GrowthMetric> growthMetrics = new LinkedList<GrowthMetric>();
	
	
	
	private GrowthAge[] lInterpolatedAges;
	private int[] growthAgeGaps;
	private GrowthMetric[] lInterpolatedGrowthMetrics;
	
	
	
	public GrowthYield(String s, String p, String thin) {
		this.species = s;
		this.productivity = p;
		this.thin = thin;
	}

	public void addGrowthAge(GrowthAge gA) {
		yAges.add(gA);	
	}

	//new growth metric object created based on name
	//public void createGrowthMetric(String name) {
	//	this.growthMetrics.add(new GrowthMetric(name)); 
	//}

	//adding metric value to growth metric based on metric name
	public void createAddMetricValue(double d, String gM) {

		boolean createdAlready = false;

		for (GrowthMetric g: growthMetrics) {
			if (g.getName().equals(gM)) { // adding growth metric to object if one has been created
				g.addMetricValue(d);
				createdAlready = true;
				break;
			}
			
			
		}

		if (createdAlready == false) {	//if after the iteration of all metric names, the variable remains false the created a new growthmetric
			this.growthMetrics.add(new GrowthMetric(gM,d));  // create growth Metric object if one hasn't been created 
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


	public GrowthAge[] getlInterpolatedAges() {
		return lInterpolatedAges;
	}

	public GrowthMetric[] getlInterpolatedGrowthMetrics() {
		return lInterpolatedGrowthMetrics;
	}

	public void yieldLinearInterpolation() {
		linearlyInterpolateAges();
	}


	


	private void linearlyInterpolateAges() {		
		//the last index in the ages array list is always the oldest
		int ageMax = yAges.get(yAges.size()-1).getAge();
		int mostRecentAge = 0;
		//The yield length is now known and arrays are used for storage efficiency as is the number of gaps required for linear interpolation
		lInterpolatedAges = new GrowthAge[ageMax+1];
		lInterpolatedGrowthMetrics = new GrowthMetric[ageMax+1];
		
		
		
		growthAgeGaps = new int[yAges.size()];
		//System.out.println("the length is: " + growthAgeGaps.length);
		
				for (int i = 0; i < lInterpolatedAges.length; i ++) {
					mostRecentAge = 0;
					int ageGapIndex = 0;
					for (GrowthAge gA: yAges) {

						//creating an array of age gaps
						growthAgeGaps[ageGapIndex] = gA.getAge() - mostRecentAge;
						mostRecentAge = gA.getAge();
						ageGapIndex += 1;
						
						if (gA.getAge() == i+1) {
							lInterpolatedAges[i] = gA;
						} else {
							lInterpolatedAges[i] = new GrowthAge(i+1);
						}
					}
				}
								
				int ageGap = 0; 
				int ageGapIndex = 0; // the current age gap to divide the metric by to add onto the array
				int nextGmAge = yAges.getFirst().getAge();
				
								
				
				//divide metric by the ageGap and add to the previous interpolated metric yield
				for (GrowthMetric gM: growthMetrics) {
//					gM.printMetricArray();		
					gM.setlInterpolatedMetricValues(ageMax+1); // set array of known length
					//System.out.println("The array length to iterate is: " + gM.getlInterpolatedMetricArray().length);
					
					for (int i = 0; i < gM.getlInterpolatedMetricArray().length; i ++) {
						//System.out.println(ageGapIndex + "here2");
							//System.out.println(gM.getName()+ " " + ageGap + " " + i + " " + ageGapIndex + " "+growthAgeGaps[ageGapIndex] + " " + yAges.get(0).getAge() + " "+ gM.getMetricValue(0));	
						if (i == nextGmAge) { // the first GM age is always equal 0 (in the dataset)
							System.out.println(ageGapIndex + " next gmAge: " + nextGmAge+  "here3" + gM.getMetricValue(28));
							gM.setInterpolatedMetricValue(i, gM.getMetricValue(ageGapIndex)); //new GrowthMetric(gM.getName());
								
							
							//System.out.println("The already present metric value is: "  + gM.getMetricValue(ageGapIndex));
							
							int currentGmAge = nextGmAge;
							
							System.out.println(growthAgeGaps.length);
							
							
							nextGmAge = nextGmAge + growthAgeGaps[ageGapIndex];
							
							
							ageGap = nextGmAge - currentGmAge;
							ageGapIndex += 1;					
					
						} else {
															//younger value to add to
							double annualMetricValue = (gM.getMetricValue(ageGapIndex-1) + 
							// this is the annual yield: 				older value						younger value				the gap between values	
															((gM.getMetricValue(ageGapIndex) - gM.getMetricValue(ageGapIndex-1)) / growthAgeGaps[ageGapIndex]) * 
																(i - (nextGmAge-ageGap))); /// growthAgeGaps[ageGapIndex];
							
							gM.setInterpolatedMetricValue(i, annualMetricValue);
							
							//System.out.println("The interpolated yield is: "  + gM.getInterpolatedMetricValue(i));
													
						}						
					}										
				}			
			}				




		//	private GrowthMetric[] lineralyInterpolateGrowthMetrics(LinkedList<GrowthMetric> growthMetrics) {
		//		//the last index in the  array list is always the oldest
		//		int ageMax = getMaxAge(ages);
		//		
		//		//The yield length is now known and arrays are used for storage efficiency
		//		GrowthMetric[] lInterpolatedAges = new GrowthMetric[ageMax];
		//		
		//		
		//
		//	}

				
				
	public LinkedList<GrowthAge> getUnInAges() {
		return yAges;
	}
	public LinkedList<GrowthMetric> getUnInGrowthMetrics() {
		return growthMetrics;
	}

	public GrowthAge[] getInterpolatedAges() {
		return lInterpolatedAges;
	}

	public GrowthMetric[] getInterpolatedGrowthMetrics() {
	    return lInterpolatedGrowthMetrics;	
	}
	
}