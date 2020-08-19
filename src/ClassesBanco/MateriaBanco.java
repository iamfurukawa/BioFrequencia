/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassesBanco;

import ClassesModelo.Materia;
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
 * @author Aluno
 */
public class MateriaBanco {

    public void cadastrarMateria(Connection con, Materia materia) {
        String sql = "insert into materia values(?,?,?,?,?,?)";
        try (PreparedStatement st = (PreparedStatement) con.prepareStatement(sql)) {

            Statement stmt = (Statement) con.createStatement();
            String sql1 = "select max(cd_mat) from materia";
            ResultSet rs = (ResultSet) stmt.executeQuery(sql1);
            rs.next();
            int proximoCodigo = rs.getInt(1) + 1;
            rs.close();

            st.setInt(1, proximoCodigo);
            st.setString(2, materia.getNomeMateria());
            st.setString(3, materia.getSigla());
            st.setInt(4, materia.getProfessorPrincipal());
            st.setInt(5, materia.getDivisao());
            st.setInt(6, materia.getTurma());
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "A Matéria foi cadastrada com sucesso!", "Status do Cadastro", 1);
        } catch (Exception e) {
            System.out.println("Algo aconteceu de errado! " + e);
            JOptionPane.showMessageDialog(null, "Não foi possivel realizar o cadastro!", "Status do Cadastro", 0);
        }
    }

    public ArrayList<Materia> ler(String sql, Connection con) {
        System.err.println(sql);
        ArrayList<Materia> lista = new ArrayList<>();
        try (com.mysql.jdbc.PreparedStatement st = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sql);
                ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                lista.add(new Materia(rs.getInt(1), rs.getString(15), rs.getInt(2), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(9), rs.getInt(3)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MateriaBanco.class.getName()).log(Level.SEVERE, null, ex);
        }
        int cont = 0;
        while (lista.size() > cont) {
            try (com.mysql.jdbc.PreparedStatement st = (com.mysql.jdbc.PreparedStatement) con.prepareStatement("select nm_func from funcionario where cd_func = '" + lista.get(cont).getProfessorPrincipal() + "'");
                    ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    lista.get(cont).setProfessorPrincipalS(rs.getString(1));
                }
            } catch (SQLException ex) {
                Logger.getLogger(MateriaBanco.class.getName()).log(Level.SEVERE, null, ex);
            }
            cont++;
        }
        cont = 0;
        while (lista.size() > cont) {
            try (com.mysql.jdbc.PreparedStatement st = (com.mysql.jdbc.PreparedStatement) con.prepareStatement("select nm_func from funcionario where cd_func = '" + lista.get(cont).getDivisao() + "'");
                    ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    lista.get(cont).setDivisaoS(rs.getString(1));
                }
            } catch (SQLException ex) {
                Logger.getLogger(MateriaBanco.class.getName()).log(Level.SEVERE, null, ex);
            }
            cont++;
        }
        return lista;
    }

    public ArrayList<Materia> lerCodigo(Connection con, int turmaAluno) {
        String sql = "select * from materia where cd_turma = cd_turmaAluno";
        ArrayList<Materia> lista = new ArrayList<>();
        try (com.mysql.jdbc.PreparedStatement st = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sql.replace("cd_turmaAluno", turmaAluno + ""));
                ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                lista.add(new Materia(rs.getInt(1)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MateriaBanco.class.getName()).log(Level.SEVERE, null, ex);
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

    public void alterar(Materia materia, int cod, Connection con) {
        String sql = "update materia set nm_mat = '"+materia.getNomeMateria()+"',sigla_mat ='"+materia.getSigla()+"',cd_principal_mat='"+materia.getProfessorPrincipal()+"',cd_divisao_mat = '"+materia.getDivisao()+"',cd_turma ='"+materia.getTurma()+"' where cd_mat = '"+cod+"'";
        System.err.println(sql);
        try (com.mysql.jdbc.PreparedStatement st = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sql)) {
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Materia alterado com sucesso!", "Status da Alteração", 1);
        } catch (Exception e) {
            System.out.println("Algo aconteceu de errado! " + e);
            JOptionPane.showMessageDialog(null, "Erro ao alterar cadastro da Materia!", "Status da Alteração", 0);
        }
    }
}
