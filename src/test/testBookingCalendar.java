package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import entity.reservation.BookingCalendar;

public class testBookingCalendar {

	@Test
	public void testSetEnkelteDageFyldt() {
		// Instantier variable
		BookingCalendar bc = new BookingCalendar();
		int counter = 0;

		// Fyld en dags booking op
		bc.setBookingDay(11, 30, 1, 500);
		// Test om dagens bookingantal blev gemt korrekt
		assertEquals(500, bc.getBookingDay(11, 30, 1));
		// Test at en anden dags bookingantal er 0
		assertEquals(0, bc.getBookingDay(11, 29, 1));
	}
	
	@Test
	public void testPeriodeFyldt(){
		BookingCalendar bc = new BookingCalendar();
		// Set test datoer og type for test
		String arrivalDate ="2015-12-25";
		String departureDate ="2016-01-05";
		int type = 1;

		// Forbered de to test arraylister
		ArrayList<String> test;
		
		// Fyld max tilladte bookinger på hver dag i perioden med indbyggede funktioner
		for(int i=0;i<100+1; i++){
				test=bc.setReservation(arrivalDate, departureDate, type, 1);
		}
		// Test om perioden på 11 dage er fyldt
		assertEquals(11, bc.getOccupiedDays(arrivalDate, departureDate, type).size());
	}

}
