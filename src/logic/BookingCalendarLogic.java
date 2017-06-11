package logic;

import java.util.ArrayList;
import entity.reservation.BookingCalendar;

public class BookingCalendarLogic {

	//	protected enum Type {Caravan, LargeCaravan, Tent, LuxuryCottagePatio, LuxuryCottage, LargeCottage, MediumCottage, SmallCottage};
	private BookingCalendar bc;

	public BookingCalendarLogic(){
		bc = new BookingCalendar();
	}

	public ArrayList<String> reservePeriod(String arrivalDate, String departureDate, int type){
		ArrayList<String> reservedDays = null;

		reservedDays = bc.getOccupiedDays(arrivalDate, departureDate, type);
		if(reservedDays.isEmpty()){
			reservedDays=bc.setReservation(arrivalDate, departureDate, type, 1);
		}
		return reservedDays;
	}

	public void deletePeriod(String startDate, String endDate, int type){
		bc.setReservation(startDate, endDate, type, -1);
	}
}
