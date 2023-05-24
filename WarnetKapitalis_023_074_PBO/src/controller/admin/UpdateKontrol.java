/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.admin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.*;
import view.*;

/**
 *
 * @author ASUS
 */
public class UpdateKontrol extends ModelAdmin{
    
    
    ViewUpdate viewUpdate;
    ViewAdmin viewAdmin;
    ModelAdmin modelAdmin;
    private String data[][];
    String marker = "";
      
    public UpdateKontrol (ModelAdmin modelAdmin, ViewUpdate viewUpdate){
        this.modelAdmin = modelAdmin;
        
        data = modelAdmin.readDataUser();

        viewUpdate.tabel_user.setModel(new DefaultTableModel(data, new String[]{
            "Username", "Password", "Role"
        }) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });

        viewUpdate.tabel_user.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = viewUpdate.tabel_user.getSelectedRow();
                String username = viewUpdate.tabel_user.getValueAt(row, 0).toString();
                String roles = viewUpdate.tabel_user.getValueAt(row, 2).toString();
                String password = viewUpdate.tabel_user.getValueAt(row, 1).toString();
                marker = username;

                viewUpdate.setUsername(username);
                viewUpdate.setPass(password);

                if (roles.equals("admin")) {
                    viewUpdate.cbrole.setSelectedIndex(1);
                } else {
                    viewUpdate.cbrole.setSelectedIndex(0);
                }
            }
        });
        
        
        viewUpdate.input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                
                String username = viewUpdate.getUsername();
                String role = viewUpdate.getRole();
                String password = viewUpdate.getPass();
                
                if(marker.equals("")){
                    marker = username;
                }
                
                if(viewUpdate.getUsername().equals("")){
                    JOptionPane.showMessageDialog(null, "Cannot be empty");
                }else{
                    modelAdmin.updateUser(username, role, marker,password ); 
                }
                ViewAdmin viewadmin = new ViewAdmin();
                ModelAdmin modeladmin = new ModelAdmin();
                AdminKontrol AdminController = new AdminKontrol(modeladmin, viewadmin);
                viewUpdate.dispose();
            }
        });
        
        viewUpdate.kembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                viewUpdate.dispose();
                ViewAdmin viewadmin = new ViewAdmin();
                ModelAdmin modeladmin = new ModelAdmin();
                AdminKontrol AdminController = new AdminKontrol(modeladmin, viewadmin);
            }
        });
    }
    
}
