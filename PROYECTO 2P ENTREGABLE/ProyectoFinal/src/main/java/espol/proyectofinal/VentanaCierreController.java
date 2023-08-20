/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package espol.proyectofinal;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
/**
 * FXML Controller class
 *
 * @author Abeni
 */
public class VentanaCierreController implements Initializable {


    @FXML
    private Pane rootcierre;
    @FXML
    private Label lbdespedida;
    @FXML
    private HBox hbllamado;
    @FXML
    private HBox hbconteo;
    @FXML
    private Label lbconteo;
    @FXML
    private ImageView imfinal;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try(FileInputStream input = new FileInputStream(InicioVentana.pathPhotos+"imgdespedida.gif")){
          
            Image image = new Image (input,100,300,true,false);
            imfinal.setImage(image);
            
        }catch(IOException e){
            System.out.println("error");
        }
        
        iniciarTarea();
        
    }    
    
    public void ejecutarTarea(){
        
        
        for(int i=5;i>=0;i--){
            
           
            String status="Esta ventana se cerrara en "+i+" segundos...";

            Platform.runLater(new Runnable(){
                @Override
                public void run(){

                   lbconteo.setText(status);
                   
                }
            
            
            });
            
            try {
                Thread.sleep(1000);
                
                
            } catch (InterruptedException ex) {
                System.out.println("Thread defectuoso");
            }
            
            
        }
        Platform.exit();
    } 
    
    public void iniciarTarea(){
        
        Thread threadconteo=new Thread(new Runnable(){
            
            public void run(){
                ejecutarTarea();
            }
        });
        
        threadconteo.setDaemon(true);
        
        threadconteo.start();
        
        
       
    
    }
}