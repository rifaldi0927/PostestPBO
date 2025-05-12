# Mendefinisikan kelas Mobil
class Mobil:
    def __init__(self, merk, warna, tahun):
        self.merk = merk  # Atribut merk
        self.warna = warna  # Atribut warna
        self.tahun = tahun  # Atribut tahun produksi

    def info(self):
        return f"Mobil {self.merk} berwarna {self.warna}, tahun produksi {self.tahun}."

    def ubah_warna(self, warna_baru):
        self.warna = warna_baru
        return f"Warna mobil {self.merk} telah diubah menjadi {self.warna}."

# Membuat beberapa objek dari kelas Mobil
mobil1 = Mobil("Toyota", "Merah", 2020)
mobil2 = Mobil("Honda", "Biru", 2018)

# Menampilkan informasi awal mobil
print(mobil1.info())
print(mobil2.info())

# Mengubah warna mobil1
print(mobil1.ubah_warna("Hitam"))

# Menampilkan informasi setelah perubahan warna
print(mobil1.info())
