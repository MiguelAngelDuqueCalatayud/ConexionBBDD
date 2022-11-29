/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ad.ae03;

import Model.Biblioteca;
import Model.Libro;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Óscar Martínez
 */
public class FXMLCrearLibroController implements Initializable {

    @FXML
    private AnchorPane rootPanel;
    @FXML
    private TextField tituloLib;
    @FXML
    private TextField autorLib;
    @FXML
    private TextField anyoLib;
    @FXML
    private TextField editorialLib;
    @FXML
    private TextField paginasLib;
    @FXML
    private Button botonAnadir;
    @FXML
    private Button botonVolver;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void anyadirLibro(ActionEvent event) {

        try {
            Libro lib = new Libro();

            lib.setTitulo(tituloLib.getText());
            lib.setAutor(autorLib.getText());
            lib.setAnyo(Integer.valueOf(anyoLib.getText()));
            lib.setEditorial(editorialLib.getText());
            lib.setnPag(Integer.valueOf(paginasLib.getText()));

            Biblioteca.crearLlibre(lib);

        } catch (Exception e) {
            System.out.print(e);
        }

    }

    @FXML
    private void volverMenu(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLMenu.fxml"));
            this.rootPanel.getChildren().setAll(root);
        } catch (IOException e) {
            System.out.print(e);
        }
    }

}
