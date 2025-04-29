package del.alstrudat;

public class Data {
  public static void initializeData(LinkedList<Kamar> semuaKamar, LinkedList<FasilitasTambahan> semuaFasilitas) {
      semuaKamar.add(new Kamar("A101", "Suite", 4, 500, "AC", "TV", "Mini Bar"));
      semuaKamar.add(new Kamar("B202", "Deluxe", 2, 300, "AC", "TV"));
      semuaKamar.add(new Kamar("C303", "Standar", 2, 200, "AC"));
      semuaKamar.add(new Kamar("D404", "Executive", 3, 400, "AC", "TV", "Coffee Maker"));
      semuaKamar.add(new Kamar("E505", "Presidential", 5, 800, "AC", "TV", "Mini Bar", "Jacuzzi"));

      semuaFasilitas.add(new FasilitasTambahan("F01", "WiFi", 50));
      semuaFasilitas.add(new FasilitasTambahan("F02", "Air Mineral", 20));
      semuaFasilitas.add(new FasilitasTambahan("F03", "Sarapan Pagi", 75));
      semuaFasilitas.add(new FasilitasTambahan("F04", "Layanan Laundry", 100));
  }
}