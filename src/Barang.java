public class Barang {
    protected int id;
    protected double harga;
    protected String nama;
    protected int stok;

    public Barang(int id, double harga, String nama, int stok) {
        this.id = id;
        this.harga = harga;
        this.nama = nama;
        this.stok = stok;
    }

    public getId() {
        return id;
    }

    public getHarga() {
        return harga;
    }

    public getNama() {
        return nama;
    }

    public getStok() {
        return stok;
    }

    public void minusStok(int jml) {
        if (jml <= stok) {
            stok -= jml;
        } else {
            System.out.println("Stok tidak cukup!");
        }
    }
}

