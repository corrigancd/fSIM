package fSIM;

public class StandYieldMatching {
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
}
