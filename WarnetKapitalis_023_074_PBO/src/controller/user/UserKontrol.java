/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.user;

/**
 *
 * @author LENOVO
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

import controller.LoginKontrol;

import model.ModelLogin;
import model.ModelUser;

import view.Login;
import view.ViewUser;

public class UserKontrol {
    ModelUser modeluser;
    ViewUser viewuser;

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date;
    
    public UserKontrol(ModelUser modeluser, ViewUser viewuser, String username) {
        this.modeluser = modeluser;
        this.viewuser = viewuser;
        int id = modeluser.cekId();
            id++;
        
        viewuser.setUsername(username);
        viewuser.setId(String.valueOf(id));
        viewuser.bselesai.setEnabled(false);
        viewuser.bgame1.setEnabled(false);
        viewuser.bgame2.setEnabled(false);
        viewuser.bgame3.setEnabled(false);
        viewuser.logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Login loginview = new Login();
                ModelLogin modellogin = new ModelLogin();
                LoginKontrol loginkontrol = new LoginKontrol(modellogin, loginview);
                viewuser.dispose();
                
                JOptionPane.showMessageDialog(null, "Logout Berhasil");
            }
        });
        
        viewuser.bgame1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                modeluser.Game1();
            }
        });
        
        viewuser.bgame2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                modeluser.Game2();
            }
        });
        
        viewuser.bgame3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                modeluser.Game3();
            }
        });
               
        viewuser.bmulai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) { 
                //input               
                String id_transaksi = viewuser.getId();
                String id_kom = viewuser.getIdKom();
                
                if(id_transaksi.equals("") ||id_kom.equals("")){
                    JOptionPane.showMessageDialog(null, "Masukan No Komputer    ");
                }else{
                    if(modeluser.tambahData(id_transaksi, username, id_kom)){
                        date = new Date();
                        viewuser.setMulai(formatter.format(date));
                        viewuser.bmulai.setEnabled(false);
                        viewuser.bselesai.setEnabled(true);
                        viewuser.bgame1.setEnabled(true);
                        viewuser.bgame2.setEnabled(true);
                        viewuser.bgame3.setEnabled(true);
                    }    
                }         
            }
        });
        
        viewuser.bselesai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String id_transaksi = viewuser.getId();
                String id_kom = viewuser.getIdKom();
                System.out.println("id " + id_transaksi);  
                modeluser.dataWaktu(id_transaksi);
                String[] data = modeluser.Bayar(id_transaksi);
                viewuser.setHarga("Rp" + data[1]);
                    modeluser.deleteUser(id_kom); 
                viewuser.setSelesai(data[0]);
                viewuser.bselesai.setEnabled(false);
                viewuser.bgame1.setEnabled(false);
                viewuser.bgame2.setEnabled(false);
                viewuser.bgame3.setEnabled(false);
            }
        });
          
    }
}
