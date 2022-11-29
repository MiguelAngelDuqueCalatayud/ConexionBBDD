/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examenprueba;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Oscar Martinez
 */
public class DBUtil {

    String usuario = null;
    String password = null;
    String bdd = null;
    String puerto = null;
    String servidor = null;
    Connection conn;

    String url1 = "jdbc:mysql://";
    String url2 = ":";
    String url3 = "/";

    public void crearConex(String usuario, String password, String bdd, String puerto, String servidor) {

        try {
            conn = DriverManager.getConnection(url1 + servidor + url2 + puerto + url3 + bdd, usuario, password);
        } catch (SQLException ex) {
            Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ResultSet EjecutarSelect(String sentenciaSelect) {

        ResultSet datos = null;

        try {
            /*
            IMPORTANTE LOS TYPE Y EL CONCUR
             */
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            System.out.println(sentenciaSelect);
            datos = st.executeQuery(sentenciaSelect);
        } catch (SQLException ex) {
            Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datos;
    }

    private static DBUtil db;

    public void ejecutarSentenciasIDU(String sentenciaSQL) throws SQLException {

        Statement st = conn.createStatement();
        System.out.println(sentenciaSQL);
        st.executeUpdate(sentenciaSQL);
    }

}
