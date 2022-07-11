package controller;
import java.sql.*;
import java.util.ArrayList;

import model.*;

public class controller {
    static dbHandler conn = new dbHandler();
    
    public static ArrayList<Category> getCategories() {
        conn.connect();
        String q = "SELECT * FROM categoryuser";
        ArrayList<Category> categories = new ArrayList<Category>();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(q);
            while (rs.next()) {
                Category category = new Category();
                category.setIdCategory(rs.getInt("id"));
                category.setName(rs.getString("name"));
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (categories);
    }

    public static ArrayList<User> getUsers(int category) {
        conn.connect();
        String q = "SELECT * FROM user WHERE idCategory='" + category + "'";
        ArrayList<User> users = new ArrayList<User>();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(q);
            while (rs.next()) {
                User user = new User();
                user.setIdUser(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setIdCategory(rs.getInt("idCategory"));
                user.setPhoto(rs.getString("photo"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (users);
    }

    public static boolean insertUser(User user) {
        conn.connect();
        String query = "INSERT INTO user VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, user.getIdUser());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            stmt.setInt(5, user.getIdCategory());
            stmt.setString(6, user.getPhoto());
            stmt.executeUpdate();
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }

    public static boolean logIn(String email, String pass) {
        conn.connect();
        String query = "SELECT * FROM user WHERE email='" + email + "' AND password = '" + pass + "'";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next() == false) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    // public static boolean insertKTP(dataPenduduk data) {
    //     conn.connect();
    //     String query = "INSERT INTO dataPenduduk VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    //     try {
    //         PreparedStatement stmt = conn.con.prepareStatement(query);
    //         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    //         stmt.setString(1, data.getNIK());
    //         stmt.setString(2, data.getNama());
    //         stmt.setString(3, data.getTempatLahir());
    //         stmt.setDate(4, java.sql.Date.valueOf(formatter.format(data.getTglLahir())));
    //         stmt.setString(5, data.getJenisKelamin());
    //         stmt.setString(6, data.getGolDarah());
    //         stmt.setString(7, data.getAlamat());
    //         stmt.setString(8, data.getRtRw());
    //         stmt.setString(9, data.getKelDesa());
    //         stmt.setString(10, data.getKecamatan());
    //         stmt.setString(11, data.getAgama());
    //         stmt.setString(12, data.getStatusKawin());
    //         stmt.setString(13, data.getPekerjaan());
    //         stmt.setString(14, data.getKewarganegaraan());
    //         stmt.setString(15, data.getPathFoto());
    //         stmt.setString(16, data.getPathTTD());
    //         stmt.setString(17, data.getKotaPembuatan());
    //         stmt.setDate(18, java.sql.Date.valueOf(formatter.format(data.getTglPembuatan())));
    //         stmt.executeUpdate();
    //         return (true);
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //         return (false);
    //     }
    // }
    
    // public static dataPenduduk getKTP(String NIK) {
    //     conn.connect();
    //     String query = "SELECT * FROM ktp WHERE NIK='" + NIK + "'";
    //     dataPenduduk data = new dataPenduduk();
    //     try {
    //         Statement stmt = conn.con.createStatement();
    //         ResultSet rs = stmt.executeQuery(query);
    //         while (rs.next()) {
    //             data.setNIK(rs.getString("NIK"));
    //             data.setNama(rs.getString("nama"));
    //             data.setTempatLahir(rs.getString("tempatLahir"));
    //             data.setTglLahir(rs.getDate("tanggalLahir"));
    //             data.setJenisKelamin(rs.getString("jenisKelamin"));
    //             data.setGolDarah(rs.getString("golonganDarah"));
    //             data.setAlamat(rs.getString("alamat"));
    //             data.setRtRw(rs.getString("rtRw"));
    //             data.setKelDesa(rs.getString("kelurahanDesa"));
    //             data.setKecamatan(rs.getString("kecamatan"));
    //             data.setAgama(rs.getString("agama"));
    //             data.setStatusKawin(rs.getString("statusKawin"));
    //             data.setPekerjaan(rs.getString("pekerjaan"));
    //             data.setKewarganegaraan(rs.getString("kewarganegaraan"));
    //             data.setPathFoto(rs.getString("pathFoto"));
    //             data.setPathTTD(rs.getString("pathTTD"));
    //             data.setKotaPembuatan(rs.getString("kotaPembuatan"));
    //             data.setTglPembuatan(rs.getDate("tanggalPembuatan"));
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    //     return (data);
    // }
    
    // public static boolean updateKTP(dataPenduduk data) {
    //     conn.connect();
    //     String query = "UPDATE ktp SET nama='" + data.getNama()+ "', "
    //             + "tempatLahir='" + data.getTempatLahir()+ "', "
    //             + "tanggalLahir='" + data.getTglLahir()+ "', "
    //             + "jenisKelamin='" + data.getJenisKelamin()+ "', "
    //             + "golonganDarah='" + data.getGolDarah()+ "', "
    //             + "alamat='" + data.getAlamat()+ "', "
    //             + "rtRw='" + data.getRtRw()+ "', "
    //             + "kelurahanDesa='" + data.getKelDesa()+ "', "
    //             + "kecamatan='" + data.getKecamatan()+ "', "
    //             + "agama='" + data.getAgama()+ "', "
    //             + "statusKawin='" + data.getStatusKawin()+ "', "
    //             + "pekerjaan='" + data.getPekerjaan()+ "', "
    //             + "kewarganegaraan='" + data.getKewarganegaraan()+ "', "
    //             + "pathFoto='" + data.getPathFoto()+ "', "
    //             + "pathTTD='" + data.getPathTTD()+ "', "
    //             + "kotaPembuatan='" + data.getKotaPembuatan()+ "', "
    //             + "tanggalPembuatan='" + data.getTglPembuatan()+ "' "
    //             + "WHERE NIK='" + data.getNIK() + "'";
    //     try {
    //         Statement stmt = conn.con.createStatement();
    //         stmt.executeUpdate(query);
    //         return (true);
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //         return (false);
    //     }
    // }
}