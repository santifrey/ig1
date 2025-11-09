

package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class clsConexion {

    static final String Controlador = "com.mysql.cj.jdbc.Driver"; //Controlador (conector de Java a MySQL)


    
    public Connection getConnection() throws ClassNotFoundException, SQLException  {
    Connection Conexion = null; 
    Class.forName(Controlador); // cargar el controlador
    Conexion = DriverManager.getConnection( "jdbc:mysql://192.168.68.55:3306/panaderia","ig1","ig1");
    return Conexion;
            }
//    public Connection getConnection() {
//        try {
//            
//            Class.forName("com.mysql.jdbc.Driver");
//            // Establecer la conexión con la base de datos Access
//            String mysql = "jdbc:mysql://localhost:3306/panaderia?zeroDateTimeBehavior=CONVERT_TO_NULL";
//            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/panaderia","ig1","ig1");
//            
//            return conexion;
//        } catch (SQLException e) {
//            System.out.println("Error de SQL: " + e.toString());
//        } catch (ClassNotFoundException ex) {
//            System.getLogger(clsConexion.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
//        }
//        return null;
//    }
}
