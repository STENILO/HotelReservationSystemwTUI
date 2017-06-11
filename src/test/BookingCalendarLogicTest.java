package test;

import java.util.ArrayList;

import logic.BookingCalendarLogic;

public class BookingCalendarLogicTest {
	private static BookingCalendarLogic bcl = new BookingCalendarLogic();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String arrivalDate = "2015-12-12";
		String departureDate = "2015-12-24";
		int type = 1;
		String startDate = "";
		String endDate = "";

		//bcl.reservePeriod(arrivalDate, departureDate, type);
		//bcl.deletePeriod(startDate, endDate, type);

		ArrayList<String> test = null;;
		ArrayList<String> test1 = null;;
		for(int i=0;i<3; i++){
			test = bcl.reservePeriod(arrivalDate, departureDate, type);
			if(!test.isEmpty()){
				for(int j=0; j<test.size();j++){
					System.out.println(test.get(j));
				}
			}
		}
		bcl.deletePeriod(arrivalDate, departureDate, type);

	}

}
