package control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import boundary.TUI;
import entity.reservation.Reservation;

public class TUIController {
	private BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
	private TUI TUI = new TUI();
	private MainController mainController;

	public TUIController(MainController mainController){
		this.mainController = mainController;
	}

	public String getStringInput() {
		String input = "";
		try{
			input = inFromUser.readLine();
		} catch (IOException e) {
			System.out.println("Ugyldigt input!");
			return getStringInput();
		}
		return input;
	}


	public String mainMenuOptions(){
		TUI.mainMenu();
		String input = getStringInput();

		switch (input){
		case "1" : newReservation();	break;
		case "2" : deleteReservation();	break;
		case "3" : changeReservation(); break;
		case "4" : checkIn();			break;
		case "5" : checkOut();			break;
		case "6" : showReservation();   break;
		default : System.out.println("Forkert tal."); break;
		}
		return input;
	}

	public void showReservation(){
		TUI.showReservation();
		String resID = null;
		String phoneNr = null;
		ArrayList<Reservation> customerReservations;
		Reservation customerReservation;
		whileLoop:
			while(true){
				String input = getStringInput();
				switch(input){
				case "1" : 
					System.out.println("Indtast reservations ID:");
					int id = Integer.parseInt(getStringInput());
					customerReservation = mainController.findReservation(id);
					printReservation(customerReservation);
					break whileLoop;
				case "2" :
					System.out.println("Indtast telefonnummer:");
					phoneNr = getStringInput();
					// Metode til at finde ID ud fra tlfnr
					customerReservations = mainController.findReservationID(phoneNr);
					for(Reservation r:customerReservations){
						printReservation(r);
					}
					break whileLoop;
				case "3" :
					break whileLoop;
				default : System.out.println("Forkert tal."); break;
				}
			}
	}

	public void newReservation(){		
		String phone = null;
		String name = null;
		String startdate = null;
		String enddate = null;
		int groundtype = 0;
		int[] renttypeinfo = null;
		int type = 0;

		whileLoop:
			while(type == 0){
				TUI.newReservation();
				String input = getStringInput();

				switch(input){
				case "1" : 
					System.out.println("Indtast telefon:");
					phone = getStringInput();
					name = mainController.userExist(phone);	
					if(name.equals("")){
						System.out.println("Indtast navn:");
						name = getStringInput();
						mainController.createUser(phone, name);
					}

					System.out.println("Indtast start dato:");
					startdate = getStringInput();

					System.out.println("Indtast slut dato:");
					enddate = getStringInput();

					System.out.println("Indtast grundtype, (1) for Cottage (2) for Campsite. ");
					groundtype = Integer.parseInt(getStringInput());

					if(groundtype != 1 && groundtype != 2){
						System.out.println("Forkert input.");
						break;
					}
					else
					{
						renttypeinfo = infoFromRentType(groundtype);
					}					
					System.out.println("Type af grund: ");
					if(groundtype == 2){
						System.out.println("0. lille campingvognsplads");
						System.out.println("1. stor campingvognsplads");
						System.out.println("2. teltplads");
					}
					else if(groundtype == 1){
						System.out.println("3. luxushytte");
						System.out.println("4. luxushytte");
						System.out.println("5. hytte");
						System.out.println("6. hytte");
						System.out.println("7. hytte");
					}
					type = Integer.parseInt(getStringInput());
					break;

				case "6" : break whileLoop;
				default : System.out.println("Forkert tal."); break;
				}
			}

		if (phone!=null && name!=null && startdate!=null && enddate!=null && renttypeinfo != null){
			mainController.createReservation(phone, startdate, enddate, renttypeinfo[0] ,renttypeinfo[1], renttypeinfo[2], renttypeinfo[3], type);
			System.out.println("Reservation Oprettet.");
		} else {
			System.out.println("Fejl");
		}
	}

	//Finder ud af hvad for noget info der skal bruges ud fra typen af grund. 
	public int[] infoFromRentType(int renttype){
		int[] renttypeinfo = new int[4];
		renttypeinfo[0] = 0;
		renttypeinfo[1] = 0;
		renttypeinfo[2] = 0;
		renttypeinfo[3] = 0;

		if(renttype == 1){
			System.out.println("Indtast antal personer:");
			renttypeinfo[0] = Integer.parseInt(getStringInput());
		}
		if(renttype == 2){
			System.out.println("Indtast antal voksne:");
			renttypeinfo[2] = Integer.parseInt(getStringInput());
			System.out.println("Indtast eventuelle antal børn: ");
			renttypeinfo[1] = Integer.parseInt(getStringInput());
			System.out.println("Indtast eventuelle antal hunde:");
			renttypeinfo[3] = Integer.parseInt(getStringInput());
		}
		return renttypeinfo;
	}

