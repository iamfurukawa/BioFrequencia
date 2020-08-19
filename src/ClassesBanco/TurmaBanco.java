/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassesBanco;

import ClassesModelo.*;
import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class TurmaBanco {

    public void cadastrarTurma(Connection con, Turma turma) {
        String sql = "insert into turma values(?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement st = (PreparedStatement) con.prepareStatement(sql)) {

            Statement stmt = (Statement) con.createStatement();
            String sql1 = "select max(cd_turma) from turma";
            ResultSet rs = (ResultSet) stmt.executeQuery(sql1);
            rs.next();
            int proximoCodigo = rs.getInt(1) + 1;
            rs.close();

            st.setInt(1, proximoCodigo);
            st.setString(2, turma.getSigla());
            st.setInt(3, turma.getModulo() + 1);
            st.setString(4, turma.getSala());
            st.setString(5, turma.getAno());
            st.setString(6, turma.getSemestre());
            st.setString(7, turma.getPeriodo());
            st.setInt(8, 1);
            st.setInt(9, turma.getCurso());


            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "A turma foi cadastrada com sucesso!", "Status do Cadastro", 1);
        } catch (Exception e) {
            System.out.println("Algo aconteceu de errado! " + e);
            JOptionPane.showMessageDialog(null, "Não foi possivel realizar o cadastro!", "Status do Cadastro", 0);
        }
    }

    public ArrayList<Turma> ler(String sql, Connection con) {
        System.err.println(sql);
        ArrayList<Turma> lista = new ArrayList<>();
        try (com.mysql.jdbc.PreparedStatement st = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sql);
                ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                lista.add(new Turma(rs.getInt(1), rs.getInt(4), rs.getString(8), rs.getString(3), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(2), rs.getInt(9), rs.getString(10)));
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
}
