package controller.admin;

import controller.LoginKontrol;
import controller.admin.TransaksiKontrol;
import view.*;
import model.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**admin
 *
 * @author ASUS
 */
public class AdminKontrol {
    
    ModelAdmin modelAdmin;
    ViewAdmin viewAdmin;
    ViewUpdate viewUpdate;
    Login login;
    ModelTransaksi modelTransaksi;

    private String data[][];
    private String marker;

    public AdminKontrol(ModelAdmin modelAdmin, ViewAdmin viewAdmin) {
        this.viewAdmin = viewAdmin;
        this.login = login;
        this.modelAdmin = modelAdmin;
        this.viewUpdate = viewUpdate;
        data = modelAdmin.readDataUser();
        

        viewAdmin.tabel_user.setModel(new DefaultTableModel(data, new String[]{
            "Username", "Password", "Role"
        }) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });

        viewAdmin.tabel_user.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = viewAdmin.tabel_user.getSelectedRow();
                String username = viewAdmin.tabel_user.getValueAt(row, 0).toString();
                marker = username;

               
            }
        });
        viewAdmin.logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Login loginview = new Login();
                ModelLogin modellogin = new ModelLogin();
                LoginKontrol loginkontrol = new LoginKontrol(modellogin, loginview);
                viewAdmin.dispose();

                JOptionPane.showMessageDialog(null, "Logout Success");
            }
        });

        viewAdmin.transaksi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                ViewTransaksi viewTransaksi = new ViewTransaksi();
                ModelTransaksi modelTransaksi = new ModelTransaksi();
                TransaksiKontrol transaksiKontrol = new TransaksiKontrol(modelTransaksi, viewTransaksi);

            }
        });
        viewAdmin.input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                ViewInput viewInput = new ViewInput();
                ModelInput modelInput = new ModelInput();
                InputKontrol inputKontrol = new InputKontrol(modelInput, viewInput);
                viewAdmin.dispose();
            }
        });
        
        viewAdmin.update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                ViewUpdate viewUpdate = new ViewUpdate();
                ModelAdmin modelAdmin = new ModelAdmin();
                UpdateKontrol updateKontrol = new UpdateKontrol(modelAdmin, viewUpdate);
                viewAdmin.dispose();
            }
        });
        viewAdmin.delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String username = marker;
                
                if(marker.equals("")){
                    JOptionPane.showMessageDialog(null, "Cannot be empty");
                }else{
                    modelAdmin.deleteUser(username);
                    marker = "";
                    
                    data = modelAdmin.readDataUser();

                    viewAdmin.tabel_user.setModel(new DefaultTableModel(data, new String[]{
                        "Username", "Password", "Role"
                    }) {
                        boolean[] canEdit = new boolean[]{
                            false, false, false, false
                        };

                        public boolean isCellEditable(int rowIndex, int columnIndex) {
                            return canEdit[columnIndex];
                        }
                    });
                    
                }
                
            }
        });

    }

}
