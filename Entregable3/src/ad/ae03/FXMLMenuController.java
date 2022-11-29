/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ad.ae03;

import Model.Libro;
import Model.Biblioteca;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Óscar Martínez
 */
public class FXMLMenuController implements Initializable {

    @FXML
    private AnchorPane rootPanel;
    @FXML
    private Button boton1;
    @FXML
    private Button boton2;
    @FXML
    private Button boton3;
    @FXML
    private Button boton4;
    @FXML
    private Button boton5;
    @FXML
    private Button boton6;

    ArrayList<Libro> libros = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void mostrarTodo(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLMostrarTodo.fxml"));
            this.rootPanel.getChildren().setAll(root);
        } catch (IOException e) {
            System.out.print(e);
        }
    }

    @FXML
    private void mostrarLibro(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLMostrarLibro.fxml"));
            this.rootPanel.getChildren().setAll(root);
        } catch (IOException e) {
            System.out.print(e);
        }
    }

    @FXML
    private void anadirLibro(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLCrearLibro.fxml"));
            this.rootPanel.getChildren().setAll(root);
        } catch (IOException e) {
            System.out.print(e);
        }
    }

    @FXML
    private void actualizarLibro(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLActualizarLibro.fxml"));
            this.rootPanel.getChildren().setAll(root);
        } catch (IOException e) {
            System.out.print(e);
        }
    }

    @FXML
    private void borrarLibro(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLBorrarLibro.fxml"));
            this.rootPanel.getChildren().setAll(root);
        } catch (IOException e) {
            System.out.print(e);
        }
    }

    @FXML
    private void cerrarBiblioteca(ActionEvent event) {
        System.exit(0);
    }

}
