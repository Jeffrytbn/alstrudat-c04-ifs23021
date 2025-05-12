package del.alstrudat;

public class DataKamar {
  public final String kode;
  public final String kelas;
  public final int kapasitas;
  public final int hargaPerMalam;
  
  public DataKamar(String kode, String kelas, int kapasitas, int hargaPerMalam) {
      this.kode = kode;
      this.kelas = kelas;
      this.kapasitas = kapasitas;
      this.hargaPerMalam = hargaPerMalam;
  }
}