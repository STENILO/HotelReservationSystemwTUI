package entity.reservation;

import java.util.ArrayList;

public class CampSite extends Reservation{
	private int numChildren;
	private int numAdults;
	private int numDogs;

	public CampSite(ArrayList<String> reservedDays, String customerID, boolean arrived, String arrivalDate,
			String departureDate, int numChildren, int numAdults, int numDogs, int type) {
		super(reservedDays, customerID, arrived, arrivalDate, departureDate, type);
		this.numChildren=numChildren;
		this.numAdults=numAdults;
		this.numDogs=numDogs;
	}
	@Override
	public int getNumChildren() {return numChildren;}
	public void setNumChildren(int numChildren) {this.numChildren = numChildren;}
	@Override
	public int getNumAdults() {return numAdults;}
	public void setNumAdults(int numAdults) {this.numAdults = numAdults;}
	@Override
	public int getNumDogs() {return numDogs;}
	public void setNumDogs(int numDogs) {this.numDogs = numDogs;}
}
