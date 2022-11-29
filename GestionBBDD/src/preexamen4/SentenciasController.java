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
    private TextField txtQuery;
    @FXML
    private TextArea txtMostrar;
    @FXML
    private Button botonEjecutar;
    @FXML
    private Button botonVolver;

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
            Logger.getLogger(SentenciasController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SentenciasController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void hacerSentencia(ActionEvent event) {

        txtMostrar.setText("");
        String query = txtQuery.getText();
        String queryMinusculas = query.toLowerCase();
        String[] palabras = queryMinusculas.split(" ");

        conn.crearConexion(usuario, password, bdd, puerto, servidor);

        if (palabras[0].equals("select")) {
            datos = conn.ejecutarSelect(query);

            try {
                while (!datos.isLast()) {

                    datos.next();
                    for (int i = 1; i <= datos.getMetaData().getColumnCount(); i++) {
                        txtMostrar.setText(txtMostrar.getText() + datos.getString(i) + "\n");
                    }

                }
            } catch (SQLException ex) {
                Logger.getLogger(SentenciasController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (palabras[0].equals("insert")) {
            conn.ejecutarIDU(query);
            txtMostrar.setText("Ciudad agregada con exito");
        } else if (palabras[0].equals("delete")) {
            conn.ejecutarIDU(query);
            txtMostrar.setText("Ciudad borrada con exito");
        } else if (palabras[0].equals("update")) {
            conn.ejecutarIDU(query);
            txtMostrar.setText("Ciudad actualizada con exito");
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
