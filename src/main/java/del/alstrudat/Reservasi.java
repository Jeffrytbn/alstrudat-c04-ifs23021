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

  //lengkapi method 

}