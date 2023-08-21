/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package espol.proyectofinal;

import Clases.Local;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.Pane;
/**
 * FXML Controller class
 *
 * @author Abeni
 */
public class LocalesController implements Initializable {


    @FXML
    private Pane rootlocales;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mostrarLocales(cargarLocales());
    }    
    
    public ArrayList<Local> cargarLocales(){
        FileReader reader=null;
        BufferedReader br=null;
        ArrayList<Local> locales=new ArrayList<>();
        try{
            try{
            reader=new FileReader(InicioVentana.pathFiles+"locales.txt");
            }catch(FileNotFoundException e){
                System.out.println("Archivo no encontrado");
            }
            br= new BufferedReader(reader);
            String linea=br.readLine();
            while(linea!=null){
                String[] datos=linea.strip().split(",");
                double cX=Double.parseDouble(datos[0]);
                double cY=Double.parseDouble(datos[1]);
                String nombre=datos[2];
                String horario=datos[3];
                Local l=new Local(cX,cY,nombre,horario);
                linea=br.readLine();
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        return locales;
    }
    
    public void mostrarLocales(ArrayList<Local> locales){
        for(Local l:locales){
            ImageView inv=null;
            try{FileInputStream input=new FileInputStream(InicioVentana.pathPhotos+"helado.jpg");
            Image image=new Image(input);
            inv=new ImageView(image);
        }catch(IOException e){
                System.out.println(e.getMessage()); 
        }
            inv.setLayoutX(l.getCoordenadax());
            inv.setLayoutY(l.getCoordenadaY());
            rootlocales.getChildren().add(inv);
        }
    }
}

