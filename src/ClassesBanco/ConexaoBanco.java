/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassesBanco;

import com.mysql.jdbc.*;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author User
 */
public class ConexaoBanco {

    private Connection con;

    public Connection conexao() {
        try {
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/biofrequencia", "root", "");
            System.out.println("Conexão Estabelecida - 1");
        } catch (Exception e) {
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/biofrequencia", "root", "123");
            System.out.println("Conexão Estabelecida - 2");
        } finally {
            return con;
        }
    }
}
