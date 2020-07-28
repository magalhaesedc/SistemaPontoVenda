/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produtos;

import bancodedados.BancoProdutos;
import controle.EstiloTabelaHeader;
import controle.EstiloTabelaRenderer;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Power Arts
 */
public class FrmListaProdutos extends javax.swing.JInternalFrame {

    /**
     * Creates new form FrmListaProdutos
     */
    public FrmListaProdutos() {
        initComponents();
        preencherTabela(bancoProdutos.listarProdutos(""));
        atualizarTabelaComboBox();
    }

    private void preencherTabela(ResultSet rs) {
        DefaultTableModel modelo = (DefaultTableModel) produtos.FrmListaProdutos.tabelaProdutos.getModel();

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

    private void atualizarTabelaComboBox() {
        //Atualizar tabela com produtos igual a CAIXA DE SELEÇÃO

        tabelaProdutos.getTableHeader().setDefaultRenderer(new EstiloTabelaHeader());
        tabelaProdutos.setDefaultRenderer(Object.class, new EstiloTabelaRenderer());
        tabelaProdutos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        cp_tipo.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent ie) {
                String busca = cp_buscar.getText();
                String tipo = "";

                if (!cp_tipo.getSelectedItem().equals("TIPO PRODUTO")) {
                    tipo = cp_tipo.getSelectedItem().toString();
                }
                preencherTabela(bancoProdutos.listarProdutos(busca, tipo));
            }
        });
    }

    public void calcularTabelaCaixa() {
        String valorTabelaCaixa;
        String quantidadeTabelaCaixa;
        double total = 0;
        double valorConvertido;
        int quantidadeConvertido;
        double calc = 0.0;

        for (int i = 0; i < vendas.FrmCaixa.tabelaCaixa.getRowCount(); i++) {
            valorTabelaCaixa = vendas.FrmCaixa.tabelaCaixa.getValueAt(i, 3).toString();
            quantidadeTabelaCaixa = vendas.FrmCaixa.tabelaCaixa.getValueAt(i, 4).toString();
            valorConvertido = Double.parseDouble(valorTabelaCaixa.replace(",", "."));
            quantidadeConvertido = Integer.parseInt(quantidadeTabelaCaixa);
            calc = valorConvertido * quantidadeConvertido;
            total = total + calc;
            vendas.FrmCaixa.tabelaCaixa.setValueAt(String.format("%.2f", Math.rint(calc * 100) / 100), i, 5);
        }
        
        vendas.FrmCaixa.cp_total.setText(String.format("%.2f", Math.rint(total * 100) / 100));
        
    }

    public static boolean isNumber(String n) {
        try {
            if (Integer.parseInt(n) > 0) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException nfe) {
            return false;
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
        cp_tipo = new org.bolivia.combo.SComboBoxBlue();
        jLabel3 = new javax.swing.JLabel();
        bt_enviarProduto = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaProdutos = new javax.swing.JTable();

        setClosable(true);
        setTitle("Bucar Produtos");
        setPreferredSize(new java.awt.Dimension(690, 432));
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "OPÇÕES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(735, 142));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jPanel2.add(cp_buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 170, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/produtos/buscarL.png"))); // NOI18N
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, 50));

        cp_tipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TIPO PRODUTO", "PAPELARIA", "BEBIDAS", "LIMPEZA", "CARNES", "CONGELADOS", "LACTINEOS", "VERDURAS" }));
        cp_tipo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cp_tipo.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel2.add(cp_tipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, 181, 50));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/produtos/tipopro.png"))); // NOI18N
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 50, -1, -1));

        bt_enviarProduto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bt_enviarProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/produtos/regis2.png"))); // NOI18N
        bt_enviarProduto.setText("Enviar Produto");
        bt_enviarProduto.setBorder(null);
        bt_enviarProduto.setContentAreaFilled(false);
        bt_enviarProduto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_enviarProduto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_enviarProduto.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/produtos/regis1.png"))); // NOI18N
        bt_enviarProduto.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bt_enviarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_enviarProdutoActionPerformed(evt);
            }
        });
        jPanel2.add(bt_enviarProduto, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 30, -1, -1));

        jPanel5.setLayout(new java.awt.GridLayout(1, 0));

        tabelaProdutos.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        tabelaProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CÓDIGO", "TIPO DE PRODUTO", "NOME DO PRODUTO", "VALOR"
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

        jPanel5.add(jScrollPane1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 671, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 1, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cp_buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cp_buscarKeyReleased

        cp_buscar.setText(cp_buscar.getText().toUpperCase());

        String buscar = cp_buscar.getText();
        String tipo = "";

        if (!cp_tipo.getSelectedItem().equals("TIPO PRODUTO")) {
            tipo = cp_tipo.getSelectedItem().toString();
        }

        preencherTabela(bancoProdutos.listarProdutos(buscar, tipo));

    }//GEN-LAST:event_cp_buscarKeyReleased

    private void bt_enviarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_enviarProdutoActionPerformed

        try {
            if (bancoProdutos.listarProdutos("").next()) {

                String quantidade = null;
                DefaultTableModel tabeladet = (DefaultTableModel) vendas.FrmCaixa.tabelaCaixa.getModel();

                String[] produto = new String[6];

                int linha = tabelaProdutos.getSelectedRow();

                if (linha == -1) {
                    JOptionPane.showMessageDialog(this, "Selecione um produto na tabela.");
                } else {
                    String codigo = tabelaProdutos.getValueAt(linha, 0).toString();
                    String tipo = tabelaProdutos.getValueAt(linha, 1).toString();
                    String nome = tabelaProdutos.getValueAt(linha, 2).toString();
                    String valor = tabelaProdutos.getValueAt(linha, 3).toString();

                    boolean erroJOptionPane = true;
                    String msgJOptionPane = "Quantidade:";
                    int tipoJOptionPane = JOptionPane.INFORMATION_MESSAGE;

                    while (erroJOptionPane) {

                        quantidade = JOptionPane.showInputDialog(this, msgJOptionPane, "Quantidade do Produto", tipoJOptionPane);

                        if (quantidade != null && !quantidade.equals("") && !isNumber(quantidade)) {
                            msgJOptionPane = "Somente valores númericos maiores que 0:";
                            tipoJOptionPane = JOptionPane.ERROR_MESSAGE;
                        } else if (quantidade != null && quantidade.equals("")) {
                            msgJOptionPane = "Digite um valor numérico maior que 0:";
                            tipoJOptionPane = JOptionPane.ERROR_MESSAGE;
                        } else {
                            erroJOptionPane = false;
                        }
                    }

                    if (quantidade != null) {
                        
                        boolean produtoNaoEstaAdiconado = true;
                        
                        for (int i = 0; i < vendas.FrmCaixa.tabelaCaixa.getRowCount(); i++) {
                            
                            String codigoVendas = vendas.FrmCaixa.tabelaCaixa.getValueAt(i, 0).toString();

                            if (codigoVendas.equals(codigo)) {
                                String quantdadeAnterior = vendas.FrmCaixa.tabelaCaixa.getValueAt(i, 4).toString();
                                int quantidadeTotal = Integer.parseInt(quantidade) + Integer.parseInt(quantdadeAnterior);
                                vendas.FrmCaixa.tabelaCaixa.setValueAt(String.valueOf(quantidadeTotal), i, 4);
                                
                                calcularTabelaCaixa();
                                
                                vendas.FrmCaixa.cp_recebido.setText("");
                                vendas.FrmCaixa.cp_troco.setText("");
                                
                                produtoNaoEstaAdiconado = false;
                            }
                        }
                        
                        if (produtoNaoEstaAdiconado) {

                            produto[0] = codigo;
                            produto[1] = tipo;
                            produto[2] = nome;
                            
                            double valorDouble = Double.parseDouble(valor.replace(",", "."));
                            String doubleString = String.format("%.2f", valorDouble);
                                   
                            produto[3] = doubleString;
                            produto[4] = quantidade;

                            tabeladet.addRow(produto);
                            vendas.FrmCaixa.tabelaCaixa.setModel(tabeladet);
                            
                            calcularTabelaCaixa();

                            vendas.FrmCaixa.cp_recebido.setText("");
                            vendas.FrmCaixa.cp_troco.setText("");
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Não existem produtos cadastrados.");
            }
        } catch (Exception e) {
            System.err.println("Erro: " + e);
        }


    }//GEN-LAST:event_bt_enviarProdutoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_enviarProduto;
    private app.bolivia.swing.JCTextField cp_buscar;
    private org.bolivia.combo.SComboBoxBlue cp_tipo;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable tabelaProdutos;
    // End of variables declaration//GEN-END:variables

    BancoProdutos bancoProdutos = new BancoProdutos();

}
