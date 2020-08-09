/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendas;

import bancodedados.BancoClientes;
import bancodedados.BancoVendas;
import controle.ControleMetodos;
import controle.EstiloTabelaHeader;
import controle.EstiloTabelaRenderer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Power Arts
 */
public class FrmVendas extends javax.swing.JInternalFrame {

    /**
     * Creates new form FrmVendas
     */
    public FrmVendas() {
        initComponents();
        configurarTabela();
        limparCampos();
    }

    private void configurarTabela() {
        tabelaProdutos.getTableHeader().setDefaultRenderer(new EstiloTabelaHeader());
        tabelaProdutos.setDefaultRenderer(Object.class, new EstiloTabelaRenderer());
        tabelaProdutos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaProdutos.getColumnModel().getColumn(0).setPreferredWidth(130);
        tabelaProdutos.getColumnModel().getColumn(1).setPreferredWidth(60);
        tabelaProdutos.getColumnModel().getColumn(2).setPreferredWidth(75);
        tabelaProdutos.getColumnModel().getColumn(3).setPreferredWidth(200);
    }

    void limparCampos() {
        if (tabelaProdutos.getSelectedRow() > -1) {
            tabelaProdutos.removeRowSelectionInterval(tabelaProdutos.getSelectedRow(), tabelaProdutos.getSelectedRow());
        }
        cp_buscar.setText("");
        cp_data.setDate(null);
        preencherTabela(bancoVendas.listarVendas(""));
    }

    private void preencherTabela(ResultSet rsVenda) {
        DefaultTableModel modelo = (DefaultTableModel) FrmVendas.tabelaProdutos.getModel();

        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }

        String dados[] = new String[4];

