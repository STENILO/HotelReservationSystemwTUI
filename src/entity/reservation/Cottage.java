package entity.reservation;

import java.util.ArrayList;

public class Cottage extends Reservation {
	private int numPersons;

	public Cottage(ArrayList<String> reservedDays, String customerID, boolean arrived, String arrivalDate,
			String departureDate, int numPersons, int type) {
		super(reservedDays, customerID, arrived, arrivalDate, departureDate, type);
		this.numPersons=numPersons;
	}

	@Override
	public int getNumPersons() { return this.numPersons; }
	public void setNumPersons(int numPersons) { this.numPersons = numPersons; }
}