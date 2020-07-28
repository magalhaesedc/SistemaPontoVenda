/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produtos;

import bancodedados.BancoProdutos;
import controle.ControleMetodos;
import controle.ControleProduto;
import controle.EstiloTabelaHeader;
import controle.EstiloTabelaRenderer;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Power Arts
 */
public class FrmProdutos extends javax.swing.JInternalFrame {

    /**
     * Creates new form FrmProdutos
     */
    public FrmProdutos() {
        initComponents();
        dadosTabela();
        limparCampos();
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
        cp_codigo.setText(tabelaProdutos.getValueAt(linha, 0).toString());
        cp_tipoProduto.setSelectedItem(tabelaProdutos.getValueAt(linha, 1).toString());
        cp_nome.setText(tabelaProdutos.getValueAt(linha, 2).toString());
        cp_preco.setText(tabelaProdutos.getValueAt(linha, 3).toString());

    }

    private void limparCampos() {
        if (tabelaProdutos.getSelectedRow() > -1) {
            tabelaProdutos.removeRowSelectionInterval(tabelaProdutos.getSelectedRow(), tabelaProdutos.getSelectedRow());
        }
        cp_codigo.setText("");
        cp_tipoProduto.setSelectedItem("TIPO PRODUTO");
        cp_nome.setText("");
        cp_preco.setText("");
        cp_buscar.setText("");

        preencherTabela(bancoProdutos.listarProdutos(""));

        if (bancoProdutos.verificarExisteRegistro()) {
            setarCodigoProduto(bancoProdutos.buscarMaiorCodigoProduto());
        } else {
            cp_codigo.setText("PRO0001");
        }
    }

    private void preencherTabela(ResultSet rs) {
        DefaultTableModel modelo = (DefaultTableModel) produtos.FrmProdutos.tabelaProdutos.getModel();

        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }

        String dados[] = new String[4];

