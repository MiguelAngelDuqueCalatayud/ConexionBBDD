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
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Oscar Martinez
 */
public class FXMLMostrarTodoController implements Initializable {

    @FXML
    private TextArea resultado;

    ArrayList<Libro> libros = new ArrayList<>();
    @FXML
    private AnchorPane rootPanel;
    @FXML
    private Button botonVolver;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        libros = Biblioteca.leerXML();
        
        for(Libro l: libros){
            
            resultado.setText(resultado.getText()+l.getId()+"\r\n"
                                                  +l.getTitulo()+"\r\n");
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
