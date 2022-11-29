/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Alex Santos
 */
public class FXMLMkdirController implements Initializable {

    @FXML
    private TextField textFieldRutaCrear;
    @FXML
    private TextField textFieldNumDir;
    @FXML
    private TextField textFieldNumFix;
    @FXML
    private Button buttonCreate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void crearDir(ActionEvent event) throws IOException {
        File archivo = new File(textFieldRutaCrear.getText());
        creacionDirectorio(archivo);
    }
    
    private void creacionDirectorio(File archivo) throws IOException {
        if(archivo.exists()){
            Alert ficheroAlertError = new Alert(Alert.AlertType.INFORMATION);
            ficheroAlertError.setHeaderText("¡DIRECTORIO EXISTENTE!");
            ficheroAlertError.setContentText("Borrando y creando....");
            ficheroAlertError.showAndWait();
            borrarArchivos(textFieldRutaCrear.getText());
            archivo.mkdirs();
            creacionDirectoriosInside(archivo);
            creacionFicherosInside(archivo);
        } else {
            Alert ficheroAlertInf = new Alert(Alert.AlertType.CONFIRMATION);
            ficheroAlertInf.setHeaderText("¡NO EXISTE!");
            ficheroAlertInf.setContentText("Creando Directorio...");
            ficheroAlertInf.showAndWait();
            archivo.mkdirs();
            creacionDirectoriosInside(archivo);
            creacionFicherosInside(archivo);
        }
    }

    private void creacionDirectoriosInside(File archivo) throws IOException {
        int numDirs = Integer.parseInt(textFieldNumDir.getText());
        for(int i = 0; i < numDirs; i++){
            File directorioNuevo = new File(archivo.getAbsolutePath(), "Alex" + i);
            directorioNuevo.mkdirs();
        }
    }

    private void creacionFicherosInside(File archivo) throws IOException {
        int numFiles = Integer.parseInt(textFieldNumFix.getText());
        for(int i = 0; i < numFiles; i++){
            File directorioNuevo = new File(archivo.getAbsolutePath(), "Santos" + i);
            directorioNuevo.createNewFile();
        }
    }

    private void borrarArchivos(String archivo) {
        File f = new File(archivo);
        File[] listaDir = f.listFiles();
        for (int i = 0; i < listaDir.length; i++) {
            if (listaDir[i].isFile()) {
                listaDir[i].delete();
            } else {
                this.borrarArchivos(listaDir[i].getAbsolutePath());
                listaDir[i].delete();
            }
        }
    }
    
    
    
}
