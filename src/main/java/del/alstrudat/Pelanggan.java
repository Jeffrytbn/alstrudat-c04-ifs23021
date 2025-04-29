package del.alstrudat;



public class Pelanggan {
  public String id;
  public String nama;
  public String noTelp;
  public String idDokumen;
  
  public Pelanggan(String id, String nama, String noTelp, String idDokumen) {
      this.id = id;
      this.nama = nama;
      this.noTelp = noTelp;
      this.idDokumen = idDokumen;
  }
  
  public void updateInfo(String noTelpBaru, String idDokumenBaru) {
      this.noTelp = noTelpBaru;
      this.idDokumen = idDokumenBaru;
  }
}