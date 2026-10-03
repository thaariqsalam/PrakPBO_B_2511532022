package Pekan4_B_2511532022;

public class RekeningGiro extends Rekening{
	private double batasOverdraft;
	
	public RekeningGiro(String nomor, String nama, double saldoAwal, String pinAwal, double batasOverdraft) {
		super(nomor, nama, saldoAwal, pinAwal);
		this.batasOverdraft = batasOverdraft;
	}
	
	// Getter khusus Giro
	public double getBatasOverdraft() {
		return batasOverdraft;
	}
}

