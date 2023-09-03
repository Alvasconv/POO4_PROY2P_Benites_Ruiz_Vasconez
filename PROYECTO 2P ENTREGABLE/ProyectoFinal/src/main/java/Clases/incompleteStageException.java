/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author AngelloV
 */
public class incompleteStageException extends Exception{
    
    /**
     *Constructor de incompleteStageException.
     */
    public incompleteStageException(){
        super("Debe elegir una opción para continuar");
    }
}
