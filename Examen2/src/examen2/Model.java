/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Alex Santos
 */
public class Model {
    public static String lectura(File archivo) throws IOException{
        int caracter;
        String informationFile = "";
        try{
            FileReader leer = new FileReader(archivo);
            caracter = leer.read();
            while(caracter != -1){
                informationFile = informationFile + (char) caracter;
                caracter = leer.read();
            }
        } catch (IOException io){
            io.getStackTrace();
        }
        return informationFile;
    }
    public static int contadorCaracteres(String txt, String word) throws IOException{
        int contChar = 0;
        while(txt.indexOf(word) >- 1){
            txt = txt.substring(txt.indexOf(word) + word.length() , txt.length());
            contChar++;
        }
        return contChar;
    }
}
