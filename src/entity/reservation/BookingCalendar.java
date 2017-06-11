package entity.reservation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class BookingCalendar {
	// Instantiate attributes
	private static Calendar calendar = new GregorianCalendar();
	private ArrayList<ArrayList<ArrayList<ArrayList<Integer>>>> capacity = new ArrayList<ArrayList<ArrayList<ArrayList<Integer>>>>();
	private int baseYear = 2015;
	protected int[] maxCapacities = {100, 100, 100, 68-65, 45-44, 2, 2+30-27, 9-6};

	public BookingCalendar(){
		// Populate starting calendar with three years worth of bookings/days/months
		initializeCalendar();
	}

	public ArrayList<String> getOccupiedDays(String arrivalDate, String departureDate, int type ){
		// Instantiate variables
		ArrayList<String> occupiedDates = new ArrayList<String>();

		int[] dates = parseDates(arrivalDate, departureDate);

		int startYear=dates[0]-baseYear;
		int startMonth=dates[1]-1;
		int startDay=dates[2]-1;
		int endYear=dates[3]-baseYear;
		int endMonth=dates[4]-1;
		int endDay;
		int currentDay=0;
		int currentEndMonth = 0;
		// Add Year until the requested Year exists in the calendar
		while(endYear>capacity.size()-1){
			addYear();
		}

		ArrayList<ArrayList<Integer>> start = capacity.get(startYear).get(startMonth);
		ArrayList<ArrayList<Integer>> end = capacity.get(endYear).get(endMonth);
		for(int currentYear=startYear; currentYear<=endYear; currentYear++){
			if(startMonth == 11 && endMonth != startMonth){
				currentEndMonth = 11;
			}
			else{
				currentEndMonth = endMonth;
			}
			for(int currentMonth=startMonth; currentMonth<=currentEndMonth; currentMonth++){
				ArrayList<ArrayList<Integer>> current = capacity.get(currentYear).get(currentMonth);
				if(current==start && current==end){
					currentDay=startDay;
					endDay=dates[5]-1;
				}
				else if(current==start){
					currentDay=startDay;
					endDay=current.size();
				}
				else if (current==end){
					currentDay=0;
					endDay = dates[5]-1;
				} else {
					currentDay=0;
					endDay=current.size();
				}
				while(currentDay<endDay){
					int currentCapacity = current.get(currentDay).get(type);
					if(currentCapacity+1>maxCapacities[type]){
						occupiedDates.add(currentYear+baseYear + "-" + (currentMonth+1) + "-"+ (currentDay+1));
					}
					//System.out.println("Get after : " + (currentDay+1) + " " + currentCapacity);
					currentDay++;
				}
			}
			startMonth=0;
		}
		return occupiedDates;
	}

	public ArrayList<String> setReservation(String arrivalDate, String departureDate, int type, int value){
		// Instantiate variables
		ArrayList<String> result = new ArrayList<>();
		int[] dates = parseDates(arrivalDate, departureDate);
		int startYear=dates[0]-baseYear;
		int startMonth=dates[1]-1;
		int startDay=dates[2]-1;
		int endYear=dates[3]-baseYear;
		int endMonth=dates[4]-1;
		int endDay;
		int currentDay=0;
		int currentEndMonth = 0;
		// Increase the booking for the given period by one
		ArrayList<ArrayList<Integer>> start = capacity.get(startYear).get(startMonth);
		ArrayList<ArrayList<Integer>> end = capacity.get(endYear).get(endMonth);
		for(int currentYear=startYear; currentYear<=endYear; currentYear++){
			if(startMonth == 11 && endMonth != startMonth){
				currentEndMonth = 11;
			}
			else{
				currentEndMonth = endMonth;
			}
			for(int currentMonth=startMonth; currentMonth<=currentEndMonth; currentMonth++){
				ArrayList<ArrayList<Integer>> current = capacity.get(currentYear).get(currentMonth);
				if(current==start && current==end){
					currentDay=startDay;
					endDay=dates[5]-1;
				}
				else if(current==start){
					currentDay=startDay;
					endDay=current.size();
				}
				else if (current==end){
					currentDay=0;
					endDay = dates[5]-1;
				} 
				else {
					currentDay=0;
					endDay=current.size();
				}
				while(currentDay<endDay){
					int currentCapacity = current.get(currentDay).get(type);
					current.get(currentDay).set(type, currentCapacity+value);
					// Add flag to show high or low season to the String that is added to the ArrayList
					if ((currentMonth+1==6 && currentDay+1>=12) || (currentMonth+1==8 && currentDay+1<=16) || currentMonth+1==7){
						result.add(currentYear+baseYear + "-" + (currentMonth+1) + "-"+ (currentDay+1) + "HIGH");
					}
					else {
						result.add(currentYear+baseYear + "-" + (currentMonth+1) + "-"+ (currentDay+1) + "LOW");
					}
					currentDay++;
				}
			}
			startMonth=0;
		}
		// Return the ArrayList with the period including seasonal attribute
		return result;
	}

	private void initializeCalendar(){
		ArrayList<Integer> booking;
		ArrayList<ArrayList<Integer>> day;
		ArrayList<ArrayList<ArrayList<Integer>>> month;
		// Get current year
		int year = Calendar.getInstance().get(Calendar.YEAR);
		// Populate the calendar with three years worth of months
		for(int cap = 0;cap < 3;cap++){
			month = new ArrayList<ArrayList<ArrayList<Integer>>>();
			for(int total = 0;total < 12;total++){
				calendar = new GregorianCalendar(year, total, 1);
				// Find maximum number of days for the current month for given year
				int daysinMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
				booking = new ArrayList<Integer>();
				for(int i = 0;i < 9;i++){
					booking.add(new Integer(0));
				}
				day = new ArrayList<ArrayList<Integer>>();
				for(int i = 0;i < daysinMonth;i++){
					day.add((ArrayList<Integer>) booking.clone());
				}
				month.add((ArrayList<ArrayList<Integer>>) day.clone());
			}
			year++;
			capacity.add(month);
		}
	}

	private void addYear(){
		ArrayList<Integer> booking;
		ArrayList<ArrayList<Integer>> day;
		ArrayList<ArrayList<ArrayList<Integer>>> month = null;
		// Add one year to the calendar
		int year = Calendar.getInstance().get(Calendar.YEAR)+capacity.size()+1;
		for(int total = 0;total < 12;total++){
			month = new ArrayList<ArrayList<ArrayList<Integer>>>();
			calendar = new GregorianCalendar(year, total, 1);
			int daysinMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			booking = new ArrayList<Integer>();
			for(int i = 0;i < 8;i++){
				booking.add(0);
			}
			day = new ArrayList<ArrayList<Integer>>();
			for(int i = 0;i < daysinMonth;i++){
				day.add((ArrayList<Integer>) booking.clone());
			}
			month.add((ArrayList<ArrayList<Integer>>) day.clone());
		}
		year++;
		capacity.add(month);
	}

	private int[] parseDates(String arrivalDate, String departureDate){
		// Separate the input strings into int values and return int array with these
		String[] firstDateString = arrivalDate.split("-");
		String[] lastDateString = departureDate.split("-");
		int[] dates = new int[6];

		for(int i=0; i<3; i++){
			dates[i] = Integer.parseInt(firstDateString[i]);
		}
		for(int i=0; i<3; i++){
			dates[i+3] = Integer.parseInt(lastDateString[i]);
		}
		return dates;
	}

	// For Testing purposes
	public void setBookingDay(int month, int day, int type, int value){
		capacity.get(0).get(month).get(day).set(type, value);
	}
	public int getBookingDay(int month, int day, int type){
		return capacity.get(0).get(month).get(day).get(type);
	}
}