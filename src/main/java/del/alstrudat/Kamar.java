package del.alstrudat;

public class Kamar implements Comparable<Kamar> {
    public String kode;
    public String kelas;
    public int kapasitas;
    public int hargaPerMalam;
    public boolean needsMaintenance;
    public LinkedList<String> fasilitas;
    public int jumlahPemesanan;

    public Kamar(String kode, String kelas, int kapasitas, int harga, String... fasilitas) {
        this.kode = kode;
        this.kelas = kelas;
        this.kapasitas = kapasitas;
        this.hargaPerMalam = harga;
        this.fasilitas = new LinkedList<>();
        for (String f : fasilitas) {
            this.fasilitas.add(f);
        }
    }

    public String getKode() {
        return kode;
    }

    public String getTipe() {
        return kelas;
    }

    public void tambahPemesan() {
        this.jumlahPemesanan++;
    }

    public Kamar copy() {
        return new Kamar(this.kode, this.kelas, this.kapasitas, this.hargaPerMalam);
    }

    @Override
    public int compareTo(Kamar other) {
        return this.kode.compareTo(other.kode);
    }

    @Override
    public String toString() {
        return kode + " | " + kelas + " | Rp" + hargaPerMalam + " / malam";
    }
}
