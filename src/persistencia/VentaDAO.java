
package persistencia;


import aplicacion.Cliente;
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
import java.time.LocalDate;
public class VentaDAO {
    
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    clsConexion cn = new clsConexion();
    


    public void agregarVenta(Venta v) throws ClassNotFoundException, SQLException {
            String sql = "INSERT INTO venta (idCliente, total, fecha) VALUES ( ?, ?, ?)";    
            con = cn.getConnection();
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, v.getCliente().getId());
            ps.setFloat(2,v.getTotal());
            ps.setDate(3,Date.valueOf(v.getFecha()));
            ps.executeUpdate();
            ResultSet resultado = ps.getGeneratedKeys();
            if (resultado.next()) {
                v.setId(resultado.getInt(1));
            }
            for ( DetalleVenta dv : v.getDetalle()){
                dv.GuardarDetalle(v);
            }
    }

    public List<Venta> CargarVentasPeriodo(LocalDate fechaInicio, LocalDate fechaFin) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM venta WHERE fecha BETWEEN ? AND ?";
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setDate(1, Date.valueOf(fechaInicio));
        ps.setDate(2, Date.valueOf(fechaFin));
        rs = ps.executeQuery();
        List<Venta> ventas = new ArrayList<>();
         while (rs.next()) 
            { 
                int id = rs.getInt("idVenta");
                float total = rs.getFloat("total");
                LocalDate fecha = rs.getDate("fecha").toLocalDate();
                Cliente cliente = new Cliente();
                cliente = cliente.buscarCliente(rs.getInt("idCliente"));
                List<DetalleVenta> detalles = new ArrayList<>();
                DetalleVenta detalle = new DetalleVenta();
                detalles = detalle.CargarDetalles(id);
                Venta ventaActual = new Venta(id,total,fecha,cliente,detalles);
                ventas.add(ventaActual);
            }
         return ventas; 
    }
}
