/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;
    import javax.naming.Context;
    import javax.naming.InitialContext;
    import javax.sql.DataSource;
    import java.sql.Connection;
    import java.sql.SQLException;
/**
 *
 * @author santi
 */
public class NewClass {
    
        public static Connection getConnection() throws Exception {
            Context initContext = new InitialContext();
            DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/conexionPorGUI");
            return ds.getConnection();
        }
    }
