package fSIM;

public class Stand {
	private String species;
	private int productivity;
	private double area;
	private int age;
	private String thin;

	public Stand(String s, int p, double a, int age) {
		this.species = s;
		this.productivity = p;
		this.age = age;
		this.area = a;
		this.thin = "thin";
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getStandIdentifier() {
		return species + productivity + thin;
	}

	// area calculation for stand level reporting
	public double calcTotStand(int a) {
		return (this.area * a);
	}
	public double calcTotStand(double a) {
		return (this.area * a);
	}

	@Override
	public String toString() {
		return "Stand [species=" + species + ", productivity=" + productivity + ", area=" + area + ", age=" + age + "]";
	}

	public String getSpecies() {
		return species;
	}

	public int getProductivity() {
		return productivity;
	}

	public void setProductivity(int productivity) {
		this.productivity = productivity;
	}

	public String getThin() {
		return thin;
	}

	public void setThin(String thin) {
		this.thin = thin;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

}
