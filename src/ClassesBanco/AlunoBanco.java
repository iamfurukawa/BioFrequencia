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
public class AlunoBanco {

    MateriaBanco materiaBanco = new MateriaBanco();

    public void cadastrarAluno(Connection con, Aluno aluno) {
        String sql = "insert into aluno values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement st = (PreparedStatement) con.prepareStatement(sql)) {

            Statement stmt = (Statement) con.createStatement();
            String sql1 = "select max(cd_al) from aluno";
            ResultSet rs = (ResultSet) stmt.executeQuery(sql1);
            rs.next();
            int proximoCodigo = rs.getInt(1) + 1;
            rs.close();

            st.setInt(1, proximoCodigo);
            st.setInt(2, aluno.getNumeroChamada());
            st.setString(3, aluno.getNomeCompleto());
            st.setString(4, aluno.getRa());
            st.setInt(5, aluno.getSexo());
            st.setString(6, aluno.getDataNascimento());
            st.setString(7, aluno.getTelResidencial());
            st.setString(8, aluno.getTelCelular());
            st.setString(9, aluno.getCep());
            st.setString(10, aluno.getCidade());
            st.setString(11, aluno.getEndereco());
            st.setString(12, aluno.getNomeResponsavel1());
            st.setString(13, aluno.getNomeResponsavel2());
            st.setInt(14, 1);

            st.executeUpdate();
            cadastrarAlunoContinuacao(con, aluno, proximoCodigo);
            JOptionPane.showMessageDialog(null, "O aluno foi cadastrado com sucesso!", "Status do Cadastro", 1);
        } catch (Exception e) {
            System.out.println("Algo aconteceu de errado! " + e);
            System.out.println("Algo aconteceu de errado! AREA PRINCIPAL");
            JOptionPane.showMessageDialog(null, "Não foi possivel realizar o cadastro!", "Status do Cadastro", 0);
        }
    }

    public void cadastrarAlunoContinuacao(Connection con, Aluno aluno, int proximoCodigo) {
        ArrayList<Materia> materias = new ArrayList<>();
        int c = 0;
        materias = materiaBanco.lerCodigo(con, aluno.getTurma());
        while (c < materias.size()) {
            cadastrarAlunoMaterias(con, proximoCodigo, materias.get(c).getCodigo());
            c++;
        }
    }

    public void cadastrarAlunoMaterias(Connection con, int cdAluno, int cdMateria) {
        String sql = "insert into alunoxmateria values(?,?,?,?)";
        try (PreparedStatement st = (PreparedStatement) con.prepareStatement(sql)) {

            Statement stmt = (Statement) con.createStatement();
            String sql1 = "select max(cd_al_mat) from alunoxmateria";
            ResultSet rs = (ResultSet) stmt.executeQuery(sql1);
            rs.next();
            int proximoCodigo = rs.getInt(1) + 1;
            rs.close();

            st.setInt(1, proximoCodigo);
            st.setString(2, "Ativo");
            st.setInt(3, cdMateria);
            st.setInt(4, cdAluno);

            st.executeUpdate();
        } catch (Exception e) {
            System.out.println("Algo aconteceu de errado! CADASTRO NAS MATERIAS ALUNO" + e);
        }
    }

    public ArrayList<Aluno> ler(String sql, Connection con) {
        System.err.println(sql);
        ArrayList<Aluno> lista = new ArrayList<>();
        try (com.mysql.jdbc.PreparedStatement st = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sql);
                ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                lista.add(new Aluno(rs.getString(4), rs.getInt(2), rs.getString(6), rs.getString(7), rs.getString(10), rs.getString(12), rs.getString(13), rs.getString(3), rs.getInt(5), rs.getString(8), rs.getString(9), rs.getString(11), rs.getInt(1)));
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
