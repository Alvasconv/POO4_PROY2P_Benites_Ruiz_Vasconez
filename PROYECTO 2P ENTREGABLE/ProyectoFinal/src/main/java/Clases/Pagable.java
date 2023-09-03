/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Clases;

/**
 *
 * @author vv
 */
public interface Pagable {
    
    /**
     *generarTransaccionE
     * @return pago
     */
    public abstract Pago generarTransaccionE();
    
    /**
     *generarTransaccionT
     * @return pago
     */
    public abstract Pago generarTransaccionT();
    
}
