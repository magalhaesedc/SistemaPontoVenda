/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import clientes.FrmClientes;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import javax.swing.Timer;
import produtos.FrmProdutos;
import usuarios.FrmUsuarios;
import vendas.FrmCaixa;
import vendas.FrmVendas;

/**
 *
 * @author Power Arts
 */
public class MenuPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form MenuPrincipal
     */
    
    class horas implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            Date sistemaHora = new Date();
            String pmAm = "HH:mm:ss";
            SimpleDateFormat formato = new SimpleDateFormat(pmAm);
            Calendar now = Calendar.getInstance();
            lb_hora.setText(String.format(formato.format(sistemaHora), now));
        }
    }
    
    public MenuPrincipal() {
        initComponents();
        iconeTitulo();
        perguntarSair();
    }

    public boolean estaFechado(Object obj) {
        JInternalFrame[] ativo = instanciarTelas.getAllFrames();
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
    
    public void iconeTitulo() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image imagem = kit.getImage(getClass().getResource("/imagens/principal/logomenor.png"));  
        setIconImage(imagem);
    }
    
    public void perguntarSair() {
        this.setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                if (e.getID() == WindowEvent.WINDOW_CLOSING) {
                    int selectedOption = JOptionPane.showConfirmDialog(null, "Deseja realmente encerrar a aplicação?", "AVISO", YES_NO_OPTION, QUESTION_MESSAGE);
                    if (selectedOption == JOptionPane.YES_OPTION) {
                        System.exit(0);
                    }
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelPrincipal = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lb_hora = new javax.swing.JLabel();
        lb_data = new javax.swing.JLabel();
        lb_usuario = new javax.swing.JLabel();
        lb_desconectar = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        bt_informacoes = new javax.swing.JButton();
        bt_clientes = new javax.swing.JButton();
        bt_produtos = new javax.swing.JButton();
        bt_vendas = new javax.swing.JButton();
        bt_relatorios = new javax.swing.JButton();
        bt_usuarios = new javax.swing.JButton();
        bt_caixa = new javax.swing.JButton();
        instanciarTelas = new principal.InstanciarTelas();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu Principal - Power Sistemas");
        setPreferredSize(new java.awt.Dimension(1100, 720));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        painelPrincipal.setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 51));
        jPanel1.setPreferredSize(new java.awt.Dimension(680, 125));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lb_hora.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lb_hora.setForeground(new java.awt.Color(255, 255, 255));
        lb_hora.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_hora.setText(" HORA");
        jPanel1.add(lb_hora, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 190, -1));

        lb_data.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lb_data.setForeground(new java.awt.Color(255, 255, 255));
        lb_data.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_data.setText("DIA - MÊS - ANO");
        jPanel1.add(lb_data, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 190, -1));

        lb_usuario.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb_usuario.setForeground(new java.awt.Color(255, 255, 255));
        lb_usuario.setText("USUÁRIO");
        jPanel1.add(lb_usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, -1, -1));

        lb_desconectar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb_desconectar.setForeground(new java.awt.Color(255, 255, 255));
        lb_desconectar.setText("DESCONECTAR");
        lb_desconectar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lb_desconectar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_desconectarMouseClicked(evt);
            }
        });
        jPanel1.add(lb_desconectar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, -1, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/principal/conectado.png"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/principal/desconec.png"))); // NOI18N
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        bt_informacoes.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bt_informacoes.setForeground(new java.awt.Color(255, 255, 255));
        bt_informacoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/principal/info.png"))); // NOI18N
        bt_informacoes.setText("INFORMAÇÕES");
        bt_informacoes.setBorder(null);
        bt_informacoes.setContentAreaFilled(false);
        bt_informacoes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_informacoes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_informacoes.setPreferredSize(new java.awt.Dimension(100, 120));
        bt_informacoes.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/principal/info2.png"))); // NOI18N
        bt_informacoes.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bt_informacoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_informacoesActionPerformed(evt);
            }
        });
        jPanel1.add(bt_informacoes, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 0, -1, -1));

        bt_clientes.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bt_clientes.setForeground(new java.awt.Color(255, 255, 255));
        bt_clientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/clientes/clientes.png"))); // NOI18N
        bt_clientes.setText("CLIENTES");
        bt_clientes.setBorder(null);
        bt_clientes.setContentAreaFilled(false);
        bt_clientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_clientes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_clientes.setPreferredSize(new java.awt.Dimension(100, 120));
        bt_clientes.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/clientes/clientes2.png"))); // NOI18N
        bt_clientes.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bt_clientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_clientesActionPerformed(evt);
            }
        });
        jPanel1.add(bt_clientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 0, -1, -1));

        bt_produtos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bt_produtos.setForeground(new java.awt.Color(255, 255, 255));
        bt_produtos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/principal/produtos.png"))); // NOI18N
        bt_produtos.setText("PRODUTOS");
        bt_produtos.setBorder(null);
        bt_produtos.setContentAreaFilled(false);
        bt_produtos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_produtos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_produtos.setPreferredSize(new java.awt.Dimension(100, 120));
        bt_produtos.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/principal/produtos2.png"))); // NOI18N
        bt_produtos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bt_produtos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_produtosActionPerformed(evt);
            }
        });
        jPanel1.add(bt_produtos, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 0, -1, -1));

        bt_vendas.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bt_vendas.setForeground(new java.awt.Color(255, 255, 255));
        bt_vendas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/principal/vendas1.png"))); // NOI18N
        bt_vendas.setText("VENDAS");
        bt_vendas.setBorder(null);
        bt_vendas.setContentAreaFilled(false);
        bt_vendas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_vendas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_vendas.setPreferredSize(new java.awt.Dimension(100, 120));
        bt_vendas.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/principal/vendas2.png"))); // NOI18N
        bt_vendas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bt_vendas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_vendasActionPerformed(evt);
            }
        });
        jPanel1.add(bt_vendas, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 0, -1, -1));

        bt_relatorios.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bt_relatorios.setForeground(new java.awt.Color(255, 255, 255));
        bt_relatorios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/relatorios/relatorio.png"))); // NOI18N
        bt_relatorios.setText("RELATÓRIOS");
        bt_relatorios.setBorder(null);
        bt_relatorios.setContentAreaFilled(false);
        bt_relatorios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_relatorios.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_relatorios.setPreferredSize(new java.awt.Dimension(100, 120));
        bt_relatorios.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/relatorios/relatorio2.png"))); // NOI18N
        bt_relatorios.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bt_relatorios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_relatoriosActionPerformed(evt);
            }
        });
        jPanel1.add(bt_relatorios, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 0, -1, -1));

        bt_usuarios.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bt_usuarios.setForeground(new java.awt.Color(255, 255, 255));
        bt_usuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/principal/usuario1.png"))); // NOI18N
        bt_usuarios.setText("USUÁRIOS");
        bt_usuarios.setBorder(null);
        bt_usuarios.setContentAreaFilled(false);
        bt_usuarios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_usuarios.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_usuarios.setPreferredSize(new java.awt.Dimension(100, 120));
        bt_usuarios.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/principal/usuario2.png"))); // NOI18N
        bt_usuarios.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bt_usuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_usuariosActionPerformed(evt);
            }
        });
        jPanel1.add(bt_usuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, -1, -1));

        bt_caixa.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bt_caixa.setForeground(new java.awt.Color(255, 255, 255));
        bt_caixa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/principal/caixa.png"))); // NOI18N
        bt_caixa.setText("CAIXA");
        bt_caixa.setBorder(null);
        bt_caixa.setContentAreaFilled(false);
        bt_caixa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_caixa.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_caixa.setPreferredSize(new java.awt.Dimension(100, 120));
        bt_caixa.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/principal/caixa2.png"))); // NOI18N
        bt_caixa.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bt_caixa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_caixaActionPerformed(evt);
            }
        });
        jPanel1.add(bt_caixa, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 0, -1, -1));

        painelPrincipal.add(jPanel1, java.awt.BorderLayout.PAGE_START);
        painelPrincipal.add(instanciarTelas, java.awt.BorderLayout.CENTER);

        getContentPane().add(painelPrincipal);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bt_informacoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_informacoesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_informacoesActionPerformed

    private void bt_produtosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_produtosActionPerformed
        // TODO add your handling code here:

        if (estaFechado(formProduto)) {
            formProduto = new FrmProdutos();
            instanciarTelas.add(formProduto).setLocation(160, 20);
            formProduto.show();
        } else {
            JOptionPane.showMessageDialog(this, "A Janela já está aberto!!");
            formProduto.toFront();
            formProduto.show();
        }
    }//GEN-LAST:event_bt_produtosActionPerformed

    private void bt_vendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_vendasActionPerformed
        // TODO add your handling code here:
        
        if (estaFechado(formVendas)) {
            formVendas = new FrmVendas();
            instanciarTelas.add(formVendas).setLocation(150, 20);
            formVendas.show();
        } else {
            JOptionPane.showMessageDialog(this, "A Janela já está aberto!!");
            formVendas.toFront();
            formVendas.show();
        }
    }//GEN-LAST:event_bt_vendasActionPerformed

    private void bt_relatoriosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_relatoriosActionPerformed
     
    }//GEN-LAST:event_bt_relatoriosActionPerformed

    private void bt_clientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_clientesActionPerformed
        
        if (estaFechado(formCliente)) {
            formCliente = new FrmClientes();
            instanciarTelas.add(formCliente).setLocation(160, 20);
            formCliente.show();
        } else {
            JOptionPane.showMessageDialog(this, "A Janela já está aberto!!");
            formCliente.toFront();
            formCliente.show();
        }
    }//GEN-LAST:event_bt_clientesActionPerformed

    private void lb_desconectarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_desconectarMouseClicked
        dispose();
        JOptionPane.showMessageDialog(this, "Seção Encerrada!");
        new Login().setVisible(true);
    }//GEN-LAST:event_lb_desconectarMouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        dispose();
        JOptionPane.showMessageDialog(this, "Seção Encerrada!");
        new Login().setVisible(true);
    }//GEN-LAST:event_jLabel6MouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        Date sistemaData = new Date();
        SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
        lb_data.setText(dataFormatada.format(sistemaData));
        
        //HORA DO SISTEMA
        Timer hr = new Timer (100, new MenuPrincipal.horas());
        hr.start();
    }//GEN-LAST:event_formWindowOpened

    private void bt_usuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_usuariosActionPerformed
        // TODO add your handling code here:
        if (estaFechado(formUsuarios)) {
            formUsuarios = new FrmUsuarios();
            instanciarTelas.add(formUsuarios).setLocation(160, 20);
            formUsuarios.show();
        } else {
            JOptionPane.showMessageDialog(this, "A Janela já está aberto!!");
            formUsuarios.toFront();
            formUsuarios.show();
        }
    }//GEN-LAST:event_bt_usuariosActionPerformed

    private void bt_caixaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_caixaActionPerformed
        // TODO add your handling code here:
        if (estaFechado(formCaixa)) {
            formCaixa = new FrmCaixa();
            instanciarTelas.add(formCaixa).setLocation(160, 20);
            formCaixa.show();
        } else {
            JOptionPane.showMessageDialog(this, "A Janela já está aberto!!");
            formCaixa.toFront();
            formCaixa.show();
        }
    }//GEN-LAST:event_bt_caixaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_caixa;
    private javax.swing.JButton bt_clientes;
    private javax.swing.JButton bt_informacoes;
    private javax.swing.JButton bt_produtos;
    private javax.swing.JButton bt_relatorios;
    private javax.swing.JButton bt_usuarios;
    private javax.swing.JButton bt_vendas;
    public static principal.InstanciarTelas instanciarTelas;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lb_data;
    private javax.swing.JLabel lb_desconectar;
    private javax.swing.JLabel lb_hora;
    public javax.swing.JLabel lb_usuario;
    private javax.swing.JPanel painelPrincipal;
    // End of variables declaration//GEN-END:variables

    FrmProdutos formProduto;
    FrmUsuarios formUsuarios;
    FrmVendas formVendas;
    FrmCaixa formCaixa;
    FrmClientes formCliente;

}
