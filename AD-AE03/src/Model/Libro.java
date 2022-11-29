/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Óscar Martínez
 */
public class Libro {
    
    private int id;
    private String titulo;
    private String autor;
    private int anyo;
    private String editorial;
    private int nPag;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnyo() {
        return anyo;
    }
    public void setAnyo(int anyo) {
        this.anyo = anyo;
    }

    public String getEditorial() {
        return editorial;
    }
    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getnPag() {
        return nPag;
    }
    public void setnPag(int nPag) {
        this.nPag = nPag;
    }

    public Libro(int id, String titulo, String autor, int anyo, String editorial, int nPag) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.anyo = anyo;
        this.editorial = editorial;
        this.nPag = nPag;
    }

    public Libro() {
    }
    
}
