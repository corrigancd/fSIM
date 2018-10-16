package fSIM;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class yieldTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		//array list of all yields, likely to go into a 'Matching' class eventually
		ArrayList<GrowthYield> yYields = new ArrayList<GrowthYield>();
		
		GrowthYield gY = new GrowthYield("SS", "16", "thin"); //add if statement here when reading rows from table to only create a new growth yield object if it hasn't been invented yet
		yYields.add(gY);
		
		//creating dummy yield data for ss yc 16
		gY.addGrowthAge(new GrowthAge(  0	));
		gY.addGrowthAge(new GrowthAge(	16	));
		gY.addGrowthAge(new GrowthAge(	17	));
		gY.addGrowthAge(new GrowthAge(	18	));
		gY.addGrowthAge(new GrowthAge(	19	));
		gY.addGrowthAge(new GrowthAge(	20	));
		gY.addGrowthAge(new GrowthAge(	21	));
		gY.addGrowthAge(new GrowthAge(	21	));
		gY.addGrowthAge(new GrowthAge(	22	));
		gY.addGrowthAge(new GrowthAge(	23	));
		gY.addGrowthAge(new GrowthAge(	24	));
		gY.addGrowthAge(new GrowthAge(	25	));
		gY.addGrowthAge(new GrowthAge(	26	));
		gY.addGrowthAge(new GrowthAge(	27	));
		gY.addGrowthAge(new GrowthAge(	28	));
		gY.addGrowthAge(new GrowthAge(	29	));
		gY.addGrowthAge(new GrowthAge(	30	));
		gY.addGrowthAge(new GrowthAge(	31	));
		gY.addGrowthAge(new GrowthAge(	32	));
		gY.addGrowthAge(new GrowthAge(	33	));
		gY.addGrowthAge(new GrowthAge(	34	));
		gY.addGrowthAge(new GrowthAge(	34	));
		gY.addGrowthAge(new GrowthAge(	35	));
		gY.addGrowthAge(new GrowthAge(	36	));
		gY.addGrowthAge(new GrowthAge(	37	));
		gY.addGrowthAge(new GrowthAge(	38	));
		gY.addGrowthAge(new GrowthAge(	39	));
		gY.addGrowthAge(new GrowthAge(	40	));
		gY.addGrowthAge(new GrowthAge(	41	));


		gY.createGrowthMetric("yCFVol");
			
		
		//creating dummy yield data for ss yc 16
		gY.createUpdateMetricValue( 	0, "yCFVol"	);
		gY.createUpdateMetricValue( 	21, "yCFVol"	);
		gY.createUpdateMetricValue( 	30.8, "yCFVol");
		gY.createUpdateMetricValue( 	40.6, "yCFVol");
		gY.createUpdateMetricValue( 	50.4, "yCFVol");
		gY.createUpdateMetricValue( 	60.2, "yCFVol");
		gY.createUpdateMetricValue( 	70, "yCFVol"	);
		gY.createUpdateMetricValue( 	70, "yCFVol"	);
		gY.createUpdateMetricValue( 	87, "yCFVol"	);
		gY.createUpdateMetricValue( 	104, "yCFVol"	);
		gY.createUpdateMetricValue( 	121, "yCFVol"	);
		gY.createUpdateMetricValue( 	138, "yCFVol"	);
		gY.createUpdateMetricValue( 	155, "yCFVol"	);
		gY.createUpdateMetricValue( 	172, "yCFVol"	);
		gY.createUpdateMetricValue( 	169, "yCFVol"	);
		gY.createUpdateMetricValue( 	204.5, "yCFVol");
		gY.createUpdateMetricValue( 	240, "yCFVol"	);
		gY.createUpdateMetricValue( 	275.5, "yCFVol");
		gY.createUpdateMetricValue( 	311, "yCFVol"	);
		gY.createUpdateMetricValue( 	346.5, "yCFVol");
		gY.createUpdateMetricValue( 	382, "yCFVol"	);
		gY.createUpdateMetricValue( 	382, "yCFVol"	);
		gY.createUpdateMetricValue( 	274, "yCFVol"	);
		gY.createUpdateMetricValue( 	319.4, "yCFVol");
		gY.createUpdateMetricValue( 	364.8, "yCFVol");
		gY.createUpdateMetricValue( 	410.2, "yCFVol");
		gY.createUpdateMetricValue( 	455.6, "yCFVol");
		gY.createUpdateMetricValue( 	501, "yCFVol"	);
		gY.createUpdateMetricValue( 	0, "yCFVol"	);

		
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
		
	}

	@Test
	void matchingTest () { //print out all stands /// this will likely go into a class called 'Matching' eventually
		for (Stand stand : a.getAllStands()) {
			
			for (GrowthYield gYield: yYields) {
				//System.out.print("yieldID: " + yield.getYieldIdentifier() + " standID: " + stand.getStandIdentifier() +"\n");
				if (stand.getStandIdentifier().equals(gYield.getYieldIdentifier())) {
					System.out.println("A " + stand.getSpecies() + " stand at YC " + stand.getProductivity() + 
							" produces " + gYield.getMetric(stand.getAge(),"yCFVol") + "m^3 /ha and " + 
							stand.calcTotStand(gYield.getMetric(stand.getAge(),"yCFVol")) + " for the entire stand.");		
				}
			}
		}
	}
	
	void test() {
		fail("Not yet implemented");
	}

}
