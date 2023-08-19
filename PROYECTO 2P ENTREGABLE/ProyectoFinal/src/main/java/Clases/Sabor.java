/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author Abeni
 */
public class Sabor {
    private String sabor;
    private double precio;

    public Sabor(String sabor, double precio) {
        this.sabor = sabor;
        this.precio = precio;
    }

    public String getSabor() {
        return sabor;
    }

    public double getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return sabor;
    }
    
    public String mostrarDetalles() {
        return sabor+" - "+precio;
    }
    
}
