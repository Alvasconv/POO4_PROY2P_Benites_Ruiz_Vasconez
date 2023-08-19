/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author Abeni
 */
public class Base {
    private String base;
    private double precio;

    public Base(String base, double precio) {
        this.base = base;
        this.precio = precio;
    }

    public String getBase() {
        return base;
    }

    public double getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return base;
    }
    
    public String mostrarDetalles() {
        return base+" - "+precio;
    }
    
}
