/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author Abeni
 */
public class Topping {
    private String topping;
    private double precio;

    public Topping(String topping, double precio) {
        this.topping = topping;
        this.precio = precio;
    }

    public String getTopping() {
        return topping;
    }

    public double getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return topping;
    }
    
    public String mostrarDetalles() {
        return "Topping: "+topping;
    }
    
}
