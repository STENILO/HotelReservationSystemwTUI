package control; 

import java.util.ArrayList;

import entity.login.CustomerDB;
import entity.reservation.Reservation;
import logic.InvoiceLogic;
import logic.LoginLogic;
import logic.ReservationLogic;

public class MainController {
	private ReservationLogic reservationLogic = new ReservationLogic();
	private InvoiceLogic invoiceLogic = new InvoiceLogic();
	private LoginLogic loginLogic = new LoginLogic();
	private TUIController tuiController = new TUIController(this);
	private CustomerDB customerDB = new CustomerDB();

	public MainController() {
	}
	public static void main(String[] args) {
		MainController mc = new MainController();
		mc.runTUI();

	}
	public void runTUI() {
		while(true){
			tuiController.mainMenuOptions();
		}
	}

	public String userExist(String phonenumber) {
		if (customerDB.getCustomer(phonenumber).getName().equals("")) return "";
		else return customerDB.getCustomer(phonenumber).getName();
	}
	public void createUser(String phonenumber, String name) {
		customerDB.addCustomer(name, phonenumber);
	}
	public boolean createReservation(String phonenumber, String arrivalDate, String departureDate, int persons, 
			int numChildren, int numAdults , int numDogs, int type){
		if (type < 3) 
			reservationLogic.makeCampsite
			(phonenumber, false, arrivalDate, departureDate, numChildren, numAdults, numDogs, type);
		else
			reservationLogic.makeCottage(phonenumber, false, arrivalDate, departureDate, persons, type);
		return true;
	}

	public void deleteReservation(int reservationID){
		reservationLogic.deleteReservation(reservationID);
	}
	public ArrayList<Reservation> findReservationID(String phonenumber) {
		ArrayList<Reservation> reservations = reservationLogic.getReservations(phonenumber);
		return reservations;
	}

	public Reservation findReservation(int id){
		return reservationLogic.getReservation(id);
	}

	public void checkIn(int reservationID) {
		reservationLogic.getReservation(reservationID).setArrived(true);
	}

	public void checkOut(String phonenumber) {
		ArrayList<Reservation> targetReservations = new ArrayList<Reservation>();
		for (Reservation r: reservationLogic.getReservations(phonenumber)) {
			if (r.isArrived()) {
				targetReservations.add(r);
				reservationLogic.deleteReservation(r.getId());
			}
		}
		invoiceLogic.makeInvoice(targetReservations);
	}
	public void changeReservation(int reservationID, String newDepartureDate) {
		reservationLogic.changeReservation(reservationID, newDepartureDate);
	}

	public boolean checklogin(String username, String password){
		//Tjekker på login, lavet til TUI controller.
		return loginLogic.validateLogin(username, password);
	}


}