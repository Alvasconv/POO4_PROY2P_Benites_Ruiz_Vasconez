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

    /**
     * set coordenadas X
     * @param coordenadax coord en el eje horizontal
     */
    public void setCoordenadax(double coordenadax) {
        this.coordenadax = coordenadax;
    }

    /**
     * set coordenadas Y
     * @param coordenadaY coord en el eje horizontal
     */
    public void setCoordenadaY(double coordenadaY) {
        this.coordenadaY = coordenadaY;
    }

    /**'
     *setNombre
     * @param nombre nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     *setHorario
     * @param horario horario
     */
    public void setHorario(String horario) {
        this.horario = horario;
    }

    /**
     *getCoordenadax
     * @return coordenadax
     */
    public double getCoordenadax() {
        return coordenadax;
    }

    /**
     *getCoordenadaY
     * @return coordenadaY
     */
    public double getCoordenadaY() {
        return coordenadaY;
    }

    /**
     *getNombre
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *getHorario
     * @return horario
     */
    public String getHorario() {
        return horario;
    }
    double coordenadax;
    double coordenadaY;
    String nombre;
    String horario;
    
    /**
     * Constructor Local
     * @param cX coord X
     * @param cY coord Y
     * @param n nombre
     * @param h horario
     */
    public Local(double cX,double cY,String n,String h){
        this.coordenadax=cX;
        this.coordenadaY=cY;
        this.nombre=n;
        this.horario=h;
    }
    
    /**
     * metodo toString
     * @return nombre+";"+horario+";"+coordenadax+";"+coordenadaY
     */
    @Override
    public String toString(){
        return nombre+";"+horario+";"+coordenadax+";"+coordenadaY;
    }
}
