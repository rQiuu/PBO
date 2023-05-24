/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.*;
import view.*;


/**
 *
 * @author ASUS
 */
public class TransaksiKontrol {
    ModelTransaksi modelTransaksi;
    ViewTransaksi viewTransaksi;
    private String data[][];

    public TransaksiKontrol(ModelTransaksi modelTransaksi, ViewTransaksi viewTransaksi) {
        this.modelTransaksi = modelTransaksi;
        this.viewTransaksi = viewTransaksi;
        data = modelTransaksi.readDataTransaksi();
        
        viewTransaksi.tabel_transaksi.setModel(new DefaultTableModel(data, new String[]{
            "id_transaksi", "id_user", "id_kom","w_mulai","w_selesai","bayar"
        }) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        
        
        viewTransaksi.bExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                viewTransaksi.dispose();
            }
        });
    }
    
}
