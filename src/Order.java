public class Order {
    private int id;
    private Voucher voucher;
    private Handphone handphone;
    private int jumlah;
    public static int total = 0;

    public Order(int id, Voucher voucher, int jumlah) {
        this.id = id;
        this.voucher = voucher;
        this.jumlah = jumlah;
        total++;
    }

    public Order(int id, Handphone handphone, int jumlah) {
        this.id = id;
        this.handphone = handphone;
        this.jumlah = jumlah;
        total++;
    }

    public int getId() {
        return id;
    }

    public Voucher getVoucher() {
        return voucher;
    }

    public Handphone getHandphone() {
        return handphone;
    }

    public int getJumlah() {
        return jumlah;
    }
}
