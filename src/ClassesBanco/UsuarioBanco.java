/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassesBanco;

import ClassesModelo.Usuario;
import com.mysql.jdbc.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class UsuarioBanco {

    public void cadastrarUsuario(Connection con, Usuario usuario) {
        String sql = "insert into funcionario values(?,?,?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement st = (PreparedStatement) con.prepareStatement(sql)) {

            Statement stmt = (Statement) con.createStatement();
            String sql1 = "select max(cd_func) from funcionario";
            ResultSet rs = (ResultSet) stmt.executeQuery(sql1);
            rs.next();
            int proximoCodigo = rs.getInt(1) + 1;
            rs.close();

            st.setInt(1, proximoCodigo);
            st.setString(2, usuario.getNomeCompleto());
            st.setString(3, usuario.getDataNascimento());
            st.setString(4, usuario.getCpf().replace(".", "").replace("-", ""));
            st.setString(5, usuario.getRg());
            st.setString(6, usuario.getTelResidencial());
            st.setString(7, usuario.getTelCelular());
            st.setString(8, usuario.getEmail());
            if (usuario.isProfessor()) {
                usuario.setNivel(1);
            }
            st.setInt(9, usuario.getNivel());
            st.setString(10, usuario.getSenha());
            st.setInt(11, usuario.getAtivo());
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "O usuário foi cadastrada com sucesso!", "Status do Cadastro", 1);
        } catch (Exception e) {
            System.out.println("Algo aconteceu de errado! " + e);
            JOptionPane.showMessageDialog(null, "Não foi possivel realizar o cadastro!", "Status do Cadastro", 0);
        }
    }

    public ArrayList<Usuario> ler(String sql, Connection con) {
        System.err.println(sql);
        ArrayList<Usuario> lista = new ArrayList<>();
        try (PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
                ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                lista.add(new Usuario(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), true, rs.getInt(9), "", rs.getInt(11), rs.getInt(1)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CursoBanco.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public int alterarSenha(Connection con, Usuario usuario) {
        int res = 1;
        String sql = "update funcionario set senha_func = '" + usuario.getSenhaNova() + "' where cd_func = '" + usuario.getCodigo() + "'";
        try (PreparedStatement st = (PreparedStatement) con.prepareStatement(sql)) {
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Senha alterada com sucesso!", "Status da Alteração", 1);
        } catch (Exception e) {
            System.out.println("Algo aconteceu de errado! " + e);
            JOptionPane.showMessageDialog(null, "Erro ao alterar senha!", "Status da Alteração", 0);
            res = 0;
        }
        return res;
    }

    public int resetarSenha(Connection con, Usuario usuario) {
        int res = 1;
        String sql = "update funcionario set senha_func = '123' where cd_func = '" + usuario.getCodigo() + "'";
        try (PreparedStatement st = (PreparedStatement) con.prepareStatement(sql)) {
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Senha padronizada com sucesso!", "Status da Padronização", 1);
        } catch (Exception e) {
            System.out.println("Algo aconteceu de errado! " + e);
            JOptionPane.showMessageDialog(null, "Erro ao padronizar senha!", "Status da Padronização", 0);
            res = 0;
        }
        return res;
    }

    public void alterar(Usuario usuario, Usuario atual, Connection con) {
        String sql = "update funcionario set nm_func = '" + usuario.getNomeCompleto() + "', dt_nas_func = '" + usuario.getDataNascimento() + "', cpf_func = '" + usuario.getCpf().replace(".", "").replace("-", "") + "', rg_func = '" + usuario.getRg() + "', tel_fix_func = '" + usuario.getTelResidencial() + "', tel_cel_func ='" + usuario.getTelCelular() + "', email_func = '" + usuario.getEmail() + "' where cd_func = '" + atual.getCodigo() + "'";
        try (PreparedStatement st = (PreparedStatement) con.prepareStatement(sql)) {
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cadastro alterado com sucesso!", "Status da Alteração", 1);
        } catch (Exception e) {
            System.out.println("Algo aconteceu de errado! " + e);
            JOptionPane.showMessageDialog(null, "Erro ao alterar cadastro!", "Status da Alteração", 0);
        }
    }

    public void various(String sql, Connection con) {
        try (PreparedStatement st = (PreparedStatement) con.prepareStatement(sql)) {
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Status alterado com sucesso!", "Status da Alteração", 1);
        } catch (Exception e) {
            System.out.println("Algo aconteceu de errado! " + e);
            JOptionPane.showMessageDialog(null, "Erro ao mudar status cadastro!", "Status da Alteração", 0);
        }
    }
}
