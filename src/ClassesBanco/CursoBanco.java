/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassesBanco;

import ClassesModelo.*;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
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
public class CursoBanco {

    public void cadastrarCurso(Connection con, Curso curso) {
        String sql = "insert into curso values(?,?,?,?)";
        try (PreparedStatement st = (PreparedStatement) con.prepareStatement(sql)) {

            Statement stmt = (Statement) con.createStatement();
            String sql1 = "select max(cd_curso) from curso";
            ResultSet rs = (ResultSet) stmt.executeQuery(sql1);
            rs.next();
            int proximoCodigo = rs.getInt(1) + 1;
            rs.close();

            st.setInt(1, proximoCodigo);
            st.setString(2, curso.getNomeCurso());
            st.setInt(3, curso.getQuantidadeModulos());
            st.setInt(4, curso.getAtivo());

            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "O curso foi cadastrado com sucesso!", "Status do Cadastro", 1);
        } catch (Exception e) {
            System.out.println("Algo aconteceu de errado! " + e);
            JOptionPane.showMessageDialog(null, "Não foi possivel realizar o cadastro!", "Status do Cadastro", 0);
        }
    }

    public ArrayList<Curso> ler(String sql, Connection con) {
        System.err.println(sql);
        ArrayList<Curso> lista = new ArrayList<>();
        try (PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
                ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                lista.add(new Curso(rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(1)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CursoBanco.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public ArrayList<Curso> lerCursoAluno(String sql, Connection con) {
        System.err.println(sql);
        ArrayList<Curso> lista = new ArrayList<>();
        try (PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
                ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                lista.add(new Curso(rs.getString(18), rs.getInt(1)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CursoBanco.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;

    }

    public void various(String sql, Connection con) {
        try (com.mysql.jdbc.PreparedStatement st = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sql)) {
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Status alterado com sucesso!", "Status da Alteração", 1);
        } catch (Exception e) {
            System.out.println("Algo aconteceu de errado! " + e);
            JOptionPane.showMessageDialog(null, "Erro ao mudar status cadastro!", "Status da Alteração", 0);
        }
    }

    public void alterar(Curso curso, Connection con) {
        String sql = "update curso set nm_curso = '" + curso.getNomeCurso() + "', mod_curso = '" + curso.getQuantidadeModulos() +"' where cd_curso = '" + curso.getAtivo()+ "'";
        System.err.println(sql);
        try (PreparedStatement st = (PreparedStatement) con.prepareStatement(sql)) {
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Curso alterado com sucesso!", "Status da Alteração", 1);
        } catch (Exception e) {
            System.out.println("Algo aconteceu de errado! " + e);
            JOptionPane.showMessageDialog(null, "Erro ao alterar Curso!", "Status da Alteração", 0);
        }
    }
}
