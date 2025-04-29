package del.alstrudat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ReservasiHelper {
    public static void buatReservasi(Scanner input, LinkedList<Kamar> semuaKamar,
                                     LinkedList<FasilitasTambahan> semuaFasilitas,
                                     LinkedList<Reservasi> semuaReservasi) {
        System.out.print("Masukkan nama pelanggan: ");
        String nama = input.nextLine();
        System.out.print("Masukkan no telp: ");
        String telp = input.nextLine();
        System.out.print("Masukkan ID dokumen: ");
        String id = input.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate checkIn = null, checkOut = null;

        while (checkIn == null) {
            try {
                System.out.print("Masukkan tanggal check-in (dd-MM-yyyy): ");
                checkIn = LocalDate.parse(input.nextLine(), formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Format tanggal tidak valid. Coba lagi.");
            }
        }
        while (checkOut == null) {
            try {
                System.out.print("Masukkan tanggal check-out (dd-MM-yyyy): ");
                checkOut = LocalDate.parse(input.nextLine(), formatter);
                if (checkOut.isBefore(checkIn)) {
                    System.out.println("Tanggal check-out tidak boleh sebelum check-in. Coba lagi.");
                    checkOut = null;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Format tanggal tidak valid. Coba lagi.");
            }
        }

        Pelanggan pelanggan = new Pelanggan("P" + (int)(Math.random() * 100), nama, telp, id);
        Reservasi reservasi = new Reservasi("RES" + (int)(Math.random() * 1000),
                                            pelanggan,
                                            checkIn.format(formatter),
                                            checkOut.format(formatter));

        System.out.println("Daftar kamar:");
        semuaKamar.printList();
        System.out.print("Masukkan kode kamar yang ingin dipesan: ");
        String kodeKamar = input.nextLine();
        Kamar kamarDipilih = DataHelper.cariKamar(kodeKamar, semuaKamar);

        if (kamarDipilih != null) {
            reservasi.tambahKamar(kamarDipilih);
        } else {
            System.out.println("Kamar tidak ditemukan.");
            return;
        }

        System.out.println("Tambahkan fasilitas tambahan? (Y/n): ");
        if (input.nextLine().equalsIgnoreCase("y")) {
            semuaFasilitas.printList();
            System.out.print("Masukkan kode fasilitas: ");
            String kodeF = input.nextLine();
            FasilitasTambahan f = DataHelper.cariFasilitas(kodeF, semuaFasilitas);
            if (f != null) {
                reservasi.tambahFasilitas(f);
            } else {
                System.out.println("Fasilitas tidak ditemukan.");
            }
        }

        semuaReservasi.add(reservasi);
        System.out.println("Reservasi berhasil ditambahkan!");
        System.out.println("Detail:");
        System.out.println(reservasi);
    }
}
