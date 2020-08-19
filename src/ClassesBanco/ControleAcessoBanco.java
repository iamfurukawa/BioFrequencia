/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassesBanco;

import ClassesModelo.*;
import com.mysql.jdbc.*;
import java.sql.ResultSet;

/**
 *
 * @author User
 */
public class ControleAcessoBanco {

    public Usuario ler(Connection con, Usuario usuario) {
        String sql = "select * from funcionario where cpf_func = " + usuario.getCpf()+"&& ativo = 1";
        try (PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
                ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                usuario.setNomeCompleto(rs.getString(2));
                usuario.setDataNascimento(rs.getString(3));
                usuario.setRg(rs.getString(5));
                usuario.setTelResidencial(rs.getString(6));
                usuario.setTelCelular(rs.getString(7));
                usuario.setEmail(rs.getString(8));
                usuario.setNivel(rs.getInt(9));
                usuario.setSenha(rs.getString(10));
                usuario.setAtivo(rs.getInt(11));
                usuario.setCodigo(rs.getInt(1));
                //usuario.testaClasse();
            }
        } catch (Exception e) {
            System.out.println("Algo aconteceu de errado! " + e);
        }
        return usuario;
    }
}
