/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import controller.admin.AdminKontrol;
import controller.user.UserKontrol;
import model.ModelLogin;
import model.ModelAdmin;
import model.ModelUser;
import view.Login;
import view.ViewAdmin;
import view.ViewUser;

/**
 *
 * @author LENOVO
 */
public class LoginKontrol {
    ModelLogin loginModel;
    Login loginView;

    public LoginKontrol(ModelLogin loginModel, Login loginView) {
        this.loginModel = loginModel;
        this.loginView = loginView;
        
        loginView.ladmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String username = loginView.getUsername();
                String password = loginView.getPassword();
                
                if(loginModel.loginAdmin(username, password)){
                    JOptionPane.showMessageDialog(null, "Login Sukses");
                        ViewAdmin viewadmin = new ViewAdmin();
                        ModelAdmin modeladmin = new ModelAdmin();
                        AdminKontrol AdminController = new AdminKontrol(modeladmin, viewadmin);
                    loginView.dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "Login Gagal");
                }
            }
        });
        
        loginView.login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String username = loginView.getUsername();
                String password = loginView.getPassword();
                
                if(loginModel.loginUser(username, password)){
                    JOptionPane.showMessageDialog(null, "Login Sukses");
                        ViewUser viewuser = new ViewUser();
                        ModelUser modeluser = new ModelUser();
                        UserKontrol userController = new UserKontrol(modeluser, viewuser, username);
                    loginView.dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "Login Gagal");
                }
            }
        });
        
    }
}
