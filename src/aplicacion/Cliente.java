/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion;

import java.sql.SQLException;
import java.util.List;
import persistencia.ClienteDAO;

/**
 *
 * @author Gale
 */
public class Cliente {
    
    private int id;
    private String nombre;
    private String apellido;
    private String telefono;
    private String direccion;

    //CONSTRUCTOR
    public Cliente(int id, String nombre, String apellido, String telefono, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public Cliente() {
    }
   
    

    // SET Y GET
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    
    //FUNCIONES
    public List<Cliente> CargarClientes() throws ClassNotFoundException, SQLException 
    {
        ClienteDAO cli = new ClienteDAO();
        return cli.CargarClientes();
    }

    public void Eliminar() throws ClassNotFoundException, SQLException {
        ClienteDAO cli = new ClienteDAO();
        cli.EliminarCliente(this);
    }

    public void AgregarNuevo() throws SQLException, ClassNotFoundException {
        ClienteDAO cli = new ClienteDAO();
        cli.AgregarCliente(this);
    }


    public void Modificar() throws ClassNotFoundException, SQLException {
        ClienteDAO cli = new ClienteDAO();
        cli.ModificarCliente(this);
    }
  
    
}
