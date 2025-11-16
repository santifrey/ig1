
package persistencia;


import aplicacion.DetalleVenta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import aplicacion.Producto;
import aplicacion.Venta;
import java.sql.Statement;
public class VentaDAO {
    
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    clsConexion cn = new clsConexion();
    
//    public List<Producto> CargarProductos() throws ClassNotFoundException, SQLException {
//        String sql = "SELECT * FROM producto where flag = true";
//            con = cn.getConnection();
//            ps = con.prepareStatement(sql);
//            rs = ps.executeQuery();
//            List<Producto> productos = new ArrayList<>();
//             while (rs.next()) 
//                { 
//                    int id = rs.getInt("idproducto");
//                    String nombre = rs.getString("nombre");
//                    float precio = rs.getFloat("precio");
//                    int stock = rs.getInt("stock");
//                    Categoria categoria = new Categoria(rs.getInt("categoria"));
//                    Producto productoActual = new Producto(id,nombre,precio,stock,categoria);
//                    productos.add(productoActual);
//                }
//             return productos;      
//}

    public void agregarVenta(Venta v) throws ClassNotFoundException, SQLException {
            String sql = "INSERT INTO producto (idCliente, total, fecha) VALUES ( ?, ?, ?)";    
            con = cn.getConnection();
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, v.getCliente().getId());
            ps.setFloat(2,v.getTotal());
            ps.setDate(3,Date.valueOf(v.getFecha()));
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            
            if (rs.next()) {
                int generatedId = rs.getInt(1); 
            }
            for ( DetalleVenta dv : v.getDetalle()){
                dv.GuardarDetalle(v.getId());
            }
    }
}
