/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package ad.seccion3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 *
 * @author Óscar Martínez
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private TextField textId;
    @FXML
    private TextField textNombre;
    @FXML
    private TextField textAbr;
    @FXML
    private TextField textApellido;
    @FXML
    private TextField textEscuderia;
    @FXML
    private Button botonLimpiar;
    @FXML
    private Button botonAnyadir;
    @FXML
    private Button botonAnt;
    @FXML
    private Button botonSig;
    @FXML
    private TextArea textoMostrar;
    @FXML
    private Button botonMostrar;
    @FXML
    private TextArea textoNodos;
    
    ArrayList<Piloto> pilotos = new ArrayList<>();
    Piloto pil = null;
    int listaPiloto = 0;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        //METODO LEER EL XML
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document document = dBuilder.parse(new File("pilotos.xml"));
            NodeList nL = document.getElementsByTagName("piloto");
            
            
            
            for (int i = 0; i < nL.getLength(); i++) {
                
                Piloto pil = new Piloto();
                Node n = nL.item(i);
                
                if (n.getNodeType() == n.ELEMENT_NODE) {
                    Element e = (Element) n;
                    pil.setId(Integer.parseInt(e.getAttribute("id")));
                    pil.setNombre(e.getElementsByTagName("nombre").item(0).getTextContent());
                    pil.setApellido(e.getElementsByTagName("apellido").item(0).getTextContent());
                    pil.setAbreviatura(e.getElementsByTagName("abreviatura").item(0).getTextContent());
                    pil.setEscuderia(e.getElementsByTagName("escuderia").item(0).getTextContent());
                }
                pilotos.add(pil);
                
                if (i == 0) {
                    textId.setText(Integer.toString(pil.getId()));
                    textNombre.setText(pil.getNombre());
                    textApellido.setText(pil.getApellido());
                    textAbr.setText(pil.getAbreviatura());
                    textEscuderia.setText(pil.getEscuderia());
                }
              
                textoNodos.setText("Total de Pilotos: " + nL.getLength());
                
            }            
        } catch (Exception e) {
            System.out.print(e);
        }
       
    }
    
    @FXML
    private void mostrarXML(ActionEvent event) throws FileNotFoundException, IOException {
        
        File xml = new File("Pilotos.xml");
        FileReader xmlLeer = new FileReader(xml.getAbsolutePath());
        int cLeido = xmlLeer.read();
        while(cLeido != -1) {
            char c = (char) cLeido;
            textoMostrar.setText(textoMostrar.getText() + c);
            cLeido = xmlLeer.read();
        }
    }

    @FXML
    private void anyadirPiloto(ActionEvent event) {
        
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document document = dBuilder.parse(new File("pilotos.xml"));
            NodeList nL = document.getElementsByTagName("piloto");
            
            Element piloto = document.createElement("piloto");
            piloto.setAttribute("id", String.valueOf(textId.getText()));
            
            Element nombre = document.createElement("nombre");
            nombre.setTextContent(textNombre.getText());
            piloto.appendChild(nombre);

            Element apellido = document.createElement("apellido");
            apellido.setTextContent(textApellido.getText());
            piloto.appendChild(apellido);
            
            Element abreviatura = document.createElement("abreviatura");
            abreviatura.setTextContent(textAbr.getText());
            piloto.appendChild(abreviatura);
            
            Element escuderia = document.createElement("escuderia");
            escuderia.setTextContent(textEscuderia.getText());
            piloto.appendChild(escuderia);
            
            document.getDocumentElement().appendChild(piloto);
            
            textoNodos.setText("Total de Pilotos: " + nL.getLength());
            
            
            Source src = new DOMSource(document);
            Result res = new StreamResult(new File("pilotos.xml"));
            Transformer trans = TransformerFactory.newInstance().newTransformer();
            trans.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            trans.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            trans.setOutputProperty(OutputKeys.INDENT, "yes");
            trans.transform(src, res);
            

            
        } catch (Exception e) {
            System.out.print(e);
        }
        
    }

    @FXML
    private void irAnterior(ActionEvent event) {
        
        listaPiloto--;
        
        if (listaPiloto >= 0) {
            textId.setText(Integer.toString(pilotos.get(listaPiloto).getId()));
            textNombre.setText(pilotos.get(listaPiloto).getNombre());
            textApellido.setText(pilotos.get(listaPiloto).getApellido());
            textAbr.setText(pilotos.get(listaPiloto).getAbreviatura());
            textEscuderia.setText(pilotos.get(listaPiloto).getEscuderia());
        } else {
            listaPiloto = pilotos.size();
            textId.setText(Integer.toString(pilotos.get(listaPiloto).getId()));
            textNombre.setText(pilotos.get(listaPiloto).getNombre());
            textApellido.setText(pilotos.get(listaPiloto).getApellido());
            textAbr.setText(pilotos.get(listaPiloto).getAbreviatura());
            textEscuderia.setText(pilotos.get(listaPiloto).getEscuderia());
        }
        
    }

    @FXML
    private void irPosterior(ActionEvent event) {
        
        listaPiloto++;
        
        if (listaPiloto < pilotos.size()) {
            textId.setText(Integer.toString(pilotos.get(listaPiloto).getId()));
            textNombre.setText(pilotos.get(listaPiloto).getNombre());
            textApellido.setText(pilotos.get(listaPiloto).getApellido());
            textAbr.setText(pilotos.get(listaPiloto).getAbreviatura());
            textEscuderia.setText(pilotos.get(listaPiloto).getEscuderia());
        } else {
            listaPiloto = 0;
            textId.setText(Integer.toString(pilotos.get(listaPiloto).getId()));
            textNombre.setText(pilotos.get(listaPiloto).getNombre());
            textApellido.setText(pilotos.get(listaPiloto).getApellido());
            textAbr.setText(pilotos.get(listaPiloto).getAbreviatura());
            textEscuderia.setText(pilotos.get(listaPiloto).getEscuderia());
        }
        
    }
    
    @FXML
    private void limpiarTextos(ActionEvent event) {
        textId.setText("");
        textNombre.setText("");
        textApellido.setText("");
        textAbr.setText("");
        textEscuderia.setText("");
    }

    
}
