package del.alstrudat;

import java.time.LocalDate; 
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;

public class Reservasi {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public String idReservasi;
    public LinkedList<Kamar> kamar;
    public Pelanggan pelanggan;
    public String tanggalCheckIn;
    public String tanggalCheckOut;
    public boolean isCancelled;
    public double totalPembayaran;
    public LinkedList<FasilitasTambahan> fasilitasTambahan;
    
    public Reservasi(String id, Pelanggan pelanggan, String checkIn, String checkOut) {
        this.idReservasi = id;
        this.pelanggan = pelanggan;
        this.tanggalCheckIn = checkIn;
        this.tanggalCheckOut = checkOut;
        this.kamar = new LinkedList<>();
        this.fasilitasTambahan = new LinkedList<>();
    }

    public LinkedList<Kamar> getDaftarKamar() {
        return kamar;
    }

    public void tambahKamar(Kamar kamar) {
        this.kamar.add(kamar);
        hitungTotalPembayaran();
    }

    public void tambahFasilitas(FasilitasTambahan fasilitas) {
        this.fasilitasTambahan.add(fasilitas);
        hitungTotalPembayaran();
    }

    private void hitungTotalPembayaran() {
        double total = 0;

        for (LinkedList.Node<Kamar> k = kamar.getHead(); k != null; k = k.next) {
            long hari = ChronoUnit.DAYS.between(
                LocalDate.parse(tanggalCheckIn, DATE_FORMAT),
                LocalDate.parse(tanggalCheckOut, DATE_FORMAT)
            );
            total += k.data.hargaPerMalam * hari;
        }

        for (LinkedList.Node<FasilitasTambahan> f = fasilitasTambahan.getHead(); f != null; f = f.next) {
            total += f.data.harga;
        }

        if (kamar.size() > 1 || getDurasi() > 3) {
            total *= 0.9;
        }

        this.totalPembayaran = total;
    }

    public int getDurasi() {
        return (int) ChronoUnit.DAYS.between(
            LocalDate.parse(tanggalCheckIn, DATE_FORMAT),
            LocalDate.parse(tanggalCheckOut, DATE_FORMAT)
        );
    }

    public double batalkanReservasi() {
        this.isCancelled = true;
        long hariSebelumCheckIn = ChronoUnit.DAYS.between(
            LocalDate.now(),
            LocalDate.parse(tanggalCheckIn, DATE_FORMAT)
        );

        if (hariSebelumCheckIn > 7) {
            return totalPembayaran * 0.9;
        } else if (hariSebelumCheckIn > 0) {
            return totalPembayaran * 0.5;
        }
        return 0;
    }

    @Override
    public String toString() {
        return  "==============================\n" +
                "Reservasi ID     : " + idReservasi + "\n" +
                "Nama Pelanggan   : " + pelanggan.nama + "\n" +
                "Check-in         : " + tanggalCheckIn + "\n" +
                "Check-out        : " + tanggalCheckOut + "\n" +
                "Total Pembayaran : Rp" + totalPembayaran + "\n" +
                "==============================";
    }
}
