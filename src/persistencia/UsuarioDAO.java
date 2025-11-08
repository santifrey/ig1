
package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import aplicacion.Usuario;



public class UsuarioDAO {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    clsConexion cn = new clsConexion();
    
        public boolean Validar(Usuario usuario){
        String sql = "SELECT * FROM usuario WHERE Nombre = ? AND Contrasena = ?";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getContraseña());
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
            else
            {
             return false;   
            }
        } catch (SQLException e) {
            
            System.err.println(e.toString());
            return false;
        } catch (Exception ex) {
            System.getLogger(UsuarioDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            return false;
        }
}
}
