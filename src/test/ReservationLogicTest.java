package test;

import java.util.ArrayList;

import entity.reservation.Reservation;
import logic.ReservationLogic;

public class ReservationLogicTest {
	private static ReservationLogic rl = new ReservationLogic();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String customerID = "29848831";
		boolean arrived = false;
		String arrivalDate  = "2015-12-12";
		String departureDate = "2015-12-24";
		String newDepartureDate = "2015-12-20";
		int numChildren = 2;
		int numAdults =2;
		int numDogs = 1;
		int type = 1;

		//ArrayList<String> result1 = rl.makeCampsite(customerID, arrived, arrivalDate, departureDate, numChildren, numAdults, numDogs, type);

		ArrayList<String> result = rl.makeCampsite(customerID, arrived, arrivalDate, departureDate, numChildren, numAdults, numDogs, type);
				if(result!=null){
			for(int i=0; i<result.size();i++){
				System.out.println("Reserved days: " + result.get(i) );
			}
		}
		ArrayList<Reservation> reservation = rl.getReservations(customerID);
		int reservationID = reservation.get(0).getId();
		System.out.println("Is arrived: " + reservation.get(0).isArrived());
		System.out.println("has set is arrived: " + rl.setArrived(reservationID));
		System.out.println("Is arrived: " + reservation.get(0).isArrived());

		ArrayList<String> newResult = rl.changeReservation(reservationID, newDepartureDate);
		for(int i=0; i<newResult.size();i++){
			System.out.println("Reserved days: " + newResult.get(i) );
		}

		String newNewDepartureDate = "2015-12-23";
		ArrayList<String> newNewResult = rl.changeReservation(reservationID, newNewDepartureDate);
		for(int i=0; i<newNewResult.size();i++){
			System.out.println("Reserved days: " + newNewResult.get(i) );
		}
	}
}
