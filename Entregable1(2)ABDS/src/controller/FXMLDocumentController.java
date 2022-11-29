/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Alex Santos
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Button buttonMkdir;
    @FXML
    private Button buttonList;
    @FXML
    private Button buttonDelete;
    @FXML
    private TextField textFieldPathDelete;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void cambioCrearDirectorio(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/FXMLMkdir.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage1 = new Stage();
        
        stage1.setScene(scene);
        stage1.show();
    }

    @FXML
    private void cambioListarContenido(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/FXMLList.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage1 = new Stage();
        
        stage1.setScene(scene);
        stage1.show();
    }

    @FXML
    private void borrarContenido(ActionEvent event) {
        File archivo = new File(textFieldPathDelete.getText());
        borrarDirectorio(archivo);
    }

    private void borrarDirectorio(File archivo) {
        if(archivo.exists()){
               Alert ficheroAlertError = new Alert(Alert.AlertType.CONFIRMATION);
               ficheroAlertError.setHeaderText("¡DIRECTORIO EXISTENTE!");
               ficheroAlertError.setContentText("Borrando archivo...");
               ficheroAlertError.showAndWait();
               borrarArchivo(textFieldPathDelete.getText());
           } else {
               Alert ficheroAlertInf = new Alert(Alert.AlertType.ERROR);
               ficheroAlertInf.setHeaderText("¡NO EXISTE!");
               ficheroAlertInf.setContentText("No puedo borrar algo que no existe.");
               ficheroAlertInf.showAndWait();
           }
    }

    private void borrarArchivo(String ruta) {
        File f = new File(ruta);
        File[] listaFicheros = f.listFiles();
        for (int i = 0; i < listaFicheros.length; i++) {
            if (listaFicheros[i].isFile()) {
                listaFicheros[i].delete();
            } else {
                this.borrarArchivo(listaFicheros[i].getAbsolutePath());
                listaFicheros[i].delete();
            }
        }
        f.delete();//Borra carpeta final
    }   
    
}
