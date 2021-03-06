package fSIM;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;



public class yieldTest {

	@Before
	static void setUpBeforeClass() {
		//array list of all yields, likely to go into a 'Matching' class eventually
		ArrayList<GrowthYield> yYields = new ArrayList<GrowthYield>();
		
		GrowthYield gY = new GrowthYield("SS", "16", "thin"); //add if statement here when reading rows from table to only create a new growth yield object if it hasn't been invented yet
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



		gY.createGrowthMetric("yCFVol");

		//creating dummy yield data for ss yc 16
		gY.createAddMetricValue( 	0, "yCFVol"	);
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
		
		gY.getMetric(17,"yCFVol");
		
	}
	
	@Test
	public void checkAnyVolume() {
		Assert.assertEquals(75, gY.getMetric(17,"yCFVol"));
	}
	

//	@Test
//	void matchingTest () { //print out all stands /// this will likely go into a class called 'Matching' eventually
//		for (Stand stand : a.getAllStands()) {
//			
//			for (GrowthYield gYield: yYields) {
//				//System.out.print("yieldID: " + yield.getYieldIdentifier() + " standID: " + stand.getStandIdentifier() +"\n");
//				if (stand.getStandIdentifier().equals(gYield.getYieldIdentifier())) {
//					System.out.println("A " + stand.getSpecies() + " stand at YC " + stand.getProductivity() + 
//							" produces " + gYield.getMetric(stand.getAge(),"yCFVol") + "m^3 /ha and " + 
//							stand.calcTotStand(gYield.getMetric(stand.getAge(),"yCFVol")) + " for the entire stand.");		
//				}
//			}
//		}
//	}
	


}
