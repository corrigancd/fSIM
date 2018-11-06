package fSIM;

import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class yieldTestV2 {

	
	Areas a;
	ArrayList<GrowthYield> yYields;
	static GrowthYield gY;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//array list of all yields, likely to go into a 'Matching' class eventually
		ArrayList<GrowthYield> yYields = new ArrayList<GrowthYield>();
		
		gY = new GrowthYield("SS", "16", "thin"); //add if statement here when reading rows from table to only create a new growth yield object if it hasn't been invented yet
		yYields.add(gY);
		
		//creating dummy yield data for ss yc 16
		gY.addGrowthAge(new GrowthAge(  0	));
		gY.addGrowthAge(new GrowthAge(	7	));
		gY.addGrowthAge(new GrowthAge(	17	));
		gY.addGrowthAge(new GrowthAge(	22	));
		gY.addGrowthAge(new GrowthAge(	27	));
		gY.addGrowthAge(new GrowthAge(	32	));
		gY.addGrowthAge(new GrowthAge(	37	));
		gY.addGrowthAge(new GrowthAge(	42	));
		gY.addGrowthAge(new GrowthAge(	47	));
		gY.addGrowthAge(new GrowthAge(	52	));
		gY.addGrowthAge(new GrowthAge(	57	));
		gY.addGrowthAge(new GrowthAge(	62	));
		gY.addGrowthAge(new GrowthAge(	67	));
		gY.addGrowthAge(new GrowthAge(	72	));
		gY.addGrowthAge(new GrowthAge(	77	));



		gY.createAddMetricValue(0, "yCFVol");

		//creating dummy yield data for ss yc 16
		//gY.createUpdateMetricValue( 	0, "yCFVol"	);
		gY.createAddMetricValue( 	30.88235294, "yCFVol"	);
		gY.createAddMetricValue( 	75, "yCFVol");
		gY.createAddMetricValue( 	156, "yCFVol");
		gY.createAddMetricValue( 	206, "yCFVol");
		gY.createAddMetricValue(     270, "yCFVol");
		gY.createAddMetricValue( 	336, "yCFVol"	);
		gY.createAddMetricValue( 	395, "yCFVol"	);
		gY.createAddMetricValue( 	443, "yCFVol"	);
		gY.createAddMetricValue( 	478, "yCFVol"	);
		gY.createAddMetricValue( 	512, "yCFVol"	);
		gY.createAddMetricValue( 	543, "yCFVol"	);
		gY.createAddMetricValue( 	569, "yCFVol"	);
		gY.createAddMetricValue( 	590, "yCFVol"	);
		gY.createAddMetricValue( 	609, "yCFVol"	);
	

		
		//print out all stands
		//for (GrowthAge gA : gY.getAges()) {
		//	System.out.print(gA.toString()+ "\n");
		//}
		
		//inventory
		//creating stand objects, to be replaced with file read eventually
		Stand s1 = new Stand("SS",16,1, 27);
		Stand s2 = new Stand("SS",16,2, 20);
		Stand s3 = new Stand("SS",16,10, 21);

		//create areas section
		Areas a = Areas.getInstance(); 
		
		//add stands to areas
		a.addStand(s1);
		a.addStand(s2);
		a.addStand(s3);
		a.addStand(new Stand("SS",24,2, 20));
		
	
		//gY.yieldLinearInterpolation();
	
	}

	@Test
	public void checkUnInterpolatedVolume() {
		Assert.assertEquals(gY.getMetric(17,"yCFVol"), 75, 0.5);
	}
	@Test
	public void checkUnInterpolatedAgeCount() {
		Assert.assertEquals(gY.getUnInAges().size(), 15, 0.5);
	}
	@Test
	public void checkUnInterpolatedGmCount() {
		Assert.assertEquals(gY.getUnInGrowthMetrics().size(), 1, 0.5);
	}
	@Test
	public void checkInterpolatedAge() {
		Assert.assertEquals(gY.getInterpolatedAges()[4].getAge(), 5, 0.5);
	}
	@Test
	public void checkInterpolatedAgeCount() {
		Assert.assertEquals(gY.getInterpolatedAges().length, 78, 0.5);	
	}

	

}





	

	


	

