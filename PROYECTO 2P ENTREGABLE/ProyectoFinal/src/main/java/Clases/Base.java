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
public class Base implements Serializable {
    private String base;
    private double precio;

    /**
     * Representa una Base.
     * @param base nombre base
     * @param precio costo
     */
    public Base(String base, double precio) {
        this.base = base;
        this.precio = precio;
    }

    /**
     * retorna el nombre de la base.
     * @return base
     */
    public String getBase() {
        return base;
    }

    /**
     *retorna el costo de la base.
     * @return precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     *retorna el nombre de la base dentro del metodo toString.
     * @return base
     */
    @Override
    public String toString() {
        return base;
    }
    
    /**
     *detalle base.
     * @return base
     */
    public String mostrarDetalles() {
        return "Base: "+base;
    }
    
}
