package fSIM;

import java.util.ArrayList;

public class fSimulator {

	public static void main(String[] args) {
		
		//yields
		//growth yields
		
		//array list of all yields, likely to go into a 'Matching' class eventually
		ArrayList<GrowthYield> yYields = new ArrayList<GrowthYield>();
		
		GrowthYield gY = new GrowthYield("SS", 16, "thin"); //add if statement here when reading rows from table to only create a new growth yield object if it hasn't been invented yet
		yYields.add(gY);
		
		
		//VLCPlayer player = new VLCPlayer();
		//OGGPlugin o = new OGGPlugin(); //create yield plugins based on headers of yields file
		//player.register("ogg", o); //register yield type object to hashmap, convert yield name to String
		
		//creating dummy yield data for ss yc 16
		gY.addGrowthAge(new GrowthAge(  0	,	0.000   ));
		gY.addGrowthAge(new GrowthAge(	16	,	21.000	));
		gY.addGrowthAge(new GrowthAge(	17	,	30.800	));
		gY.addGrowthAge(new GrowthAge(	18	,	40.600	));
		gY.addGrowthAge(new GrowthAge(	19	,	50.400	));
		gY.addGrowthAge(new GrowthAge(	20	,	60.200	));
		gY.addGrowthAge(new GrowthAge(	21	,	70.000	));
		gY.addGrowthAge(new GrowthAge(	21	,	70.000	));
		gY.addGrowthAge(new GrowthAge(	22	,	87.000	));
		gY.addGrowthAge(new GrowthAge(	23	,	104.000	));
		gY.addGrowthAge(new GrowthAge(	24	,	121.000	));
		gY.addGrowthAge(new GrowthAge(	25	,	138.000	));
		gY.addGrowthAge(new GrowthAge(	26	,	155.000	));
		gY.addGrowthAge(new GrowthAge(	27	,	172.000	));
		gY.addGrowthAge(new GrowthAge(	28	,	169.000	));
		gY.addGrowthAge(new GrowthAge(	29	,	204.500	));
		gY.addGrowthAge(new GrowthAge(	30	,	240.000	));
		gY.addGrowthAge(new GrowthAge(	31	,	275.500	));
		gY.addGrowthAge(new GrowthAge(	32	,	311.000	));
		gY.addGrowthAge(new GrowthAge(	33	,	346.500	));
		gY.addGrowthAge(new GrowthAge(	34	,	382.000	));
		gY.addGrowthAge(new GrowthAge(	34	,	382.000	));
		gY.addGrowthAge(new GrowthAge(	35	,	274.000	));
		gY.addGrowthAge(new GrowthAge(	36	,	319.400	));
		gY.addGrowthAge(new GrowthAge(	37	,	364.800	));
		gY.addGrowthAge(new GrowthAge(	38	,	410.200	));
		gY.addGrowthAge(new GrowthAge(	39	,	455.600	));
		gY.addGrowthAge(new GrowthAge(	40	,	501.000	));
		gY.addGrowthAge(new GrowthAge(	41	,	0.000	));

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
		
		//print out all stands /// this will likely go into a class called 'Matching' eventually
		for (Stand stand : a.getAllStands()) {
			
			for (GrowthYield gYield: yYields) {
				//System.out.print("yieldID: " + yield.getYieldIdentifier() + " standID: " + stand.getStandIdentifier() +"\n");
				if (stand.getStandIdentifier().equals(gYield.getYieldIdentifier())) {
					System.out.println("An " + stand.getSpecies() + " stand at YC " + stand.getProductivity() + 
							" produces " + gYield.getCFVol(stand.getAge()) + "m^3 /ha and " + 
							stand.calcTotStand(gYield.getCFVol(stand.getAge())) + " for the entire stand.");						
				}
			}
			 
		}

	}

}
