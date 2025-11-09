
package persistencia;

import aplicacion.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    clsConexion cn = new clsConexion();
    
    public List<Cliente> CargarClientes() throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM cliente WHERE flag = true";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            List<Cliente> clientes = new ArrayList<>();
             while (rs.next()) 
                { 
                    int id = rs.getInt("idcliente");
                    String nombre = rs.getString("nombre");
                    String apellido = rs.getString("apellido");
                    String telefono = rs.getString("telefono");
                    String direccion = rs.getString("direccion");
                    
                    Cliente clienteActual = new Cliente(id, nombre, apellido, telefono, direccion);
                    clientes.add(clienteActual);
                }
             return clientes;
    }

//    public void EliminarCliente(Cliente cli) {
//            String sql = "DELETE FROM producto where idproducto = "+ producto.getId();
//            con = cn.getConnection();
//            ps = con.prepareStatement(sql);
//            ps.executeUpdate();
//        
//    }

    
}
