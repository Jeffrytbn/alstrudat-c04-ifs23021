package del.alstrudat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class App {
    static LinkedList<Kamar> semuaKamar = new LinkedList<>();
    static LinkedList<FasilitasTambahan> semuaFasilitas = new LinkedList<>();
    static LinkedList<Reservasi> semuaReservasi = new LinkedList<>();

    public static void main(String[] args) {
        initializeData();
        Scanner input = new Scanner(System.in);
        int pilihan = -1;

        do {
            System.out.println(" MENU UTAMA ");
            System.out.println("1. Lihat Semua Kamar");
            System.out.println("2. Lihat Semua Fasilitas");
            System.out.println("3. Buat Reservasi Baru");
            System.out.println("4. Lihat Semua Reservasi");
            System.out.println("0. Keluar");

            boolean inputValid = false;
            while (!inputValid) {
                try {
                    System.out.println("Pilih menu: ");
                    pilihan = Integer.parseInt(input.nextLine());
                    inputValid = true;
                } catch (NumberFormatException e) {
                    System.out.println("Input tidak valid. Harap masukkan angka.");
                }
            }

            switch (pilihan) {
                case 1:
                    tampilkanSemuaKamar(semuaKamar, semuaReservasi);
                    break;
                case 2:
                    semuaFasilitas.printList();
                    break;
                case 3:
                    buatReservasi(input);
                    break;
                case 4:
                    if (semuaReservasi.getHead() == null) {
                        System.out.println("Belum ada reservasi yang tercatat.");
                    } else {
                        semuaReservasi.printList();
                    }
                    break;
                case 0:
                    System.out.println("Sampai jumpa, king.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 0);
    }

    public static void initializeData() {
        // Tambahkan data kamar
        semuaKamar.add(new Kamar("A101", "Suite", 4, 500, "AC", "TV", "Mini Bar"));
        semuaKamar.add(new Kamar("B202", "Deluxe", 2, 300, "AC", "TV"));
        semuaKamar.add(new Kamar("C303", "Standar", 2, 200, "AC"));
        semuaKamar.add(new Kamar("D404", "Executive", 3, 400, "AC", "TV", "Coffee Maker"));
        semuaKamar.add(new Kamar("E505", "Presidential", 5, 800, "AC", "TV", "Mini Bar", "Jacuzzi"));
        semuaKamar.add(new Kamar("F606", "Single", 1, 150, "Fan"));
        semuaKamar.add(new Kamar("G707", "Double", 2, 250, "AC", "TV", "Balcony"));
        semuaKamar.add(new Kamar("H808", "Deluxe Garden", 3, 350, "AC", "TV", "Garden View"));
    
        // Tambahkan data fasilitas tambahan
        semuaFasilitas.add(new FasilitasTambahan("F01", "WiFi", 50));
        semuaFasilitas.add(new FasilitasTambahan("F02", "Air Mineral", 20));
        semuaFasilitas.add(new FasilitasTambahan("F03", "Sarapan Pagi", 75));
        semuaFasilitas.add(new FasilitasTambahan("F04", "Setrika", 30));
        semuaFasilitas.add(new FasilitasTambahan("F05", "Layanan Laundry", 100));
        semuaFasilitas.add(new FasilitasTambahan("F06", "Akses Kolam Renang", 60));
        semuaFasilitas.add(new FasilitasTambahan("F07", "Parkir VIP", 40));
        semuaFasilitas.add(new FasilitasTambahan("F08", "Kursi Roda", 0)); 
    }
    

    static void buatReservasi(Scanner input) {
        System.out.print("Masukkan nama pelanggan: ");
        String nama = input.nextLine();
        System.out.print("Masukkan no telp: ");
        String telp = input.nextLine();
        System.out.print("Masukkan ID dokumen: ");
        String id = input.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate checkIn = null;
        LocalDate checkOut = null;

        while (checkIn == null) {
            try {
                System.out.print("Masukkan tanggal check-in (dd-MM-yyyy): ");
                String checkInStr = input.nextLine();
                checkIn = LocalDate.parse(checkInStr, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Format tanggal tidak valid. Coba lagi.");
            }
        }

        while (checkOut == null) {
            try {
                System.out.print("Masukkan tanggal check-out (dd-MM-yyyy): ");
                String checkOutStr = input.nextLine();
                checkOut = LocalDate.parse(checkOutStr, formatter);
                if (checkOut.isBefore(checkIn)) {
                    System.out.println("Tanggal check-out tidak boleh sebelum check-in. Coba lagi.");
                    checkOut = null;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Format tanggal tidak valid. Coba lagi.");
            }
        }

        Pelanggan pelanggan = new Pelanggan("P" + (int)(Math.random() * 100), nama, telp, id);
        System.out.print("Masukkan ID Reservasi: ");
        String idReservasi = input.nextLine();
        Reservasi reservasi = new Reservasi(
            idReservasi,
            pelanggan,
            checkIn.format(formatter),
            checkOut.format(formatter)
        );
        


        // Pilih kamar
        System.out.println("Daftar kamar:");
        semuaKamar.printList();
        System.out.print("Masukkan kode kamar yang ingin dipesan: ");
        String kodeKamar = input.nextLine();
        Kamar kamarDipilih = cariKamar(kodeKamar);
        if (kamarDipilih != null) {
            reservasi.tambahKamar(kamarDipilih);
        } else {
            System.out.println("Kamar tidak ditemukan.");
            return;
        }

        // Pilih fasilitas
        System.out.print("Tambahkan fasilitas tambahan? (Y/n): ");
        String ya = input.nextLine();
        if (ya.equalsIgnoreCase("y")) {
            semuaFasilitas.printList();
            System.out.print("Masukkan kode fasilitas: ");
            String kodeF = input.nextLine();
            FasilitasTambahan f = cariFasilitas(kodeF);
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

    static Kamar cariKamar(String kode) {
        for (LinkedList.Node<Kamar> k = semuaKamar.getHead(); k != null; k = k.next) {
            if (k.data.kode.equalsIgnoreCase(kode)) return k.data;
        }
        return null;
    }

    static FasilitasTambahan cariFasilitas(String kode) {
        for (LinkedList.Node<FasilitasTambahan> f = semuaFasilitas.getHead(); f != null; f = f.next) {
            if (f.data.kode.equalsIgnoreCase(kode)) return f.data;
        }
        return null;
    }

    public static void tampilkanSemuaKamar(LinkedList<Kamar> semuaKamar, LinkedList<Reservasi> semuaReservasi) {
        System.out.println(" DAFTAR KAMAR ");
        for (int i = 0; i < semuaKamar.size(); i++) {
            Kamar kamar = semuaKamar.get(i);
            boolean dibooking = false;
            for (int j = 0; j < semuaReservasi.size(); j++) {
                Reservasi r = semuaReservasi.get(j);
                if (r.getDaftarKamar().contains(kamar)) {
                    dibooking = true;
                    break;
                }
            }
            String status = dibooking ? "DIBOOKING" : "TERSEDIA";
            System.out.println(kamar.getKode() + " - " + kamar.getTipe() + " | Status: " + status);
        }
    }
}

