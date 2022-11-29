/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package Entregable2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private Label label;
    @FXML
    private TextField pathTxt;
    @FXML
    private Button aceptarButtpn;
    private TextArea SalidaInfo1Txt;
    @FXML
    private TextField palabraBuscarTxt;
    @FXML
    private TextField palabraReemplazarTxt;
    @FXML
    private Button buscarButton;
    @FXML
    private Button reemplazarButton;
    private TextArea salidaInfoTxt2;
    @FXML
    private TextArea salidaTexto;
    @FXML
    private TextArea salidaTexto2;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }    

    @FXML
    private void handleButtonAceptar(ActionEvent event) throws IOException {
        SalidaInfo1Txt.setText("");
        File archivo = new File(pathTxt.getText());
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(archivo.getPath()));
            String texto = br.readLine();

            while (texto != null) {
                SalidaInfo1Txt.setText(SalidaInfo1Txt.getText() + "\n" + texto);
                texto = br.readLine();
            }

        } catch (FileNotFoundException ex) {
            SalidaInfo1Txt.setText("No existe el Fichero");
            ex.printStackTrace();
        }
    }

    @FXML
    private void handleButtonBuscar(ActionEvent event) throws FileNotFoundException, IOException {
        String palabra = palabraBuscarTxt.getText();
        File archivo = new File(pathTxt.getText());
        try{
            if(archivo.exists()){
                BufferedReader leerArchivo = new BufferedReader(new FileReader(archivo));
                
                String lineaLeida = SalidaInfo1Txt.getText();
                int lineasTotales = 0;
                int totalCoincidencias = 0;
                
                    
                     String[] palabras = lineaLeida.split(" ");
                     
                     for(int i = 0; i<palabras.length; i++){
                         
                         if(palabras[i].equals(palabra)){
                           totalCoincidencias = totalCoincidencias + 1;  
                                                                           
                         }
                        
                     }
                     salidaInfoTxt2.setText("Coincidencias: "+String.valueOf(totalCoincidencias));
            }
        }catch(Exception e){
            
        }
    }

    @FXML
    private void handleButtonReemplazar(ActionEvent event) {
        String palabra = palabraBuscarTxt.getText();
        File archivo = new File(pathTxt.getText());
        try{
            if(archivo.exists()){
                BufferedReader leerArchivo = new BufferedReader(new FileReader(archivo));
                
                String lineaLeida = SalidaInfo1Txt.getText();
                int lineasTotales = 0;
                int totalCoincidencias = 0;
                
                    
                     String[] palabras = lineaLeida.split(" ");
                     
                     for(int i = 0; i<palabras.length; i++){
                         
                         if(palabras[i].equals(palabra)){
                            salidaInfoTxt2.setText(salidaInfoTxt2.getText() + " " + palabraReemplazarTxt.getText());
                                                                           
                         }else{
                            salidaInfoTxt2.setText(salidaInfoTxt2.getText() + " " + palabras[i]); 
                         }
                        
                     }
                     
            }
        }catch(Exception e){
            
        }
        
        
        
        
    }
    
}
