/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package entregable1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author DAM 2
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Button obtenerInformacion;
    @FXML
    private TextField textRuta;
    @FXML
    private Button crearCarpeta;
    @FXML
    private Button crearFichero;
    @FXML
    private Button eliminar;
    @FXML
    private Button renombrar;
    @FXML
    private TextArea textInfo;
    @FXML
    private TextField textRutaRenom;
    
    
    public void confirmacionCarpeta(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("");
        alert.setContentText("Directorio creado con éxito.");
        alert.showAndWait();
    }
    
    public void errorCarpeta(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText("No se pudo crear el directorio");
        alert.showAndWait();
    }
    
    public void confirmacionFichero(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("");
        alert.setContentText("Fichero creado con éxito.");
        alert.showAndWait();
    }
    
    public void errorFichero(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText("No se pudo crear el fichero");
        alert.showAndWait();
    }
    
    public void confirmacionEliminar(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("");
        alert.setContentText("Eliminado con éxito.");
        alert.showAndWait();
    }
    
    public void confirmacionRenombrar(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("");
        alert.setContentText("Renombrado con éxito.");
        alert.showAndWait();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("AE01_T1_1");
        alert.setContentText("Bienvenido al entregable de Miguel Angel");
        alert.showAndWait();
    
    }    

    @FXML
    private void obtenerInformacion(ActionEvent event) {
        
        File fichero = new File(textRuta.getText());
        if (fichero.exists()) {
            textInfo.setText("Nombre de Archivo: " + fichero.getName() + "\n" + "Es archivo: " + fichero.isFile() + "\n" + "Es directorio " + fichero.isDirectory() + "\n" +  "Data ultima modificacio: " + fichero.lastModified() + "\n" + "Absolute path: " + fichero.getAbsolutePath()+ "\n" + "Es oculto: " + fichero.isHidden() + "\n" + "Tamaño del archivo: " + fichero.length() + "\n" + "Tamaño usable del archivo: " + fichero.getUsableSpace());
        } else {
            System.out.println("El archivo no existe.");
        }
    }
     

    @FXML
    private void crearCarpeta(ActionEvent event) {
        
        File directorio = new File(textRuta.getText());
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                confirmacionCarpeta(event);
            } else {
                errorCarpeta(event);
            }
        }
    }

    @FXML
    private void crearFichero(ActionEvent event) throws IOException {
        
        File fichero = new File(textRuta.getText());
        if (!fichero.exists()) {
            if (fichero.createNewFile()) {
                confirmacionFichero(event);
            } else {
                errorFichero(event);
            }
        }
    }

    @FXML
    private void botonEliminar(ActionEvent event) {
        
        File fichero = new File(textRuta.getText());
        if(fichero.exists()) {
            fichero.delete();
            confirmacionEliminar(event);
        }else {
            while ((fichero.exists()) != true ){}
        }
        
    }

    @FXML
    private void botonRenombrar(ActionEvent event) {
        
        File fichero = new File(textRuta.getText());
        File ficheroRenombrado = new File (textRutaRenom.getText());
        
        boolean renombrado = fichero.renameTo(ficheroRenombrado);
        
        if (renombrado) {
            confirmacionRenombrar(event);
        } else {
            
        }
        
    }   
    
}
