package Pekan2_B_2511532022;

import java.util.ArrayList;
import java.text.DecimalFormat;

public class Rekening {
	String nomorRekening;
	String namaPemilik;
	double saldo;
	
	// Implementasi Asosiasi ( 1-to-many)
	ArrayList<Transaksi> riwayatTransaksi;
	
	// Formatter
	DecimalFormat formatter = new DecimalFormat("#,##0.00");
	public String f(double angka) {
		return formatter.format(angka);
	}
	
	public Rekening(String nomor, String nama, double saldoAwal){
		this.nomorRekening= nomor;
		this.namaPemilik= nama;
		this.saldo = saldoAwal;
		this.riwayatTransaksi = new ArrayList<>();
		
		if (saldoAwal < 0) {
			saldo = 0;
			System.out.println("Saldo awal tidak boleh minus!");
		} else {
			saldo= saldoAwal;
		}
		System.out.println("Rekening atas nama " + namaPemilik + " "
				+ "berhasil dibuat dengan saldo Rp" + f(saldo));
	}
	
	public void setorTunai(double nominal){
		if (nominal > 0){
			saldo += nominal;
			// Merekam riwayat (Pembuatan objek Transaksi di dalam method)
			String idTrx = "TRX-S-" + System.currentTimeMillis();
			Transaksi trxBaru = new Transaksi(idTrx, "Kredit", nominal);
			riwayatTransaksi.add(trxBaru);
			
			System.out.println("Setor tunai Rp" + f(nominal) + 
					" berhasil. Saldo saat ini: Rp" + f(saldo));
		} else {
			System.out.println("Gagal:  Nominal setor harus lebih dari 0!");
		}
	}

	public void cekInformasi(){
		System.out.println("--- INFO REKENING ---");
		System.out.println("No. Rekening : " + nomorRekening);
		System.out.println("Nama Pemilik : " + namaPemilik);
		System.out.println("Saldo Akhir : Rp" + f(saldo));
		System.out.println("-----------------------");
	}

	public void tarikTunai(double nominal){
		if(nominal <10000){
			System.out.println("Minimal nominal penarikan 10.000");
		} else if(nominal > saldo){
			System.out.println("Transaksi Gagal: Saldo tidak mencukupi");
			System.out.println("Saldo Anda: Rp" + f(saldo));
		} else {
			saldo -= nominal;
			System.out.println("Tarik tunai Rp" + f(nominal) + 
					" berhasil. Saldo saat ini: Rp" + f(saldo));
			
		// Merekam riwayat (Pembuatan objek Transaksi di dalam method)
			String idTrx = "TRX-T-" + System.currentTimeMillis();
			Transaksi trxBaru = new Transaksi(idTrx, "Debit", nominal);
			riwayatTransaksi.add(trxBaru);			
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
}

