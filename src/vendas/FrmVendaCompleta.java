/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendas;

import bancodedados.BancoClientes;
import bancodedados.BancoDeposito;
import bancodedados.BancoParcelas;
import produtos.*;
import bancodedados.BancoProdutos;
import bancodedados.BancoVendas;
import controle.ControleMetodos;
import controle.ControleProduto;
import controle.EstiloTabelaHeader;
import controle.EstiloTabelaRenderer;
import java.awt.Color;
import java.awt.color.ColorSpace;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import static produtos.FrmListaProdutos.isNumber;

/**
 *
 * @author Power Arts
 */
public class FrmVendaCompleta extends javax.swing.JInternalFrame {

    /**
     * Creates new form FrmProdutos
     */
    public FrmVendaCompleta() {
        initComponents();
        dadosTabela();
        limparCampos();
        preencherDados();
    }

    private void preencherDados() {
        try {
            ResultSet venda = bancoVendas.buscarCodigo(codigo_venda);

            if (venda.next()) {
                dataVenda = venda.getString("data_ven");
                formaPagamento = venda.getString("forma_pagamento_ven"); //Concertar
                valorCompra = venda.getString("total_ven").replace(".", ",");
                entrada = venda.getString("entrada").replace(".", ",");
            }

            String codigoCliente = venda.getString("cod_cliente_ven");
            ResultSet cliente = bancoClientes.buscarCodigo(codigoCliente);
            if (cliente.next()) {
                nomeCliente = cliente.getString("nome_cl");
            }

            lb_nomeCliente.setText(nomeCliente);
            lb_dataVenda.setText(dataVenda);
            lb_formaPagamento.setText(formaPagamento);
            lb_valorCompra.setText(valorCompra);
            lb_entrada.setText(entrada);

            if (valorPendente == null || valorPendente.equals("0")) {
                lb_status.setText("PAGO");
                lb_status.setForeground(new Color(0,153,0));
            } else {
                lb_status.setText("PENDENTE");
                lb_status.setForeground(Color.RED);
            }

            if (valorPendente == null) {
                lb_valorPendente.setText("NENHUM");
            } else {
                lb_valorPendente.setText(valorPendente.replace(".", ","));
            }

            if (quantidadeParcelas.equals("0")) {
                lb_quantidadeParcelas.setText("AVISTA");
                lb_quantidadeParcelasPagas.setText("AVISTA");
                lb_quantidadeParcelasPendentes.setText("AVISTA");
            } else {
                lb_quantidadeParcelas.setText(quantidadeParcelas);
                lb_quantidadeParcelasPagas.setText(parcelasPagas);
                lb_quantidadeParcelasPendentes.setText(parcelasPendente);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FrmVendaCompleta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void dadosTabela() {
        tabelaProdutos.getTableHeader().setDefaultRenderer(new EstiloTabelaHeader());
        tabelaProdutos.setDefaultRenderer(Object.class, new EstiloTabelaRenderer());
        tabelaProdutos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaProdutos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (tabelaProdutos.getSelectedRow() != -1) {
                    atualizarDados();
                }
            }
        });
    }

    private void atualizarDados() {
        int linha = tabelaProdutos.getSelectedRow();
//        cp_codigo.setText(tabelaProdutos.getValueAt(linha, 0).toString());
//        cp_tipoProduto.setSelectedItem(tabelaProdutos.getValueAt(linha, 1).toString());
//        cp_nome.setText(tabelaProdutos.getValueAt(linha, 2).toString());
//        cp_preco.setText(tabelaProdutos.getValueAt(linha, 3).toString());
//        cp_quantidade.setText(tabelaProdutos.getValueAt(linha, 4).toString());

    }

    private void limparCampos() {
        if (tabelaProdutos.getSelectedRow() > -1) {
            tabelaProdutos.removeRowSelectionInterval(tabelaProdutos.getSelectedRow(), tabelaProdutos.getSelectedRow());
        }
//        cp_codigo.setText("");
//        cp_tipoProduto.setSelectedItem("TIPO PRODUTO");
//        cp_nome.setText("");
//        cp_preco.setText("");
//        cp_buscar.setText("");
//        cp_quantidade.setText("");

        preencherTabela(bancoDeposito.listarDeposito(codigo_venda));

    }

    private void preencherTabela(ResultSet rs) {
        DefaultTableModel modelo = (DefaultTableModel) vendas.FrmVendaCompleta.tabelaProdutos.getModel();

        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }

        String dados[] = new String[6];

