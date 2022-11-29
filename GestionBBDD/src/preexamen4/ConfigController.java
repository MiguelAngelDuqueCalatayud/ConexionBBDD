/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package preexamen4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
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
import jdk.jfr.events.FileWriteEvent;

/**
 * FXML Controller class
 *
 * @author Óscar Martínez
 */
public class ConfigController extends DBUtil implements Initializable {
    
    @FXML
    private AnchorPane rootPanel;
    @FXML
    private TextField txtArchivo;
    @FXML
    private TextField txtServidor;
    @FXML
    private TextField txtPuerto;
    @FXML
    private TextField txtContraseña;
    @FXML
    private TextField txtUsuario;
    @FXML
    private TextField txtBDD;
    @FXML
    private TextField txtUbicacion;
    @FXML
    private TextArea textoMostrar;
    @FXML
    private Button botonCrear;
    @FXML
    private Button botonMostrar;
    @FXML
    private Button botonVolver;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DBUtil conn = new DBUtil();
        File f = new File(txtArchivo.getText());
        txtUbicacion.setText(f.getAbsolutePath());
        leerConfig();
        
        String usuario = txtUsuario.getText();
        String password = txtContraseña.getText();
        String bdd = txtBDD.getText();
        String puerto = txtPuerto.getText();
        String servidor = txtServidor.getText();
        
        try {
            conn.crearConexion(usuario, password, bdd, puerto, servidor);
            System.out.print("Conexion establecida");
        } catch (Exception e) {
            System.err.print("No se pudo conectar");
        }
        
    }
    
    @FXML
    private void crearArchivo(ActionEvent event) throws IOException {
        
        File f = new File(txtArchivo.getText());
        FileWriter fw = new FileWriter(f);
        
        fw.write("Nombre:"+txtUsuario.getText() + "\n");
        fw.write("Contraseña:"+txtContraseña.getText() + "\n");
        fw.write("Puerto:"+txtPuerto.getText() + "\n");
        fw.write("BDD:"+txtBDD.getText() + "\n");
        fw.write("Host:"+txtServidor.getText() + "\n");
        fw.close();
        
    }
    
    @FXML
    private void mostrarArchivo(ActionEvent event) throws FileNotFoundException, IOException {
        
        textoMostrar.setText("");
        File f = new File(txtArchivo.getText());
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        
        String linea = "";
        
        while ((linea = br.readLine()) != null) {
            textoMostrar.setText(textoMostrar.getText() + linea + "\n");
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
    
    public void leerConfig() {
        DBUtil conn = new DBUtil();
        String[] palabras;
        ArrayList<String> arrayDatos = new ArrayList<>();
        
        try {
            File f = new File(txtArchivo.getText());
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            
            String linea = "";
            
            while ((linea = br.readLine()) != null) {
                palabras = linea.split(":");
                arrayDatos.add(palabras[1]);
            }
            txtUsuario.setText(conn.usuario = arrayDatos.get(0));
            txtContraseña.setText(conn.password = arrayDatos.get(1));
            txtPuerto.setText(conn.puerto = arrayDatos.get(2));
            txtBDD.setText(conn.bdd = arrayDatos.get(3));
            txtServidor.setText(conn.servidor = arrayDatos.get(4));
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConfigController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ConfigController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
