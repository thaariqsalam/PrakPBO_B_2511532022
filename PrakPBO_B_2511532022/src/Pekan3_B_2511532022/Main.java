package Pekan3_B_2511532022;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Rekening> daftarRekening = new ArrayList<>();
        Rekening akunAktif = null;
        boolean isRunning = true;
        
        System.out.println("=== SISTEM PERBANKAN MINI (MULTI-AKUN) ===");
        
        while (isRunning) {
            System.out.println("\nMenu Utama");
            System.out.println("1. Buka Rekening Baru");
            System.out.println("2. Setor Tunai");
            System.out.println("3. Tarik Tunai");
            System.out.println("4. Cek Informasi Rekening");
            System.out.println("5. Ganti Akun Aktif");
            System.out.println("6. Cetak Mutasi (Riwayat)");
            System.out.println("7. Ganti PIN");
            System.out.println("0. Keluar");
            System.out.print("Pilih Menu: ");
            
            int pilihan = input.nextInt();
            input.nextLine(); // Membersihkan buffer enter
            
            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan No Rekening: ");
                    String no = input.nextLine();
                    System.out.print("Masukkan Nama Pemilik: ");
                    String nama = input.nextLine();
                    System.out.print("Masukkan Saldo Awal: ");
                    double saldo = input.nextDouble();
                    input.nextLine(); // Membersihkan buffer enter
                    System.out.print("Masukkan PIN (6 digit angka): ");
                    String pin = input.nextLine();
                    
                    Rekening akunBaru = new Rekening(no, nama, saldo, pin);
                    daftarRekening.add(akunBaru);
                    akunAktif = akunBaru;
                    System.out.println("-> Akun ini otomatis diset sebagai Akun Aktif saat ini.");
                    break;
                    
                case 2:
                    if (akunAktif == null) {
                        System.out.println("Error: Mohon maaf, Anda belum memiliki nomor rekening!");
                    } else {
                        System.out.print("Masukkan nominal setor: ");
                        double setor = input.nextDouble();
                        input.nextLine();
                        akunAktif.setorTunai(setor);
                    }
                    break;
                
                case 3:
                    if (akunAktif == null) {
                        System.out.println("Error: Mohon maaf, Anda belum memiliki nomor rekening!");
                    } else {
                        System.out.print("Masukkan PIN anda: ");
                        String pinInput = input.nextLine();
                        
                        if (akunAktif.otentikasi(pinInput)) {
                            System.out.print("Masukkan nominal Tarik Tunai: ");
                            double tarik = input.nextDouble();
                            input.nextLine();
                            akunAktif.tarikTunai(tarik);
                        } else {
                            System.out.println("Akses Ditolak: PIN yang Anda masukkan salah!");
                        }
                    }
                    break;
                    
                case 4:
                    if (akunAktif == null) {
                        System.out.println("Error: Anda belum membuka rekening!");
                    } else {
                        akunAktif.cekInformasi();
                    }
                    break;
                    
                case 5:
                    if (daftarRekening.isEmpty()) {
                        System.out.println("Error: Belum ada rekening yang terdaftar di sistem!");
                    } else {
                        System.out.println("\n--- DAFTAR REKENING TERDAFTAR ---");
                        for (int i = 0; i < daftarRekening.size(); i++) {
                            System.out.println((i + 1) + ". No: " + daftarRekening.get(i).getNomorRekening() + 
                                               " | Nama: " + daftarRekening.get(i).getNamaPemilik());
                        }
                        System.out.print("Masukkan No Rekening yang ingin diaktifkan: ");
                        String cariNo = input.nextLine();
                        
                        boolean ditemukan = false;
                        for (Rekening rek : daftarRekening) {
                            if (rek.getNomorRekening().equals(cariNo)) {
                                akunAktif = rek;
                                ditemukan = true;
                                System.out.println("Berhasil beralih! Akun aktif sekarang: " + akunAktif.getNamaPemilik());
                                break;
                            }
                        }
                        
                        if (!ditemukan) {
                            System.out.println("Error: Nomor rekening tersebut tidak ditemukan!");
                        }
                    }
                    break;
                    
                case 6:
                    if (akunAktif == null) {
                        System.out.println("Error: Mohon maaf, Anda belum memiliki nomor rekening aktif!");
                    } else {
                        System.out.print("Masukkan PIN anda: ");
                        String pinInput = input.nextLine();
                        
                        if (akunAktif.otentikasi(pinInput)) {
                            akunAktif.cetakMutasi();
                        } else {
                            System.out.println("Akses Ditolak: PIN yang Anda masukkan salah!");
                        }
                    }
                    break;
                    
                case 7:
                    if (akunAktif == null) {
                        System.out.println("Error: Anda belum memiliki nomor rekening aktif!");
                    } else {
                        System.out.print("Masukkan PIN anda saat ini: ");
                        String pinSaatIni = input.nextLine();
                        
                        if (akunAktif.otentikasi(pinSaatIni)) {
                            System.out.print("Masukkan PIN Baru (6 digit angka): ");
                            String pinBaru = input.nextLine();
                            akunAktif.gantiPin(pinBaru);
                        } else {
                            System.out.println("Akses Ditolak: PIN yang Anda masukkan salah!");
                        }
                    }
                    break;
                    
                case 0:
                    isRunning = false;
                    System.out.println("Sistem ditutup. Terima kasih!");
                    break;
                    
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
        input.close();
    }
}