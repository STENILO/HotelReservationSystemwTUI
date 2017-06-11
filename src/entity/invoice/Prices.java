package entity.invoice;

public class Prices {
	// plads 0 = lavsæson
	// plads 1 = højsæson
	private static double[] caravanSmall = {25, 25}; //Pladsgebyr
	private static double[] caravanLarge = {40, 40}; // Pladsgebyr for pladser større en 110 m²
	private static double[] cottageTypeOne = {790, 890}; //Luksus hytte - nr. 65 – 68: 4-6 personers hytte
	private static double[] cottageTypeTwo = {810, 910}; //Luksus hytte - nr. 44 – 45: 4-6 personers hytte
	private static double[] cottageTypeThree = {700, 800}; //Hytte nr. 40 og 41: 4 personers hytte
	private static double[] cottageTypeFour = {380, 410}; //Hytte nr. 10, 11 og 27 - 30: 2 personers hytte
	private static double[] cottageTypeFive = {415, 450}; // Hytte nr. 6 - 9
	private static double[] adults = {82, 87}; // personer over 12 år
	private static double[] children = {42, 49}; // børn 0-11 år 
	private static double dogs = 10; // ikke tilladt i hytter
	private static double power = 3.75; // pris pr KWh: 16 Amp
	private static double extraPerson = 100; // pr. ekstra person i luksushytter

	public Prices() {}

	public static double[] getCaravanSmall() {
		return caravanSmall;
	}

	public static double[] getCaravanLarge() {
		return caravanLarge;
	}

	public static double[] getCottageTypeOne() {
		return cottageTypeOne;
	}

	public static double[] getCottageTypeTwo() {
		return cottageTypeTwo;
	}

	public static double[] getCottageTypeThree() {
		return cottageTypeThree;
	}

	public static double[] getCottageTypeFour() {
		return cottageTypeFour;
	}

	public static double[] getCottageTypeFive() {
		return cottageTypeFive;
	}

	public static double[] getAdults() {
		return adults;
	}

	public static double[] getChildren() {
		return children;
	}

	public static double getDogs() {
		return dogs;
	}

	public static double getPower() {
		return power;
	}

	public static double getExtraPerson() {
		return extraPerson;
	}


}
