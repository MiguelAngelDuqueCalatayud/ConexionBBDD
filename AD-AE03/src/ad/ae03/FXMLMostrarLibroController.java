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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Óscar Martínez
 */
public class FXMLMostrarLibroController implements Initializable {

    @FXML
    private AnchorPane rootPanel;
    @FXML
    private TextArea infoLibro;
    @FXML
    private TextField idTF;
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
    }

    @FXML
    private void mostrarLibro(ActionEvent event) {

        ArrayList<Libro> libros = Biblioteca.leerXML();
        int id = Integer.parseInt(idTF.getText());
        boolean entroIF = false;
        try {
            for (Libro l : libros) {
                if (id == l.getId()) {
                    entroIF = true;
                    infoLibro.setText("");
                    infoLibro.setText(infoLibro.getText() + "ID: " + Integer.toString(l.getId()) + "\n\r"
                            + "Titulo: " + l.getTitulo() + "\n\r"
                            + "Autor: " + l.getAutor() + "\n\r"
                            + "Año: " + Integer.toString(l.getAnyo()) + "\n\r"
                            + "Editorial: " + l.getEditorial() + "\n\r"
                            + "Número de páginas " + Integer.toString(l.getnPag()) + "\n\r");
                }
               
            }
            if (entroIF == false) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("El libro no se pudo encontrar/no existe");
                alert.showAndWait();
            }
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
