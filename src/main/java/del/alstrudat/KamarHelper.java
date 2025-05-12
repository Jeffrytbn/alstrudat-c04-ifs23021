package del.alstrudat;


public class KamarHelper {
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
