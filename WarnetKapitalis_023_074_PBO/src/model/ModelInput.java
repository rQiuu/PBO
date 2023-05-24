/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class ModelInput extends Konektor{
    public ModelInput(){
    }
        
        public void insertUser(String username, String role, String pass){
        String roles;
        if(role.equals("user")){
            roles = "user";
        }else{
            roles = "admin";
        }
        try {
            String query = "INSERT INTO `user` (`username`, `password`, `role`) "
                    + "VALUES "
                    + "('" + username + "','" + pass + "','" + roles + "')";
            
            statement = koneksi.createStatement();
            statement.executeUpdate(query);
            
            statement.close();
            JOptionPane.showMessageDialog(null, "Input Sukses");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed : " + e.getMessage());
        }
    }
    }
    

