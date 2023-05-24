/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;

/**
 *
 * @author LENOVO
 */
public class ModelLogin extends Konektor{
    
    public boolean loginAdmin(String user, String pass){
        int totalData = 0;
        try {
            String query = "SELECT * FROM `user` WHERE "
                    + "`username`='" + user + "' AND "
                    + "`password`='" + pass + "' AND "
                    + "`role`='admin'";
            statement = koneksi.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            
            while(resultSet.next()){
                totalData++;
            };
            
            statement.close();
            if(totalData>0){
                return true;
            }
            
            return false;
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return false;
        } 
    }
    
    public boolean loginUser(String user, String pass){
        int totalData = 0;
        try {
            String query = "SELECT * FROM `user` WHERE "
                    + "`username`='" + user + "' AND "
                    + "`password`='" + pass + "' AND "
                    + "`role`='user'";
            statement = koneksi.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            
            while(resultSet.next()){
                totalData++;
            };
            
            statement.close();
            if(totalData>0){
                return true;
            }
            
            return false;
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return false;
        } 
    }
}
