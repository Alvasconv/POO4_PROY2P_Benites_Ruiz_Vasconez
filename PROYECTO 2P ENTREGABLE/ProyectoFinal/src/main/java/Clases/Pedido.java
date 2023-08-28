/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.io.Serializable;
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

    public Pedido(Base base, ArrayList<Sabor> sabores, ArrayList<Topping> toppings, int total, String nombre) {
        this.pedido = pedidoNum++;
        this.base = base;
        this.sabores = sabores;
        this.toppings = toppings;
        calcularTotal();
        this.nombre = nombre;
        
    }

    public Pedido(String nombre) {
        this.pedido = pedidoNum++;
        this.nombre = nombre;
    }
    
    
    public Pedido(Base base) {
        this.pedido = pedidoNum++;
        this.base = base;
        this.sabores = null;
        this.toppings = null;
        this.total = 0;
        this.nombre = null;
        
    }
    
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
    
    public String writePedido() {
        return pedido+","+nombre+","+total ; 
    }

//    @Override
//    public Pago generarTransacción(boolean b) {
//        double valor=this.calcularTotal();
//        int idpedido=this.pedido;
//        String n=this.nombre;
//        LocalDate fechaActual = LocalDate.now();
//        String tipo;
//        double adicional;
//        double iva;
//        double t;
//        if(b==true){
//            tipo="C";
//            adicional=valor*10/100;
//            iva=(valor+adicional)*12/100;
//            t=valor+adicional+iva;
//        }else{
//            tipo="E";
//            adicional=0.0;
//            iva=(valor+adicional)*12/100;
//            t=valor+adicional+iva;
//        }
//        Pago p=new Pago(idpedido,n ,t,iva,adicional,valor, fechaActual ,tipo);
//        return p;
//    }
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

