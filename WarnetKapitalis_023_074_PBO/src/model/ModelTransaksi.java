/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;

/**
 *
 * @author ASUS
 */
public class ModelTransaksi extends Konektor{
    public ModelTransaksi(){
        
    }
    public int totalDataTransaksi(){
        try {
            int totalData = 0;
            String query = "SELECT * FROM `transaksi`";
            statement = koneksi.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            
            while(resultSet.next()){
                totalData++;
            }
            statement.close();
            return totalData;
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return 0;
        }
        
    }
     public String[][] readDataTransaksi(){
         
        String data[][] = new String[totalDataTransaksi()][6];
        try {
            int indexData = 0;  
            String query = "SELECT * FROM `transaksi`";
            statement = koneksi.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            
            while(resultSet.next()){
                data[indexData][0] = resultSet.getString("id_transaksi");
                data[indexData][1] = resultSet.getString("id_user");
                data[indexData][2] = resultSet.getString("id_kom");
                data[indexData][3] = resultSet.getString("w_mulai");
                data[indexData][4] = resultSet.getString("w_selesai");
                data[indexData][5] = resultSet.getString("bayar");
                indexData++;
            }
            statement.close();
            return data;
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return data;
        }
    }
}
