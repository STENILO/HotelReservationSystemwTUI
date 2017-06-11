package entity.invoice;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import entity.invoice.Invoice;
import entity.reservation.Cottage;
import entity.reservation.Reservation;

public class Print {
	private int invoice_id;
	private ArrayList<String> expenses;

	public void printInvoice(ArrayList<String> expenses, int invoice_id) {
		this.expenses=expenses;
		this.invoice_id=invoice_id;
		writeFile();
	}
	private void writeFile() {
		String dbFile = "Invoices/invoice"+invoice_id;
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(dbFile));
			printFile(bw);
		} catch (FileNotFoundException e) {e.printStackTrace();
		} catch (IOException e) {e.printStackTrace();} 
		finally {
			if (bw != null) try {bw.close();} catch (IOException e) {e.printStackTrace();}
		}
	}

	private void printFile(BufferedWriter bw) throws IOException {
		for (String expense : expenses) {
			bw.write(expense+"\n");
		}
	}

}
