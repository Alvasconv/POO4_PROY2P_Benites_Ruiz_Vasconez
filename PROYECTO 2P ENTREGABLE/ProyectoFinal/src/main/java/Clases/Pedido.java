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
    private ArrayList<Sabor> sabores;
    private ArrayList<Topping> toppings;
    private double total;
    private String nombre;
    private static int pedidoNum = 1;

    public Pedido(Base base, ArrayList<Sabor> sabores, ArrayList<Topping> toppings, int total, String nombre) {
        this.pedido = pedidoNum;
        this.base = base;
        this.sabores = sabores;
        this.toppings = toppings;
        this.total = calcularTotal(base, sabores, toppings);
        this.nombre = nombre;
        pedidoNum++;
    }

    public Pedido(String nombre) {
        this.pedido = pedidoNum++;
        this.nombre = nombre;
    }
    
    
    public Pedido(Base base) {
        this.pedido = pedidoNum;
        this.base = base;
        this.sabores = null;
        this.toppings = null;
        this.total = 0;
        this.nombre = null;
        pedidoNum++;
    }
    
    private double calcularTotal( Base base,ArrayList<Sabor> sabores, ArrayList<Topping> toppings){
        double total = base.getPrecio();
        for(Sabor s:sabores){
            total+=s.getPrecio();
        }
        for(Topping t :toppings){
            total+=t.getPrecio();
        }
        return total;
    }

    public int getPedido() {
        return pedido;
    }

    public Base getBase() {
        return base;
    }

    public ArrayList<Sabor> getSabores() {
        return sabores;
    }

    public ArrayList<Topping> getToppings() {
        return toppings;
    }

    public double getTotal() {
        return total;
    }

    public String getNombre() {
        return nombre;
    }

    public void setPedido(int pedido) {
        this.pedido = pedido;
    }

    public void setBase(Base base) {
        this.base = base;
    }

    public void setSabores(ArrayList<Sabor> sabor) {
        this.sabores = sabor;
    }

    public void setToppings(ArrayList<Topping> topping) {
        this.toppings = topping;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Override
    public String toString() {
        return nombre +","+pedido ;
        
    }
    
}

