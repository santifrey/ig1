
package aplicacion;

import persistencia.UsuarioDAO;
public class Usuario {

    public String getNombre() {
        return nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public Usuario(String nombre, String contraseña) {
        this.nombre = nombre;
        this.contraseña = contraseña;
    }
    
    private  String nombre;
    private String contraseña;
    
    public boolean ValidarUsuario()
            {
                UsuarioDAO usuario = new UsuarioDAO();
                return usuario.Validar(this);        
            }
    
           
           
}
