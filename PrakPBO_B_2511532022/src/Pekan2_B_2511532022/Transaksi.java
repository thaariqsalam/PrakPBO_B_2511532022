package Pekan2_B_2511532022;

import java.text.DecimalFormat;

public class Transaksi {
	String idTransaksi;
	String jenis;
	double nominal;
	
	// Formatter
		DecimalFormat formatter = new DecimalFormat("#,##0.00");
		public String f(double angka) {
			return formatter.format(angka);
		}
	
	// Constructor
	public Transaksi(String id, String jenis, double nominal) {
		this.idTransaksi = id;
		this.jenis = jenis;
		this.nominal = nominal;
	}
	// METHOD
	public void cetakDetail() {
		System.out.println("ID: " + idTransaksi + " | Jenis: " 
				+ jenis + " | Nominal: Rp" + f(nominal));
	}
}
