/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

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
    private Date fecha;
    private String tipoPago;

    public Pago(int idpago, int idpedido, String nombreCLiente, double totalPagar, Date fecha, String tipoPago) {
        this.idpago = idpago;
        this.idpedido = idpedido;
        this.nombreCLiente = nombreCLiente;
        this.totalPagar = totalPagar;
        this.fecha = fecha;
        this.tipoPago = tipoPago;
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

    public Date getFecha() {
        return fecha;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    @Override
    public String toString() {
        return idpago + ","+ idpedido + "," + nombreCLiente + "," + totalPagar + "," + fecha + "," + tipoPago;
    }
    
    
}
