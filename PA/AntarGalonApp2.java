import java.util.ArrayList;
import java.util.Scanner;

// Interface yang menentukan metode pelanggan
interface PelangganInterface {
    void tampilkanData();
}

// Abstract class untuk pelanggan
abstract class PelangganBase implements PelangganInterface {
    final String nama; // Final agar tidak bisa diubah
    final String alamat;
    int nomorTelepon;
    int jumlahGalon;
    static int totalPelanggan = 0; // Static untuk menyimpan jumlah pelanggan

    public PelangganBase(String nama, String alamat, int nomorTelepon, int jumlahGalon) {
        this.nama = nama;
        this.alamat = alamat;
        this.nomorTelepon = nomorTelepon;
        this.jumlahGalon = jumlahGalon;
        totalPelanggan++;
    }

    // Metode abstrak yang harus diimplementasikan oleh subclass
    abstract void jenisPelanggan();

    @Override
    public void tampilkanData() {
        System.out.println("Nama: " + nama);
        System.out.println("Alamat: " + alamat);
        System.out.println("Nomor Telepon: " + nomorTelepon);
        System.out.println("Jumlah Galon: " + jumlahGalon);
    }
}

// Subclass yang menggunakan Polimorfisme
class PelangganReguler extends PelangganBase {
    public PelangganReguler(String nama, String alamat, int nomorTelepon, int jumlahGalon) {
        super(nama, alamat, nomorTelepon, jumlahGalon);
    }

    @Override
    void jenisPelanggan() {
        System.out.println("Jenis: Pelanggan Reguler");
    }
}

class PelangganPremium extends PelangganBase {
    public PelangganPremium(String nama, String alamat, int nomorTelepon, int jumlahGalon) {
        super(nama, alamat, nomorTelepon, jumlahGalon);
    }

    @Override
    void jenisPelanggan() {
        System.out.println("Jenis: Pelanggan Premium");
    }
}

public class AntarGalonApp2 {
    static ArrayList<PelangganBase> daftarPelanggan = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nAplikasi Antar Galon:");
            System.out.println("1. Tambah Pelanggan");
            System.out.println("2. Lihat Daftar Pelanggan");
            System.out.println("3. Update Data Pelanggan");
            System.out.println("4. Hapus Pelanggan");
            System.out.println("5. Total Pelanggan");
            System.out.println("6. Keluar");

            int pilihan = getValidIntegerInput("Pilih opsi: ");

            switch (pilihan) {
                case 1 -> tambahPelanggan();
                case 2 -> lihatPelanggan();
                case 3 -> updatePelanggan();
                case 4 -> hapusPelanggan();
                case 5 -> System.out.println("Total pelanggan saat ini: " + PelangganBase.totalPelanggan);
                case 6 -> {
                    System.out.println("Keluar dari aplikasi.");
                    return;
                }
                default -> System.out.println("Pilihan tidak valid! Silakan coba lagi.");
            }
        }
    }

    static void tambahPelanggan() {
        System.out.print("Masukkan Nama: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan Alamat: ");
        String alamat = scanner.nextLine();

        int nomorTelepon = getValidIntegerInput("Masukkan Nomor Telepon: ");
        int jumlahGalon = getValidIntegerInput("Masukkan Jumlah Galon: ");

        System.out.println("Pilih jenis pelanggan:");
        System.out.println("1. Reguler");
        System.out.println("2. Premium");
        int jenis = getValidIntegerInput("Pilih opsi: ");

        PelangganBase pelanggan;
        if (jenis == 1) {
            pelanggan = new PelangganReguler(nama, alamat, nomorTelepon, jumlahGalon);
        } else {
            pelanggan = new PelangganPremium(nama, alamat, nomorTelepon, jumlahGalon);
        }

        daftarPelanggan.add(pelanggan);
        System.out.println("Pelanggan berhasil ditambahkan!");
    }

    static void lihatPelanggan() {
        if (daftarPelanggan.isEmpty()) {
            System.out.println("Belum ada pelanggan.");
        } else {
            for (int i = 0; i < daftarPelanggan.size(); i++) {
                System.out.println("\nPelanggan #" + (i + 1));
                daftarPelanggan.get(i).jenisPelanggan();
                daftarPelanggan.get(i).tampilkanData();
            }
        }
    }

    static void updatePelanggan() {
        lihatPelanggan();
        int indeks = getValidIntegerInput("Masukkan nomor pelanggan yang ingin di-update: ");

        if (indeks < 1 || indeks > daftarPelanggan.size()) {
            System.out.println("Nomor pelanggan tidak valid!");
            return;
        }

        PelangganBase pelanggan = daftarPelanggan.get(indeks - 1);
        System.out.print("Masukkan Nomor Telepon Baru: ");
        pelanggan.nomorTelepon = getValidIntegerInput("");
        System.out.print("Masukkan Jumlah Galon Baru: ");
        pelanggan.jumlahGalon = getValidIntegerInput("");

        System.out.println("Data pelanggan berhasil diperbarui!");
    }

    static void hapusPelanggan() {
        lihatPelanggan();
        int indeks = getValidIntegerInput("Masukkan nomor pelanggan yang ingin dihapus: ");

        if (indeks < 1 || indeks > daftarPelanggan.size()) {
            System.out.println("Nomor pelanggan tidak valid!");
            return;
        }

        daftarPelanggan.remove(indeks - 1);
        PelangganBase.totalPelanggan--; // Mengurangi jumlah pelanggan
        System.out.println("Pelanggan berhasil dihapus!");
    }

    static int getValidIntegerInput(String prompt) {
        int number;
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                number = scanner.nextInt();
                scanner.nextLine();
                return number;
            } else {
                System.out.println("Masukkan angka yang valid! Coba lagi.");
                scanner.nextLine();
            }
        }
    }
}
