/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ad.ae03;

import Model.Biblioteca;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Óscar Martínez
 */
public class FXMLBorrarLibroController implements Initializable {

    @FXML
    private TextField idTF;
    @FXML
    private Button botonVolver;
    @FXML
    private Button botonBorrar;
    @FXML
    private AnchorPane rootPanel;

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
    private void borrarLibro(ActionEvent event) {
        
        int id = Integer.parseInt(idTF.getText());
        Biblioteca.borrarLlibre(id);
    }
    
}
