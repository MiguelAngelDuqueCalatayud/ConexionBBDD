/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Model.Libro;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 *
 * @author Óscar Martínez
 */
public class Biblioteca {

    public static ArrayList<Libro> leerXML() {

        ArrayList<Libro> libros = new ArrayList<>();
        Libro lib = null;

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document document = dBuilder.parse(new File("libreriaOMR.xml"));
            document.getDocumentElement().normalize();

            //Element raiz = document.getDocumentElement();
            NodeList nL = document.getElementsByTagName("libro");
            for (int i = 0; i < nL.getLength(); i++) {
                Node n = nL.item(i);

                if (n.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) n;

                    lib = new Libro();
                    lib.setId(Integer.parseInt(eElement.getAttribute("id")));
                    lib.setTitulo(eElement.getElementsByTagName("titulo").item(0).getTextContent());
                    lib.setAutor(eElement.getElementsByTagName("autor").item(0).getTextContent());
                    lib.setAnyo(Integer.parseInt(eElement.getElementsByTagName("anyo").item(0).getTextContent()));
                    lib.setEditorial(eElement.getElementsByTagName("editorial").item(0).getTextContent());
                    lib.setnPag(Integer.parseInt(eElement.getElementsByTagName("nPag").item(0).getTextContent()));
                }
                libros.add(lib);
            }
        } catch (Exception e) {
            System.out.print(e);
        }
        return libros;
    }

    public static void actualizarXML(ArrayList<Libro> libros) throws TransformerException {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            DOMImplementation dImp = dBuilder.getDOMImplementation();

            Document doc = dImp.createDocument(null, "libros", null);
            doc.setXmlVersion("1.0");

            for (Libro l : libros) {
                Element libro = doc.createElement("libro");
                libro.setAttribute("id", String.valueOf(l.getId()));

                Element titulo = doc.createElement("titulo");
                Text tituloTN = doc.createTextNode(l.getTitulo());
                titulo.appendChild(tituloTN);
                libro.appendChild(titulo);

                Element autor = doc.createElement("autor");
                Text autorTN = doc.createTextNode(l.getAutor());
                autor.appendChild(autorTN);
                libro.appendChild(autor);

                Element anyo = doc.createElement("anyo");
                Text anyoTN = doc.createTextNode(String.valueOf(l.getAnyo()));
                anyo.appendChild(anyoTN);
                libro.appendChild(anyo);

                Element editorial = doc.createElement("editorial");
                Text editorialTN = doc.createTextNode(l.getEditorial());
                editorial.appendChild(editorialTN);
                libro.appendChild(editorial);

                Element nPags = doc.createElement("nPag");
                Text nPagsTN = doc.createTextNode(String.valueOf(l.getnPag()));
                nPags.appendChild(nPagsTN);
                libro.appendChild(nPags);

                doc.getDocumentElement().appendChild(libro);
            }

            Source src = new DOMSource(doc);
            Result res = new StreamResult(new File("libreriaOMR.xml"));

            Transformer trans = TransformerFactory.newInstance().newTransformer();
            trans.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            trans.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            trans.setOutputProperty(OutputKeys.INDENT, "yes");
            trans.transform(src, res);
            

        } catch (Exception e) {
            System.out.print(e);
        }

    }

    public static void borrarLlibre(int id) {

        ArrayList<Libro> libros = leerXML();
        boolean entroIF = false;

        for (Libro l : libros) {
            if (id == l.getId()) {
                entroIF = true;
                libros.remove(l);

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setTitle("Confirmacion");
                alert.setContentText("El libro se pudo borrar");
                alert.showAndWait();
            }
            try {
                actualizarXML(libros);
            } catch (TransformerException ex) {
                Logger.getLogger(Biblioteca.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (entroIF == false) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("El libro no se pudo borrar/no existe");
            alert.showAndWait();
        }

    }

    public static int crearLlibre(Libro libro) {
        
        try {
            ArrayList<Libro> libros = leerXML();
            Integer idMas = libros.get(libros.size() - 1).getId() + 1;
            libro.setId(idMas);
            libros.add(libro);
            try {
                actualizarXML(libros);
            } catch (TransformerException ex) {
                Logger.getLogger(Biblioteca.class.getName()).log(Level.SEVERE, null, ex);

            }

            
        } catch (Exception e) {
            System.out.println(e);
        }
     
        return libro.getId();
    }
    

}
