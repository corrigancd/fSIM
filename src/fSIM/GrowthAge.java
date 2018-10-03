package fSIM;

public class GrowthAge {
	
	int age;
	double yCFvol;
	
	public GrowthAge(int age, double cfvol) {
		this.age = age;
		this.yCFvol = cfvol;	
	}

	public int getAge() {
		return age;
	}
	
	public double getCFVol() {
		return yCFvol;
	}
	
	@Override
	public String toString() {
		return "GrowthAge [age=" + age + ", yCFvol=" + yCFvol + "]";
	}
	
	
	
}
