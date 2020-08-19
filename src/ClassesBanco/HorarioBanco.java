/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassesBanco;

import ClassesModelo.Curso;
import ClassesModelo.Horario;
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
public class HorarioBanco {

    public void cadastrarHorario(Connection con, ArrayList<Horario> horario) {
        String sql = "insert into horario_aula values(?,?,?,?)";
        int c = 0;
        try {
            while (horario.size() > c) {
                PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);

                Statement stmt = (Statement) con.createStatement();
                String sql1 = "select max(cd_hr) from horario_aula";
                ResultSet rs = (ResultSet) stmt.executeQuery(sql1);
                rs.next();
                int proximoCodigo = rs.getInt(1) + 1;
                rs.close();

                st.setInt(1, proximoCodigo);
                st.setInt(2, horario.get(c).getOrdem());
                st.setInt(3, horario.get(c).getDiaSemana());
                st.setInt(4, horario.get(c).getCdMat());
                st.executeUpdate();
                st.close();
                c++;
            }
            JOptionPane.showMessageDialog(null, "O Horario foi cadastrado com sucesso!", "Status do Cadastro", 1);
        } catch (Exception e) {
            System.out.println("Algo aconteceu de errado! " + e);
            JOptionPane.showMessageDialog(null, "Não foi possivel realizar o cadastro!", "Status do Cadastro", 0);
        }
    }

    public ArrayList<Horario> ler(String sql, Connection con) {
        System.err.println(sql);
        ArrayList<Horario> lista = new ArrayList<>();
        try (PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
                ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                lista.add(new Horario(rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getInt(1), rs.getString(6), rs.getString(7), rs.getString(8)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CursoBanco.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public void various(String sql, Connection con) {
        try (com.mysql.jdbc.PreparedStatement st = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sql)) {
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println("Algo aconteceu de errado! " + e);
        }
    }
}
