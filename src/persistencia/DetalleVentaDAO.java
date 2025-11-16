
package persistencia;


import aplicacion.Categoria;
import aplicacion.DetalleVenta;
import aplicacion.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DetalleVentaDAO {
    
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    clsConexion cn = new clsConexion();
    
    public List<DetalleVenta> CargarDetallesVenta(int idVenta) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM producto where idVenta = "+ idVenta;
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            List<DetalleVenta> detalles = new ArrayList<>();
             while (rs.next()) 
                { 
                    int id = rs.getInt("idDetalle");
                    // producto
                    Producto producto = new Producto();
                    producto = producto.BuscarProducto(rs.getInt("idProducto"));
                    float precio = rs.getFloat("precio");
                    float cantidad = rs.getInt("cantidad");
                    float subtotal = rs.getInt("subtotal");
                    DetalleVenta detalleActual = new DetalleVenta(id,producto,precio,cantidad,subtotal);
                    detalles.add(detalleActual);
                }
             return detalles;   
}

    public void agregarDetalleVenta(DetalleVenta dv,int idVenta) throws ClassNotFoundException, SQLException 
    {
            String sql = "INSERT INTO detalleVenta (idVenta, idProducto, precioUnitario, cantidad, subtotal) VALUES ( ?, ?, ?, ?, ?)";    
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idVenta);
            ps.setInt(2,dv.getProducto().getId());
            ps.setFloat(3, dv.getPrecio());
            ps.setFloat(4,dv.getCantidad());
            ps.setFloat(5,dv.getSubtotal());
            ps.executeUpdate();
    }


    
}
