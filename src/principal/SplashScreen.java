/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import bancodedados.Conectar;
import bancodedados.ConfiguracoesBanco;
import controle.ControleMetodos;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;

/**
 *
 * @author Power Arts
 */
public class SplashScreen extends javax.swing.JFrame {

    /**
     * Creates new form SplashScreen
     */
    public SplashScreen() {
        initComponents();
        carregarBarra();
        iconeTitulo();
    }

    void carregarBarra() {
        new Thread() {
            public void run() {
                for (int n = 0; n < 100; n++) {
                    try {
                        sleep(30);
                        barraProgresso.setValue(n);
                        if (n == 50) {
                            ConfiguracoesBanco configuracoesBanco = controleMetodos.lerXML();
                            Connection conn = Conectar.testConexao(configuracoesBanco.getUsuario(), configuracoesBanco.getSenha(), configuracoesBanco.getBanco(), configuracoesBanco.getServidor());
                            Conectar.closeConexao(conn);
                            System.out.println("Conectado com Sucesso");
                            abrirLogin();
                        }
                    } catch (Exception e) {
                        new ConfigurarBanco().setVisible(true);
                        dispose();
                        System.out.println("Erro conexão");
                        break;
                    }
                    lb_carregando.setText("Abrindo o Sistema...");
                }
            }
        }.start();
    }

    public void abrirLogin() {
        new Login().setVisible(true);
        dispose();
    }

    public void iconeTitulo() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image imagem = kit.getImage(getClass().getResource("/imagens/principal/logomenor.png"));
        setIconImage(imagem);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelGeral = new javax.swing.JPanel();
        painelProgresso1 = new javax.swing.JPanel();
        bt_fechar = new javax.swing.JButton();
        painelProgresso = new javax.swing.JPanel();
        barraProgresso = new javax.swing.JProgressBar();
        lb_carregando = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Power Sistemas - Ponto de Venda");
        setLocationByPlatform(true);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(601, 389));
        setResizable(false);

        painelGeral.setPreferredSize(new java.awt.Dimension(603, 391));
        painelGeral.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        painelProgresso1.setOpaque(false);
        painelProgresso1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bt_fechar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/principal/fechar.png"))); // NOI18N
        bt_fechar.setBorderPainted(false);
        bt_fechar.setContentAreaFilled(false);
        bt_fechar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/principal/fechar2.png"))); // NOI18N
        bt_fechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_fecharActionPerformed(evt);
            }
        });
        painelProgresso1.add(bt_fechar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 40));

        painelGeral.add(painelProgresso1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 0, 50, 40));

        painelProgresso.setOpaque(false);
        painelProgresso.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        painelProgresso.add(barraProgresso, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 300, 20));

        lb_carregando.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lb_carregando.setForeground(new java.awt.Color(255, 255, 255));
        lb_carregando.setText(" Carregando Sistema ...");
        painelProgresso.add(lb_carregando, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        painelGeral.add(painelProgresso, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 330, 60));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/principal/splash.png"))); // NOI18N
        painelGeral.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelGeral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelGeral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bt_fecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_fecharActionPerformed
        System.exit(0);
    }//GEN-LAST:event_bt_fecharActionPerformed

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
            java.util.logging.Logger.getLogger(SplashScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SplashScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SplashScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SplashScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SplashScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar barraProgresso;
    private javax.swing.JButton bt_fechar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lb_carregando;
    private javax.swing.JPanel painelGeral;
    private javax.swing.JPanel painelProgresso;
    private javax.swing.JPanel painelProgresso1;
    // End of variables declaration//GEN-END:variables

    ControleMetodos controleMetodos = new ControleMetodos();
    
}