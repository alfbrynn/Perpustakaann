/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

/**
 *
 * @author ASUS
 */
import java.util.ArrayList;
import java.sql.*;

public class Buku {
    private int idbuku;
    private Kategori kategori = new Kategori();
    private String judul;
    private String penerbit;
    private String penulis;

    public int getIdbuku() {
        return idbuku;
    }

    public Kategori getKategori() {
        return kategori;
    }

    public String getJudul() {
        return judul;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setIdbuku(int idBuku) {
        this.idbuku = idBuku;
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public Buku() {
    }

    public Buku(String judul, String penerbit, String penulis) {
        this.judul = judul;
        this.penerbit = penerbit;
        this.penulis = penulis;
    }
    
    public Buku getById(int id) {
    Buku buku = new Buku();
    ResultSet rs = DBHelper.selectQuery("SELECT "
        + " b.idbuku AS idbuku, "
        + " b.judul AS judul, "
        + " b.penerbit AS penerbit, "
        + " b.penulis AS penulis, "
        + "k.idkategori AS idkategori, "
        + "k.nama AS nama, "
        + "k.keterangan AS keterangan "
        + "FROM buku b "
        + "LEFT JOIN kategori k ON b.idkategori = k.idkategori "
        + "WHERE b.idbuku = '" + id + "'");
    
    try {
        if (rs.next()) { // Tambahkan validasi
            buku.setIdbuku(rs.getInt("idbuku"));
            buku.getKategori().setIdkategori(rs.getInt("idkategori"));
            buku.getKategori().setNama(rs.getString("nama"));
            buku.getKategori().setKeterangan(rs.getString("keterangan"));
            buku.setJudul(rs.getString("judul"));
            buku.setPenerbit(rs.getString("penerbit"));
            buku.setPenulis(rs.getString("penulis"));
        } else {
            System.out.println("Data tidak ditemukan untuk idbuku: " + id);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return buku;
}
    
    public ArrayList<Buku> getAll(){
        ArrayList<Buku> ListBuku = new ArrayList();
        
        ResultSet rs = DBHelper.selectQuery("SELECT "
        + " b.idbuku AS idbuku, "
        + " b.judul AS judul, "
        + " b.penerbit AS penerbit, "
        + " b.penulis AS penulis, "
        + "k.idkategori AS idkategori, "
        + "k.nama AS nama, "
        + "k.keterangan AS keterangan "
        + "FROM buku b "
        + "LEFT JOIN kategori k ON b.idkategori = k.idkategori ");
        
        try{
            while(rs.next()){
                Buku buku = new Buku();
                buku.setIdbuku(rs.getInt("idbuku"));
                buku.getKategori().setIdkategori(rs.getInt("idkategori"));
                buku.getKategori().setNama(rs.getString("nama"));
                buku.getKategori().setKeterangan(rs.getString("keterangan"));
                buku.setJudul(rs.getString("judul"));
                buku.setPenerbit(rs.getString("penerbit"));
                buku.setPenulis(rs.getString("penulis"));
                ListBuku.add(buku); // Tambahkan objek ke ArrayList
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ListBuku;
    }
    
    public ArrayList<Buku> search(String keyword){
        ArrayList<Buku> ListBuku = new ArrayList();
        
        ResultSet rs = DBHelper.selectQuery("SELECT "
        + " b.idbuku AS idbuku, "
        + " b.judul AS judul, "
        + " b.penerbit AS penerbit, "
        + " b.penulis AS penulis, "
        + "k.idkategori AS idkategori, "
        + "k.nama AS nama, "
        + "k.keterangan AS keterangan "
        + "FROM buku b "
        + "LEFT JOIN kategori k ON b.idkategori = k.idkategori "
        + "WHERE b.judul LIKE '%" + keyword + "%' "
        + " OR b.penerbit LIKE '%" + keyword + "%' "
        + " OR b.penulis LIKE '%" + keyword + "%' ");
        
        try{
            while(rs.next()){
                Buku buku = new Buku();
                buku.setIdbuku(rs.getInt("idbuku"));
                buku.getKategori().setIdkategori(rs.getInt("idkategori"));
                buku.getKategori().setNama(rs.getString("nama"));
                buku.getKategori().setKeterangan(rs.getString("keterangan"));
                buku.setJudul(rs.getString("judul"));
                buku.setPenerbit(rs.getString("penerbit"));
                buku.setPenulis(rs.getString("penulis"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ListBuku;
    }
   
    public void save() {
    // Validasi apakah idkategori valid
    ResultSet rs = DBHelper.selectQuery("SELECT * FROM kategori WHERE idkategori = " + this.getKategori().getIdkategori());
    try {
        if (rs.next()) { // Jika idkategori ditemukan
            if (getById(idbuku).getIdbuku() == 0) {
                String SQL = "INSERT INTO buku (judul, idkategori, penulis, penerbit) VALUES ("
                    + "'" + this.judul + "'" + ", "
                    + this.getKategori().getIdkategori() + ", "
                    + "'" + this.penulis + "'" + ", "
                    + "'" + this.penerbit + "'" + ")";
                this.idbuku = DBHelper.insertQueryGetId(SQL);
            } else {
                String SQL = "UPDATE buku SET "
                    + "judul = '" + this.judul + "', "
                    + "idkategori = " + this.getKategori().getIdkategori() + ", "
                    + "penulis = '" + this.penulis + "', "
                    + "penerbit = '" + this.penerbit + "' "
                    + "WHERE idbuku = " + this.idbuku;
                DBHelper.executeQuery(SQL);
            }
        } else {
            System.out.println("Error: idkategori tidak valid. Pastikan idkategori sudah ada di tabel kategori.");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}


    public void delete() {
        String SQL = "DELETE FROM buku WHERE idbuku = " + this.idbuku;
        DBHelper.executeQuery(SQL);
    }
}
