/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;


import java.util.ArrayList;
import java.util.Objects;

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
    
    public Usuario( String usuario, String contrasenia) {
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.usuario);
        hash = 89 * hash + Objects.hashCode(this.contrasenia);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario u = (Usuario) obj;
        if (!Objects.equals(this.usuario, u.usuario)) {
            return false;
        }
        return Objects.equals(this.contrasenia, u.contrasenia);
    }
    
    
    
    
    
}
