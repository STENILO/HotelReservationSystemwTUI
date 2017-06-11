package boundary;

public class TUI {

	public TUI () {

	}

	public void login (){
		System.out.println("#######################################");
		System.out.println ("1. Indtast brugernavn");
		System.out.println("2. Indtast Kodeord");
		System.out.println("#######################################");
	}

	public void mainMenu(){
		System.out.println("#######################################");
		System.out.println("Hoved Menu");
		System.out.println("1. Ny Reservation");
		System.out.println("2. Slet Reservation");
		System.out.println("3. Ændre reservation");
		System.out.println("4. Tjek ind");
		System.out.println("5. Tjek ud");
		System.out.println("6. Vis Reservation(udfra reservations id)");
		System.out.println("#######################################");
	}
	public void showReservation () {
		System.out.println("#######################################");
		System.out.println("Udskriv Reservation");
		System.out.println("1. Vis reservation ud fra reservations id");
		System.out.println("2. Vis reservationer ud fra kundenummer");
		System.out.println("3. Tilbage til hovedmenu");
		System.out.println("#######################################");
	}
	public void newReservation (){
		System.out.println("#######################################");
		System.out.println("Ny Reservation");
		System.out.println("1. Indtast kundes telefonnummer");
		System.out.println("");
		System.out.println("2. Tilbage til hovedmenu");
		System.out.println("#######################################");
	}

	public void deleteReservation(){
		System.out.println("#######################################");
		System.out.println("Slet Reservation");
		System.out.println("1. Indtast reservations id");
		System.out.println("2. Søg reservation på kundes telefonnummer");
		System.out.println("");
		System.out.println("3. Tilbage til hovedmenu");
		System.out.println("#######################################");
	}

	public void changeReservation(){
		System.out.println("#######################################");
		System.out.println("Ændre Reservation");
		System.out.println("1. Indtast reservations id");
		System.out.println("2. Søg reservation på kundes telefonnummer");
		System.out.println("");
		System.out.println("3. Tilbage til hovedmenu");
		System.out.println("#######################################");
	}

	public void reservationID (){
		System.out.println("#######################################");
		System.out.println("Reservation ID");
		System.out.println("1. Ændre fra dato");
		System.out.println("2. Ændre til dato");
		System.out.println("");
		System.out.println("3. Fortryd og tilbage til hovedmenu");
		System.out.println("#######################################");
	}

	public void checkIn (){
		System.out.println("#######################################");
		System.out.println("Tjek Ind");
		System.out.println("1. Indtast reservations id");
		System.out.println("2. Søg reservation på kundes telefonnummer");
		System.out.println("");
		System.out.println("3. Tilbage til hovedmenu");
		System.out.println("#######################################");
	}

	public void checkOut (){
		System.out.println("#######################################");
		System.out.println("Tjek Ud");
		System.out.println("1. Indtast reservations id");
		System.out.println("");
		System.out.println("3. Tilbage til hovedmenu");
		System.out.println("#######################################");
	}

	public void print (){
		System.out.println("#######################################");
		System.out.println("Print kvittering");
		System.out.println("1. Udskriv kvittering");
		System.out.println("");
		System.out.println("2. Tilbage til hovedmenu");
		System.out.println("#######################################");
	}

	public void CottageInfo(){
		System.out.println("#######################################");
		System.out.println("Information for for Cottage:");
		System.out.println("1. Indtast antal personer");
		System.out.println("");
		System.out.println("2. Tilbage");
		System.out.println("#######################################");
	}

	public void CampSiteInfo(){
		System.out.println("#######################################");
		System.out.println("Tjek Ud");
		System.out.println("1. Indtast antal voksne");
		System.out.println("2. Indtast antal børn");
		System.out.println("3. Indtast antal eventuelle hunde");
		System.out.println("");
		System.out.println("4. Gem og Tilbage");
		System.out.println("#######################################");
	}


}
