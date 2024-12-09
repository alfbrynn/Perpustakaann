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

public class Pegawai {
    private int idPegawai;
    private String nama;
    private String alamat;
    private String noTelp;
    private String jabatan;
    private String username;
    private String password;

    // Konstruktor
    public Pegawai() {
    }

    public Pegawai(String nama, String alamat, String noTelp, String jabatan, String username, String password) {
        this.nama = nama;
        this.alamat = alamat;
        this.noTelp = noTelp;
        this.jabatan = jabatan;
        this.username = username;
        this.password = password;
    }

    // Getter dan Setter
    public int getIdPegawai() {
        return idPegawai;
    }

    public void setIdPegawai(int idPegawai) {
        this.idPegawai = idPegawai;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Method untuk mendapatkan data berdasarkan ID
    public Pegawai getById(int id) {
        Pegawai peg = new Pegawai();
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM pegawai WHERE idPegawai = " + id);
        try {
            while (rs.next()) {
                peg = new Pegawai();
                peg.setIdPegawai(rs.getInt("idPegawai"));
                peg.setNama(rs.getString("nama"));
                peg.setAlamat(rs.getString("alamat"));
                peg.setNoTelp(rs.getString("noTelp"));
                peg.setJabatan(rs.getString("jabatan"));
                peg.setUsername(rs.getString("username"));
                peg.setPassword(rs.getString("password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return peg;
    }

    // Method untuk mendapatkan semua data pegawai
    public ArrayList<Pegawai> getAll() {
        ArrayList<Pegawai> ListPegawai = new ArrayList<>();
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM pegawai");
        try {
            while (rs.next()) {
                Pegawai peg = new Pegawai();
                peg.setIdPegawai(rs.getInt("idPegawai"));
                peg.setNama(rs.getString("nama"));
                peg.setAlamat(rs.getString("alamat"));
                peg.setNoTelp(rs.getString("noTelp"));
                peg.setJabatan(rs.getString("jabatan"));
                peg.setUsername(rs.getString("username"));
                peg.setPassword(rs.getString("password"));
                ListPegawai.add(peg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ListPegawai;
    }

    // Metode untuk menyimpan data pegawai
    public void save() {
        if (getById(idPegawai).getIdPegawai() == 0) {
            String SQL = "INSERT INTO pegawai (nama, alamat, noTelp, jabatan, username, password) VALUES('" +
                    this.nama + "', '" +
                    this.alamat + "', '" +
                    this.noTelp + "', '" +
                    this.jabatan + "', '" +
                    this.username + "', '" +
                    this.password + "')";
            this.idPegawai = DBHelper.insertQueryGetId(SQL);
        } else {
            String SQL = "UPDATE pegawai SET " +
                    "nama = '" + this.nama + "', " +
                    "alamat = '" + this.alamat + "', " +
                    "noTelp = '" + this.noTelp + "', " +
                    "jabatan = '" + this.jabatan + "', " +
                    "username = '" + this.username + "', " +
                    "password = '" + this.password + "' " +
                    "WHERE idPegawai = " + this.idPegawai;
            DBHelper.executeQuery(SQL);
        }
    }

    // Metode untuk menghapus data pegawai
    public void delete() {
        String SQL = "DELETE FROM pegawai WHERE idPegawai = '" + this.idPegawai + "'";
        DBHelper.executeQuery(SQL);
    }
}
