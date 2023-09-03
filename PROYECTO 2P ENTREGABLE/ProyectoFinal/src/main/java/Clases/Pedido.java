/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Abeni
 */
public class Pedido implements Serializable,Pagable {
    private int pedido;
    private Base base;
    private ArrayList<Sabor> sabores;
    private ArrayList<Topping> toppings;
    private double total;
    private String nombre;
    private static int pedidoNum = 1;

    /**
     * Constructor Pedido
     * @param base base
     * @param sabores sabores
     * @param toppings toppings
     * @param nombre String nombre
     */
    public Pedido(Base base, ArrayList<Sabor> sabores, ArrayList<Topping> toppings, String nombre) {
        this.pedido = pedidoNum++;
        this.base = base;
        this.sabores = sabores;
        this.toppings = toppings;
        this.total = calcularTotal();
        this.nombre = nombre;
        
    }

    /**
     * Constructor Pedido
     * @param nombre nombre
     */
    public Pedido(String nombre) {
        this.pedido = pedidoNum++;
        this.nombre = nombre;
    }
    
    /**
     *Constructor Pedido
     * @param base base
     */
    public Pedido(Base base) {
        this.pedido = pedidoNum++;
        this.base = base;
        this.sabores = null;
        this.toppings = null;
        this.total = 0;
        this.nombre = null;
        
    }
    
    /**
     *detallarPedido
     * @return pedidoDetallado
     */
    public ArrayList<String> detallarPedido(){
        ArrayList<String> pedidoDetallado = new ArrayList<>();
        pedidoDetallado.add(this.base.mostrarDetalles());
        for(Sabor s: this.sabores){
            pedidoDetallado.add(s.mostrarDetalles());
        }
        for(Topping t: this.toppings){
            pedidoDetallado.add(t.mostrarDetalles());
        }
        return pedidoDetallado;
    }
    
    /**
     *calcularTotal
     * @return total
     */
    public double calcularTotal( ){
        this.total = base.getPrecio();
        for(Sabor s:sabores){
            this.total+=s.getPrecio();
        }
        for(Topping t :toppings){
            this.total+=t.getPrecio();
        }
        return total;
    }

    /**
     *getPedido
     * @return pedido
     */
    public int getPedido() {
        return pedido;
    }

    /**
     *getBase
     * @return base
     */
    public Base getBase() {
        return base;
    }

    /**
     *getSabores
     * @return sabores
     */
    public ArrayList<Sabor> getSabores() {
        return sabores;
    }

    /**
     *getToppings
     * @return toppings
     */
    public ArrayList<Topping> getToppings() {
        return toppings;
    }

    /**
     *getTotal
     * @return total
     */
    public double getTotal() {
        return total;
    }

    /**
     *getNombre
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *setPedido
     * @param pedido int
     */
    public void setPedido(int pedido) {
        this.pedido = pedido;
    }

    /**
     *setBase
     * @param base Base
     */
    public void setBase(Base base) {
        this.base = base;
    }

    /**
     *setSabores
     * @param sabor ArrayList Sabor
     */
    public void setSabores(ArrayList<Sabor> sabor) {
        this.sabores = sabor;
    }

    /**
     *setToppings
     * @param topping ArrayList Topping
     */
    public void setToppings(ArrayList<Topping> topping) {
        this.toppings = topping;
    }

    /**
     *setNombre
     * @param nombre string
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     *metodo toString
     * @return nombre +","+pedido
     */
    @Override
    public String toString() {
        return nombre +","+pedido ; 
    }
    
    /**
     *writePedido
     * @return pedido+","+nombre+","+total
     */
    public String writePedido() {
        return pedido+","+nombre+","+total ; 
    }
 
    /**
     *generarTransaccionE
     * @return p
     */
    @Override
    public Pago generarTransaccionE(){
        double valor=this.calcularTotal();
        int idpedido=this.pedido;
        String n=this.nombre;
        LocalDate fechaActual = LocalDate.now();
        String tipo="E";
        double adicional=0.0;
        double iva=(valor+adicional)*12/100;
        double t=valor+adicional+iva;
        Pago p=new Pago(idpedido,n ,t,iva,adicional,valor, fechaActual ,tipo);
        return p;
    }
    
    /**
     *generarTransaccionT
     * @return p
     */
    @Override
    public Pago generarTransaccionT(){
        double valor=this.calcularTotal();
        int idpedido=this.pedido;
        String n=this.nombre;
        LocalDate fechaActual = LocalDate.now();
        String tipo="C";
        double adicional=valor*10/100;
        double iva=(valor+adicional)*12/100;
        double t=valor+adicional+iva;
        Pago p=new Pago(idpedido,n ,t,iva,adicional,valor, fechaActual ,tipo);
        return p;
    }
}

