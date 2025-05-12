package del.alstrudat;



public class FasilitasTambahan {
  public String kode;
  public String nama;
  public double harga;
  
  public FasilitasTambahan(String kode, String nama, double harga) {
      this.kode = kode;
      this.nama = nama;
      this.harga = harga;
  }

  @Override
public String toString() {
    return kode + " - " + nama + " (Rp" + harga + ")";
}

}
