/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import espol.proyectofinal.InicioVentana;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author Abeni
 */
public class Pago {
    
    private int idpago;
    private int idpedido;
    private String nombreCLiente;
    private double totalPagar;
    private double iva;
    private double valorTrj;
    private double valorSinAdiciones;
    private LocalDate fecha;
    private String tipoPago;

    /**
     *Constructor Pago
     * @param idpedido id pedido
     * @param nombreCLiente nombreCLiente
     * @param totalPagar totalPagar
     * @param iva iva
     * @param valorTrj valorTrj
     * @param valorSinAdiciones valorSinAdiciones
     * @param fecha fecha
     * @param tipoPago tipoPago
     */
    public Pago(int idpedido, String nombreCLiente, double totalPagar,double iva,double valorTrj,double valorSinAdiciones, LocalDate fecha, String tipoPago) {
        this.idpago = numPagos()+1;
        this.idpedido = idpedido;
        this.nombreCLiente = nombreCLiente;
        this.totalPagar = totalPagar;
        this.fecha = fecha;
        this.tipoPago = tipoPago;
        this.valorSinAdiciones=valorSinAdiciones;
        this.iva=iva;
        this.valorTrj=valorTrj;
    }

    /**
     *getIdpago
     * @return idpago
     */
    public int getIdpago() {
        return idpago;
    }

    /**
     *getIdpedido
     * @return idpedido
     */
    public int getIdpedido() {
        return idpedido;
    }

    /**
     *getNombreCLiente
     * @return nombreCLiente
     */
    public String getNombreCLiente() {
        return nombreCLiente;
    }

    /**
     *getTotalPagar
     * @return totalPagar
     */
    public double getTotalPagar() {
        return totalPagar;
    }

    /**
     *getFecha
     * @return fecha
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     *getTipoPago
     * @return tipoPago
     */
    public String getTipoPago() {
        return tipoPago;
    }
    
    /**
     *getIva
     * @return iva
     */
    public double getIva() {
        return iva;
    }

    /**
     *getValorTrj
     * @return valorTrj
     */
    public double getValorTrj() {
        return valorTrj;
    }

    /**
     *getValorSinAdiciones
     * @return valorSinAdiciones
     */
    public double getValorSinAdiciones() {
        return valorSinAdiciones;
    }
    
    /**
     *writePago
     * @return idpago+","+idpedido+","+nombreCLiente+","+totalPagar+","+fecha+","+tipoPago
     */
    public String writePago(){
        return idpago+","+idpedido+","+nombreCLiente+","+totalPagar+","+fecha+","+tipoPago;
    }

    /**
     * metodo toString
     * @return idpago + ","+ idpedido + "," + nombreCLiente + "," + totalPagar + "," + fecha + "," + tipoPago
     */
    @Override
    public String toString() {
        return idpago + ","+ idpedido + "," + nombreCLiente + "," + totalPagar + "," + fecha + "," + tipoPago;
    }
    
        /**
     * Devuelve el valor mas alto en los numeros de pedidos registrados en pedidos.txt.
     * @return indPedidos
     */
    public static int numPagos(){
        int indPagos = 0;
        try(BufferedReader bfr = new BufferedReader(new FileReader(InicioVentana.pathFiles + "pagos.txt"))){
            String datos;
            while((datos=bfr.readLine())!=null){
                String[] elementos = datos.split(",");
                int ind = Integer.parseInt(elementos[0]);
                if(indPagos<=ind){
                    indPagos=ind;
                }
            }
        } catch (IOException | NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }
        return indPagos;
    }
    
}
