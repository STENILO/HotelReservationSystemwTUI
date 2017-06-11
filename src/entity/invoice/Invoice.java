package entity.invoice;

import java.util.ArrayList;

import entity.reservation.Reservation;

public class Invoice {
	private static int idCounter=0;
	private int id;
	private ArrayList<Reservation> reservationList;

	public Invoice(ArrayList<Reservation> reservationList){
		id = idCounter++;
		this.reservationList = reservationList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Reservation> getReservationList() {
		return reservationList;
	}

	public void setReservationList(ArrayList<Reservation> reservationList) {
		this.reservationList = reservationList;
	}



}
