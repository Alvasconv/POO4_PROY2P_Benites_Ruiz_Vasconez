/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import espol.proyectofinal.InicioVentana;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

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

    /**
     * Constructor Pedido
     * @param base base
     * @param sabores sabores
     * @param toppings toppings
     * @param nombre String nombre
     */
    public Pedido(Base base, ArrayList<Sabor> sabores, ArrayList<Topping> toppings, String nombre) {
        this.pedido = numPedidos()+1;
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
        this.pedido = numPedidos()+1;
        this.nombre = nombre;
    }
    
    /**
     *Constructor Pedido
     * @param base base
     */
    public Pedido(Base base) {
        this.pedido = numPedidos()+1;
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
     * @return numero del pedido
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

    /**
     * Metodo equals
     * @param pe objeto comparado
     * @return boolean
     */
    @Override
    public boolean equals(Object pe) {
        if (pe==null){
            return false;
        }
        if (pe.getClass() != this.getClass()){
            return false;
        }
        final Pedido p = (Pedido) pe;
        return (p.pedido==this.pedido && p.nombre==this.nombre && p.total==this.total);
    }

    /**
     * hashCode
     * @return hash
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.pedido;
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.total) ^ (Double.doubleToLongBits(this.total) >>> 32));
        hash = 79 * hash + Objects.hashCode(this.nombre);
        return hash;
    }
    
    /**
     * Devuelve el valor mas alto en los numeros de pedidos registrados en pedidos.txt.
     * @return indPedidos
     */
    public static int numPedidos(){
        int indPedidos = 0;
        try(BufferedReader bfr = new BufferedReader(new FileReader(InicioVentana.pathFiles + "pedidos.txt"))){
            String datos;
            while((datos=bfr.readLine())!=null){
                String[] elementos = datos.split(",");
                int ind = Integer.parseInt(elementos[0]);
                if(indPedidos<=ind){
                    indPedidos=ind;
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return indPedidos;
    }

    /**
     * Genera una lista de tipo pedido con todos los pedidos registrados en el sistema que han
     * sigo seralizados, los deserializa y los añade al ArrayList.
     * @return pedidos
     */
    public static ArrayList<Pedido> pedidosGenerados(){
        ArrayList<Pedido> pedidos = new ArrayList<>();
        
        for(int i=1;i<=Pedido.numPedidos();i++){
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(InicioVentana.pathFiles + "pedido" +i+ ".bin"))) {
                Pedido p = (Pedido) ois.readObject();
                pedidos.add(p);
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println(ex.getMessage());
                System.out.println("No hay archivo de pedido "+i+".bin");
            }
        }
        return pedidos;
    }
}

