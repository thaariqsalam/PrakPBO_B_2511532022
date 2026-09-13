package PrakPBO_B_2511532022;

public class Rekening {
	String nomorRekening;
	String namaPemilik;
	double saldo;
	
	public Rekening(String nomor, String nama, double saldoAwal){
		nomorRekening= nomor;
		namaPemilik= nama;
		
		if (saldoAwal < 0) {
			saldo = 0;
			System.out.println("Saldo awal tidak boleh minus!");
		} else {
			saldo= saldoAwal;
		}
		System.out.println("Rekening atas nama " + namaPemilik + " berhasil dibuat dengan saldo Rp" + saldo);
	}
	
	public void setorTunai(double nominal){
		if (nominal > 0){
			saldo += nominal;
			System.out.println("Setor tunai Rp" + nominal + " berhasil. Saldo saat ini: Rp" + saldo);
		} else {
			System.out.println("Gagal:  Nominal setor harus lebih dari 0!");
		}
	}

	public void cekInformasi(){
		System.out.println("--- INFO REKENING ---");
		System.out.println("No. Rekening : " + nomorRekening);
		System.out.println("Nama Pemilik : " + namaPemilik);
		System.out.println("Saldo Akhir : Rp" + saldo);
		System.out.println("-----------------------");
	}

	public void tarikTunai(double nominal){
		if(nominal <10000){
			System.out.println("Minimal nominal penarikan 10.000");
		} else if(nominal > saldo){
			System.out.println("Transaksi Gagal: Saldo tidak mencukupi");
			System.out.println("Saldo Anda: Rp" + saldo);
		} else {
			saldo -= nominal;
			System.out.println("Tarik tunai Rp" + nominal + " berhasil. Saldo saat ini: Rp" + saldo);
		}
	}
}

