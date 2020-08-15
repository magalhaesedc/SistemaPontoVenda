/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientes;

//import clientes.*;
import bancodedados.BancoClientes;
import controle.ControleMetodos;
import controle.ControleCliente;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import controle.EstiloTabelaHeader;
import controle.EstiloTabelaRenderer;
import javax.swing.JOptionPane;

/**
 *
 * @author Power Arts
 */
public class FrmClientes extends javax.swing.JInternalFrame {

    /**
     * Creates new form FrmClientes
     */
    public FrmClientes() {
        initComponents();
        alterarImagemLabel();
        dadosTabela();
        limparCampos();
    }

    private void dadosTabela() {
        tabelaClientes.getTableHeader().setDefaultRenderer(new EstiloTabelaHeader());
        tabelaClientes.setDefaultRenderer(Object.class, new EstiloTabelaRenderer());
        tabelaClientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaClientes.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (tabelaClientes.getSelectedRow() != -1) {
                    atualizarDados();
                }
            }
        });
    }

    private void atualizarDados() {
        int linha = tabelaClientes.getSelectedRow();
        cp_codigo.setText(tabelaClientes.getValueAt(linha, 0).toString());
        cp_nome.setText(tabelaClientes.getValueAt(linha, 1).toString());
        cp_sexo.setSelectedItem(tabelaClientes.getValueAt(linha, 2).toString());
        cp_celular.setText(tabelaClientes.getValueAt(linha, 3).toString());
    }

    private void limparCampos() {
        if (tabelaClientes.getSelectedRow() > -1) {
            tabelaClientes.removeRowSelectionInterval(tabelaClientes.getSelectedRow(), tabelaClientes.getSelectedRow());
        }
        cp_codigo.setText("");
        cp_nome.setText("");
        cp_sexo.setSelectedItem("SEXO");
        cp_celular.setText("");
        cp_buscar.setText("");
        preencherTabela(bancoClientes.listarClientes(""));

        if (bancoClientes.verificarExisteRegistro()) {
            setarCodigoCliente(bancoClientes.buscarMaiorCodigoCliente());
        } else {
            cp_codigo.setText("CLI0001");
        }
    }

    private void selecionarLinha(String codigo) {
        for (int i = 0; i < tabelaClientes.getRowCount(); i++) {
            if (codigo.equals(tabelaClientes.getValueAt(i, 0))) {
                tabelaClientes.setRowSelectionInterval(i, i);
                break;
            }
        }
    }

    private void preencherTabela(ResultSet rs) {
        DefaultTableModel modelo = (DefaultTableModel) clientes.FrmClientes.tabelaClientes.getModel();

        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }

        String dados[] = new String[4];

        try {
            while (rs.next()) {
                dados[0] = rs.getString("codigo_cl");
                dados[1] = rs.getString("nome_cl");
                dados[2] = rs.getString("sexo_cl");
                dados[3] = rs.getString("celular_cl");
                modelo.addRow(dados);
            }
        } catch (SQLException ex) {

        }
    }

    private void alterarImagemLabel() {
        //Alterar imagem JLabel SEXO 
        cp_sexo.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent ie) {
                if (cp_sexo.getSelectedItem().equals("SEXO")) {
                    lb_sexo.setIcon(new ImageIcon(getClass().getResource("/imagens/usuarios/sexoL.png")));
                }

                if (cp_sexo.getSelectedItem().equals("MASCULINO")) {
                    lb_sexo.setIcon(new ImageIcon(getClass().getResource("/imagens/usuarios/masL.png")));
                }

                if (cp_sexo.getSelectedItem().equals("FEMININO")) {
                    lb_sexo.setIcon(new ImageIcon(getClass().getResource("/imagens/usuarios/femL.png")));
                }
            }
        });
    }

    private void setarCodigoCliente(ResultSet rs) {

        int MAIOR_CODIGO_CLIENTE = 1;

        try {
            if (!rs.next()) {
                cp_codigo.setText("CLI0001");
            } else {
                String codigo_us = rs.getString(MAIOR_CODIGO_CLIENTE);
                char r1 = codigo_us.charAt(3);
                char r2 = codigo_us.charAt(4);
                char r3 = codigo_us.charAt(5);
                char r4 = codigo_us.charAt(6);

                //Pegar somente a parte numérica do CODIGO_US
                String codigoInteiroConcatenado = "" + r1 + r2 + r3 + r4;
                int codigoInteiro = Integer.parseInt(codigoInteiroConcatenado);
                String proximoCodigo = controleMetodos.gerarCodigoCliente(codigoInteiro);
                cp_codigo.setText(proximoCodigo);
            }
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cp_sexo = new org.bolivia.combo.SComboBoxBlue();
        lb_sexo = new javax.swing.JLabel();
        cp_celular = new app.bolivia.swing.JCTextField();
        jLabel3 = new javax.swing.JLabel();
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
        tabelaClientes = new javax.swing.JTable();

        setClosable(true);
        setTitle("Clientes");
        setPreferredSize(new java.awt.Dimension(845, 544));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "REGISTRO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(735, 142));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cp_nome.setBackground(new java.awt.Color(34, 102, 145));
        cp_nome.setBorder(null);
        cp_nome.setForeground(new java.awt.Color(255, 255, 255));
        cp_nome.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cp_nome.setOpaque(false);
        cp_nome.setPhColor(new java.awt.Color(255, 255, 255));
        cp_nome.setPlaceholder("NOME CLIENTE");
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
        jPanel2.add(cp_nome, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 40, 170, 30));

        cp_codigo.setEditable(false);
        cp_codigo.setBackground(new java.awt.Color(34, 102, 145));
        cp_codigo.setBorder(null);
        cp_codigo.setForeground(new java.awt.Color(255, 255, 255));
        cp_codigo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cp_codigo.setOpaque(false);
        cp_codigo.setPhColor(new java.awt.Color(255, 255, 255));
        cp_codigo.setPlaceholder("CÓDIGO");
        jPanel2.add(cp_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, 170, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/produtos/codigoL.png"))); // NOI18N
        jLabel1.setOpaque(true);
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/usuarios/nomUs.png"))); // NOI18N
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 30, -1, -1));

        cp_sexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SEXO", "MASCULINO", "FEMININO" }));
        cp_sexo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cp_sexo.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel2.add(cp_sexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 180, 50));

        lb_sexo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/usuarios/sexoL.png"))); // NOI18N
        lb_sexo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(lb_sexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 110, -1, -1));

        cp_celular.setBackground(new java.awt.Color(34, 102, 145));
        cp_celular.setBorder(null);
        cp_celular.setForeground(new java.awt.Color(255, 255, 255));
        cp_celular.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cp_celular.setOpaque(false);
        cp_celular.setPhColor(new java.awt.Color(255, 255, 255));
        cp_celular.setPlaceholder("CELULAR");
        cp_celular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cp_celularActionPerformed(evt);
            }
        });
        cp_celular.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cp_celularKeyReleased(evt);
            }
        });
        jPanel2.add(cp_celular, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 120, 170, 30));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/clientes/celular.png"))); // NOI18N
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 110, -1, -1));

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
                .addGap(15, 15, 15)
                .addComponent(bt_registrar)
                .addGap(15, 15, 15)
                .addComponent(bt_atualizar)
                .addGap(15, 15, 15)
                .addComponent(bt_excluir)
                .addGap(15, 15, 15)
                .addComponent(bt_excluirTudo)
                .addGap(15, 15, 15)
                .addComponent(bt_limparCampos)
                .addGap(15, 15, 15))
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
        cp_buscar.setPhColor(new java.awt.Color(255, 255, 255));
        cp_buscar.setPlaceholder("CÓDIGO/NOME");
        cp_buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cp_buscarKeyReleased(evt);
            }
        });
        jPanel4.add(cp_buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 54, 170, 40));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/produtos/buscarL.png"))); // NOI18N
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 48, -1, -1));

        tabelaClientes.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        tabelaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "CÓDIGO", "NOME", "SEXO", "CELUALR"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabelaClientes);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cp_nomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cp_nomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cp_nomeActionPerformed

    private void cp_buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cp_buscarKeyReleased
        // TODO add your handling code here:
        cp_buscar.setText(cp_buscar.getText().toUpperCase());
        preencherTabela(bancoClientes.listarClientes(cp_buscar.getText()));
    }//GEN-LAST:event_cp_buscarKeyReleased

    private void bt_limparCamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_limparCamposActionPerformed
        limparCampos();
    }//GEN-LAST:event_bt_limparCamposActionPerformed

    private void bt_registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_registrarActionPerformed
        String codigo = cp_codigo.getText();
        String nome = cp_nome.getText();
        String sexo = cp_sexo.getSelectedItem().toString();
        String celular = cp_celular.getText();

        String msg = controleCliente.registrarCliente(codigo, nome, sexo, celular);
        if (msg.equals("Registrado com sucesso!")) {
            limparCampos();
            selecionarLinha(codigo);
        }
        JOptionPane.showMessageDialog(this, msg);
    }//GEN-LAST:event_bt_registrarActionPerformed

    private void bt_atualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_atualizarActionPerformed
        String codigo = cp_codigo.getText();
        String nome = cp_nome.getText();
        String sexo = cp_sexo.getSelectedItem().toString();
        String celular = cp_celular.getText();

        String msg;

        if (tabelaClientes.getRowCount() > 0) {
            if (tabelaClientes.getSelectedRow() >= 0) {
                msg = controleCliente.atualizarCliente(codigo, nome, sexo, celular);
            } else {
                msg = "Nenhum cliente selecionado!\nSelecione um cliente na tabela abaixo.";
            }
        } else {
            msg = "Nenhum cliente cadastrado!";
        }

        if (msg.equals("Atualizado com sucesso!")) {
            limparCampos();
            selecionarLinha(codigo);
        }
        JOptionPane.showMessageDialog(this, msg);
    }//GEN-LAST:event_bt_atualizarActionPerformed

    private void cp_nomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cp_nomeKeyReleased
        cp_nome.setText(cp_nome.getText().toUpperCase());
    }//GEN-LAST:event_cp_nomeKeyReleased

    private void bt_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_excluirActionPerformed
        // TODO add your handling code here:
        String codigo = cp_codigo.getText();
        String nome = cp_nome.getText();

        String msg;

        if (tabelaClientes.getSelectedRow() >= 0) {
            msg = bancoClientes.exluirCliente(codigo);
        } else {
            msg = "Nenhum cliente selecionado!\nSelecione um cliente na tabela abaixo.";
        }

        if (msg.equals("O cliente foi excluido com sucesso!")) {
            msg = "o cliente \"" + nome + "\" foi excluido com sucesso!";
            limparCampos();
            selecionarLinha(codigo);
        }
        JOptionPane.showMessageDialog(this, msg);
    }//GEN-LAST:event_bt_excluirActionPerformed

    private void bt_excluirTudoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_excluirTudoActionPerformed
        String msg = bancoClientes.exluirTodos();

        if (msg.equals("Todos os registros foram excluidos com sucesso!")) {
            limparCampos();
        }

        JOptionPane.showMessageDialog(this, msg);
    }//GEN-LAST:event_bt_excluirTudoActionPerformed

    private void cp_celularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cp_celularActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cp_celularActionPerformed

    private void cp_celularKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cp_celularKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cp_celularKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_atualizar;
    private javax.swing.JButton bt_excluir;
    private javax.swing.JButton bt_excluirTudo;
    private javax.swing.JButton bt_limparCampos;
    private javax.swing.JButton bt_registrar;
    private app.bolivia.swing.JCTextField cp_buscar;
    private app.bolivia.swing.JCTextField cp_celular;
    private app.bolivia.swing.JCTextField cp_codigo;
    private app.bolivia.swing.JCTextField cp_nome;
    private org.bolivia.combo.SComboBoxBlue cp_sexo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_sexo;
    public static javax.swing.JTable tabelaClientes;
    // End of variables declaration//GEN-END:variables

    ControleCliente controleCliente = new ControleCliente();
    BancoClientes bancoClientes = new BancoClientes();
    ControleMetodos controleMetodos = new ControleMetodos();

}
