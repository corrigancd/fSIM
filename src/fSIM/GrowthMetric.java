package fSIM;

import java.util.LinkedList;

public class GrowthMetric implements java.io.Serializable {

	private static final long serialVersionUID = 1815846301841691467L;

	private String name;
      
    private LinkedList<Double> metricValues = new LinkedList<Double>();
    private double lInterpolatedMetricValues[];
    


	public GrowthMetric(String metricName, double d) {
    	this.name = metricName; 
    	this.metricValues.add(d);
    }   
    public String getName() {
    	return name;
    }   
    public void addMetricValue(double metricValue) {
    	metricValues.add(metricValue);
    }  
    public double getMetricValue(int index) {
    	return metricValues.get(index);
    }

    
    
    public double[] getlInterpolatedMetricArray() {
		return lInterpolatedMetricValues;
	}
	public void setlInterpolatedMetricValues(int arrayLength) {
		this.lInterpolatedMetricValues = new double[arrayLength];
	}  
	public void setInterpolatedMetricValue(int i, double metric) {
		lInterpolatedMetricValues[i] = metric;
	}
	public double getInterpolatedMetricValue(int i) {
		// TODO Auto-generated method stub
		return lInterpolatedMetricValues[i];
	}
	
	public void printMetricArray() {
		int index = 0;
		for (double d : metricValues) {
			System.out.println(d);
			index += 0;
		}
	}
  
	
}