        try {
            while (rsVenda.next()) {
                dados[0] = rsVenda.getString("numero_ven");
                dados[1] = rsVenda.getString("total_ven").replace(".", ",");
                dados[2] = rsVenda.getString("data_ven");

                ResultSet rsCliente = bancoClientes.buscarCodigo(rsVenda.getString("cod_cliente_ven"));
                rsCliente.next();
                dados[3] = rsCliente.getString("nome_cl");

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
        cp_buscar = new app.bolivia.swing.JCTextField();
        jLabel5 = new javax.swing.JLabel();
        bt_pesquisar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        bt_abrirVenda = new javax.swing.JButton();
        bt_excluirTudo = new javax.swing.JButton();
        bt_excluir = new javax.swing.JButton();
        bt_imparCampos1 = new javax.swing.JButton();
        cp_data = new com.toedter.calendar.JDateChooser();
        bp_vendasHoje = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaProdutos = new javax.swing.JTable();

        setClosable(true);
        setTitle("Vendas");
        setPreferredSize(new java.awt.Dimension(1050, 390));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "OPÇÕES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cp_buscar.setBackground(new java.awt.Color(34, 102, 145));
        cp_buscar.setBorder(null);
        cp_buscar.setForeground(new java.awt.Color(255, 255, 255));
        cp_buscar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cp_buscar.setOpaque(false);
        cp_buscar.setPhColor(new java.awt.Color(255, 255, 255));
        cp_buscar.setPlaceholder("NÚMERO VENDA");
        cp_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cp_buscarActionPerformed(evt);
            }
        });
        cp_buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cp_buscarKeyReleased(evt);
            }
        });
        jPanel2.add(cp_buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 170, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/produtos/buscarL.png"))); // NOI18N
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, -1, -1));

        bt_pesquisar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bt_pesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/vendas/buscaF2.png"))); // NOI18N
        bt_pesquisar.setBorder(null);
        bt_pesquisar.setContentAreaFilled(false);
        bt_pesquisar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_pesquisar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_pesquisar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/vendas/buscaF1.png"))); // NOI18N
        bt_pesquisar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bt_pesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_pesquisarActionPerformed(evt);
            }
        });
        jPanel2.add(bt_pesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 90, -1, 50));

        bt_abrirVenda.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bt_abrirVenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/vendas/abrirvenda2.png"))); // NOI18N
        bt_abrirVenda.setText("Abrir Venda");
        bt_abrirVenda.setBorder(null);
        bt_abrirVenda.setContentAreaFilled(false);
        bt_abrirVenda.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_abrirVenda.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_abrirVenda.setPreferredSize(new java.awt.Dimension(95, 95));
        bt_abrirVenda.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/vendas/abrirvenda1.png"))); // NOI18N
        bt_abrirVenda.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bt_abrirVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_abrirVendaActionPerformed(evt);
            }
        });

        bt_excluirTudo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bt_excluirTudo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/produtos/apagarT1.png"))); // NOI18N
        bt_excluirTudo.setText("Excluir Tudo");
        bt_excluirTudo.setBorder(null);
        bt_excluirTudo.setContentAreaFilled(false);
        bt_excluirTudo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_excluirTudo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_excluirTudo.setPreferredSize(new java.awt.Dimension(95, 95));
        bt_excluirTudo.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/produtos/apagarT.png"))); // NOI18N
        bt_excluirTudo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bt_excluirTudo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_excluirTudoActionPerformed(evt);
            }
        });

        bt_excluir.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bt_excluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/produtos/apagar1.png"))); // NOI18N
        bt_excluir.setText("Excluir");
        bt_excluir.setBorder(null);
        bt_excluir.setContentAreaFilled(false);
        bt_excluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_excluir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_excluir.setPreferredSize(new java.awt.Dimension(95, 95));
        bt_excluir.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/produtos/apagar.png"))); // NOI18N
        bt_excluir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bt_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_excluirActionPerformed(evt);
            }
        });

        bt_imparCampos1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bt_imparCampos1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/produtos/limpar1.png"))); // NOI18N
        bt_imparCampos1.setText("Limpar Campos");
        bt_imparCampos1.setBorder(null);
        bt_imparCampos1.setContentAreaFilled(false);
        bt_imparCampos1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_imparCampos1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_imparCampos1.setPreferredSize(new java.awt.Dimension(95, 95));
        bt_imparCampos1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/produtos/limpar.png"))); // NOI18N
        bt_imparCampos1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bt_imparCampos1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_imparCampos1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(bt_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(bt_excluirTudo, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(bt_abrirVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(bt_imparCampos1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(bt_excluirTudo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                    .addComponent(bt_abrirVenda, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_excluir, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_imparCampos1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 210, 440, -1));

        cp_data.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel2.add(cp_data, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 180, 40));

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
        jPanel2.add(bp_vendasHoje, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 150, 160, 50));

        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        tabelaProdutos.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        tabelaProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NÚMERO VENDA", "TOTAL", "DATA", "CLIENTE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaProdutos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(tabelaProdutos);

        jPanel4.add(jScrollPane1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

    private void cp_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cp_buscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cp_buscarActionPerformed

    private void bt_abrirVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_abrirVendaActionPerformed

        if (tabelaProdutos.getRowCount() > 0) {
            if (tabelaProdutos.getSelectedRowCount() > 0) {
                JOptionPane.showMessageDialog(this, "Falta Construir");
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um registro na tabela!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Tabela de vendas vazia!\nSelecione um registro na tabela antes de excluir.");
        }

    }//GEN-LAST:event_bt_abrirVendaActionPerformed

    private void cp_buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cp_buscarKeyReleased
        cp_buscar.setText(cp_buscar.getText().toUpperCase());

        String buscar = cp_buscar.getText();
        String data = "";

        if (cp_data.getDate() != null) {
            data = controleMetodos.converteDataString(cp_data.getDate());
        }

        preencherTabela(bancoVendas.listarVendas(buscar, data));
    }//GEN-LAST:event_cp_buscarKeyReleased

    private void bt_pesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_pesquisarActionPerformed
        cp_buscar.setText(cp_buscar.getText().toUpperCase());

        String buscar = cp_buscar.getText();
        String data = "";

        if (cp_data.getDate() != null) {
            data = controleMetodos.converteDataString(cp_data.getDate());
        }

        preencherTabela(bancoVendas.listarVendas(buscar, data));
    }//GEN-LAST:event_bt_pesquisarActionPerformed

    private void bp_vendasHojeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bp_vendasHojeActionPerformed
        String data = controleMetodos.converteDataString(new Date());
        preencherTabela(bancoVendas.listarVendas(data));
    }//GEN-LAST:event_bp_vendasHojeActionPerformed

    private void bt_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_excluirActionPerformed
        if (tabelaProdutos.getRowCount() > 0) {
            if (tabelaProdutos.getSelectedRowCount() > 0) {
                if (JOptionPane.showConfirmDialog(this, "Deseja excluir esta venda?", "Vendas", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    int linha = tabelaProdutos.getSelectedRow();
                    String numero = tabelaProdutos.getValueAt(linha, 0).toString();
                    String msg = bancoVendas.exluirVenda(numero);

                    if (msg.equals("A venda foi excluido com sucesso!")) {
                        limparCampos();
                        JOptionPane.showMessageDialog(this, "A venda de código \"" + numero + "\" foi excluido com sucesso!");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um registro na tabela!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Tabela de vendas vazia!\nSelecione um registro na tabela antes de excluir.");
        }
    }//GEN-LAST:event_bt_excluirActionPerformed

    private void bt_excluirTudoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_excluirTudoActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Deseja Excluir todas as vendas?", "Venda", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            String msg = bancoVendas.excluirTodos();
            if (msg.equals("Todos os registros foram excluidos com sucesso!")) {
                limparCampos();
                JOptionPane.showMessageDialog(this, "Todos os registros foram excluidos com sucesso.");
            }
            if (msg.equals("Nenhum produto cadastrado!")) {
                JOptionPane.showMessageDialog(this, "Não há registro para excluir");
            }
        }
    }//GEN-LAST:event_bt_excluirTudoActionPerformed

    private void bt_imparCampos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_imparCampos1ActionPerformed
        limparCampos();
    }//GEN-LAST:event_bt_imparCampos1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bp_vendasHoje;
    private javax.swing.JButton bt_abrirVenda;
    private javax.swing.JButton bt_excluir;
    private javax.swing.JButton bt_excluirTudo;
    private javax.swing.JButton bt_imparCampos1;
    private javax.swing.JButton bt_pesquisar;
    private app.bolivia.swing.JCTextField cp_buscar;
    private com.toedter.calendar.JDateChooser cp_data;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable tabelaProdutos;
    // End of variables declaration//GEN-END:variables
    BancoVendas bancoVendas = new BancoVendas();
    BancoClientes bancoClientes = new BancoClientes();
    ControleMetodos controleMetodos = new ControleMetodos();
}
