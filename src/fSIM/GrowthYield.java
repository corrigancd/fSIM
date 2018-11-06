package fSIM;

import java.util.Collections;
import java.util.LinkedList;

import org.apache.commons.math3.analysis.interpolation.LinearInterpolator;

public class GrowthYield implements java.io.Serializable {


	private static final long serialVersionUID = 5170141421229647397L;
	private String species, productivity, thin;

	//temp lists for use in linear interpolation
	private LinkedList<GrowthAge> yAges = new LinkedList<GrowthAge>();
	private int[] growthAgeGaps;
	
	

	//permanently stored as linearly interpolated yields
	private LinkedList<GrowthMetric> growthMetrics = new LinkedList<GrowthMetric>();
	private GrowthAge[] lInterpolatedAges;
	
	//private GrowthMetric[] lInterpolatedGrowthMetrics;

	public GrowthYield(String s, String p, String thin) {
		this.species = s;
		this.productivity = p;
		this.thin = thin;
	}

	public void addGrowthAge(GrowthAge gA) {
		yAges.add(gA);	
	}

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

	
	
	//retrieving metric from array of the corresponding yield age index to stand age //THIS IS OUTDATED AS IT IS NOW POSSIBLE TO INDEX THE INTERPOLATED ARRAYS DIRECTLY 
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
	
	public double getlInterpolatedGrowthMetric(String metricName, int age) {
		double vol = 0;
		System.out.println("The age is: " + age);
		
		for (GrowthMetric gM: growthMetrics) { 
			if (gM.getName().equals(metricName)) {
				vol = gM.getInterpolatedMetricValue(age);
				
			}
		}
		return vol;
	}

	public void yieldLinearInterpolation() {
		
		linearlyInterpolate();
		
		//nulling the temp values for storage
		yAges = null;
		growthAgeGaps = null;
	}

	private void linearlyInterpolate() {		
		//the last index in the ages array list is always the oldest
		int ageMax = yAges.get(yAges.size()-1).getAge();
		int mostRecentAge = 0;
		//The yield length is now known and arrays are used for storage efficiency as is the number of gaps required for linear interpolation
		
		growthAgeGaps = new int[yAges.size()];
		//System.out.println("the length is: " + growthAgeGaps.length);
		
				for (int i = 0; i < (ageMax+1); i ++) {
					mostRecentAge = 0;
					int ageGapIndex = 0;
					for (GrowthAge gA: yAges) {

						//creating an array of age gaps
						growthAgeGaps[ageGapIndex] = gA.getAge() - mostRecentAge;
						mostRecentAge = gA.getAge();
						ageGapIndex += 1;
					}
				}
					
				//divide metric by the ageGap and add to the previous interpolated metric yield
				for (GrowthMetric gM: growthMetrics) {			
					
					int ageGap = 0; 
					int ageGapIndex = 0; // the current age gap to divide the metric by to add onto the array
					int nextGmAge = yAges.getFirst().getAge();
					
					gM.setlInterpolatedMetricValues(ageMax+1); // set array of known length
					
					for (int i = 0; i < gM.getlInterpolatedMetricArray().length; i ++) {
						System.out.println(gM.getName() + " " + nextGmAge + " " + i);
						if (i == nextGmAge) { // the first GM age is always equal 0 (in the dataset)
							
							gM.setInterpolatedMetricValue(i, gM.getMetricValue(ageGapIndex)); 
																		
						 if (yAges.getLast().getAge() > nextGmAge) { //only iterate indexing variables if there is another index to move to, this avoids index out of bounds issues
							int currentGmAge = nextGmAge;
							ageGapIndex += 1;
							nextGmAge = nextGmAge + growthAgeGaps[ageGapIndex];
							ageGap = nextGmAge - currentGmAge;		
							}
						 
						} else {
							 // interpolated metric 	//younger value to add to
							double annualMetricValue = (gM.getMetricValue(ageGapIndex-1) + 
							// this is the annual yield: 				older value						younger value				the gap between values	
															((gM.getMetricValue(ageGapIndex) - gM.getMetricValue(ageGapIndex-1)) / growthAgeGaps[ageGapIndex]) * 
																(i - (nextGmAge-ageGap))); 
							
							gM.setInterpolatedMetricValue(i, annualMetricValue);													
						}						
					}										
				}
			}								
}