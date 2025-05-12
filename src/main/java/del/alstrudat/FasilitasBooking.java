package del.alstrudat;

public class FasilitasBooking implements Comparable<FasilitasBooking> {
    public final String kode;
    public final int kapasitasTerpakai;
    
    public FasilitasBooking(String kode, int kapasitasTerpakai) {
        this.kode = kode;
        this.kapasitasTerpakai = kapasitasTerpakai;
    }
    
    public FasilitasBooking copy() {
        return new FasilitasBooking(this.kode, this.kapasitasTerpakai);
    }
    
    @Override
    public int compareTo(FasilitasBooking other) {
        int capCompare = Integer.compare(other.kapasitasTerpakai, this.kapasitasTerpakai);
        return capCompare != 0 ? capCompare : this.kode.compareTo(other.kode);
    }
    
    @Override
    public String toString() {
        return kode + " (" + kapasitasTerpakai + " orang)";
    }
  }