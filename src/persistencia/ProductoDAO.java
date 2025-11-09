package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import aplicacion.Producto;

public class ProductoDAO {



    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    clsConexion cn = new clsConexion();
    
    public List<Producto> CargarProductos() throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM producto";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            List<Producto> productos = new ArrayList<>();
             while (rs.next()) 
                { 
                    int id = rs.getInt("idproducto");
                    String nombre = rs.getString("nombre");
                    float precio = rs.getFloat("precio");
                    int stock = rs.getInt("stock");
                    Producto productoActual = new Producto(id,nombre,precio,stock);
                    productos.add(productoActual);
                }
             return productos;

       
}

    public void EliminarProducto(Producto producto) throws ClassNotFoundException, SQLException 
    {
            String sql = "DELETE FROM producto where idproducto = "+ producto.getId();
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
    }

    public void ModificarProducto(Producto p)throws ClassNotFoundException, SQLException 
    {
            String sql = "UPDATE producto set nombre = ?, precio = ?, stock = ? where idproducto = ? ";
            
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getNombre());
            ps.setString(2,Float.toString(p.getPrecio()));
            ps.setString(3, Integer.toString(p.getStock()));
            ps.setInt(4, p.getId());
            ps.executeUpdate();
    }

    public void agregarProducto(Producto p) throws ClassNotFoundException, SQLException 
    {
            String sql = "INSERT INTO producto ( nombre, precio, stock) VALUES ( ?, ?, ?)";    
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getNombre());
            ps.setString(2,Float.toString(p.getPrecio()));
            ps.setString(3, Integer.toString(p.getStock()));
            ps.executeUpdate();
    }


}
    