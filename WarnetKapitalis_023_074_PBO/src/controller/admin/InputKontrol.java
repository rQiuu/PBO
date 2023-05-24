/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.admin;

import view.*;
import model.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 */
public class InputKontrol {
    ViewInput viewInput;
    ViewAdmin viewAdmin;
    ModelInput modelInput;
    ModelAdmin modelAdmin;
    String marker = "" ;
    private String data[][];
            
    
    
    public InputKontrol(ModelInput modelInput, ViewInput viewInput){
        
        viewInput.input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String username = viewInput.getUsername();
                String role = viewInput.getRole();
                String pass = viewInput.getPass();

                if (viewInput.getUsername().equals("")) {
                    JOptionPane.showMessageDialog(null, "Cannot be empty");
                } else {
                    modelInput.insertUser(username, role, pass);
                }
                
                ViewAdmin viewadmin = new ViewAdmin();
                ModelAdmin modeladmin = new ModelAdmin();
                AdminKontrol AdminController = new AdminKontrol(modeladmin, viewadmin);
                viewInput.dispose();
            }
        });
        viewInput.kembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                viewInput.dispose();
                ViewAdmin viewadmin = new ViewAdmin();
                ModelAdmin modeladmin = new ModelAdmin();
                AdminKontrol AdminController = new AdminKontrol(modeladmin, viewadmin);
            }
        });
    }
    
}
