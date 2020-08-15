/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendas;

import javax.swing.JOptionPane;

/**
 *
 * @author Power Arts
 */
public class FrmFormaPag extends javax.swing.JInternalFrame {

    /**
     * Creates new form FrmListaProdutos
     */
    public FrmFormaPag() {
        initComponents();
        formaPagamento = null;
        cp_valorAvista.setText(vendas.FrmCaixa.cp_total.getText());
        //cp_valorAvista.setText("44");   
    }

    private String verificarCampos() {

        if (!cp_avista.isSelected() && !cp_parcelado.isSelected()) {
            return "Por favor, informe se o pagamento será AVISTA ou PARCELADO.";
        } else if (cp_formaPagamentoAvista.getSelectedIndex() == 0 && cp_avista.isSelected()) {
            return "Preencha o campo FORMA DE PAGAMENTO!";
        } else if (cp_formaPagamentoParcelado.getSelectedIndex() == 0 && cp_parcelado.isSelected()) {
            return "Preencha o campo FORMA DE PAGAMENTO!";
        } else if (cp_numeroParcelas.getSelectedIndex() == 0 && cp_parcelado.isSelected()) {
            return "Preencha o campo PARCELAS!";
        } else if (cp_valorAvista.getText().isEmpty() && cp_avista.isSelected()) {
            return "Preencha o campo VALOR!";
        } else if (cp_valorParcelado.getText().isEmpty() && cp_parcelado.isSelected()) {
            return "Preencha o campo VALOR!";
        } else if (cp_entrada.getText().isEmpty() && cp_parcelado.isSelected()) {
            return "Preencha o campo ENTRADA!";
        } else {
            return "";
        }
    }

