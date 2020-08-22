/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendas;

import bancodedados.BancoClientes;
import bancodedados.BancoParcelas;
import controle.ControleMetodos;
import controle.EstiloTabelaHeader;
import controle.EstiloTabelaRenderer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JInternalFrame;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Power Arts
 */
public class FrmParcelas extends javax.swing.JInternalFrame {

    /**
     * Creates new form FrmParcelas
     */
    public FrmParcelas() {
        initComponents();
        configurarTabela();
        limparCampos();
    }

    public boolean estaFechado(Object obj) {
        JInternalFrame[] ativo = principal.MenuPrincipal.instanciarTelas.getAllFrames();
        boolean fechado = true;
        int i = 0;
        while (i < ativo.length && fechado) {
            if (ativo[i] == obj) {
                fechado = false;
            }
            i++;
        }
        return fechado;
    }

    private void configurarTabela() {
        tabelaParcelas.getTableHeader().setDefaultRenderer(new EstiloTabelaHeader());
        tabelaParcelas.setDefaultRenderer(Object.class, new EstiloTabelaRenderer());
        tabelaParcelas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaParcelas.getColumnModel().getColumn(0).setPreferredWidth(80);
        tabelaParcelas.getColumnModel().getColumn(1).setPreferredWidth(75);
        tabelaParcelas.getColumnModel().getColumn(2).setPreferredWidth(100);
        tabelaParcelas.getColumnModel().getColumn(3).setPreferredWidth(100);
        tabelaParcelas.getColumnModel().getColumn(4).setPreferredWidth(120);
    }

    void limparCampos() {
        if (tabelaParcelas.getSelectedRow() > -1) {
            tabelaParcelas.removeRowSelectionInterval(tabelaParcelas.getSelectedRow(), tabelaParcelas.getSelectedRow());
        }
        preencherTabela(bancoParcelas.listarParcelas(vendas.FrmVendaCompleta.getCodigoVendaPublico()));
    }

    private void preencherTabela(ResultSet rsVenda) {
        DefaultTableModel modelo = (DefaultTableModel) FrmParcelas.tabelaParcelas.getModel();

        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }

        String dados[] = new String[5];

        try {
            while (rsVenda.next()) {
                dados[0] = rsVenda.getString("numero");
                dados[1] = rsVenda.getString("valor").replace(".", ",");
                dados[2] = rsVenda.getString("data_vencimento");
                dados[3] = rsVenda.getString("data_pagamento");
                
                if(rsVenda.getString("status").equals("1")){
                    dados[4] = "PAGO";
                }else{
                    dados[4] = "PENDENTE";
                }
                
                modelo.addRow(dados);
            }
        } catch (SQLException ex) {

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        bp_vendasHoje = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaParcelas = new javax.swing.JTable();

        setClosable(true);
        setTitle("Parcelas");
        setPreferredSize(new java.awt.Dimension(554, 324));

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bp_vendasHoje.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bp_vendasHoje.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/vendas/vendasH1.png"))); // NOI18N
        bp_vendasHoje.setBorder(null);
        bp_vendasHoje.setContentAreaFilled(false);
        bp_vendasHoje.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bp_vendasHoje.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bp_vendasHoje.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/vendas/vendasH.png"))); // NOI18N
        bp_vendasHoje.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bp_vendasHoje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bp_vendasHojeActionPerformed(evt);
            }
        });
        jPanel2.add(bp_vendasHoje, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 210, 160, 50));

        tabelaParcelas.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        tabelaParcelas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PARCELA", "VALOR", "VENCIMENTO", "PAGAMENTO", "SITUAÇÃO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaParcelas.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(tabelaParcelas);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 500, 190));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bp_vendasHojeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bp_vendasHojeActionPerformed
        String data = controleMetodos.converteDataString(new Date());
        preencherTabela(bancoParcelas.listarParcelas(data));
    }//GEN-LAST:event_bp_vendasHojeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bp_vendasHoje;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable tabelaParcelas;
    // End of variables declaration//GEN-END:variables

    FrmVendaCompleta formVendaCompleta;
    
    private int linha = vendas.FrmVendas.tabelaVendas.getSelectedRow();
    private int coluna = 0;
    public String codigo_venda = vendas.FrmVendas.tabelaVendas.getValueAt(linha, coluna).toString();
    
    BancoParcelas bancoParcelas = new BancoParcelas();
    BancoClientes bancoClientes = new BancoClientes();
    ControleMetodos controleMetodos = new ControleMetodos();
}