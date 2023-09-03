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

    /**
     *Constructor Usiario
     * @param nombre nombre
     * @param apellido apellido
     * @param usuario usuario
     * @param contrasenia contraseña
     */
    public Usuario(String nombre, String apellido, String usuario, String contrasenia) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }
    
    /**
     *getNombre
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *getApellido
     * @return apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     *getUsuario
     * @return usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     *getContrasenia
     * @return contrasenia
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     *Metodo toString
     * @return "usuario = " + usuario + ", contrasenia = " + contrasenia
     */
    @Override
    public String toString() {
        return "usuario = " + usuario + ", contrasenia = " + contrasenia;
    }
    

    
    
    
}
