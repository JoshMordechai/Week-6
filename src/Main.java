import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Handphone> handphones = new ArrayList<Handphone>();
    static ArrayList<Voucher> vouchers = new ArrayList<Voucher>();
    static ArrayList<Order> orders = new ArrayList<Order>();
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        // Tambahkan data awal agar bisa langsung dites
        handphones.add(new Handphone(1, "Samsung Galaxy A15", 2500000, 5, "Hitam"));
        handphones.add(new Handphone(2, "iPhone 13", 9500000, 3, "Putih"));
        vouchers.add(new Voucher(1, "Pulsa Telkomsel 50K", 50000, 10, 10));
        vouchers.add(new Voucher(2, "Pulsa XL 25K", 25000, 15, 10));

        int pilihan;

        do {
            System.out.println("\n=========== MENU TOKO VOUCHER & HP ===========");
            System.out.println("1. Pesan Barang");
            System.out.println("2. Lihat Pesanan");
            System.out.println("3. Barang Baru");
            System.out.println("0. Keluar");
            System.out.print("Pilihan: ");
            pilihan = input.nextInt();
            input.nextLine(); // clear buffer

            switch (pilihan) {
                case 1:
                    pesanBarang();
                    break;
                case 2:
                    lihatPesanan();
                    break;
                case 3:
                    tambahBarangBaru();
                    break;
                case 0:
                    System.out.println("Keluar dari program...");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } while (pilihan != 0);
    }

    // ================= PESAN BARANG =================
    static void pesanBarang() {
        System.out.println("\nDaftar Barang:");
        System.out.println("1. Handphone");
        System.out.println("2. Voucher");
        System.out.print("Pilih jenis barang: ");
        int jenis = input.nextInt();

        if (jenis == 1) {
            tampilkanHandphone();
            System.out.print("Masukkan ID Handphone yang ingin dibeli (0 untuk batal): ");
            int id = input.nextInt();
            if (id == 0) return;

            Handphone h = cariHandphone(id);
            if (h == null) {
                System.out.println("Barang tidak ditemukan!");
                return;
            }

            System.out.print("Masukkan jumlah: ");
            int jumlah = input.nextInt();
            if (jumlah > h.getStok()) {
                System.out.println("Stok tidak mencukupi!");
                return;
            }

            double total = h.getHarga() * jumlah;
            System.out.println(jumlah + " @ " + h.getNama() + " total harga Rp " + total);
            System.out.print("Masukkan jumlah uang: ");
            double uang = input.nextDouble();

            if (uang < total) {
                System.out.println("Jumlah uang tidak mencukupi!");
                return;
            }

            h.minusStok(jumlah);
            orders.add(new Order(orders.size() + 1, h, jumlah));
            System.out.println("Berhasil dipesan!");
            System.out.println("Kembalian Anda: Rp " + (uang - total));
        } 
        else if (jenis == 2) {
            tampilkanVoucher();
            System.out.print("Masukkan ID Voucher yang ingin dibeli (0 untuk batal): ");
            int id = input.nextInt();
            if (id == 0) return;

            Voucher v = cariVoucher(id);
            if (v == null) {
                System.out.println("Barang tidak ditemukan!");
                return;
            }

            System.out.print("Masukkan jumlah: ");
            int jumlah = input.nextInt();
            if (jumlah > v.getStok()) {
                System.out.println("Stok tidak mencukupi!");
                return;
            }

            double total = v.getHarga() * jumlah;
            System.out.println(jumlah + " @ " + v.getNama() + " total harga Rp " + total);
            System.out.print("Masukkan jumlah uang: ");
            double uang = input.nextDouble();

            if (uang < total) {
                System.out.println("Jumlah uang tidak mencukupi!");
                return;
            }

            v.minusStok(jumlah);
            orders.add(new Order(orders.size() + 1, v, jumlah));
            System.out.println("Berhasil dipesan!");
            System.out.println("Kembalian Anda: Rp " + (uang - total));
        } 
        else {
            System.out.println("Pilihan tidak valid!");
        }
    }

    // ================= LIHAT PESANAN =================
    static void lihatPesanan() {
        System.out.println("\n========== DAFTAR PESANAN ==========");
        if (orders.isEmpty()) {
            System.out.println("Belum ada pesanan.");
            return;
        }

        for (Order o : orders) {
            System.out.println("ID Pesanan: " + o.getId());
            if (o.getHandphone() != null) {
                System.out.println("Nama Barang: " + o.getHandphone().getNama());
                System.out.println("Jumlah: " + o.getJumlah());
            } else if (o.getVoucher() != null) {
                System.out.println("Nama Barang: " + o.getVoucher().getNama());
                System.out.println("Jumlah: " + o.getJumlah());
            }
            System.out.println("-----------------------------------");
        }
    }

    // ================= TAMBAH BARANG BARU =================
    static void tambahBarangBaru() {
        System.out.print("Tambah Voucher / Handphone (V/H): ");
        String jenis = input.nextLine().toUpperCase();

        System.out.print("Nama: ");
        String nama = input.nextLine();
        System.out.print("Harga: ");
        double harga = input.nextDouble();
        System.out.print("Stok: ");
        int stok = input.nextInt();

        if (jenis.equals("V")) {
            System.out.print("PPN (%): ");
            double pajak = input.nextDouble();
            vouchers.add(new Voucher(vouchers.size() + 1, nama, harga, stok, pajak));
            System.out.println("Voucher baru berhasil ditambahkan!");
        } else if (jenis.equals("H")) {
            input.nextLine(); // clear buffer
            System.out.print("Warna: ");
            String warna = input.nextLine();
            handphones.add(new Handphone(handphones.size() + 1, nama, harga, stok, warna));
            System.out.println("Handphone baru berhasil ditambahkan!");
        } else {
            System.out.println("Jenis tidak dikenali!");
        }
    }

    // ================= FUNGSI TAMBAHAN =================
    static void tampilkanHandphone() {
        System.out.println("\n===== Daftar Handphone =====");
        for (Handphone h : handphones) {
            System.out.println("ID: " + h.getId());
            System.out.println("Nama: " + h.getNama());
            System.out.println("Warna: " + h.getWarna());
            System.out.println("Harga: Rp " + h.getHarga());
            System.out.println("Stok: " + h.getStok());
            System.out.println("------------------------------");
        }
    }

    static void tampilkanVoucher() {
        System.out.println("\n===== Daftar Voucher =====");
        for (Voucher v : vouchers) {
            System.out.println("ID: " + v.getId());
            System.out.println("Nama: " + v.getNama());
            System.out.println("Harga Dasar: Rp " + v.getHarga());
            System.out.println("Harga Jual (pajak): Rp " + v.getHarga());
            System.out.println("Stok: " + v.getStok());
            System.out.println("------------------------------");
        }
    }

    static Handphone cariHandphone(int id) {
        for (Handphone h : handphones) {
            if (h.getId() == id) return h;
        }
        return null;
    }

    static Voucher cariVoucher(int id) {
        for (Voucher v : vouchers) {
            if (v.getId() == id) return v;
        }
        return null;
    }
}
