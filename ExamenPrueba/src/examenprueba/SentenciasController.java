/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package examenprueba;

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
 * @author Oscar Martinez
 */
public class SentenciasController implements Initializable {

    @FXML
    private AnchorPane rootPanel;
    @FXML
    private TextArea txtResultado;
    @FXML
    private Button botonVolver;
    @FXML
    private Button botonSentencia;
    @FXML
    private TextField txtSentencia;

    /**
     * Initializes the controller class.
     */
    ResultSet datos;
    DBUtil conn = new DBUtil();

    String usuario = null;
    String password = null;
    String bdd = null;
    String puerto = null;
    String servidor = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        String[] palabras;
        ArrayList<String> arrayCon = new ArrayList<>();
        try {
            File f = new File("config.txt");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            String linea = "";

            while ((linea = br.readLine()) != null) {
                palabras = linea.split(":");
                arrayCon.add(palabras[1]);
            }
            usuario = arrayCon.get(0);
            password = arrayCon.get(1);
            puerto = arrayCon.get(2);
            bdd = arrayCon.get(3);
            servidor = arrayCon.get(4);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConfiguracionController.class.getName()).log(Level.SEVERE, null, ex);
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

    @FXML
    private void ejecutarSQL(ActionEvent event) {

        txtResultado.setText("");
        String query = txtSentencia.getText();
        String[] palabras = query.split(" ");

        if (palabras[0].equals("SELECT") || palabras[0].equals("select")) {
            conn.crearConex(usuario, password, bdd, puerto, servidor);
            datos = conn.EjecutarSelect(query);
            try {

                while (!datos.isLast()) {
                    datos.next();
                    for (int i = 1; i <= datos.getMetaData().getColumnCount(); i++) {
                        txtResultado.setText(txtResultado.getText() + datos.getString(i) + "\r\n");
                    }

                }

            } catch (Exception e) {
                txtResultado.setText(e.toString());
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, e);
            }

        } else if (palabras[0].equals("INSERT") || palabras[0].equals("insert")) {
            conn.crearConex(usuario, password, bdd, puerto, servidor);
            try {
                conn.ejecutarSentenciasIDU(query);
                txtResultado.setText("Ciudad agregada con exito" + "\r\n" + query);
            } catch (Exception e) {
                txtResultado.setText(e.toString());
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, e);
            }
        } else if (palabras[0].equals("DELETE") || palabras[0].equals("delete")) {
            conn.crearConex(usuario, password, bdd, puerto, servidor);
            try {
                conn.ejecutarSentenciasIDU(query);
                txtResultado.setText("Ciudad borrada con exito" + "\r\n" + query);
            } catch (Exception e) {
                txtResultado.setText(e.toString());
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, e);
            }
        } else if (palabras[0].equals("UPDATE") || palabras[0].equals("update")) {
            conn.crearConex(usuario, password, bdd, puerto, servidor);
            try {
                conn.ejecutarSentenciasIDU(query);
                txtResultado.setText("Ciudad actualizada con exito" + "\r\n" + query);
            } catch (Exception e) {
                txtResultado.setText(e.toString());
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

}
