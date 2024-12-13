  /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author ASUS
 */
public class Anggota {
    private int idanggota;
    private String nama;
    private String alamat;
    private String telepon;

    // Constructor tanpa parameter
    public Anggota() {
    }

    // Constructor dengan parameter
    public Anggota(String nama, String alamat, String telepon) {
        this.nama = nama;
        this.alamat = alamat;
        this.telepon = telepon;
    }

    // Getter
    public int getIdanggota() {
        return idanggota;
    }

    public String getNama() {
        return nama;
    }
    
    @Override
     //    menambahkan nama anggota pada cmbAnggota di class buku
    public String toString(){
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getTelepon() {
        return telepon;
    }

    // Setter
    public void setIdanggota(int idanggota) {
        this.idanggota = idanggota;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    // Method untuk mendapatkan anggota berdasarkan ID
    public Anggota getById(int id) {
        Anggota anggota = new Anggota();
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM anggota WHERE idanggota = " + id + ";");
        try {
            while (rs.next()) {
                anggota = new Anggota();
                anggota.setIdanggota(rs.getInt("idanggota"));
                anggota.setNama(rs.getString("nama"));
                anggota.setAlamat(rs.getString("alamat"));
                anggota.setTelepon(rs.getString("telepon"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return anggota;
    }

    // Method untuk mendapatkan semua anggota
    public ArrayList<Anggota> getAll() {
        ArrayList<Anggota> ListAnggota = new ArrayList<>();
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM anggota");
        try {
            while (rs.next()) {
                Anggota anggota = new Anggota();
                anggota.setIdanggota(rs.getInt("idanggota"));
                anggota.setNama(rs.getString("nama"));
                anggota.setAlamat(rs.getString("alamat"));
                anggota.setTelepon(rs.getString("telepon"));
                ListAnggota.add(anggota);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ListAnggota;
    }

    // Method untuk mencari anggota berdasarkan keyword
    public ArrayList<Anggota> search(String keyword) {
        ArrayList<Anggota> ListAnggota = new ArrayList<>();
        String sql = "SELECT * FROM anggota WHERE " +
                    "nama LIKE '%" + keyword + "%' " +
                    "OR alamat LIKE '%" + keyword + "%' " +
                    "OR telepon LIKE '%" + keyword + "%'";
        ResultSet rs = DBHelper.selectQuery(sql);
        try {
            while (rs.next()) {
                Anggota anggota = new Anggota();
                anggota.setIdanggota(rs.getInt("idanggota"));
                anggota.setNama(rs.getString("nama"));
                anggota.setAlamat(rs.getString("alamat"));
                anggota.setTelepon(rs.getString("telepon"));
                ListAnggota.add(anggota);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ListAnggota;
    }

    // Method untuk menyimpan data anggota
    public void save() {
        if (getById(idanggota).getIdanggota() == 0) {
            String SQL = "INSERT INTO anggota (nama, alamat, telepon) VALUES('" +
                        this.nama + "', '" +
                        this.alamat + "', '" +
                        this.telepon + "')";
            this.idanggota = DBHelper.insertQueryGetId(SQL);
        } else {
            String SQL = "UPDATE anggota SET " +
                        "nama = '" + this.nama + "', " +
                        "alamat = '" + this.alamat + "', " +
                        "telepon = '" + this.telepon + "' " +
                        "WHERE idanggota = " + this.idanggota + ";";
            DBHelper.executeQuery(SQL);  
        }
    }

    // Method untuk menghapus data anggota
    public void delete() {
        String SQL = "DELETE FROM anggota WHERE idanggota = '" + this.idanggota + "'";
        DBHelper.executeQuery(SQL);
    }
}
