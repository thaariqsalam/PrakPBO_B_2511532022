package Pekan4_B_2511532022;

import java.text.DecimalFormat;

public class Transaksi {
	private String idTransaksi;
	private String jenis;
	private double nominal;
	
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
	
	// Hanya menyediakan read-only
	public String getIdTransaksi() {return idTransaksi;}
	public String getJenis() {return jenis;}
	public double getNominal() {return nominal;}
	
	
	// METHOD
	public void cetakDetail() {
		System.out.println("ID: " + idTransaksi + " | Jenis: " 
				+ jenis + " | Nominal: Rp" + f(nominal));
	}
}
