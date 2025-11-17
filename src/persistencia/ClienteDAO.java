
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


    public void EliminarCliente(Cliente cli) throws ClassNotFoundException, SQLException {
            String sql = "UPDATE cliente set flag = false where idcliente = "+ cli.getId();
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
    }

    public void AgregarCliente(Cliente cli) throws SQLException, ClassNotFoundException {
            String sql = "INSERT INTO cliente (nombre, apellido, telefono, direccion) VALUES ( ?, ?, ?, ?)";    
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, cli.getNombre());
            ps.setString(2, cli.getApellido());
            ps.setString(3, cli.getTelefono());
            ps.setString(4, cli.getDireccion());
            ps.executeUpdate();
    }
    

    public void ModificarCliente(Cliente cli) throws ClassNotFoundException, SQLException {
            String sql = "UPDATE cliente set nombre = ?, apellido = ?, telefono = ?, direccion = ? where idcliente = ? ";
            
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, cli.getNombre());
            ps.setString(2, cli.getApellido());
            ps.setString(3, cli.getTelefono());
            ps.setString(4, cli.getDireccion());
            ps.setInt(5, cli.getId());
            ps.executeUpdate();
    }

    public Cliente buscarCliente(int id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM cliente WHERE idcliente ="+ id;
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        rs.next();
        int idclien= rs.getInt("idcliente");
        String nombre = rs.getString("nombre");
        String apellido = rs.getString("apellido");
        String telefono = rs.getString("telefono");
        String direccion = rs.getString("direccion");
        Cliente clienteActual = new Cliente(idclien, nombre, apellido, telefono, direccion);
        return clienteActual;
    }

    
}
