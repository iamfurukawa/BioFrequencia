/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Telas;

import ClassesCPF.ValidarCPF;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import ClassesBanco.*;
import ClassesModelo.*;
import com.mysql.jdbc.Connection;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pindorama
 */
public class Login extends javax.swing.JFrame {

    private ValidarCPF vcpf = new ValidarCPF();
    private Usuario usuario = new Usuario();
    private ConexaoBanco conexaoBanco = new ConexaoBanco();
    private ControleAcessoBanco controleAcessoBanco = new ControleAcessoBanco();
    private Connection con;

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
        try {
            con = conexaoBanco.conexao();
        } catch (Exception ex) {
            ex.printStackTrace();
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

        pControleAcesso = new javax.swing.JPanel();
        lBioFrequencia = new javax.swing.JLabel();
        lAcesso = new javax.swing.JLabel();
        lImagem = new javax.swing.JLabel();
        lSenha = new javax.swing.JLabel();
        lLogin = new javax.swing.JLabel();
        tSenha = new javax.swing.JPasswordField();
        tLogin = new javax.swing.JTextField();
        pRodape = new java.awt.Panel();
        btn_Logar = new javax.swing.JButton();
        btn_Sair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Controle de Acesso");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setUndecorated(true);
        setResizable(false);

        pControleAcesso.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pControleAcesso.setPreferredSize(new java.awt.Dimension(349, 188));

        lBioFrequencia.setFont(new java.awt.Font("Kartika", 1, 24)); // NOI18N
        lBioFrequencia.setForeground(new java.awt.Color(204, 0, 0));
        lBioFrequencia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lBioFrequencia.setText("BioFrequência");
        lBioFrequencia.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lAcesso.setForeground(new java.awt.Color(204, 0, 0));
        lAcesso.setText(" ");

        lImagem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lImagem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/NewIcons/usuario_sem_imagem_adicionada.jpg"))); // NOI18N
        lImagem.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lSenha.setText("Senha:");

        lLogin.setText("Login:");

        tSenha.setText("root");
        tSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tSenhaKeyPressed(evt);
            }
        });

        tLogin.setText("53616546206");
        tLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tLoginKeyPressed(evt);
            }
        });

        pRodape.setBackground(new java.awt.Color(63, 138, 217));

        btn_Logar.setText("Logar");
        btn_Logar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LogarActionPerformed(evt);
            }
        });

        btn_Sair.setText("Cancelar");
        btn_Sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pRodapeLayout = new javax.swing.GroupLayout(pRodape);
        pRodape.setLayout(pRodapeLayout);
        pRodapeLayout.setHorizontalGroup(
            pRodapeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pRodapeLayout.createSequentialGroup()
                .addContainerGap(167, Short.MAX_VALUE)
                .addComponent(btn_Logar, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_Sair)
                .addContainerGap())
        );

        pRodapeLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btn_Logar, btn_Sair});

        pRodapeLayout.setVerticalGroup(
            pRodapeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pRodapeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pRodapeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Logar, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(btn_Sair))
                .addContainerGap())
        );

        javax.swing.GroupLayout pControleAcessoLayout = new javax.swing.GroupLayout(pControleAcesso);
        pControleAcesso.setLayout(pControleAcessoLayout);
        pControleAcessoLayout.setHorizontalGroup(
            pControleAcessoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pRodape, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pControleAcessoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lImagem, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                .addGroup(pControleAcessoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pControleAcessoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lBioFrequencia, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pControleAcessoLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(pControleAcessoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pControleAcessoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pControleAcessoLayout.createSequentialGroup()
                                    .addComponent(lSenha)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(tSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(lAcesso, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pControleAcessoLayout.createSequentialGroup()
                                .addComponent(lLogin)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );

        pControleAcessoLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {tLogin, tSenha});

        pControleAcessoLayout.setVerticalGroup(
            pControleAcessoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pControleAcessoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pControleAcessoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pControleAcessoLayout.createSequentialGroup()
                        .addComponent(lBioFrequencia, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pControleAcessoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pControleAcessoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lAcesso))
                    .addComponent(lImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pRodape, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pControleAcessoLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {tLogin, tSenha});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pControleAcesso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pControleAcesso, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setSize(new java.awt.Dimension(349, 196));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_LogarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LogarActionPerformed
        logar();
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_LogarActionPerformed

    private void btn_SairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SairActionPerformed
        try {
            con.close();
            System.out.println("Encerrando Conexão Com o Banco");
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.exit(0);
    }//GEN-LAST:event_btn_SairActionPerformed

    private void tSenhaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tSenhaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            logar();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_tSenhaKeyPressed

private void tLoginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tLoginKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        logar();
    }
    // TODO add your handling code here:
}//GEN-LAST:event_tLoginKeyPressed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Logar;
    private javax.swing.JButton btn_Sair;
    private javax.swing.JLabel lAcesso;
    private javax.swing.JLabel lBioFrequencia;
    private javax.swing.JLabel lImagem;
    private javax.swing.JLabel lLogin;
    private javax.swing.JLabel lSenha;
    private javax.swing.JPanel pControleAcesso;
    private java.awt.Panel pRodape;
    private javax.swing.JTextField tLogin;
    private javax.swing.JPasswordField tSenha;
    // End of variables declaration//GEN-END:variables

    public Login(GraphicsConfiguration gc) {
        super(gc);
    }

    public Login(String title) throws HeadlessException {
        super(title);
    }

    @Override
    public void setUndecorated(boolean undecorated) {
        super.setUndecorated(undecorated);
    }

    private void logar() {
        //verificando o cpf
        try {//pega conexão
            if (tLogin.getText().equals("")) {
                lAcesso.setText("Preencha os Campos");
            } else if (vcpf.CPF(tLogin.getText()) == true) {
                System.out.println("Passou pelo teste do CPF");
                usuario.setCpf(tLogin.getText());//seta o cpf na classe do user
                usuario = controleAcessoBanco.ler(con, usuario);//manda pesqusiar no banco se há um registro com o cpf
                String senha = new String(tSenha.getPassword());//converte a senha pra string
                if (usuario.getSenha().equals(senha)) {//compara se a senha que o usuario digitou é igual á da classe
                    try {
                        con.close();
                        System.out.println("Encerrando Conexão Com o Banco");
                    } catch (SQLException ex) {
                        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    lAcesso.setText("Acesso Liberado");//seta texto
                    Principal pf = new Principal(usuario);//chama a prox tela
                    pf.setVisible(true);

                    this.dispose();
                } else {//caso a senha não for igual a da classe usuario ele faz isso
                    tLogin.setText("");
                    tSenha.setText("");
                    lAcesso.setText("Acesso Negado");
                    tLogin.requestFocus();
                }
            } else {//o cpf que o usuario digitou é inválido
                tLogin.setText("");
                tSenha.setText("");
                lAcesso.setText("Acesso Negado");
                tLogin.requestFocus();
                System.out.println("Não passou pelo teste do CPF");
            }
        } catch (Exception e) {
            System.out.println(e);
            tLogin.setText("");
            tSenha.setText("");
            lAcesso.setText("Acesso Negado");
            tLogin.requestFocus();
        }
    }
}
