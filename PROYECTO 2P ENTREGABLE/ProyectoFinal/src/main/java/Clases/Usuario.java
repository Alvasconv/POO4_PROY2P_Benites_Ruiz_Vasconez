/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;


import java.util.ArrayList;

/**
 *
 * @author Abeni
 */
public class Usuario {
    
    private String nombre;
    private String apellido;
    private String usuario;
    private String contrasenia;

    public Usuario(String nombre, String apellido, String usuario, String contrasenia) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }
    
    

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    @Override
    public String toString() {
        return "usuario = " + usuario + ", contrasenia = " + contrasenia;
    }
    

    
    
    
}
