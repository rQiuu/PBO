/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author LENOVO
 */
public class ModelAdmin extends Konektor{
    
    public ModelAdmin(){
        
    }
    
    public int totalDataUser(){
        try {
            int totalData = 0;
            String query = "SELECT * FROM `user`";
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
     public String[][] readDataUser(){
         
        String data[][] = new String[totalDataUser()][3];
        try {
            int indexData = 0;  
            String query = "SELECT * FROM `user`";
            statement = koneksi.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            
            while(resultSet.next()){
                data[indexData][0] = resultSet.getString("username");
                data[indexData][1] = resultSet.getString("password");
                data[indexData][2] = resultSet.getString("role");
                indexData++;
            }
            statement.close();
            return data;
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return data;
        }
    }
     
     
      public void updateUser(String username, String role, String marker, String pass){
        
        String roles;
        if(role.equals("user")){
            roles = "user";
        }else{
            roles = "admin";
        }
        try {
            String query = "UPDATE `user` "
                    + "SET "
                    + "`username`='" + username + "', "
                    + "`role`='" + roles + "', "
                    + "`password`='" + pass +"' "
                    + "WHERE `username`='" + marker + "'";
            
            statement = koneksi.createStatement();
            statement.executeUpdate(query);
            
            statement.close();
            JOptionPane.showMessageDialog(null, "Update Sukses");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed : " + e.getMessage());
        }
    }
     
      public void deleteUser(String username){
        
        
        try {
            String query = "DELETE FROM `user` WHERE `username`='" + username + "'";
            statement = koneksi.createStatement();
            statement.executeUpdate(query);
            statement.close();
            
            JOptionPane.showMessageDialog(null, "Delete Sukses");
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Delete Gagal");
        }
    }
}
