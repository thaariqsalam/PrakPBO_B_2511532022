package Pekan4_B_2511532022;

public class RekeningTabungan extends Rekening {
	private double sukuBunga;
	
	// Constructor Subclass
	public RekeningTabungan(String nomor, String nama, double saldoAwal, String pinAwal, double sukuBunga) {
		// super() memanggil constructor kelas Induk (Rekening). WAJIB berada dibaris pertama
		super(nomor, nama, saldoAwal, pinAwal);
		this.sukuBunga = sukuBunga;
	}
	
	public void tambahBungaAkhirBulan() {
		double nominalBunga = saldo * (sukuBunga/100);
		saldo += nominalBunga;
		
		// Mencatat riwayat transaksi
		String idTrx = "TRX-B-" + System.currentTimeMillis();
		riwayatTransaksi.add(new Transaksi(idTrx, "Bunga", nominalBunga));
		
		System.out.println("Bunga " + sukuBunga + "% berhasil ditambahkan: Rp" + nominalBunga);
	}
}
