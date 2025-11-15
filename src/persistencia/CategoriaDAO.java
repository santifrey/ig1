
package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import aplicacion.Categoria;

public class CategoriaDAO {

    

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    clsConexion cn = new clsConexion();
    
    public String getnamebyID(int IdCategoria) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM categoria WHERE idCategoria = "+ IdCategoria;
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        rs.next();
        return rs.getString("nombre");
    }
    
    public List<Categoria> CargarCategorias() throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM categoria WHERE flag = true";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            List<Categoria> Categorias = new ArrayList<>();
             while (rs.next()) 
                { 
                    int id = rs.getInt("idCategoria");
                    String nombre = rs.getString("nombre");
                    Categoria CategoriaActual = new Categoria(id, nombre);
                    Categorias.add(CategoriaActual);
                }
             return Categorias;
    }
}
