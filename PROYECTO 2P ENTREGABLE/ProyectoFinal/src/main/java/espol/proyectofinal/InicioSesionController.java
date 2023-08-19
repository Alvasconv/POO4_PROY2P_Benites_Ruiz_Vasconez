/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package espol.proyectofinal;

import Clases.Base;
import Clases.Sabor;
import Clases.Topping;
import Clases.Usuario;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.layout.Pane;
/**
 * FXML Controller class
 *
 * @author Abeni
 */
public class InicioSesionController implements Initializable {

    public static ArrayList<Usuario> lstusuarios = new ArrayList<>();
    public static ArrayList<Base> lstbases = new ArrayList<>();
    public static ArrayList<Sabor> lstsabores = new ArrayList<>();
    public static ArrayList<Topping> lsttoppings = new ArrayList<>();
    

    //public static ArrayList<Usuario> lstusuario = new ArrayList<>();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarUsuarios();
        cargarBases();
        cargarSabores();
        cargarTopping();
        
        
    }   
    
    public void cargarUsuarios(){
        try(BufferedReader br = new BufferedReader(new FileReader(InicioVentana.pathFiles+"usuarios.txt",StandardCharsets.UTF_8))){
            String linea= br.readLine();
            while (linea != null) {
                //System.out.println(linea);
                String p[]=linea.split(",");
                lstusuarios.add(new Usuario(p[0].trim(),p[1].trim(),p[2].trim(), p[3].trim()));
                linea= br.readLine();
            }
            
        }catch(FileNotFoundException ex){
            System.out.println("No se pudo encontrar el archivo");
        }catch(IOException e){
            System.out.println("ERROOOORRR.......");
        }
    }
    
    public void cargarBases(){
        try(BufferedReader br = new BufferedReader(new FileReader(InicioVentana.pathFiles+"bases.txt",StandardCharsets.UTF_8))){
            String linea= br.readLine();
            while (linea != null) {
                //System.out.println(linea);
                String p[]=linea.split(",");
                lstbases.add(new Base(p[0].trim(),Double.valueOf(p[1])));
                linea= br.readLine();
            }
            
        }catch(FileNotFoundException ex){
            System.out.println("No se pudo encontrar el archivo");
        }catch(IOException e){
            System.out.println("ERROOOORRR.......");
        }
    }
    
    public void cargarSabores(){
        try(BufferedReader br = new BufferedReader(new FileReader(InicioVentana.pathFiles+"sabores.txt",StandardCharsets.UTF_8))){
            String linea= br.readLine();
            while (linea != null) {
                //System.out.println(linea);
                String p[]=linea.split(",");
                lstsabores.add(new Sabor(p[0].trim(),Double.valueOf(p[1])));
                linea= br.readLine();
            }
            
        }catch(FileNotFoundException ex){
            System.out.println("No se pudo encontrar el archivo");
        }catch(IOException e){
            System.out.println("ERROOOORRR.......");
        }
    }
    
    
    public void cargarTopping(){
        try(BufferedReader br = new BufferedReader(new FileReader(InicioVentana.pathFiles+"toppings.txt",StandardCharsets.UTF_8))){
            String linea= br.readLine();
            while (linea != null) {
                //System.out.println(linea);
                String p[]=linea.split(",");
                lsttoppings.add(new Topping(p[0].trim(),Double.valueOf(p[1])));
                linea= br.readLine();
            }
            
        }catch(FileNotFoundException ex){
            System.out.println("No se pudo encontrar el archivo");
        }catch(IOException e){
            System.out.println("ERROOOORRR.......");
        }
    }
    
    
    

}
