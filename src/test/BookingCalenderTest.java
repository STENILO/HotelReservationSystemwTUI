package test;

import java.util.ArrayList;

import entity.reservation.BookingCalendar;

public class BookingCalenderTest {
	private static BookingCalendar bc = new BookingCalendar();

	public static void main(String[] args){
		String arrivalDate ="2015-12-30";
		String departureDate ="2016-01-02";
		int type = 1;

		ArrayList<String> test = null;;
		ArrayList<String> test1 = null;;
		for(int i=0;i<3; i++){
			test= bc.getOccupiedDays(arrivalDate, departureDate, type );
			if(test.isEmpty()){
				test1=bc.setReservation(arrivalDate, departureDate, type, 1);
				for(int k=0; k<test1.size();k++){
					System.out.println(test1.get(k));
				}
			}
			if(!test.isEmpty()){
				for(int j=0; j<test.size();j++){
					System.out.println(test.get(j));
				}
			}

		}
	}
}