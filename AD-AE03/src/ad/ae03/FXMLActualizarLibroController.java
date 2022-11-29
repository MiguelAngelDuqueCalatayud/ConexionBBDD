/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ad.ae03;

import Model.Biblioteca;
import static Model.Biblioteca.leerXML;
import Model.Libro;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.xml.transform.TransformerException;

/**
 * FXML Controller class
 *
 * @author Óscar Martínez
 */
public class FXMLActualizarLibroController implements Initializable {

    @FXML
    private AnchorPane rootPanel;
    @FXML
    private Button botonVolver;
    @FXML
    private Button botonActualizar;
    @FXML
    private TextField textId;
    @FXML
    private TextField textTitulo;
    @FXML
    private TextField textAutor;
    @FXML
    private TextField textAnyo;
    @FXML
    private TextField textEditorial;
    @FXML
    private TextField textNpags;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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

    @FXML
    private void actualizarLibro(ActionEvent event) {

        ArrayList<Libro> libros = leerXML();
        int id = Integer.parseInt(textId.getText());
        boolean entroIF = false;

        for (Libro l : libros) {
            if (id == l.getId()) {
                entroIF = true;
                if (textTitulo.getText() != null) {
                    l.setTitulo(textTitulo.getText());
                }
                if (textAutor.getText() != null) {
                    l.setAutor(textAutor.getText());
                }
                if (textAnyo.getText() != null) {
                    l.setAnyo(Integer.parseInt(textAnyo.getText()));
                }
                if (textEditorial.getText() != null) {
                    l.setEditorial(textEditorial.getText());
                }
                if (textNpags.getText() != null) {
                    l.setnPag(Integer.parseInt(textNpags.getText()));
                }
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setTitle("Confirmacion");
                alert.setContentText("El libro se pudo actualizar");
                alert.showAndWait();
            }
        }
        if (entroIF == false) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("El libro no se pudo actualizar/no existe");
            alert.showAndWait();
        }

        try {
            Biblioteca.actualizarXML(libros);
        } catch (TransformerException ex) {
            Logger.getLogger(FXMLActualizarLibroController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
