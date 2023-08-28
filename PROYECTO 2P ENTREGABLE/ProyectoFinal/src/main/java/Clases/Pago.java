/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

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
    private static int pagoNum = 1;

    public Pago(int idpedido, String nombreCLiente, double totalPagar,double iva,double valorTrj,double valorSinAdiciones, LocalDate fecha, String tipoPago) {
        this.idpago = pagoNum++;
        this.idpedido = idpedido;
        this.nombreCLiente = nombreCLiente;
        this.totalPagar = totalPagar;
        this.fecha = fecha;
        this.tipoPago = tipoPago;
        this.valorSinAdiciones=valorSinAdiciones;
        this.iva=iva;
        this.valorTrj=valorTrj;
    }

    public int getIdpago() {
        return idpago;
    }

    public int getIdpedido() {
        return idpedido;
    }

    public String getNombreCLiente() {
        return nombreCLiente;
    }

    public double getTotalPagar() {
        return totalPagar;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getTipoPago() {
        return tipoPago;
    }
    
    public double getIva() {
        return iva;
    }

    public double getValorTrj() {
        return valorTrj;
    }

    public double getValorSinAdiciones() {
        return valorSinAdiciones;
    }
    
    public String writePago(){
        return idpago+","+idpedido+","+nombreCLiente+","+totalPagar+","+fecha+","+tipoPago;
    }

    @Override
    public String toString() {
        return idpago + ","+ idpedido + "," + nombreCLiente + "," + totalPagar + "," + fecha + "," + tipoPago;
    }
    
    
}