    private void calcularTabelaCaixa() {
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

        gp_formaPagamento = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        bt_sair = new javax.swing.JButton();
        bt_calcular = new javax.swing.JButton();
        bt_finalizarPagamento = new javax.swing.JButton();
        lb_informacoesPagamento1 = new javax.swing.JLabel();
        lb_informacoesPagamento2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        cp_formaPagamentoAvista = new org.bolivia.combo.SComboBoxBlue();
        cp_valorAvista = new app.bolivia.swing.JCTextField();
        jLabel10 = new javax.swing.JLabel();
        cp_avista = new javax.swing.JRadioButton();
        jPanel5 = new javax.swing.JPanel();
        cp_formaPagamentoParcelado = new org.bolivia.combo.SComboBoxBlue();
        cp_valorParcelado = new app.bolivia.swing.JCTextField();
        jLabel11 = new javax.swing.JLabel();
        cp_numeroParcelas = new org.bolivia.combo.SComboBoxBlue();
        cp_parcelado = new javax.swing.JRadioButton();
        cp_entrada = new app.bolivia.swing.JCTextField();
        jLabel12 = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Pagamento");
        setPreferredSize(new java.awt.Dimension(580, 554));
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "INFORMAÇÕES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel3.setPreferredSize(new java.awt.Dimension(735, 142));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "OPÇÕES"));
        jPanel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jPanel6.setOpaque(false);
        jPanel6.setRequestFocusEnabled(false);

        bt_sair.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bt_sair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/vendas/sair2.png"))); // NOI18N
        bt_sair.setText("Sair");
        bt_sair.setBorder(null);
        bt_sair.setContentAreaFilled(false);
        bt_sair.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_sair.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_sair.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/vendas/sair1.png"))); // NOI18N
        bt_sair.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bt_sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_sairActionPerformed(evt);
            }
        });

        bt_calcular.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bt_calcular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/vendas/calcularpagamento2.png"))); // NOI18N
        bt_calcular.setText("Calcular");
        bt_calcular.setBorder(null);
        bt_calcular.setContentAreaFilled(false);
        bt_calcular.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_calcular.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_calcular.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/vendas/calcularpagamento1.png"))); // NOI18N
        bt_calcular.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bt_calcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_calcularActionPerformed(evt);
            }
        });

        bt_finalizarPagamento.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bt_finalizarPagamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/vendas/finalizarpagamento2.png"))); // NOI18N
        bt_finalizarPagamento.setText("Finalizar Pagamento");
        bt_finalizarPagamento.setBorder(null);
        bt_finalizarPagamento.setContentAreaFilled(false);
        bt_finalizarPagamento.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_finalizarPagamento.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_finalizarPagamento.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/vendas/finalizarpagamento1.png"))); // NOI18N
        bt_finalizarPagamento.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bt_finalizarPagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_finalizarPagamentoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(bt_calcular)
                .addGap(54, 54, 54)
                .addComponent(bt_finalizarPagamento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addComponent(bt_sair)
                .addGap(54, 54, 54))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bt_calcular, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bt_finalizarPagamento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bt_sair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 510, 130));

        lb_informacoesPagamento1.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lb_informacoesPagamento1.setForeground(new java.awt.Color(255, 0, 0));
        lb_informacoesPagamento1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_informacoesPagamento1.setText("Informações do Pagamento");
        lb_informacoesPagamento1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel3.add(lb_informacoesPagamento1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 510, -1));

        lb_informacoesPagamento2.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lb_informacoesPagamento2.setForeground(new java.awt.Color(255, 0, 0));
        lb_informacoesPagamento2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_informacoesPagamento2.setText("Informações do Pagamento");
        lb_informacoesPagamento2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel3.add(lb_informacoesPagamento2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 510, -1));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "A VISTA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel4.setPreferredSize(new java.awt.Dimension(735, 142));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cp_formaPagamentoAvista.setMaximumRowCount(5);
        cp_formaPagamentoAvista.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "FORMA DE PAGAMENTO", "Dinheiro", "Cartão de Crédito", "Cartão de Débito" }));
        cp_formaPagamentoAvista.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cp_formaPagamentoAvista.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel4.add(cp_formaPagamentoAvista, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, 250, 50));

        cp_valorAvista.setEditable(false);
        cp_valorAvista.setBackground(new java.awt.Color(34, 102, 145));
        cp_valorAvista.setBorder(null);
        cp_valorAvista.setForeground(new java.awt.Color(255, 255, 255));
        cp_valorAvista.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cp_valorAvista.setOpaque(false);
        cp_valorAvista.setPhColor(new java.awt.Color(255, 255, 255));
        cp_valorAvista.setPlaceholder("VALOR");
        cp_valorAvista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cp_valorAvistaActionPerformed(evt);
            }
        });
        jPanel4.add(cp_valorAvista, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 120, -1));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/vendas/recibi.png"))); // NOI18N
        jLabel10.setOpaque(true);
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, -1, -1));

        gp_formaPagamento.add(cp_avista);
        cp_avista.setContentAreaFilled(false);
        cp_avista.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cp_avista.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cp_avista.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel4.add(cp_avista, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 31, 50, 50));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "PARCELADO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel5.setPreferredSize(new java.awt.Dimension(735, 142));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cp_formaPagamentoParcelado.setMaximumRowCount(5);
        cp_formaPagamentoParcelado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "FORMA DE PAGAMENTO", "Dinheiro", "Cartão de Crédito", "Cartão de Débito" }));
        cp_formaPagamentoParcelado.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cp_formaPagamentoParcelado.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel5.add(cp_formaPagamentoParcelado, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, 250, 50));

        cp_valorParcelado.setBackground(new java.awt.Color(34, 102, 145));
        cp_valorParcelado.setBorder(null);
        cp_valorParcelado.setForeground(new java.awt.Color(255, 255, 255));
        cp_valorParcelado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cp_valorParcelado.setOpaque(false);
        cp_valorParcelado.setPhColor(new java.awt.Color(255, 255, 255));
        cp_valorParcelado.setPlaceholder("VALOR");
        cp_valorParcelado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cp_valorParceladoActionPerformed(evt);
            }
        });
        jPanel5.add(cp_valorParcelado, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 120, -1));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/vendas/recibi.png"))); // NOI18N
        jLabel11.setOpaque(true);
        jPanel5.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, -1, -1));

        cp_numeroParcelas.setMaximumRowCount(5);
        cp_numeroParcelas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "PARCELAS", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        cp_numeroParcelas.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cp_numeroParcelas.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel5.add(cp_numeroParcelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 100, 250, -1));

        gp_formaPagamento.add(cp_parcelado);
        cp_parcelado.setContentAreaFilled(false);
        cp_parcelado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cp_parcelado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cp_parcelado.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel5.add(cp_parcelado, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 50, 120));

        cp_entrada.setBackground(new java.awt.Color(34, 102, 145));
        cp_entrada.setBorder(null);
        cp_entrada.setForeground(new java.awt.Color(255, 255, 255));
        cp_entrada.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cp_entrada.setOpaque(false);
        cp_entrada.setPhColor(new java.awt.Color(255, 255, 255));
        cp_entrada.setPlaceholder("ENTRADA");
        cp_entrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cp_entradaActionPerformed(evt);
            }
        });
        jPanel5.add(cp_entrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 120, -1));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/vendas/recibi.png"))); // NOI18N
        jLabel12.setOpaque(true);
        jPanel5.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, -1, -1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cp_valorAvistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cp_valorAvistaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cp_valorAvistaActionPerformed

    private void cp_valorParceladoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cp_valorParceladoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cp_valorParceladoActionPerformed

    private void bt_sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_sairActionPerformed

        dispose();

    }//GEN-LAST:event_bt_sairActionPerformed

    private void bt_calcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_calcularActionPerformed

        double total = vendas.FrmCaixa.total;

        if (verificarCampos().isEmpty()) {
            if (cp_avista.isSelected()) {
                numeroParcelas = 0;
                entrada = total;
                formaPagamento = cp_formaPagamentoAvista.getSelectedItem().toString();
                String informacao = "AVISTA - "
                        + "R$: " + total + " | "
                        + formaPagamento;
                lb_informacoesPagamento1.setText(informacao);
                lb_informacoesPagamento2.setText("");
            } else if (cp_parcelado.isSelected()) {
                numeroParcelas = Integer.parseInt(cp_numeroParcelas.getSelectedItem().toString());
                entrada = Double.parseDouble(cp_entrada.getText());
                double valorParcelado = Double.parseDouble(cp_valorParcelado.getText());
                formaPagamento = cp_formaPagamentoParcelado.getSelectedItem().toString();
                double valorParcelas = (total - entrada) / numeroParcelas;
                String informacao1 = "PARCELADO - "
                        + "R$: " + total + " | "
                        + "R$: " + entrada + " + "
                        + numeroParcelas + "X de "
                        + "R$: " + valorParcelas;
                String informacao2 = formaPagamento + " ==> "
                        + "Troco: R$ " + (valorParcelado - entrada);
                lb_informacoesPagamento1.setText(informacao1);
                lb_informacoesPagamento2.setText(informacao2);
            }
        } else {
            JOptionPane.showMessageDialog(null, verificarCampos(), "AVISO", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_bt_calcularActionPerformed

    private void bt_finalizarPagamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_finalizarPagamentoActionPerformed

        if (!lb_informacoesPagamento1.getText().equals("Informações do Pagamento")) {
            if (formaPagamento.equals("Dinheiro") && cp_avista.isSelected()) {
                vendas.FrmCaixa.cp_recebido.setText(String.format("%.2f", entrada));
                vendas.FrmCaixa.cp_recebido.setEditable(true);
                vendas.FrmCaixa.bt_realizarCalculo.setEnabled(true);
                dispose();
            } else {
                vendas.FrmCaixa.cp_recebido.setText("");
                vendas.FrmCaixa.cp_troco.setText("");
                vendas.FrmCaixa.cp_recebido.setEditable(false);
                vendas.FrmCaixa.bt_realizarCalculo.setEnabled(false);
                
                vendas.FrmCaixa.parcelas = this.numeroParcelas;
                vendas.FrmCaixa.formaPagamento = this.formaPagamento;
                vendas.FrmCaixa.entrada = this.entrada;
                
                dispose();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Pressione o botão \"Calcular\" para continuar!", "AVISO", JOptionPane.INFORMATION_MESSAGE);
        }


    }//GEN-LAST:event_bt_finalizarPagamentoActionPerformed

    private void cp_entradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cp_entradaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cp_entradaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_calcular;
    private javax.swing.JButton bt_finalizarPagamento;
    private javax.swing.JButton bt_sair;
    private javax.swing.JRadioButton cp_avista;
    public static app.bolivia.swing.JCTextField cp_entrada;
    private org.bolivia.combo.SComboBoxBlue cp_formaPagamentoAvista;
    private org.bolivia.combo.SComboBoxBlue cp_formaPagamentoParcelado;
    private org.bolivia.combo.SComboBoxBlue cp_numeroParcelas;
    private javax.swing.JRadioButton cp_parcelado;
    public static app.bolivia.swing.JCTextField cp_valorAvista;
    public static app.bolivia.swing.JCTextField cp_valorParcelado;
    private javax.swing.ButtonGroup gp_formaPagamento;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel lb_informacoesPagamento1;
    private javax.swing.JLabel lb_informacoesPagamento2;
    // End of variables declaration//GEN-END:variables

    private double entrada;
    private int numeroParcelas;
    private String formaPagamento;

}
