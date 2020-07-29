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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import static principal.MenuPrincipal.instanciarTelas;
import produtos.FrmListaProdutos;

/**
 *
 * @author Power Arts
 */
public class FrmCaixa extends javax.swing.JInternalFrame {

    private String codigo = null;
    private int parcelas = -1;
    private double entrada = -1;

    /**
     * Creates new form FrmCaixa
     */
    public FrmCaixa() {
        initComponents();
        limparCampos();
        preencherCaixaSelecao(bancoClientes.listarClientes(""));
        setarCodigo();
        FrmCaixa.tabelaCaixa.getTableHeader().setDefaultRenderer(new EstiloTabelaHeader());
        FrmCaixa.tabelaCaixa.setDefaultRenderer(Object.class, new EstiloTabelaRenderer());
        FrmCaixa.tabelaCaixa.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public void setarData() {
        cp_data.setText(controleMetodos.converteDataString(new Date()));
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

    void limparCampos() {
        DefaultTableModel modelo = (DefaultTableModel) tabelaCaixa.getModel();

        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
        cp_recebido.setText("");
        cp_troco.setText("");
        cp_total.setText("0,00");
        cp_clientes.setSelectedIndex(0);
        setarData();
        parcelas = -1;
        entrada = -1;

        if (bancoVendas.verificarExisteRegistro()) {
            setarCodigoVenda(bancoVendas.buscarMaiorNumeroVenda());
        } else {
            cp_numeroVenda.setText("00000000000001");
        }

    }

    private void preencherCaixaSelecao(ResultSet clientes) {

        cp_clientes.removeAllItems();
        cp_clientes.addItem("SELECIONE UM CLIENTE");

        try {
            while (clientes.next()) {
                clientes.getString("codigo_cl");
                cp_clientes.addItem(clientes.getString("nome_cl") + " - " + clientes.getString("codigo_cl"));
            }
        } catch (SQLException ex) {

        }

    }

    private void setarCodigo() {
        //Capturar o código do cliente da caixa de seleção
        cp_clientes.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent ie) {
                if (cp_clientes.getSelectedItem().toString().contains("- CLI")) {
                    String campo = cp_clientes.getSelectedItem().toString();
                    codigo = campo.substring((campo.length() - 1) - 6, campo.length());
                } else {
                    codigo = null;
                }
            }
        });
    }

    private void setarCodigoVenda(ResultSet rs) {

        int MAIOR_NUMERO_VENDA = 1;

        try {
            if (!rs.next()) {
                cp_numeroVenda.setText("00000000000001");
            } else {
                String numero_ven = rs.getString(MAIOR_NUMERO_VENDA);
                long codigoInteiro = Long.parseLong(numero_ven);
                String proximoCodigo = controleMetodos.gerarNumeroVenda(codigoInteiro);
                cp_numeroVenda.setText(proximoCodigo);
            }
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
        }
    }

    private double realizarCalculoTroco() {

        double valor = -1;

        if (tabelaCaixa.getRowCount() < 1) {
            JOptionPane.showMessageDialog(this, "Nenhum produto foi adicionado", "Erro", JOptionPane.ERROR_MESSAGE);
        } else if (cp_recebido.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Insira o valor recebido.");
        } else {
            double recebido = Double.parseDouble(cp_recebido.getText().replace(",", "."));
            double total = Double.parseDouble(cp_total.getText().replace(",", "."));

            if (recebido < total) {
                JOptionPane.showMessageDialog(this, "Valor recebido não pode ser menor que o valor total");
            } else {
                valor = recebido - total;
                parcelas = 1;
                entrada = valor;
                this.cp_troco.setText(String.format("%.2f", valor));
            }
        }
        return valor;
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
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        cp_data = new app.bolivia.swing.JCTextField();
        jLabel5 = new javax.swing.JLabel();
        cp_recebido = new app.bolivia.swing.JCTextField();
        jLabel6 = new javax.swing.JLabel();
        cp_troco = new app.bolivia.swing.JCTextField();
        jLabel7 = new javax.swing.JLabel();
        bt_produtos = new javax.swing.JButton();
        cp_clientes = new org.bolivia.combo.SComboBoxBlue();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        cp_numeroVenda = new app.bolivia.swing.JCTextField();
        jLabel9 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        bt_realizarCalculo = new javax.swing.JButton();
        bt_realizarVenda = new javax.swing.JButton();
        bt_eliminar = new javax.swing.JButton();
        bt_cancelar = new javax.swing.JButton();
        pagamento = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaCaixa = new javax.swing.JTable();
        cp_total = new app.bolivia.swing.JCTextField();
        jLabel10 = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Caixa");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel1.setText("Power Sistemas - Ponto de Venda");

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/principal/logomenor.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel11)
                .addGap(46, 46, 46)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addGap(65, 65, 65))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cp_data.setEditable(false);
        cp_data.setBackground(new java.awt.Color(34, 102, 145));
        cp_data.setBorder(null);
        cp_data.setForeground(new java.awt.Color(255, 255, 255));
        cp_data.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cp_data.setOpaque(false);
        cp_data.setPhColor(new java.awt.Color(255, 255, 255));
        cp_data.setPlaceholder("DATA");
        jPanel3.add(cp_data, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 60, 170, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/vendas/data.png"))); // NOI18N
        jLabel5.setOpaque(true);
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, -1, -1));

        cp_recebido.setBackground(new java.awt.Color(34, 102, 145));
        cp_recebido.setBorder(null);
        cp_recebido.setForeground(new java.awt.Color(255, 255, 255));
        cp_recebido.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cp_recebido.setOpaque(false);
        cp_recebido.setPhColor(new java.awt.Color(255, 255, 255));
        cp_recebido.setPlaceholder("RECEBIDO");
        cp_recebido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cp_recebidoActionPerformed(evt);
            }
        });
        jPanel3.add(cp_recebido, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 60, 120, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/vendas/recibi.png"))); // NOI18N
        jLabel6.setOpaque(true);
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 50, -1, -1));

        cp_troco.setEditable(false);
        cp_troco.setBackground(new java.awt.Color(34, 102, 145));
        cp_troco.setBorder(null);
        cp_troco.setForeground(new java.awt.Color(255, 255, 255));
        cp_troco.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cp_troco.setOpaque(false);
        cp_troco.setPhColor(new java.awt.Color(255, 255, 255));
        cp_troco.setPlaceholder("TROCO");
        cp_troco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cp_trocoActionPerformed(evt);
            }
        });
        jPanel3.add(cp_troco, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 60, 120, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/vendas/valores.png"))); // NOI18N
        jLabel7.setOpaque(true);
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 50, -1, -1));

        bt_produtos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bt_produtos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/vendas/produto1.png"))); // NOI18N
        bt_produtos.setBorder(null);
        bt_produtos.setContentAreaFilled(false);
        bt_produtos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_produtos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_produtos.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/vendas/produto.png"))); // NOI18N
        bt_produtos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bt_produtos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_produtosActionPerformed(evt);
            }
        });
        jPanel3.add(bt_produtos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        cp_clientes.setMaximumRowCount(5);
        cp_clientes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECIONE UM CLIENTE" }));
        cp_clientes.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cp_clientes.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel3.add(cp_clientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 10, 370, 29));

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("NÚMERO DE VENDA:");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, -1));

        cp_numeroVenda.setEditable(false);
        cp_numeroVenda.setBackground(new java.awt.Color(34, 102, 145));
        cp_numeroVenda.setBorder(null);
        cp_numeroVenda.setForeground(new java.awt.Color(255, 255, 255));
        cp_numeroVenda.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cp_numeroVenda.setOpaque(false);
        cp_numeroVenda.setPhColor(new java.awt.Color(255, 255, 255));
        cp_numeroVenda.setPlaceholder("FATURA");
        cp_numeroVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cp_numeroVendaActionPerformed(evt);
            }
        });
        jPanel4.add(cp_numeroVenda, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 120, 20));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/vendas/numFac.png"))); // NOI18N
        jLabel9.setOpaque(true);
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "OPÇÕES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        bt_realizarCalculo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bt_realizarCalculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/vendas/calculo2.png"))); // NOI18N
        bt_realizarCalculo.setBorder(null);
        bt_realizarCalculo.setContentAreaFilled(false);
        bt_realizarCalculo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_realizarCalculo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_realizarCalculo.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/vendas/calculo1.png"))); // NOI18N
        bt_realizarCalculo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bt_realizarCalculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_realizarCalculoActionPerformed(evt);
            }
        });

        bt_realizarVenda.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bt_realizarVenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/vendas/venda2.png"))); // NOI18N
        bt_realizarVenda.setBorder(null);
        bt_realizarVenda.setContentAreaFilled(false);
        bt_realizarVenda.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_realizarVenda.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_realizarVenda.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/vendas/venda1.png"))); // NOI18N
        bt_realizarVenda.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bt_realizarVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_realizarVendaActionPerformed(evt);
            }
        });

        bt_eliminar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bt_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/vendas/elimina2.png"))); // NOI18N
        bt_eliminar.setBorder(null);
        bt_eliminar.setContentAreaFilled(false);
        bt_eliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_eliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_eliminar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/vendas/elimina1.png"))); // NOI18N
        bt_eliminar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bt_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_eliminarActionPerformed(evt);
            }
        });

        bt_cancelar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bt_cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/vendas/cancela2.png"))); // NOI18N
        bt_cancelar.setBorder(null);
        bt_cancelar.setContentAreaFilled(false);
        bt_cancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_cancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_cancelar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/vendas/cancela1.png"))); // NOI18N
        bt_cancelar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bt_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_cancelarActionPerformed(evt);
            }
        });

        pagamento.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        pagamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/vendas/cancela2.png"))); // NOI18N
        pagamento.setBorder(null);
        pagamento.setContentAreaFilled(false);
        pagamento.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pagamento.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pagamento.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/vendas/cancela1.png"))); // NOI18N
        pagamento.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        pagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pagamentoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bt_realizarCalculo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bt_realizarVenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bt_eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bt_cancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pagamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(bt_realizarCalculo)
                .addGap(18, 18, 18)
                .addComponent(bt_realizarVenda)
                .addGap(18, 18, 18)
                .addComponent(bt_eliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bt_cancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pagamento)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabelaCaixa.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        tabelaCaixa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CÓDIGO", "TIPO PRODUTO", "DESCRIÇÃO", "VALOR", "QUANTIDADE", "TOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabelaCaixa);

        jPanel6.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 780, 170));

        cp_total.setEditable(false);
        cp_total.setBackground(new java.awt.Color(34, 102, 145));
        cp_total.setBorder(null);
        cp_total.setForeground(new java.awt.Color(255, 255, 255));
        cp_total.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cp_total.setOpaque(false);
        cp_total.setPhColor(new java.awt.Color(255, 255, 255));
        cp_total.setPlaceholder("TOTAL");
        cp_total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cp_totalActionPerformed(evt);
            }
        });
        jPanel6.add(cp_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 200, 80, 20));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/vendas/total.png"))); // NOI18N
        jLabel10.setOpaque(true);
        jPanel6.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 190, -1, -1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 804, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void cp_recebidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cp_recebidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cp_recebidoActionPerformed

    private void cp_trocoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cp_trocoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cp_trocoActionPerformed

    private void cp_numeroVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cp_numeroVendaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cp_numeroVendaActionPerformed

    private void cp_totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cp_totalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cp_totalActionPerformed

    private void bt_produtosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_produtosActionPerformed
        if (estaFechado(formListarProdutos)) {
            formListarProdutos = new FrmListaProdutos();
            instanciarTelas.add(formListarProdutos).setLocation(180, 20);
            formListarProdutos.show();
        } else {
            JOptionPane.showMessageDialog(this, "A janela já está aberto!!");
            formListarProdutos.toFront();
            formListarProdutos.show();
        }
    }//GEN-LAST:event_bt_produtosActionPerformed

    private void bt_realizarCalculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_realizarCalculoActionPerformed
        realizarCalculoTroco();
    }//GEN-LAST:event_bt_realizarCalculoActionPerformed

    private void bt_realizarVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_realizarVendaActionPerformed

        if (tabelaCaixa.getRowCount() < 1) {
            JOptionPane.showMessageDialog(this, "Nenhum produto foi adicionado.", "Erro", JOptionPane.ERROR_MESSAGE);
        } else if (codigo == null) {
            JOptionPane.showMessageDialog(this, "Selecione um cliente.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            if (cp_troco.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Para continuar, realize o calculo do troco", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                Venda v = new Venda();
                v.setNumero_ven(cp_numeroVenda.getText());
                v.setTotal_ven(cp_total.getText());
                v.setData_ven(cp_data.getText());
                String msg = bancoVendas.registrarVenda(v);
                if (msg.equals("Registrado com sucesso!")) {
                    limparCampos();
                    JOptionPane.showMessageDialog(this, "Venda Efetuada.");
                }
            }
        }

    }//GEN-LAST:event_bt_realizarVendaActionPerformed

    private void bt_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_eliminarActionPerformed
        if (tabelaCaixa.getRowCount() > 0) {
            DefaultTableModel modelo = (DefaultTableModel) tabelaCaixa.getModel();
            int linha = tabelaCaixa.getSelectedRow();
            if (linha >= 0) {
                modelo.removeRow(linha);
                FrmListaProdutos lp = new FrmListaProdutos();
                lp.calcularTabelaCaixa();
            } else {
                JOptionPane.showMessageDialog(this, "Nenhum produto foi adicionado.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Não há registro para excluir.");
        }
    }//GEN-LAST:event_bt_eliminarActionPerformed

    private void bt_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_cancelarActionPerformed

        limparCampos();

    }//GEN-LAST:event_bt_cancelarActionPerformed

    private void pagamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pagamentoActionPerformed
        if (estaFechado(formFormaPag)) {
            formFormaPag = new FrmFormaPag();
            instanciarTelas.add(formFormaPag).setLocation(180, 20);
            formFormaPag.show();
        } else {
            JOptionPane.showMessageDialog(this, "A janela já está aberto!!");
            formFormaPag.toFront();
            formFormaPag.show();
        }
    }//GEN-LAST:event_pagamentoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_cancelar;
    private javax.swing.JButton bt_eliminar;
    private javax.swing.JButton bt_produtos;
    private javax.swing.JButton bt_realizarCalculo;
    private javax.swing.JButton bt_realizarVenda;
    private org.bolivia.combo.SComboBoxBlue cp_clientes;
    private app.bolivia.swing.JCTextField cp_data;
    private app.bolivia.swing.JCTextField cp_numeroVenda;
    public static app.bolivia.swing.JCTextField cp_recebido;
    public static app.bolivia.swing.JCTextField cp_total;
    public static app.bolivia.swing.JCTextField cp_troco;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton pagamento;
    public static javax.swing.JTable tabelaCaixa;
    // End of variables declaration//GEN-END:variables

    FrmListaProdutos formListarProdutos;
    FrmFormaPag formFormaPag;
    ControleMetodos controleMetodos = new ControleMetodos();
    BancoVendas bancoVendas = new BancoVendas();
    BancoClientes bancoClientes = new BancoClientes();
}
