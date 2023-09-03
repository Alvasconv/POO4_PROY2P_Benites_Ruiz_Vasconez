/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.io.Serializable;

/**
 *
 * @author Abeni
 */
public class Sabor implements Serializable{
    private String sabor;
    private double precio;

    /**
     *Constructor Sabor
     * @param sabor string
     * @param precio double
     */
    public Sabor(String sabor, double precio) {
        this.sabor = sabor;
        this.precio = precio;
    }

    /**
     *getSabor
     * @return sabor
     */
    public String getSabor() {
        return sabor;
    }

    /**
     *getPrecio
     * @return precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     *toString
     * @return sabor
     */
    @Override
    public String toString() {
        return sabor;
    }
    
    /**
     *mostrarDetalles
     * @return "Sabor: "+sabor
     */
    public String mostrarDetalles() {
        return "Sabor: "+sabor;
    }
    
}
