/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ad.seccion3;

/**
 *
 * @author Óscar Martínez
 */
public class Piloto {
    
    private int id;
    private String nombre;
    private String apellido;
    private String abreviatura;
    private String escuderia;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getAbreviatura() {
        return abreviatura;
    }
    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }
    
    public String getEscuderia() {
        return escuderia;
    }
    public void setEscuderia(String escuderia) {
        this.escuderia = escuderia;
    }

    public Piloto() {
    }

    public Piloto(int id, String nombre, String apellido, String abreviatura, String escuderia) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.abreviatura = abreviatura;
        this.escuderia = escuderia;
    }
    
}
