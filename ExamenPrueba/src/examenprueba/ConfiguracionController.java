/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package examenprueba;

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
 * @author Oscar Martinez
 */
public class ConfiguracionController extends DBUtil implements Initializable {

    @FXML
    private TextField txtUbicacion;
    @FXML
    private TextField txtArchivo;
    @FXML
    private TextField txtServidor;
    @FXML
    private TextField txtPuerto;
    @FXML
    private TextField txtBDD;
    @FXML
    private TextField txtUsuario;
    @FXML
    private TextField txtContrasenya;
    @FXML
    private TextArea txtMostrar;
    @FXML
    private Button botonCrear;
    @FXML
    private Button botonMostrar;
    @FXML
    private Button botonVolver;
    @FXML
    private AnchorPane rootPanel;

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
        String password = txtContrasenya.getText();
        String bdd = txtBDD.getText();
        String puerto = txtPuerto.getText();
        String servidor = txtServidor.getText();
        
        
        try {
            conn.crearConex(usuario, password, bdd, puerto, servidor);
            System.out.println("Conexion hecha");
        } catch (Exception e) {
            System.out.println("No se pudo conectar");
        }
        
        
        
    }

    @FXML
    private void crearArchivo(ActionEvent event) {
        
        try {
            File f = new File(txtUbicacion.getText());
            FileWriter fw = new FileWriter(f);
            
            fw.write("Nombre:"+txtUsuario.getText()+"\n");
            fw.write("Contraseña:"+txtContrasenya.getText()+"\n");
            fw.write("Puerto:"+txtPuerto.getText()+"\n");
            fw.write("BDD:"+txtBDD.getText()+"\n");
            fw.write("Host:"+txtServidor.getText()+"\n");
            fw.close();
            
        } catch (IOException ex) {
            Logger.getLogger(ConfiguracionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void mostrarConfig(ActionEvent event) {
        
        txtMostrar.setText("");
        
        try {
            File f = new File(txtUbicacion.getText());
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            
            String linea = "";
            
            while((linea = br.readLine()) != null) {
                txtMostrar.setText(txtMostrar.getText() + "\n\r" + linea);
            }
            
            
        } catch (IOException ex) {
            Logger.getLogger(ConfiguracionController.class.getName()).log(Level.SEVERE, null, ex);
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
        ArrayList<String> arrayCon = new ArrayList<>();
        try {
            File f = new File(txtArchivo.getText());
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            String linea = "";

            while ((linea = br.readLine()) != null) {
                palabras = linea.split(":");
                arrayCon.add(palabras[1]);
            }
            txtUsuario.setText(conn.usuario = arrayCon.get(0));
            txtContrasenya.setText(conn.password = arrayCon.get(1));
            txtPuerto.setText(conn.puerto = arrayCon.get(2));
            txtBDD.setText(conn.bdd = arrayCon.get(3));
            txtServidor.setText(conn.servidor = arrayCon.get(4));
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConfiguracionController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ConfiguracionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
