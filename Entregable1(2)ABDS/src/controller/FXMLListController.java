/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Alex Santos
 */
public class FXMLListController implements Initializable {

    @FXML
    private Button buttonList;
    @FXML
    private TextField textFieldPath;
    @FXML
    private TextArea textAreaListado;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void listarContenido(ActionEvent event) {
        limpiarInformacion();
        File archivo = new File(textFieldPath.getText());
        File[] listaDir = archivo.listFiles();
        listarArchivo(listaDir);
    }

    private void listarArchivo(File[] listaDir) {
        
        for(File f: listaDir){
            textAreaListado.setText(textAreaListado.getText() + f.getName() + "\r\n");
            
            if(f.isDirectory()){
                textAreaListado.setText(textAreaListado.getText() + "Es un directorio.\r\n");
            } else if(f.isFile()){
                textAreaListado.setText(textAreaListado.getText() + "Es un fichero.\r\n");
            }
            
            if(f.isHidden()){
                textAreaListado.setText(textAreaListado.getText() + "Es un archivo oculto.\r\n");
            } else {
                textAreaListado.setText(textAreaListado.getText() + "No es un archivo oculto.\r\n");
            }
            
        }
    }

    private void limpiarInformacion() {
        textAreaListado.setText("");
    }
    
     
    
}
