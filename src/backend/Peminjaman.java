/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Peminjaman {
    private int idpeminjaman;
    private Anggota anggota = new Anggota();
    private Buku buku = new Buku();
    private Pegawai pegawai = new Pegawai();
    private LocalDate tanggalPinjam;
    private LocalDate tanggalKembali;

    // Getters and Setters
    public int getIdpeminjaman() {
        return idpeminjaman;
    }

    public void setIdpeminjaman(int idpeminjaman) {
        this.idpeminjaman = idpeminjaman;
    }

    public Anggota getAnggota() {
        return anggota;
    }

    public void setAnggota(Anggota anggota) {
        this.anggota = anggota;
    }

    public Buku getBuku() {
        return buku;
    }

    public void setBuku(Buku buku) {
        this.buku = buku;
    }

    public Pegawai getPegawai() {
        return pegawai;
    }

    public void setPegawai(Pegawai pegawai) {
        this.pegawai = pegawai;
    }

    public LocalDate getTanggalPinjam() {
        return tanggalPinjam;
    }

    public void setTanggalPinjam(LocalDate tanggalPinjam) {
        this.tanggalPinjam = tanggalPinjam;
    }

    public LocalDate getTanggalKembali() {
        return tanggalKembali;
    }

    public void setTanggalKembali(LocalDate tanggalKembali) {
        this.tanggalKembali = tanggalKembali;
    }

    // Constructor
    public Peminjaman() {
    }

    public Peminjaman(Anggota anggota, Buku buku, Pegawai pegawai, LocalDate tanggalPinjam, LocalDate tanggalKembali) {
        this.anggota = anggota;
        this.buku = buku;
        this.pegawai = pegawai;
        this.tanggalPinjam = tanggalPinjam;
        this.tanggalKembali = tanggalKembali;
    }

    // CRUD Methods
    public Peminjaman getById(int id) {
    Peminjaman peminjaman = new Peminjaman();
    String query = "SELECT "
            + " p.idpeminjaman AS idpeminjaman, "
            + " p.tanggalpinjam AS tanggalpinjam, "
            + " p.tanggalkembali AS tanggalkembali, "
            + " a.idanggota AS idanggota, "
            + " a.nama AS nama, "
            + " a.alamat AS alamat, "
            + " a.telepon AS telepon, "
            + " b.idbuku AS idbuku, "
            + " b.judul AS judul, "
            + " b.penerbit AS penerbit, "
            + " b.penulis AS penulis, "
            + " pg.idpegawai AS idpegawai, "
            + " pg.nama AS namapegawai "
            + "FROM peminjaman p "
            + "JOIN anggota a ON p.idanggota = a.idanggota "
            + "JOIN buku b ON p.idbuku = b.idbuku "
            + "JOIN pegawai pg ON p.idpegawai = pg.idpegawai "
            + "WHERE p.idpeminjaman = " + id;

    ResultSet rs = DBHelper.selectQuery(query);

    try {
        if (rs.next()) {
            // Set data ke objek peminjaman
            peminjaman.setIdpeminjaman(rs.getInt("idpeminjaman"));
            // Convert tanggalPinjam dan tanggalKembali ke LocalDate
            LocalDate tanggalPinjam = rs.getDate("tanggalpinjam").toLocalDate();
            LocalDate tanggalKembali = rs.getDate("tanggalkembali").toLocalDate();
            
            peminjaman.setTanggalPinjam(tanggalPinjam);
            peminjaman.setTanggalKembali(tanggalKembali);
            
            // Set data anggota
            Anggota anggota = new Anggota();
            anggota.setIdanggota(rs.getInt("idanggota"));
            anggota.setNama(rs.getString("nama"));
            anggota.setAlamat(rs.getString("alamat"));
            anggota.setTelepon(rs.getString("telepon"));
            peminjaman.setAnggota(anggota);

            // Set data buku
            Buku buku = new Buku();
            buku.setIdbuku(rs.getInt("idbuku"));
            buku.setJudul(rs.getString("judul"));
            buku.setPenulis(rs.getString("penerbit"));
            buku.setPenerbit(rs.getString("penulis"));
            peminjaman.setBuku(buku);

            // Set data pegawai
            Pegawai pegawai = new Pegawai();
            pegawai.setIdPegawai(rs.getInt("idpegawai"));
            pegawai.setNama(rs.getString("namapegawai"));
            peminjaman.setPegawai(pegawai);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    return peminjaman;
}


    public ArrayList<Peminjaman> getAll() {
    ArrayList<Peminjaman> listPeminjaman = new ArrayList<>();
    String query = "SELECT "
            + " p.idpeminjaman AS idpeminjaman, "
            + " p.tanggalpinjam AS tanggalpinjam, "
            + " p.tanggalkembali AS tanggalkembali, "
            + " a.idanggota AS idanggota, "
            + " a.nama AS nama, "
            + " a.alamat AS alamat, "
            + " a.telepon AS telepon, "
            + " b.idbuku AS idbuku, "
            + " b.judul AS judul, "
            + " b.penerbit AS penerbit, "
            + " b.penulis AS penulis, "
            + " pg.idpegawai AS idpegawai, "
            + " pg.nama AS namapegawai "
            + "FROM peminjaman p "
            + "JOIN anggota a ON p.idanggota = a.idanggota "
            + "JOIN buku b ON p.idbuku = b.idbuku "
            + "JOIN pegawai pg ON p.idpegawai = pg.idpegawai";

    ResultSet rs = DBHelper.selectQuery(query);

    try {
        while (rs.next()) {
            // Buat objek peminjaman
            Peminjaman peminjaman = new Peminjaman();
            peminjaman.setIdpeminjaman(rs.getInt("idpeminjaman"));
            // Convert tanggalPinjam dan tanggalKembali ke LocalDate
            LocalDate tanggalPinjam = rs.getDate("tanggalpinjam").toLocalDate();
            LocalDate tanggalKembali = rs.getDate("tanggalkembali").toLocalDate();
            
            peminjaman.setTanggalPinjam(tanggalPinjam);
            peminjaman.setTanggalKembali(tanggalKembali);

            // Set data anggota
            Anggota anggota = new Anggota();
            anggota.setIdanggota(rs.getInt("idanggota"));
            anggota.setNama(rs.getString("nama"));
            anggota.setAlamat(rs.getString("alamat"));
            anggota.setTelepon(rs.getString("telepon"));
            peminjaman.setAnggota(anggota);

            // Set data buku
            Buku buku = new Buku();
            buku.setIdbuku(rs.getInt("idbuku"));
            buku.setJudul(rs.getString("judul"));
            buku.setPenulis(rs.getString("penerbit"));
            buku.setPenerbit(rs.getString("penulis"));
            peminjaman.setBuku(buku);

            // Set data pegawai
            Pegawai pegawai = new Pegawai();
            pegawai.setIdPegawai(rs.getInt("idpegawai"));
            pegawai.setNama(rs.getString("namapegawai"));
            peminjaman.setPegawai(pegawai);

            // Tambahkan ke list
            listPeminjaman.add(peminjaman);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    return listPeminjaman;
}


    public void save() {
    // Format untuk LocalDate menjadi string (yyyy-MM-dd)
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String formattedTanggalPinjam = this.tanggalPinjam.format(formatter);
    String formattedTanggalKembali = this.tanggalKembali.format(formatter);

    if (getById(idpeminjaman).getIdpeminjaman() == 0) {
        String SQL = "INSERT INTO peminjaman (idanggota, idbuku, idpegawai, tanggalPinjam, tanggalKembali) VALUES ("
            + this.getAnggota().getIdanggota() + ", "
            + this.getBuku().getIdbuku() + ", "
            + this.getPegawai().getIdPegawai() + ", '"
            + formattedTanggalPinjam + "', '"
            + formattedTanggalKembali + "')";
        this.idpeminjaman = DBHelper.insertQueryGetId(SQL);
    } else {
        String SQL = "UPDATE peminjaman SET "
            + "idanggota = " + this.getAnggota().getIdanggota() + ", "
            + "idbuku = " + this.getBuku().getIdbuku() + ", "
            + "idpegawai = " + this.getPegawai().getIdPegawai() + ", "
            + "tanggalPinjam = '" + formattedTanggalPinjam + "', "
            + "tanggalKembali = '" + formattedTanggalKembali + "' "
            + "WHERE idpeminjaman = " + this.idpeminjaman;
        DBHelper.executeQuery(SQL);
    }
}
    
    public void update() {
    // Pastikan idpeminjaman sudah ada
    if (getById(idpeminjaman).getIdpeminjaman() != 0) {
        // Format untuk LocalDate menjadi string (yyyy-MM-dd) hanya untuk tanggalKembali
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedTanggalKembali = this.tanggalKembali.format(formatter);

        // SQL query hanya mengupdate tanggalKembali
        String SQL = "UPDATE peminjaman SET "
                + "tanggalKembali = '" + formattedTanggalKembali + "' "
                + "WHERE idpeminjaman = " + this.idpeminjaman;

        // Menjalankan query untuk mengupdate tanggal kembali
        DBHelper.executeQuery(SQL);
    } else {
        System.out.println("Peminjaman dengan ID " + idpeminjaman + " tidak ditemukan.");
    }
}


}

 