        try {
            while (rs.next()) {

                String codigo_produto = rs.getString("codigo_produto");
                ResultSet produto = bancoProdutos.buscarCodigo(codigo_produto);
                produto.next();

                dados[0] = produto.getString("codigo_pro");
                dados[1] = produto.getString("tipo_pro");
                dados[2] = produto.getString("nome_pro");
                dados[3] = produto.getString("valor_pro").replace(".", ",");
                dados[4] = rs.getString("quantidade");

                Double valorProduto = Double.parseDouble(produto.getString("valor_pro"));
                Double quantidadeVenda = Double.parseDouble(rs.getString("quantidade"));

                dados[5] = String.valueOf(valorProduto * quantidadeVenda).replace(".", ",");
                modelo.addRow(dados);
            }
        } catch (SQLException ex) {

        }
    }

    private void selecionarLinha(String codigo) {
        for (int i = 0; i < tabelaProdutos.getRowCount(); i++) {
            if (codigo.equals(tabelaProdutos.getValueAt(i, 0))) {
                tabelaProdutos.setRowSelectionInterval(i, i);
                break;
            }
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

        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaProdutos = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lb_quantidadeParcelasPendentes = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        bt_registrar1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        lb_nomeCliente = new javax.swing.JLabel();
        lb_dataVenda = new javax.swing.JLabel();
        lb_formaPagamento = new javax.swing.JLabel();
        lb_status = new javax.swing.JLabel();
        lb_valorCompra = new javax.swing.JLabel();
        lb_entrada = new javax.swing.JLabel();
        lb_quantidadeParcelas = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lb_valorPendente = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        lb_quantidadeParcelasPagas = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Venda Datalhada");
        setPreferredSize(new java.awt.Dimension(606, 382));

        tabelaProdutos.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        tabelaProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "CÓDIGO", "TIPO DE PRODUTO", "NOME DO PRODUTO", "PREÇO", "QUANTIDADE", "TOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabelaProdutos);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "REGISTRO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(735, 142));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lb_quantidadeParcelasPendentes.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb_quantidadeParcelasPendentes.setText("NENHUMA");
        jPanel2.add(lb_quantidadeParcelasPendentes, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 90, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Status:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Data da Compra:");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Parcelas Pendentes:");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 90, -1, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Quantidade de Parcelas:");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 70, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("Entrada: R$");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 50, -1, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("Valor da Compra: R$");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 30, -1, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setText("Forma de Pagamento:");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        bt_registrar1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bt_registrar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/vendas/produto1.png"))); // NOI18N
        bt_registrar1.setBorder(null);
        bt_registrar1.setContentAreaFilled(false);
        bt_registrar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_registrar1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_registrar1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/vendas/produto.png"))); // NOI18N
        bt_registrar1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bt_registrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_registrar1ActionPerformed(evt);
            }
        });
        jPanel2.add(bt_registrar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Cliente:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        lb_nomeCliente.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb_nomeCliente.setText("Edson Magalhães da Costa");
        jPanel2.add(lb_nomeCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, -1, -1));

        lb_dataVenda.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb_dataVenda.setText("18/08/2020");
        jPanel2.add(lb_dataVenda, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, -1, -1));

        lb_formaPagamento.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb_formaPagamento.setText("CARTÃO DE CREDITO");
        jPanel2.add(lb_formaPagamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, -1, -1));

        lb_status.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb_status.setForeground(new java.awt.Color(0, 153, 0));
        lb_status.setText("PAGO");
        jPanel2.add(lb_status, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, -1, -1));

        lb_valorCompra.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb_valorCompra.setText("200,00");
        jPanel2.add(lb_valorCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 30, -1, -1));

        lb_entrada.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb_entrada.setText("200,00");
        jPanel2.add(lb_entrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 50, -1, -1));

        lb_quantidadeParcelas.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb_quantidadeParcelas.setText("NENHUMA");
        jPanel2.add(lb_quantidadeParcelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 70, -1, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel17.setText("Valor pendente: R$");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 130, -1, -1));

        lb_valorPendente.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb_valorPendente.setText("NENHUM");
        jPanel2.add(lb_valorPendente, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 130, -1, -1));

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel22.setText("Parcelas Pagas:");
        jPanel2.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 110, -1, -1));

        lb_quantidadeParcelasPagas.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb_quantidadeParcelasPagas.setText("NENHUMA");
        jPanel2.add(lb_quantidadeParcelasPagas, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 110, -1, -1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 149, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(197, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(13, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_registrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_registrar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_registrar1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_registrar1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_dataVenda;
    private javax.swing.JLabel lb_entrada;
    private javax.swing.JLabel lb_formaPagamento;
    private javax.swing.JLabel lb_nomeCliente;
    private javax.swing.JLabel lb_quantidadeParcelas;
    private javax.swing.JLabel lb_quantidadeParcelasPagas;
    private javax.swing.JLabel lb_quantidadeParcelasPendentes;
    private javax.swing.JLabel lb_status;
    private javax.swing.JLabel lb_valorCompra;
    private javax.swing.JLabel lb_valorPendente;
    public static javax.swing.JTable tabelaProdutos;
    // End of variables declaration//GEN-END:variables

    BancoProdutos bancoProdutos = new BancoProdutos();
    BancoDeposito bancoDeposito = new BancoDeposito();
    BancoParcelas bancoParcelas = new BancoParcelas();
    BancoClientes bancoClientes = new BancoClientes();
    BancoVendas bancoVendas = new BancoVendas();
    ControleMetodos controleMetodos = new ControleMetodos();
    ControleProduto controleProdutos = new ControleProduto();

    private int linha = vendas.FrmVendas.tabelaProdutos.getSelectedRow();
    private int coluna = 0;
    private String codigo_venda = vendas.FrmVendas.tabelaProdutos.getValueAt(linha, coluna).toString();
    private String nomeCliente;
    private String dataVenda;
    private String formaPagamento;
    private String status;
    private String valorCompra;
    private String entrada;
    private String quantidadeParcelas = bancoParcelas.quantidadeTotalParcelas(codigo_venda);
    private String parcelasPendente = bancoParcelas.quantidadeParcelasPendentes(codigo_venda);
    private String parcelasPagas = bancoParcelas.quantidadeParcelasPagas(codigo_venda);
    private String valorPendente = bancoParcelas.valorPendente(codigo_venda);

}
