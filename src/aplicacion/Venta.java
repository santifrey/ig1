
package aplicacion;


import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import persistencia.VentaDAO;


public class Venta {
    
    private int id;
    private float total;
    private LocalDate fecha;
    private Cliente cliente;
    private List<DetalleVenta> detalle;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<DetalleVenta> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<DetalleVenta> detalle) {
        this.detalle = detalle;
    }


    
    
    public void agregarVenta () throws ClassNotFoundException, SQLException{
        VentaDAO ven = new VentaDAO();
        ven.agregarVenta(this);
    }
    
    
}
