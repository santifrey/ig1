
package aplicacion;

import java.sql.SQLException;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import persistencia.ProductoDAO;


public class Producto {
    
    private int id;
    private String nombre;
    private float precio;
    private int stock;

    public Producto(int id, String nombre, float precio, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public Producto() {
    }

    public List<Producto> CargarProductos() throws ClassNotFoundException, SQLException
    {
        ProductoDAO prod = new ProductoDAO();
        return prod.CargarProductos();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void Eliminar() throws ClassNotFoundException, SQLException {
        ProductoDAO prod = new ProductoDAO();
        prod.EliminarProducto(this);
    }

    public void Modificar() throws ClassNotFoundException, SQLException
    {
        ProductoDAO prod = new ProductoDAO();
        prod.ModificarProducto(this);
    }

    public void agregarNuevo() throws ClassNotFoundException, SQLException 
    {
        ProductoDAO prod = new ProductoDAO();
        prod.agregarProducto(this);
    }
}
