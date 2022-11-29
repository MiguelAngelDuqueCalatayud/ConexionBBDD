/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package preexamen4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Óscar Martínez
 */
public class SentenciasController extends DBUtil implements Initializable {

    @FXML
    private AnchorPane rootPanel;
    @FXML
    private TextField txtSentencia;
    @FXML
    private TextArea txtMostrar;
    @FXML
    private Button botonEjecutar;
    @FXML
    private Button botonVolver;

        ResultSet datos;
        DBUtil conn = new DBUtil();
    
        String usuario = null;
        String password = null;
        String bdd = null;
        String puerto = null;
        String servidor = null;
        
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        String[] palabras;
        ArrayList<String> arrayDatos = new ArrayList<>();

        try {
            File f = new File("config.txt");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            String linea = "";

            while ((linea = br.readLine()) != null) {
                palabras = linea.split(":");
                arrayDatos.add(palabras[1]);
            }
            
            usuario = arrayDatos.get(0);
            password = arrayDatos.get(1);
            puerto = arrayDatos.get(2);
            bdd = arrayDatos.get(3);
            servidor = arrayDatos.get(4);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(SentenciasController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SentenciasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    

    @FXML
    private void hacerSentencia(ActionEvent event) {
        
        txtMostrar.setText("");
        String query = txtMostrar.getText();
        String queryMin = query.toLowerCase();
        String[] palabras = queryMin.split(" ");
        
        conn.crearConexion(usuario, password, bdd, puerto, servidor);
        
        if(palabras[0].equals("select")) {
            datos = conn.ejecutarSelect(query);
        
        
        try {
            while(!datos.isLast()) {
                datos.next();
                for(int i = 1; i <= datos.getMetaData().getColumnCount(); i++) {
                    txtMostrar.setText(txtMostrar.getText() + datos.getString(i) + "\n");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SentenciasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        } else if(palabras[0].equals("insert")) {
            conn.ejecutarIDU(query);
            txtMostrar.setText("Ciudad agregada.");
        } else if(palabras[0].equals("delete")) {
            conn.ejecutarIDU(query);
            txtMostrar.setText("Ciudad eliminada.");
        } else if(palabras[0].equals("update")) {
            conn.ejecutarIDU(query);
            txtMostrar.setText("Ciudad actualizada");
        }
    }
    

    @FXML
    private void volver(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            this.rootPanel.getChildren().setAll(root);
        } catch (IOException e) {
            System.out.print(e);
        }
    }
}
