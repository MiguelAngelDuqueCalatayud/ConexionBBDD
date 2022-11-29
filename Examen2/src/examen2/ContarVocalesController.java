/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package examen2;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Alex Santps
 */
public class ContarVocalesController implements Initializable {

    @FXML
    private TextArea textArea;
    @FXML
    private TextField pathOrigen;
    @FXML
    private Text numeroVecesVocales;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            File file = new File(pathOrigen.getText());
            textArea.setText(Model.lectura(file));
        } catch (IOException ioex){
            Logger.getLogger(null);
        }
    }  

    @FXML
    private void leerArchivoContar(ActionEvent event) throws IOException {
        numeroVecesVocales.setText("Número de veces que aparece la a: " + Model.contadorCaracteres(textArea.getText() , "a") + " e: " + Model.contadorCaracteres(textArea.getText() , "e") + " i: " + Model.contadorCaracteres(textArea.getText() , "i") + " o: " + Model.contadorCaracteres(textArea.getText() , "o") + " u: " + Model.contadorCaracteres(textArea.getText() , "u"));
    }
    
}