	public void deleteReservation(){
		String resID = null;
		String phoneNr = null;
		ArrayList<Reservation> resInfo = null;

		whileLoop:
			while(true){
				TUI.deleteReservation();
				String input = getStringInput();
				switch(input){
				case "1" : 
					System.out.println("Indtast reservations ID:");
					resID = getStringInput();
					int resIDint = Integer.parseInt(resID);
					mainController.deleteReservation(resIDint);
					System.out.println("Reservation Slettet.");
					TUI.deleteReservation();
					break;
				case "2" :
					System.out.println("Indtast telefonnummer:");
					phoneNr = getStringInput();
					resInfo = mainController.findReservationID(phoneNr);

					for(Reservation r:resInfo){
						printReservation(r);
					}
					TUI.deleteReservation();
					break;
				case "3" :
					break whileLoop;
				default : System.out.println("Forkert tal."); break;
				}
			}
	}

	public void changeReservation(){
		String resID = null;
		String phoneNr = null;

		whileLoop:
			while(true){
				TUI.changeReservation();
				String input = getStringInput();
				switch(input){
				case "1" : 
					System.out.println("Indtast reservations ID:");
					resID = getStringInput();
					changeReservationInner(resID);
					break;
				case "2" :
					System.out.println("Indtast telefonnummer:");
					phoneNr = getStringInput();
					System.out.println("Navn: "+mainController.userExist(phoneNr));
					break;
				case "3" :
					break whileLoop;
				default : System.out.println("Forkert tal."); break;
				}
			}
	}

	public void changeReservationInner(String resID){
		System.out.println("Ændrer for: " + resID);
		String newDepartureDate;

		System.out.println("Indtast ny afrejsedato");
		newDepartureDate = getStringInput();
		String oldDepartureDate = mainController.findReservation(Integer.parseInt(resID)).getDepartureDate();
		mainController.changeReservation(Integer.parseInt(resID), newDepartureDate);
		System.out.println("Reservationens afrejse dato ændret \n fra "+oldDepartureDate+"\n til "+newDepartureDate);
	}

	public void checkIn(){
		String resID = null;
		String phoneNr = null;

		whileLoop:
			while(true){
				TUI.checkIn();
				String input = getStringInput();
				switch(input){
				case "1" : 
					System.out.println("Indtast reservations ID:");
					resID = getStringInput();
					mainController.checkIn(Integer.parseInt(resID));
					break;
				case "2" :
					System.out.println("Indtast telefonnummer:");
					phoneNr = getStringInput();
					System.out.println("Navn: "+mainController.userExist(phoneNr));
					break;
				case "3" :
					break whileLoop;
				default : System.out.println("Forkert tal."); break;
				}
			}
	}

	public void checkOut(){
		String resID = null;
		String phoneNr = null;

		whileLoop:
			while(true){
				TUI.checkOut();
				String input = getStringInput();
				switch(input){
				case "1" : 
					System.out.println("Indtast telefonnummer:");
					phoneNr = getStringInput();
					mainController.checkOut(phoneNr);
					break;
				case "3" :
					break whileLoop;
				default : System.out.println("Forkert tal."); break;
				}
			}
	}

	public void login(){
		String username = null;
		String password = null;

		whileLoop:
			while(true){
				TUI.login();        
				String input = getStringInput();
				switch(input){
				case "1" : 
					System.out.println("Indtast brugernavnet");
					username = getStringInput();
					break;
				case "2" : 
					System.out.println("Indtast kodeordet");
					password = getStringInput();
					break;
				default : System.out.println("Forkert tal."); break;
				}
				if(username != null && password != null){
					if(mainController.checklogin(username, password)){
						break whileLoop;
					}
					else{ System.out.println("Brugeren eksisterer ikke."); }}
				else{ System.out.println("Du har glemt at indskrive et brugernavn eller adgangskode.");}
			}
		mainMenuOptions();
	}

