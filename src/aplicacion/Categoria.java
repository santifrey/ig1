
package aplicacion;
import java.sql.SQLException;
import persistencia.CategoriaDAO;
import java.util.List;

public class Categoria 
{
    private int IdCategoria;
    private String nombre;

    public Categoria(int id, String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getIdCategoria() {
        return IdCategoria;
    }

    public void setIdCategoria(int IdCategoria) {
        this.IdCategoria = IdCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Categoria(int IdCategoria) throws ClassNotFoundException, SQLException {
        this.IdCategoria = IdCategoria;
        CategoriaDAO cat = new CategoriaDAO();
        this.nombre = cat.getnamebyID(IdCategoria);
    }
    
    public List<Categoria> CargarCategorias() throws ClassNotFoundException, SQLException{
        CategoriaDAO cat = new CategoriaDAO();
        return cat.CargarCategorias();
    }
}
    
    
    

