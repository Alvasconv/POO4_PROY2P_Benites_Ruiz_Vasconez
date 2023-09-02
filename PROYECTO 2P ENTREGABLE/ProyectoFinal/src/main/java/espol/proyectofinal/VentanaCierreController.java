/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package espol.proyectofinal;

import Clases.Pedido;
import static espol.proyectofinal.VentanaBienvenidaController.pedidosgenerados;
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
        
        Label lped= new Label ();
        lped.setStyle("-fx-text-fill: black;-fx-font-weight: bold; -fx-font-size: 15");
        try(FileInputStream input = new FileInputStream(InicioVentana.pathPhotos+"imgdespedida.gif")){
          
            Image image = new Image (input,100,300,true,false);
            imfinal.setImage(image);
            
        }catch(IOException e){
            System.out.println("error");
        }
        for (Pedido pe:pedidosgenerados){
            if (pe.getNombre().equals(InicioSesionController.usuario)){
                lped.setText("Tu pedido es el #"+String.valueOf(pe.getPedido())+"."+" Te llamaremos cuando este listo.");
            }
                
        }
        hbllamado.getChildren().add(lped);
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