
package aplicacion;

import java.util.List;
import persistencia.DetalleVentaDAO;
import java.time.LocalDate;

public class DetalleVenta {
    
    private int idDetalleVenta;
    private Producto producto;
    private float precio;
    private float cantidad;
    private float subtotal;

    public int getIdDetalleVenta() {
        return idDetalleVenta;
    }

    public void setIdDetalleVenta(int idDetalleVenta) {
        this.idDetalleVenta = idDetalleVenta;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    public DetalleVenta(int idDetalleVenta, Producto producto, float precio, float cantidad, float subtotal) {
        this.idDetalleVenta = idDetalleVenta;
        this.producto = producto;
        this.precio = precio;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }
    
    
    

    
}
