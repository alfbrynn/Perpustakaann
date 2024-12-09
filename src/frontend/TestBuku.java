/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frontend;

import backend.Buku;
import backend.Kategori;

/**
 *
 * @author ASUS
 */
public class TestBuku {
    public static void main(String[] args) {
        // Test insert
        Kategori kat1 = new Kategori().getById(1); // Pastikan idkategori ini ada di database
        Kategori kat2 = new Kategori().getById(2);

        Buku buku1 = new Buku("Laskar Pelangi", "Bentang Pustaka", "Andrea Hirata");
        buku1.setKategori(kat1);
        buku1.save();

        Buku buku2 = new Buku("Pemrograman Java", "Informatika", "Eko Kurniawan");
        buku2.setKategori(kat2);
        buku2.save();

        Buku buku3 = new Buku("Komik Naruto", "Elex Media", "Masashi Kishimoto");
        buku3.setKategori(kat1);
        buku3.save();

        // Test update
        buku2.setJudul("Pemrograman Java Lengkap");
        buku2.save();

        // Test delete
        buku3.delete();

        // Test select all
        for (Buku b : new Buku().getAll()) {
            System.out.println("Judul: " + b.getJudul() + ", Kategori: " + b.getKategori().getNama() + 
                ", Penulis: " + b.getPenulis() + ", Penerbit: " + b.getPenerbit());
        }

        // Test search
        for (Buku b : new Buku().search("Java")) {
            System.out.println("Hasil Pencarian -> Judul: " + b.getJudul() + ", Kategori: " + b.getKategori().getNama() + 
                ", Penulis: " + b.getPenulis() + ", Penerbit: " + b.getPenerbit());
        }
    }
}
