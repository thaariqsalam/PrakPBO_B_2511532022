package Pekan5_B_2511532022;

import java.util.ArrayList;
import java.text.DecimalFormat;

public class Rekening {
	private String nomorRekening;
	private String namaPemilik;
	private String pin;
	protected double saldo; // Data Sensitif
	
	// Implementasi Asosiasi (One-to-Many)
	protected ArrayList<Transaksi> riwayatTransaksi;
	
	// Formatter
	DecimalFormat formatter = new DecimalFormat("#,##0.00");
	public String f(double angka) {
		return formatter.format(angka);
	}
	
	public Rekening(String nomor, String nama, double saldoAwal, String pinAwal){
		this.nomorRekening = nomor;
		this.namaPemilik = nama;
		this.saldo = saldoAwal;
		
		if (pinAwal.matches("\\d{6}")) { // REGEX hanya angka length 6 digit
			this.pin = pinAwal;
		} else {
			System.out.println("Peringatan : PIN harus 6 digit Angka! Menggunakan PIN default 123456");
			this.pin = "123456";
		}
		
		if (saldoAwal < 0) {
			saldo = 0;
			System.out.println("Saldo awal tidak boleh minus!");
		} else {
			saldo = saldoAwal;
		}
		
		this.riwayatTransaksi = new ArrayList<>();
		System.out.println("Rekening atas nama " + namaPemilik + " berhasil dibuat dengan saldo Rp" + f(saldo));
	}
	
	// Getter untuk dibaca publik
	public String getNomorRekening() { return nomorRekening; }
	public String getNamaPemilik() { return namaPemilik; }
	
	// Method otentikasi internal
	public boolean otentikasi(String inputPin) {
		return this.pin.equals(inputPin);
	}
		
	// METHOD
	public void setorTunai(double nominal){
		if (nominal > 0){
			saldo += nominal;
			String idTrx = "TRX-S-" + System.currentTimeMillis();
			Transaksi trxBaru = new Transaksi(idTrx, "Kredit", nominal);
			riwayatTransaksi.add(trxBaru);
			
			System.out.println("Setor tunai Rp" + f(nominal) + " berhasil. Saldo saat ini: Rp" + f(saldo));
		} else {
			System.out.println("Gagal: Nominal setor harus lebih dari 0!");
		}
	}

	public void cekInformasi(){
		System.out.println("--- INFO REKENING ---");
		System.out.println("No. Rekening : " + nomorRekening);
		System.out.println("Nama Pemilik : " + namaPemilik);
		System.out.println("Saldo Akhir  : Rp" + f(saldo));
		System.out.println("-----------------------");
	}

	// Langkah 1: Refaktor tarikTunai dari void menjadi boolean
	public boolean tarikTunai(double nominal){
		if(nominal <= 0){
			System.out.println("Gagal: Nominal tarik harus lebih dari 0!");
			return false;
		} else if(nominal > saldo){
			System.out.println("Transaksi Gagal: Saldo tidak mencukupi.");
			System.out.println("Saldo Anda: Rp" + f(saldo));
			return false;
		} else {
			saldo -= nominal;
			System.out.println("Tarik tunai Rp" + f(nominal) + " berhasil. Sisa saldo: Rp" + f(saldo));
			
			String idTrx = "TRX-T-" + System.currentTimeMillis();
			Transaksi trxBaru = new Transaksi(idTrx, "Debit", nominal);
			riwayatTransaksi.add(trxBaru);
			return true;
		}
	}
	
	// Tugas Praktikum 5: Method Transfer Antar Rekening (Parameter Polimorfik)
	public void transfer(Rekening tujuan, double nominal, String pinInput) {
		if (!otentikasi(pinInput)) {
			System.out.println("Akses Ditolak: PIN yang Anda masukkan salah!");
			return;
		}
		
		if (tujuan == null) {
			System.out.println("Error: Rekening tujuan tidak ditemukan!");
			return;
		}

		System.out.println("--- PROSES TRANSFER ---");
		boolean statusTarik = this.tarikTunai(nominal); // Menggunakan Dynamic Method Dispatch
		if (statusTarik) {
			tujuan.setorTunai(nominal);
			System.out.println("Transfer sebesar Rp" + f(nominal) + " ke Rekening " + 
			tujuan.getNomorRekening() + " (" + tujuan.getNamaPemilik() + ") berhasil.");
		} else {
			System.out.println("Transfer gagal.");
		}
	}
	
	public void cetakMutasi() {
		System.out.println("--- MUTASI REKENING ---");
		if (riwayatTransaksi.isEmpty()) {
			System.out.println("Belum ada transaksi pada rekening ini");
		} else {
			for (Transaksi trx : riwayatTransaksi) {
				trx.cetakDetail();
			}
		}
		System.out.println("-----------------------");
	}
	
	public void gantiPin(String pinBaru) {
		pin = pinBaru;
		System.out.println("PIN berhasil diubah.");
	}
}