/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Óscar Martínez
 */
public class FXMLDocumentController extends DBUtil implements Initializable {

    @FXML
    private AnchorPane rootPanel;
    @FXML
    private TextField txtID;
    @FXML
    private TextField txtCiudad;
    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtDistrito;
    @FXML
    private TextField txtPoblacion;
    @FXML
    private Button botonAnterior;
    @FXML
    private Button botonPosterior;
    @FXML
    private Button botonSentencias;
    @FXML
    private Button botonConfig;

    DBUtil conn = new DBUtil();
    ResultSet datos = null;

    String tempId, tempCiudad, tempCodigo, tempDistrito, tempPoblacion = "";
    int llaveIzq = 1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        String usuario = "";
        String password = "";
        String bdd = "";
        String puerto = "";
        String servidor = "";

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
            Logger.getLogger(ConfigController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ConfigController.class.getName()).log(Level.SEVERE, null, ex);
        }

        conn.crearConexion(usuario, password, bdd, puerto, servidor);
        datos = conn.ejecutarSelect("SELECT * FROM city");

        try {
            while (!datos.isLast()) {
                datos.next();
                for (int i = 1; i <= datos.getMetaData().getColumnCount(); i++) {
                    System.out.println(datos.getString(i));
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            datos.first();
            txtID.setText(datos.getString(1));
            txtCiudad.setText(datos.getString(2));
            txtCodigo.setText(datos.getString(3));
            txtDistrito.setText(datos.getString(4));
            txtPoblacion.setText(datos.getString(5));

            tempId = txtID.getText();
            tempCiudad = txtCiudad.getText();
            tempCodigo = txtCodigo.getText();
            tempDistrito = txtDistrito.getText();
            tempPoblacion = txtPoblacion.getText();

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void irAtras(ActionEvent event) {

        try {
            datos.previous();
            
            if (!"".equals(tempId) && "".equals(txtID.getText())) {
                
                datos.next();
                datos.deleteRow();
                datos.previous(); 
            }
            
            if (tempCiudad != txtCiudad.getText() || tempCodigo != txtCodigo.getText() || tempDistrito != txtDistrito.getText() || tempPoblacion != txtPoblacion.getText()) {
                
                datos.next();
                datos.updateString("Name", txtCiudad.getText());
                datos.updateString("CountryCode", txtCodigo.getText());
                datos.updateString("District", txtDistrito.getText());
                datos.updateString("Population", txtPoblacion.getText());
                datos.updateRow();
                datos.previous();
            }

            if (!datos.isBeforeFirst()) {
                txtID.setText(datos.getString(1));
                txtCiudad.setText(datos.getString(2));
                txtCodigo.setText(datos.getString(3));
                txtDistrito.setText(datos.getString(4));
                txtPoblacion.setText(datos.getString(5));
            } else {
                datos.afterLast();
            }

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tempId = txtID.getText();
        tempCiudad = txtCiudad.getText();
        tempCodigo = txtCodigo.getText();
        tempDistrito = txtDistrito.getText();
        tempPoblacion = txtPoblacion.getText();
        llaveIzq = 0;
    }

    @FXML
    private void irAlante(ActionEvent event) {

        try {
            datos.next();

            //DELETE 
            if (!"".equals(tempId) && "".equals(txtID.getText())) {

                datos.previous();
                datos.deleteRow();
                datos.next();

            }

            //UPDATE 
            if (txtCiudad.getText() != tempCiudad || txtCodigo.getText() != tempCodigo || txtDistrito.getText() != tempDistrito || txtPoblacion.getText() != tempPoblacion) {

                datos.previous();
                datos.updateString("Name", txtCiudad.getText());
                datos.updateString("CountryCode", txtCodigo.getText());
                datos.updateString("District", txtDistrito.getText());
                datos.updateString("Population", txtPoblacion.getText());
                datos.updateRow();
                datos.next();
            }

            if (!datos.isAfterLast()) {
                txtID.setText(datos.getString(1));
                txtCiudad.setText(datos.getString(2));
                txtCodigo.setText(datos.getString(3));
                txtDistrito.setText(datos.getString(4));
                txtPoblacion.setText(datos.getString(5));
            } else {

                //INSERT 
                if ("".equals(tempCiudad) && !"".equals(txtCiudad.getText()) && llaveIzq == 1) {
                    //conn.ejecutarSentenciasIDU("INSERT INTO city (Name) VALUES (" + txtCiudad.getText() + ")";
                    datos.moveToInsertRow();
                    datos.updateString("Name", txtCiudad.getText());
                    datos.updateString("CountryCode", txtCodigo.getText());
                    datos.updateString("District", txtDistrito.getText());
                    datos.updateString("Population", txtPoblacion.getText());
                    datos.insertRow();
                    datos.next();
                }

                txtID.setText("");
                txtCiudad.setText("");
                txtCodigo.setText("");
                txtDistrito.setText("");
                txtPoblacion.setText("");
            }

            tempId = txtID.getText();
            tempCiudad = txtCiudad.getText();
            tempCodigo = txtCodigo.getText();
            tempDistrito = txtDistrito.getText();
            tempPoblacion = txtPoblacion.getText();
            llaveIzq = 1;

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void sentenciasLib(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Sentencias.fxml"));
            this.rootPanel.getChildren().setAll(root);
        } catch (IOException e) {
            System.out.print(e);
        }
    }

    @FXML
    private void irConfig(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Config.fxml"));
            this.rootPanel.getChildren().setAll(root);
        } catch (IOException e) {
            System.out.print(e);
        }
    }

}