        try {
            while (rs.next()) {
                dados[0] = rs.getString("codigo_pro");
                dados[1] = rs.getString("tipo_pro");
                dados[2] = rs.getString("nome_pro");
                dados[3] = rs.getString("valor_pro").replace(".", ",");
                modelo.addRow(dados);
            }
        } catch (SQLException ex) {

        }
    }

    private void setarCodigoProduto(ResultSet rs) {

        int MAIOR_CODIGO_PRODUTO = 1;

        try {
            if (!rs.next()) {
                cp_codigo.setText("PRO0001");
            } else {
                String codigo_us = rs.getString(MAIOR_CODIGO_PRODUTO);
                char r1 = codigo_us.charAt(3);
                char r2 = codigo_us.charAt(4);
                char r3 = codigo_us.charAt(5);
                char r4 = codigo_us.charAt(6);

                //Pegar somente a parte numérica do CODIGO_US
                String codigoInteiroConcatenado = "" + r1 + r2 + r3 + r4;
                int codigoInteiro = Integer.parseInt(codigoInteiroConcatenado);
                String proximoCodigo = controleMetodos.gerarCodigoProduto(codigoInteiro);
                cp_codigo.setText(proximoCodigo);
            }
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        cp_nome = new app.bolivia.swing.JCTextField();
        cp_codigo = new app.bolivia.swing.JCTextField();
        cp_preco = new app.bolivia.swing.JCTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lb_tipoProduto = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cp_tipoProduto = new org.bolivia.combo.SComboBoxBlue();
        jPanel3 = new javax.swing.JPanel();
        bt_excluirTudo = new javax.swing.JButton();
        bt_registrar = new javax.swing.JButton();
        bt_atualizar = new javax.swing.JButton();
        bt_excluir = new javax.swing.JButton();
        bt_limparCampos = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        cp_buscar = new app.bolivia.swing.JCTextField();
        jLabel5 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaProdutos = new javax.swing.JTable();

        setClosable(true);
        setTitle("Produtos");
        setPreferredSize(new java.awt.Dimension(780, 530));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "REGISTRO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(735, 142));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cp_nome.setBackground(new java.awt.Color(34, 102, 145));
        cp_nome.setBorder(null);
        cp_nome.setForeground(new java.awt.Color(255, 255, 255));
        cp_nome.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cp_nome.setOpaque(false);
        cp_nome.setPlaceholder("NOME PRODUTO");
        cp_nome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cp_nomeActionPerformed(evt);
            }
        });
        cp_nome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cp_nomeKeyReleased(evt);
            }
        });
        jPanel2.add(cp_nome, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 96, 170, 40));

        cp_codigo.setEditable(false);
        cp_codigo.setBackground(new java.awt.Color(34, 102, 145));
        cp_codigo.setBorder(null);
        cp_codigo.setForeground(new java.awt.Color(255, 255, 255));
        cp_codigo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cp_codigo.setOpaque(false);
        cp_codigo.setPlaceholder("CÓDIGO");
        jPanel2.add(cp_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 36, 170, 40));

        cp_preco.setBackground(new java.awt.Color(34, 102, 145));
        cp_preco.setBorder(null);
        cp_preco.setForeground(new java.awt.Color(255, 255, 255));
        cp_preco.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cp_preco.setOpaque(false);
        cp_preco.setPlaceholder("PREÇO");
        jPanel2.add(cp_preco, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 96, 170, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/produtos/codigoL.png"))); // NOI18N
        jLabel1.setOpaque(true);
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/produtos/nomeL.png"))); // NOI18N
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, -1, -1));

        lb_tipoProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/produtos/tipopro.png"))); // NOI18N
        lb_tipoProduto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(lb_tipoProduto, new org.netbeans.lib.awtextra.AbsoluteConstraints(611, 30, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/produtos/precoL.png"))); // NOI18N
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 90, -1, -1));

        cp_tipoProduto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TIPO PRODUTO", "PAPELARIA", "BEBIDAS", "LIMPEZA", "CARNES", "CONGELADOS", "LACTINEOS", "VERDURAS" }));
        cp_tipoProduto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cp_tipoProduto.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel2.add(cp_tipoProduto, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 30, 181, 50));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "OPÇÕES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setRequestFocusEnabled(false);

        bt_excluirTudo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bt_excluirTudo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/produtos/apagarT1.png"))); // NOI18N
        bt_excluirTudo.setText("Excluir Tudo");
        bt_excluirTudo.setBorder(null);
        bt_excluirTudo.setContentAreaFilled(false);
        bt_excluirTudo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_excluirTudo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_excluirTudo.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/produtos/apagarT.png"))); // NOI18N
        bt_excluirTudo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bt_excluirTudo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_excluirTudoActionPerformed(evt);
            }
        });

        bt_registrar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bt_registrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/produtos/regis2.png"))); // NOI18N
        bt_registrar.setText("Registrar");
        bt_registrar.setBorder(null);
        bt_registrar.setContentAreaFilled(false);
        bt_registrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_registrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_registrar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/produtos/regis1.png"))); // NOI18N
        bt_registrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bt_registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_registrarActionPerformed(evt);
            }
        });

        bt_atualizar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bt_atualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/produtos/editar1.png"))); // NOI18N
        bt_atualizar.setText("Atualizar");
        bt_atualizar.setBorder(null);
        bt_atualizar.setContentAreaFilled(false);
        bt_atualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_atualizar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_atualizar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/produtos/editar.png"))); // NOI18N
        bt_atualizar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bt_atualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_atualizarActionPerformed(evt);
            }
        });

        bt_excluir.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bt_excluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/produtos/apagar1.png"))); // NOI18N
        bt_excluir.setText("Excluir");
        bt_excluir.setBorder(null);
        bt_excluir.setContentAreaFilled(false);
        bt_excluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_excluir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_excluir.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/produtos/apagar.png"))); // NOI18N
        bt_excluir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bt_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_excluirActionPerformed(evt);
            }
        });

        bt_limparCampos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bt_limparCampos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/produtos/limpar1.png"))); // NOI18N
        bt_limparCampos.setText("Limpar Campos");
        bt_limparCampos.setBorder(null);
        bt_limparCampos.setContentAreaFilled(false);
        bt_limparCampos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_limparCampos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_limparCampos.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/produtos/limpar.png"))); // NOI18N
        bt_limparCampos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bt_limparCampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_limparCamposActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(bt_registrar)
                .addGap(10, 10, 10)
                .addComponent(bt_atualizar)
                .addGap(10, 10, 10)
                .addComponent(bt_excluir)
                .addGap(10, 10, 10)
                .addComponent(bt_excluirTudo)
                .addGap(10, 10, 10)
                .addComponent(bt_limparCampos)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bt_registrar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
            .addComponent(bt_atualizar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bt_excluir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bt_excluirTudo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bt_limparCampos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "BUSCAR", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cp_buscar.setBackground(new java.awt.Color(34, 102, 145));
        cp_buscar.setBorder(null);
        cp_buscar.setForeground(new java.awt.Color(255, 255, 255));
        cp_buscar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cp_buscar.setOpaque(false);
        cp_buscar.setPlaceholder("CÓDIGO/NOME");
        cp_buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cp_buscarKeyReleased(evt);
            }
        });
        jPanel4.add(cp_buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 54, 170, 40));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/produtos/buscarL.png"))); // NOI18N
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 48, -1, -1));

        tabelaProdutos.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        tabelaProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "CÓDIGO", "TIPO DE PRODUTO", "NOME DO PRODUTO", "PREÇO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
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
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 770, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 4, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 4, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 498, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 10, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 10, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cp_nomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cp_nomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cp_nomeActionPerformed

    private void cp_buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cp_buscarKeyReleased
        cp_buscar.setText(cp_buscar.getText().toUpperCase());
        preencherTabela(bancoProdutos.listarProdutos(cp_buscar.getText()));
    }//GEN-LAST:event_cp_buscarKeyReleased

    private void bt_limparCamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_limparCamposActionPerformed
        limparCampos();
    }//GEN-LAST:event_bt_limparCamposActionPerformed

    private void bt_registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_registrarActionPerformed

        String codigo = cp_codigo.getText();
        String tipo = cp_tipoProduto.getSelectedItem().toString();
        String nome = cp_nome.getText();
        String valor = cp_preco.getText();

        String msg = controleProdutos.registrarProduto(codigo, tipo, nome, valor);
        if (msg.equals("Registrado com sucesso!")) {
            limparCampos();
            selecionarLinha(codigo);
        }
        JOptionPane.showMessageDialog(this, msg);
    }//GEN-LAST:event_bt_registrarActionPerformed

    private void cp_nomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cp_nomeKeyReleased
        cp_nome.setText(cp_nome.getText().toUpperCase());
    }//GEN-LAST:event_cp_nomeKeyReleased

    private void bt_atualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_atualizarActionPerformed

        String codigo = cp_codigo.getText();
        String tipo = cp_tipoProduto.getSelectedItem().toString();
        String nome = cp_nome.getText();
        String valor = cp_preco.getText();

        String msg;

        if (tabelaProdutos.getRowCount() > 0) {
            if (tabelaProdutos.getSelectedRow() >= 0) {
                msg = controleProdutos.atualizarProduto(codigo, tipo, nome, valor);
            } else {
                msg = "Nenhum produto selecionado!\nSelecione um produto na tabela abaixo.";
            }
        } else {
            msg = "Nenhum produto cadastrado!";
        }

        if (msg.equals("Atualizado com sucesso!")) {
            limparCampos();
            selecionarLinha(codigo);
        }
        JOptionPane.showMessageDialog(this, msg);

    }//GEN-LAST:event_bt_atualizarActionPerformed

    private void bt_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_excluirActionPerformed

        String codigo = cp_codigo.getText();
        String nome = cp_nome.getText();

        String msg;

        if (tabelaProdutos.getSelectedRow() >= 0) {
            msg = bancoProdutos.exluirProduto(codigo);
        } else {
            msg = "Nenhum produto selecionado!\nSelecione um produto na tabela abaixo.";
        }

        if (msg.equals("O produto foi excluido com sucesso!")) {
            msg = "o produto \"" + nome + "\" foi excluido com sucesso!";
            limparCampos();
            selecionarLinha(codigo);
        }
        JOptionPane.showMessageDialog(this, msg);
    }//GEN-LAST:event_bt_excluirActionPerformed

    private void bt_excluirTudoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_excluirTudoActionPerformed

        String msg = bancoProdutos.exluirTodos();

        if (msg.equals("Todos os registros foram excluidos com sucesso!")) {
            limparCampos();
        }

        JOptionPane.showMessageDialog(this, msg);
    }//GEN-LAST:event_bt_excluirTudoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_atualizar;
    private javax.swing.JButton bt_excluir;
    private javax.swing.JButton bt_excluirTudo;
    private javax.swing.JButton bt_limparCampos;
    private javax.swing.JButton bt_registrar;
    private app.bolivia.swing.JCTextField cp_buscar;
    private app.bolivia.swing.JCTextField cp_codigo;
    private app.bolivia.swing.JCTextField cp_nome;
    private app.bolivia.swing.JCTextField cp_preco;
    private org.bolivia.combo.SComboBoxBlue cp_tipoProduto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_tipoProduto;
    public static javax.swing.JTable tabelaProdutos;
    // End of variables declaration//GEN-END:variables

    BancoProdutos bancoProdutos = new BancoProdutos();
    ControleMetodos controleMetodos = new ControleMetodos();
    ControleProduto controleProdutos = new ControleProduto();

}
