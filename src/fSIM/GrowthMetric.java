package fSIM;

import java.util.LinkedList;

public class GrowthMetric {
    private String name;
      
    private LinkedList<Double> metricValues = new LinkedList<Double>();
    
    public GrowthMetric(String metricName) {
    	this.name = metricName;  	
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
      
}
