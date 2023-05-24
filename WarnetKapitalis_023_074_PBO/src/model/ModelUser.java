/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author LENOVO
 */
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


public class ModelUser extends Konektor implements Waktu{
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public ModelUser() {
        
    }   
        
    public int cekId(){
        int data = 0;
        try {
            String query = "SELECT id_transaksi FROM transaksi ORDER BY id_transaksi DESC LIMIT 1";
        statement = koneksi.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        
        if (resultSet.next()) {
            data = resultSet.getInt("id_transaksi");
        }
        
        statement.close();
            
            return data;
        } catch (Exception e) {
            
            System.out.println("Error : " + e.getMessage());
            return 0;
        }
    }
        
    public boolean tambahData(String id_transaksi, String id_user, String id_kom){
        Date dateNow = new Date();

        try {
            String query = "INSERT INTO `customer` (`id_kom`, `id_user`, `w_mulai`) VALUES "
                + "('"+ id_kom + "'"
                + ",'"+ id_user + "'"
                + ",'" + formatter.format(dateNow) + "')";
            statement = koneksi.createStatement();
            int queryresult = statement.executeUpdate(query);
            
            if (queryresult > 0) {
            String query2 = "INSERT INTO `transaksi` (`id_transaksi`, `id_user`, `id_kom`, `w_mulai`) VALUES "
                + "('"+ id_transaksi + "'"
                + ",'"+ id_user + "'"
                + ",'"+ id_kom + "'"
                + ",'" + formatter.format(dateNow) + "')";

            statement.executeUpdate(query2);
            statement.close();
            }else{
                JOptionPane.showMessageDialog(null, "Failed : " );
            return false;
            }
            JOptionPane.showMessageDialog(null, "Input Success");
            return true;
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            JOptionPane.showMessageDialog(null, "GAGAL.Silahkan Cek NO Komputer Anda");
            return false;
        }
    }
    public void deleteUser(String id_kom){
        try {
            String query = "DELETE FROM `customer` WHERE `id_kom`='" + id_kom + "'";
            statement = koneksi.createStatement();
            statement.executeUpdate(query);
            statement.close();
            
            JOptionPane.showMessageDialog(null, "Silahkan Ke Admin Untuk Bayar");
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Failed");
        }
    }
    
    public String[] readData(String id_transaksi){
        String[] data = new String[4];
        try {
            String query = "SELECT * FROM `transaksi` WHERE `id_transaksi`=" + id_transaksi;
            statement = koneksi.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                data[0] = resultSet.getString("id_transaksi");
                data[1] = resultSet.getString("id_kom");
                data[2] = resultSet.getString("w_mulai");
                data[3] = resultSet.getString("bayar");
            }
            statement.close();

            return data;
            
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Failed");
            return null;
        }
    }
    
    public void selesai(long total, String format, String id_transaksi){
        try {
            String query = "UPDATE `transaksi` "
                    + "SET "
                    + "`w_selesai`='" + format + "', "
                    + "`bayar`='" + total + "' "
                    + "WHERE `id_transaksi`=" + id_transaksi;
            
            statement = koneksi.createStatement();
            statement.executeUpdate(query);
            
            statement.close();
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }
    
    public String[] Bayar(String id_transaksi){
        String[] data = new String[2];
        try {
            String query = "SELECT * FROM `transaksi` WHERE `id_transaksi`=" + id_transaksi;
            statement = koneksi.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                data[0] = resultSet.getString("w_selesai");
                data[1] = resultSet.getString("bayar"); 
            }
            statement.close();

            return data;
            
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Failed");
            return null;
        }
    }
    
    public void Game1() {
        String url = "https://krunker.io/";
        try {
            Desktop desktop = Desktop.getDesktop();
            URI uri = new URI(url);
            desktop.browse(uri);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
    public void Game2() {
        String url = "https://tetris.com/play-tetris";
        try {
            Desktop desktop = Desktop.getDesktop();
            URI uri = new URI(url);
            desktop.browse(uri);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
    public void Game3() {
        String url = "https://poki.co.id/";
        try {
            Desktop desktop = Desktop.getDesktop();
            URI uri = new URI(url);
            desktop.browse(uri);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
    
    public void dataWaktu(String id_transaksi){
        try {
            String[] data = readData(id_transaksi);
            Date d1 = formatter.parse(data[2]);
            Date d2 = new Date();
            
            long time = d2.getTime() - d1.getTime();
            long diffHours = time / (60 * 60 * 1000);
            
            long total = hargasewa(diffHours, data[0]);
            selesai(total, formatter.format(d2), id_transaksi);
            
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }
    
    @Override
    public long hargasewa(long time, String jam) {
        long first=0, add=0;

        first = 2000;
        add = 2000;
        
        if(time<=1){
            return first;
        }
        
        if(time>1){
            return first + (time*add);
        }
        
        if(time >= 24){
            return first + (24*add);
        }
        
        return 0;
    }

}
