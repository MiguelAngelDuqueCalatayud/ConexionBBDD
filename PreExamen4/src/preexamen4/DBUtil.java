/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package preexamen4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Óscar Martínez
 */
public class DBUtil {
    
    String usuario = "";
    String password = "";
    String bdd = "";
    String puerto = "";
    String servidor = "";
    Connection conn;
    
    String url1 = "jdbc:mysql://";
    String url2 = ":";
    String url3 = "/";
    
    public void crearConexion (String usuario, String password, String bdd, String puerto, String servidor) {
        
        try {
            conn = DriverManager.getConnection(url1 + servidor + url2 + puerto + url3 + bdd, usuario, password);
        } catch (SQLException ex) {
            Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public ResultSet ejecutarSelect (String query) {

        ResultSet datos = null;

        try {
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            System.err.print(query);
            datos = st.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return datos;
    }
    
    
    public void ejecutarIDU (String query) {

        try {
            Statement st = conn.createStatement();
            System.err.print(query);
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
    }
    
}
