/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.io.Serializable;
import java.text.DecimalFormat;

/**
 *
 * @author Abeni
 */
public class Topping implements Serializable{
    private String topping;
    private double precio;
    DecimalFormat df = new DecimalFormat("0.00");

    /**
     *
     * @param topping nombre topping
     * @param precio costo
     */
    public Topping(String topping, double precio) {
        this.topping = topping;
        this.precio = precio;
    }

    /**
     *getTopping
     * @return topping
     */
    public String getTopping() {
        return topping;
    }

    /**
     *getPrecio
     * @return precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     *metodo toString
     * @return topping
     */
    @Override
    public String toString() {
        return topping+" - "+df.format(precio);
    }
    
    /**
     *mostrarDetalles
     * @return mostrarDetalles
     */
    public String mostrarDetalles() {
        return "Topping: "+topping;
    }
    
}
