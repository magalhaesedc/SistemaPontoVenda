/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuarios;

import bancodedados.BancoUsuarios;
import controle.ControleMetodos;
import controle.ControleUsuario;
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
public class FrmUsuarios extends javax.swing.JInternalFrame {

    /**
     * Creates new form FrmUsuarios
     */
    public FrmUsuarios() {
        initComponents();
        alterarImagemLabel();
        dadosTabela();
        limparCampos();
    }

    private void dadosTabela() {
        tabelaUsuarios.getTableHeader().setDefaultRenderer(new EstiloTabelaHeader());
        tabelaUsuarios.setDefaultRenderer(Object.class, new EstiloTabelaRenderer());
        tabelaUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaUsuarios.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (tabelaUsuarios.getSelectedRow() != -1) {
                    atualizarDados();
                }
            }
        });
    }

    private void atualizarDados() {
        int linha = tabelaUsuarios.getSelectedRow();
        cp_codigo.setText(tabelaUsuarios.getValueAt(linha, 0).toString());
        cp_nome.setText(tabelaUsuarios.getValueAt(linha, 1).toString());
        cp_login.setText(tabelaUsuarios.getValueAt(linha, 4).toString());
        cp_sexo.setSelectedItem(tabelaUsuarios.getValueAt(linha, 2).toString());
        cp_tipoUsuario.setSelectedItem(tabelaUsuarios.getValueAt(linha, 3).toString());
    }

    private void limparCampos() {
        if (tabelaUsuarios.getSelectedRow() > -1) {
            tabelaUsuarios.removeRowSelectionInterval(tabelaUsuarios.getSelectedRow(), tabelaUsuarios.getSelectedRow());
        }
        cp_codigo.setText("");
        cp_nome.setText("");
        cp_login.setText("");
        cp_sexo.setSelectedItem("SEXO");
        cp_tipoUsuario.setSelectedItem("TIPO USUÁRIO");
        cp_senha.setText("");
        cp_buscar.setText("");
        preencherTabela(bancoUsuarios.listarUsuarios(""));

        if (bancoUsuarios.verificarExisteRegistro()) {
            setarCodigoUsuario(bancoUsuarios.buscarMaiorCodigoUsuario());
        } else {
            cp_codigo.setText("USU0001");
        }
    }

    private void selecionarLinha(String codigo) {
        for (int i = 0; i < tabelaUsuarios.getRowCount(); i++) {
            if (codigo.equals(tabelaUsuarios.getValueAt(i, 0))) {
                tabelaUsuarios.setRowSelectionInterval(i, i);
                break;
            }
        }
    }

    private void preencherTabela(ResultSet rs) {
        DefaultTableModel modelo = (DefaultTableModel) usuarios.FrmUsuarios.tabelaUsuarios.getModel();

        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }

        String dados[] = new String[5];

        try {
            while (rs.next()) {

                if (rs.getString("login_us").equalsIgnoreCase("root")) {
                    continue;
                }

                dados[0] = rs.getString("codigo_us");
                dados[1] = rs.getString("nome_us");
                dados[2] = rs.getString("sexo_us");
                dados[3] = rs.getString("tipo_us");
                dados[4] = rs.getString("login_us");
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

        //Alterar imagem JLabel TIPO USUÁRIO 
        cp_tipoUsuario.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent ie) {
                if (cp_tipoUsuario.getSelectedItem().equals("TIPO USUÁRIO")) {
                    lb_tipoUsuario.setIcon(new ImageIcon(getClass().getResource("/imagens/usuarios/tipousL.png")));
                }

                if (cp_tipoUsuario.getSelectedItem().equals("ADMINISTRADOR")) {
                    lb_tipoUsuario.setIcon(new ImageIcon(getClass().getResource("/imagens/usuarios/administrador.png")));
                }

                if (cp_tipoUsuario.getSelectedItem().equals("PADRÃO")) {
                    lb_tipoUsuario.setIcon(new ImageIcon(getClass().getResource("/imagens/usuarios/normal.png")));
                }
            }
        });
    }

    private void setarCodigoUsuario(ResultSet rs) {

        int MAIOR_CODIGO_USUARIO = 1;

        try {
            if (!rs.next()) {
                cp_codigo.setText("USU0001");
            } else {
                String codigo_us = rs.getString(MAIOR_CODIGO_USUARIO);
                char r1 = codigo_us.charAt(3);
                char r2 = codigo_us.charAt(4);
                char r3 = codigo_us.charAt(5);
                char r4 = codigo_us.charAt(6);

                //Pegar somente a parte numérica do CODIGO_US
                String codigoInteiroConcatenado = "" + r1 + r2 + r3 + r4;
                int codigoInteiro = Integer.parseInt(codigoInteiroConcatenado);
                String proximoCodigo = controleMetodos.gerarCodigoUsuario(codigoInteiro);
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
        cp_senha = new javax.swing.JPasswordField();
        cp_nome = new app.bolivia.swing.JCTextField();
        cp_codigo = new app.bolivia.swing.JCTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lb_tipoUsuario = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cp_tipoUsuario = new org.bolivia.combo.SComboBoxBlue();
        cp_sexo = new org.bolivia.combo.SComboBoxBlue();
        lb_sexo = new javax.swing.JLabel();
        cp_login = new app.bolivia.swing.JCTextField();
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
        tabelaUsuarios = new javax.swing.JTable();

        setClosable(true);
        setTitle("Usuários");
        setPreferredSize(new java.awt.Dimension(845, 530));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "REGISTRO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(735, 142));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cp_senha.setBackground(new java.awt.Color(42, 102, 142));
        cp_senha.setForeground(new java.awt.Color(255, 255, 255));
        cp_senha.setBorder(null);
        cp_senha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cp_senhaActionPerformed(evt);
            }
        });
        jPanel2.add(cp_senha, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 40, 170, 30));

        cp_nome.setBackground(new java.awt.Color(34, 102, 145));
        cp_nome.setBorder(null);
        cp_nome.setForeground(new java.awt.Color(255, 255, 255));
        cp_nome.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cp_nome.setOpaque(false);
        cp_nome.setPhColor(new java.awt.Color(255, 255, 255));
        cp_nome.setPlaceholder("NOME USUÁRIO");
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
        jPanel2.add(cp_nome, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 40, 170, 30));

        cp_codigo.setEditable(false);
        cp_codigo.setBackground(new java.awt.Color(34, 102, 145));
        cp_codigo.setBorder(null);
        cp_codigo.setForeground(new java.awt.Color(255, 255, 255));
        cp_codigo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cp_codigo.setOpaque(false);
        cp_codigo.setPhColor(new java.awt.Color(255, 255, 255));
        cp_codigo.setPlaceholder("CÓDIGO");
        jPanel2.add(cp_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 170, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/produtos/codigoL.png"))); // NOI18N
        jLabel1.setOpaque(true);
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/usuarios/nomUs.png"))); // NOI18N
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, -1, -1));

        lb_tipoUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/usuarios/tipousL.png"))); // NOI18N
        lb_tipoUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(lb_tipoUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 100, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/usuarios/senha.png"))); // NOI18N
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 30, -1, -1));

        cp_tipoUsuario.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TIPO USUÁRIO", "ADMINISTRADOR", "PADRÃO" }));
        cp_tipoUsuario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cp_tipoUsuario.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel2.add(cp_tipoUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 100, 180, 50));

        cp_sexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SEXO", "MASCULINO", "FEMININO" }));
        cp_sexo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cp_sexo.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel2.add(cp_sexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 100, 180, 50));

        lb_sexo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/usuarios/sexoL.png"))); // NOI18N
        lb_sexo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(lb_sexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 100, -1, -1));

        cp_login.setBackground(new java.awt.Color(34, 102, 145));
        cp_login.setBorder(null);
        cp_login.setForeground(new java.awt.Color(255, 255, 255));
        cp_login.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cp_login.setOpaque(false);
        cp_login.setPhColor(new java.awt.Color(255, 255, 255));
        cp_login.setPlaceholder("LOGIN");
        cp_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cp_loginActionPerformed(evt);
            }
        });
        cp_login.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cp_loginKeyReleased(evt);
            }
        });
        jPanel2.add(cp_login, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 170, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/usuarios/loginuser.png"))); // NOI18N
        jLabel3.setOpaque(true);
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

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
                .addContainerGap()
                .addComponent(bt_registrar)
                .addGap(34, 34, 34)
                .addComponent(bt_atualizar)
                .addGap(28, 28, 28)
                .addComponent(bt_excluir)
                .addGap(27, 27, 27)
                .addComponent(bt_excluirTudo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bt_limparCampos)
                .addContainerGap())
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

        tabelaUsuarios.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        tabelaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "CÓDIGO", "NOME", "SEXO", "TIPO", "LOGIN"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabelaUsuarios);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 806, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
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
                .addContainerGap())
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

    private void cp_senhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cp_senhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cp_senhaActionPerformed

    private void cp_buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cp_buscarKeyReleased
        // TODO add your handling code here:
        cp_buscar.setText(cp_buscar.getText().toUpperCase());
        preencherTabela(bancoUsuarios.listarUsuarios(cp_buscar.getText()));
    }//GEN-LAST:event_cp_buscarKeyReleased

    private void bt_limparCamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_limparCamposActionPerformed
        limparCampos();
    }//GEN-LAST:event_bt_limparCamposActionPerformed

    private void bt_registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_registrarActionPerformed
        String codigo = cp_codigo.getText();
        String nome = cp_nome.getText();
        String login = cp_login.getText();
        String sexo = cp_sexo.getSelectedItem().toString();
        String tipo = cp_tipoUsuario.getSelectedItem().toString();
        String senha = new String(cp_senha.getPassword());

        if (!senha.isEmpty()) {
            senha = controleMetodos.criptografar(senha);
        }

        String msg = controleUsuario.registrarUsuario(codigo, nome, login, sexo, tipo, senha);
        if (msg.equals("Registrado com sucesso!")) {
            limparCampos();
            selecionarLinha(codigo);
        }
        JOptionPane.showMessageDialog(this, msg);
    }//GEN-LAST:event_bt_registrarActionPerformed

    private void bt_atualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_atualizarActionPerformed
        String codigo = cp_codigo.getText();
        String nome = cp_nome.getText();
        String login = cp_login.getText();
        String sexo = cp_sexo.getSelectedItem().toString();
        String tipo = cp_tipoUsuario.getSelectedItem().toString();
        String senha = new String(cp_senha.getPassword());

        String msg;

        if (tabelaUsuarios.getRowCount() > 0) {
            if (tabelaUsuarios.getSelectedRow() >= 0) {
                if (senha.isEmpty()) {
                    try {
                        ResultSet senhaAtual = bancoUsuarios.buscarCodigo(codigo);
                        senhaAtual.next();
                        senha = senhaAtual.getString("senha");
                    } catch (SQLException ex) {
                        System.err.println(ex);
                    }
                } else {
                    senha = controleMetodos.criptografar(senha);
                }
                msg = controleUsuario.atualizarUsuario(codigo, nome, login, sexo, tipo, senha);
            } else {
                msg = "Nenhum usuário selecionado!\nSelecione um usuário na tabela abaixo.";
            }
        } else {
            msg = "Nenhum usuário cadastrado!";
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

        if (tabelaUsuarios.getSelectedRow() >= 0) {
            msg = bancoUsuarios.exluirUsuario(codigo);
        } else {
            msg = "Nenhum usuário selecionado!\nSelecione um usuário na tabela abaixo.";
        }

        if (msg.equals("O usuário foi excluido com sucesso!")) {
            msg = "o usuário \"" + nome + "\" foi excluido com sucesso!";
            limparCampos();
            selecionarLinha(codigo);
        }
        JOptionPane.showMessageDialog(this, msg);
    }//GEN-LAST:event_bt_excluirActionPerformed

    private void bt_excluirTudoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_excluirTudoActionPerformed
        String msg = bancoUsuarios.exluirTodos();

        if (msg.equals("Todos os registros foram excluidos com sucesso!")) {
            limparCampos();
        }

        JOptionPane.showMessageDialog(this, msg);
    }//GEN-LAST:event_bt_excluirTudoActionPerformed

    private void cp_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cp_loginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cp_loginActionPerformed

    private void cp_loginKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cp_loginKeyReleased
         cp_login.setText(cp_login.getText().toUpperCase());
    }//GEN-LAST:event_cp_loginKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_atualizar;
    private javax.swing.JButton bt_excluir;
    private javax.swing.JButton bt_excluirTudo;
    private javax.swing.JButton bt_limparCampos;
    private javax.swing.JButton bt_registrar;
    private app.bolivia.swing.JCTextField cp_buscar;
    private app.bolivia.swing.JCTextField cp_codigo;
    private app.bolivia.swing.JCTextField cp_login;
    private app.bolivia.swing.JCTextField cp_nome;
    private javax.swing.JPasswordField cp_senha;
    private org.bolivia.combo.SComboBoxBlue cp_sexo;
    private org.bolivia.combo.SComboBoxBlue cp_tipoUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_sexo;
    private javax.swing.JLabel lb_tipoUsuario;
    public static javax.swing.JTable tabelaUsuarios;
    // End of variables declaration//GEN-END:variables

    ControleUsuario controleUsuario = new ControleUsuario();
    BancoUsuarios bancoUsuarios = new BancoUsuarios();
    ControleMetodos controleMetodos = new ControleMetodos();

}
