/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author Julian Ruiz
 */
public class Local {

    public void setCoordenadax(double coordenadax) {
        this.coordenadax = coordenadax;
    }

    public void setCoordenadaY(double coordenadaY) {
        this.coordenadaY = coordenadaY;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public double getCoordenadax() {
        return coordenadax;
    }

    public double getCoordenadaY() {
        return coordenadaY;
    }

    public String getNombre() {
        return nombre;
    }

    public String getHorario() {
        return horario;
    }
    double coordenadax;
    double coordenadaY;
    String nombre;
    String horario;
    
    public Local(double cX,double cY,String n,String h){
        this.coordenadax=cX;
        this.coordenadaY=cY;
        this.nombre=n;
        this.horario=h;
    }
    
    public String toString(){
        return nombre+";"+horario+";"+coordenadax+";"+coordenadaY;
    }
}
