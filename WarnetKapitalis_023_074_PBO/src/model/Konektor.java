/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author LENOVO
 */
import java.sql.*;
public class Konektor {

    String DBurl = "jdbc:mysql://localhost/warnet_kapitalis";
    String DBusername = "root";
    String DBpassword = "";
    
    Connection koneksi;
    Statement statement;

    public Konektor(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            koneksi = DriverManager.getConnection(DBurl,DBusername, DBpassword);
            System.out.println("Connect Berhasil");
        }catch(Exception ex){
            System.out.println("Connect Gagal");
        }
    }
}
