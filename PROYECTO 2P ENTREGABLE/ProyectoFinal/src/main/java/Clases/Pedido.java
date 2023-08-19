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
public class Pedido {
    private int pedido;
    private Base base;
    private ArrayList<Sabor> sabor;
    private ArrayList<Topping> topping;
    private int total;
    private String nombre;

    public Pedido(int pedido, Base base, ArrayList<Sabor> sabor, ArrayList<Topping> topping, int total, String nombre) {
        this.pedido = pedido;
        this.base = base;
        this.sabor = sabor;
        this.topping = topping;
        this.total = total;
        this.nombre = nombre;
    }

    public int getPedido() {
        return pedido;
    }

    public Base getBase() {
        return base;
    }

    public ArrayList<Sabor> getSabor() {
        return sabor;
    }

    public ArrayList<Topping> getTopping() {
        return topping;
    }

    public int getTotal() {
        return total;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return pedido + "," + total + "," + nombre ;
    }
    
    
    
    
    
    
}
