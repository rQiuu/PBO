/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author LENOVO
 */

import controller.LoginKontrol;
import model.ModelLogin;
import view.Login;

public class Main {
    public static void main(String[] args) {
        Login loginView = new Login();
        ModelLogin loginModel = new ModelLogin();
        LoginKontrol loginController = new LoginKontrol(loginModel, loginView);
    }
}
