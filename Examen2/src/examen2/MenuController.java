/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package examen2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author Alex Santos
 */
public class MenuController implements Initializable {
    
    private Label label;
    @FXML
    private Button button;
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void buscarPalabras(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("buscarPalabras.fxml"));
        
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void contarVocales(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("contarVocales.fxml"));
        
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();        
    }

    @FXML
    private void leerEscribirArchivos(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("leerEscribir.fxml"));
        
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();            
    }
    
}
