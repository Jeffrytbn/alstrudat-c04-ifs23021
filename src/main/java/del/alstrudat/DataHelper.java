package del.alstrudat;


public class DataHelper {
    public static Kamar cariKamar(String kode, LinkedList<Kamar> semuaKamar) {
        for (LinkedList.Node<Kamar> k = semuaKamar.getHead(); k != null; k = k.next) {
            if (k.data.kode.equalsIgnoreCase(kode)) return k.data;
        }
        return null;
    }

    public static FasilitasTambahan cariFasilitas(String kode, LinkedList<FasilitasTambahan> semuaFasilitas) {
        for (LinkedList.Node<FasilitasTambahan> f = semuaFasilitas.getHead(); f != null; f = f.next) {
            if (f.data.kode.equalsIgnoreCase(kode)) return f.data;
        }
        return null;
    }
}