	private void printReservation(Reservation r){
		ArrayList<String> liste = r.getReservedDays();
		switch(r.getType()){
		case 0:
			System.out.println("Kunde telefonnummer: " + r.getCustomerID());
			System.out.println("Reservation ID: " + r.getId());
			System.out.println("Ankomst Dato: " + r.getArrivalDate());
			System.out.println("Afgangs Dato: " + r.getDepartureDate());
			System.out.println("Dags Liste:");
			for(String s:liste){
				System.out.println("Booket Dato: " + s);
			}

			System.out.println("Antal Voksne: " + r.getNumAdults());
			System.out.println("Antal Børn: " + r.getNumChildren());
			System.out.println("Antal Hunde: " + r.getNumDogs());
			break;
		case 1:
			System.out.println("Kunde telefonnummer: " + r.getCustomerID());
			System.out.println("Reservation ID: " + r.getId());
			System.out.println("Ankomst Dato: " + r.getArrivalDate());
			System.out.println("Afgangs Dato: " + r.getDepartureDate());
			System.out.println("Dags Liste:");
			for(String s:liste){
				System.out.println("Booket Dato: " + s);
			}

			System.out.println("Antal Voksne: " + r.getNumAdults());
			System.out.println("Antal Børn: " + r.getNumChildren());
			System.out.println("Antal Hunde: " + r.getNumDogs());
			break;
		case 2:
			System.out.println("Kunde telefonnummer: " + r.getCustomerID());
			System.out.println("Reservation ID: " + r.getId());
			System.out.println("Ankomst Dato: " + r.getArrivalDate());
			System.out.println("Afgangs Dato: " + r.getDepartureDate());
			System.out.println("Dags Liste:");
			for(String s:liste){
				System.out.println("Booket Dato: " + s);
			}

			System.out.println("Antal Voksne: " + r.getNumAdults());
			System.out.println("Antal Børn: " + r.getNumChildren());
			System.out.println("Antal Hunde: " + r.getNumDogs());
			break;
		case 3:
			System.out.println("Kunde telefonnummer: " + r.getCustomerID());
			System.out.println("Reservation ID: " + r.getId());
			System.out.println("Ankomst Dato: " + r.getArrivalDate());
			System.out.println("Afgangs Dato: " + r.getDepartureDate());
			System.out.println("Dags Liste:");
			for(String s:liste){
				System.out.println("Booket Dato: " + s);
			}
			System.out.println("Antal Personer: " + r.getNumPersons());
			break;
		case 4:
			System.out.println("Kunde telefonnummer: " + r.getCustomerID());
			System.out.println("Reservation ID: " + r.getId());
			System.out.println("Ankomst Dato: " + r.getArrivalDate());
			System.out.println("Afgangs Dato: " + r.getDepartureDate());
			System.out.println("Dags Liste:");
			for(String s:liste){
				System.out.println("Booket Dato: " + s);
			}
			System.out.println("Antal Personer: " + r.getNumPersons());
			break;
		case 5:
			System.out.println("Kunde telefonnummer: " + r.getCustomerID());
			System.out.println("Reservation ID: " + r.getId());
			System.out.println("Ankomst Dato: " + r.getArrivalDate());
			System.out.println("Afgangs Dato: " + r.getDepartureDate());
			System.out.println("Dags Liste:");
			for(String s:liste){
				System.out.println("Booket Dato: " + s);
			}
			System.out.println("Antal Personer: " + r.getNumPersons());
			break;
		case 6:
			System.out.println("Kunde telefonnummer: " + r.getCustomerID());
			System.out.println("Reservation ID: " + r.getId());
			System.out.println("Ankomst Dato: " + r.getArrivalDate());
			System.out.println("Afgangs Dato: " + r.getDepartureDate());
			System.out.println("Dags Liste:");
			for(String s:liste){
				System.out.println("Booket Dato: " + s);
			}
			System.out.println("Antal Personer: " + r.getNumPersons());
			break;
		case 7:
			System.out.println("Kunde telefonnummer: " + r.getCustomerID());
			System.out.println("Reservation ID: " + r.getId());
			System.out.println("Ankomst Dato: " + r.getArrivalDate());
			System.out.println("Afgangs Dato: " + r.getDepartureDate());
			System.out.println("Dags Liste:");
			for(String s:liste){
				System.out.println("Booket Dato: " + s);
			}
			System.out.println("Antal Personer: " + r.getNumPersons());
			break;
		default:
			System.out.println("Forkert Indtastning, prøv igen");
			break;
		}
	}
}