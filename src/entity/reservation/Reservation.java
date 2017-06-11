package entity.reservation;

import java.util.ArrayList;

public abstract class Reservation {
	private ArrayList<String> reservedDays;
	private static int idCounter = 0;
	private int id;
	private int type;
	private String customerID;
	private boolean arrived;
	private String arrivalDate;
	private String departureDate;
	private int powerConsumption;

	public Reservation(ArrayList<String> reservedDays, String customerID, boolean arrived, String arrivalDate, String departureDate, int type) {
		id=idCounter++;
		this.reservedDays=reservedDays;
		this.customerID=customerID;
		this.arrived=arrived;
		this.arrivalDate=arrivalDate;
		this.departureDate=departureDate;
		this.type=type;
		this.powerConsumption=0;
	}
	public String getCustomerID() {return customerID;}
	public void setCustomerID(String customerID) {this.customerID = customerID;}
	public boolean isArrived() {return arrived;}
	public void setArrived(boolean arrived) {this.arrived = arrived;}
	public String getArrivalDate() {return arrivalDate;}
	public void setArrivalDate(String arrivalDate) {this.arrivalDate = arrivalDate;}
	public String getDepartureDate() {return departureDate;}
	public void setDepartureDate(String departureDate) {this.departureDate = departureDate;}
	public int getPowerConsumption() {return powerConsumption;}
	public void setPowerConsumption(int powerConsumption) {this.powerConsumption = powerConsumption;}
	public int getId() {return id;}
	public int getType() { return type;}
	public ArrayList<String> getReservedDays() {return reservedDays;}
	public void setReservedDays(ArrayList<String> reservedDays){this.reservedDays= reservedDays;}
	public int getNumPersons() { return 0; }
	public int getNumDogs() {return 0;}
	public int getNumAdults() {return 0;}
	public int getNumChildren() {return 0;}
}
