package logic;

import java.util.ArrayList;

import entity.invoice.Invoice;
import entity.invoice.Prices;
import entity.invoice.Print;
import entity.reservation.Reservation;

public class InvoiceLogic {
	private static ArrayList<Invoice> invoiceList;
	private static Prices priceList = new Prices();
	private static Print printer = new Print();


	public InvoiceLogic(){
		invoiceList = new ArrayList<Invoice>();
	}

	public int makeInvoice(String reservations, ArrayList<Reservation> reservationList){
		String[] reservationIDs = reservations.split(",");
		ArrayList<Reservation> targetReservations = new ArrayList<Reservation>();
		for (Reservation r: reservationList){
			for (int i=0;i<reservationIDs.length;i++){
				if (r.getId()==Integer.parseInt(reservationIDs[i])) targetReservations.add(r);
			}
		}
		Invoice i = new Invoice(targetReservations);
		generateInvoiceFile(targetReservations, i.getId());
		invoiceList.add(i);
		return i.getId();
	}

	public int makeInvoice(ArrayList<Reservation> reservationList){
		Invoice i = new Invoice(reservationList);
		invoiceList.add(i);
		generateInvoiceFile(reservationList, i.getId());
		return i.getId();
	}

	public ArrayList<Invoice> getCustomerInvoices(String customerID){
		ArrayList<Invoice> targetList = new ArrayList<Invoice>();
		for (Invoice invoice : invoiceList){
			for(int i = 0;i<invoice.getReservationList().size();i++){
				if (invoice.getReservationList().get(i).getCustomerID() == customerID){
					targetList.add(invoice);
				}
			}
		}
		return targetList;
	}

	public ArrayList<Invoice> getInvoiceList() {
		return invoiceList;
	}
	private void generateInvoiceFile(ArrayList<Reservation> reservationList, int invoice_id) {
		ArrayList<String> expenses = new ArrayList<String>();
		double globalPrice = 0;
		for (Reservation r: reservationList) {
			double totalPrice = 0;
			double adultPrice = 0;
			double childrenPrice = 0;
			double cottagePrice = 0;
			double extraPersonPrice = 0;
			double dogPrice = 0;
			double areaPrice=0;
			double power=priceList.getPower()*r.getPowerConsumption();
			int season=0;
			boolean highseasonUsed = false;
			int type = r.getType();
			for (String s: r.getReservedDays()){
				if (s.endsWith("LOW")) season=0; 
				else {season=1; highseasonUsed=true;}

				if (type<3) {
					adultPrice += r.getNumAdults()*getPriceOfIndex(7, season);
					childrenPrice += r.getNumChildren()*getPriceOfIndex(8, season);
					dogPrice += r.getNumDogs()*priceList.getDogs();
				} else if (type==3) {
					cottagePrice += getPriceOfIndex(2, season);
					extraPersonPrice += (r.getNumPersons()%4)*priceList.getExtraPerson();
				} else if (type==4) {
					cottagePrice += getPriceOfIndex(3, season);
					extraPersonPrice += (r.getNumPersons()%4)*priceList.getExtraPerson();
				} 
				else if (type==5) cottagePrice += getPriceOfIndex(4, season);
				else if (type==6) cottagePrice += getPriceOfIndex(5, season);
				else if (type==7) cottagePrice += getPriceOfIndex(6, season);
			}
			if (highseasonUsed) season = 1;
			if (type==0) areaPrice=getPriceOfIndex(0, season);
			else if (type==1) areaPrice=getPriceOfIndex(1, season);
			expenses.add("Udlejning "+Integer.toString(r.getId()));
			expenses.add("--------------");
			expenses.add("Kunde: "+r.getCustomerID());
			expenses.add("Ankomst dato: "+r.getArrivalDate()+"\t Afrejse dato: "+r.getDepartureDate());
			expenses.add("--------------\n");
			expenses.add(getTypeDescribtion(type)+"\n");
			if (type<3){
				expenses.add("Pladsgebyr: "+areaPrice);
				expenses.add("Voksne: "+adultPrice);
				expenses.add("Børn (0-11 år): "+childrenPrice);
				expenses.add("Hunde: "+dogPrice);
				expenses.add("Strøm: "+power);
				totalPrice = adultPrice+childrenPrice+dogPrice+areaPrice+power;
			} else if (type==3 || type==4) {
				expenses.add("Pris for hytte: "+cottagePrice);
				if (r.getNumPersons()>4) expenses.add("Tillæg pr ekstra person: "+extraPersonPrice);
				expenses.add("Strøm: "+power);
				totalPrice = cottagePrice+extraPersonPrice+power;
			} else if (type > 4){
				expenses.add("Pris for hytte: "+cottagePrice);
				expenses.add("Strøm: "+power);
				totalPrice = cottagePrice+power;
			}
			expenses.add("--------------");
			expenses.add("Total for udlejning: "+totalPrice);
			globalPrice+=totalPrice;
		}
		expenses.add("--------------");
		expenses.add("Totalpris for alle udlejninger: "+globalPrice);
		printer.printInvoice(expenses, invoice_id);
	}

	private String getTypeDescribtion(int type){
		switch(type) {
		case 0: return "Lille campingvogns areal";
		case 1: return "Stort campingvogns areal";
		case 2: return "Telt areal";
		case 3: return "Luksus hytte - nr. 65 – 68: \n 4-6 personers hytte";
		case 4: return "Luksus hytte - nr. 44 – 45: \n 4-6 personers hytte";
		case 5: return "Hytte nr. 40 og 41: \n 4 personers hytte";
		case 6: return "Hytte nr. 10, 11 og 27 - 30: \n 2 personers hytte";
		case 7: return "Hytte nr. 6 - 9: \n 2 personers hytte,";
		default: return "";
		}
	}

	// index = type of price
	// seasonprice = 0 for low season, 1 for high season
	private double getPriceOfIndex(int index, int seasonindex) {
		switch (index){
		case 0: return priceList.getCaravanSmall()[seasonindex];
		case 1: return priceList.getCaravanLarge()[seasonindex];
		case 2: return priceList.getCottageTypeOne()[seasonindex];
		case 3: return priceList.getCottageTypeTwo()[seasonindex];
		case 4: return priceList.getCottageTypeThree()[seasonindex];
		case 5: return priceList.getCottageTypeFour()[seasonindex];
		case 6: return priceList.getCottageTypeFive()[seasonindex];
		case 7: return priceList.getAdults()[seasonindex];
		case 8: return priceList.getChildren()[seasonindex];
		default: return 0;
		}
	}





}
