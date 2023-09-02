/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package espol.proyectofinal;

import Clases.Pago;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author vv
 */
public class VentanaPagoController implements Initializable {

   @FXML
   private AnchorPane rootPago;
   
   @FXML
   private AnchorPane rootDetalles;
   
   @FXML
   private RadioButton efectivo;
   
   @FXML
   private RadioButton trjcredito;
   
   @FXML
   private TextField valor ;
   
   @FXML
   private TextField iva ;
   
   @FXML
   private TextField valortrj;
   
   @FXML
   private TextField total;
      
   @FXML
   private Button btnConfirmar;
   
   @FXML
   private Button btnCancelar;
   
   @FXML
   private VBox hbLabel;
    @FXML
    private ToggleGroup opciones;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        detallarPago();
    }    
    
 
    
    public void detallarPago(){
        Pago p=ElegirBaseController.pedido.generarTransaccionE();
        valor.setText(Double.toString(p.getValorSinAdiciones()));
        iva.setText(Double.toString(p.getIva()));
        valortrj.setText(Double.toString(p.getValorTrj()));
        total.setText(Double.toString(p.getTotalPagar()));
        
        efectivo.setOnMouseClicked(new EventHandler <MouseEvent>(){
           @Override
           public void handle(MouseEvent m){
               Pago p=ElegirBaseController.pedido.generarTransaccionE();
                valor.clear();
                iva.clear();
                valortrj.clear();
                total.clear();
                valor.setText(Double.toString(p.getValorSinAdiciones()));
                iva.setText(Double.toString(p.getIva()));
                valortrj.setText(Double.toString(p.getValorTrj()));
                total.setText(Double.toString(p.getTotalPagar()));
                hbLabel.getChildren().clear();
                if(efectivo.isSelected()){
                    Label l=new Label();
                    l.setText("Acercate a caja para que puedas pagar tu pedido");
                    hbLabel.getChildren().add(l);
                }
           }
        });
        
        trjcredito.setOnMouseClicked(new EventHandler <MouseEvent>(){
           @Override
           public void handle(MouseEvent m){
               Pago p=ElegirBaseController.pedido.generarTransaccionT();
               valor.clear();
                iva.clear();
                valortrj.clear();
                total.clear();
                valor.setText(Double.toString(p.getValorSinAdiciones()));
                iva.setText(Double.toString(p.getIva()));
                valortrj.setText(Double.toString(p.getValorTrj()));
                total.setText(Double.toString(p.getTotalPagar()));
                hbLabel.getChildren().clear();
                
                Label lab=new Label("Inserte los datos de su tarjeta:");
                HBox h=new HBox();
                Label name=new Label("Nombre:");
                Label num=new Label("Numero:");
                Label Fecha=new Label("Fecha Caducidad:");
                Label cvv=new Label("CVV");
                VBox labels=new VBox();
                labels.getChildren().addAll(name,num,Fecha,cvv);
                VBox textfields=new VBox();
                TextField tname=new TextField();
                TextField tnum=new TextField();
                TextField tcvv=new TextField();
                DatePicker date=new DatePicker();
                textfields.getChildren().addAll(tname,tnum,date,tcvv);
                h.getChildren().addAll(labels,textfields);
                
                hbLabel.getChildren().addAll(lab,h);
                
             }
        });
    }
  
    @FXML
    public void confirmar(){
        try(BufferedWriter br=new BufferedWriter(new FileWriter(InicioVentana.pathFiles+"pagos.txt"))){
            br.write(ElegirBaseController.pedido.generarTransaccionT().writePago()+"\n");
        }catch(IOException e){
            System.out.println("FALLO ESCRITURA EN AL ARCHIVO TXT PAGOS");
            System.out.println(e.getMessage());
        }
        try {
            InicioVentana.cambiarEscenasPedirPedidos("VentanaBienvenida.fxml",VentanaBienvenidaController.stage1,"Ventana Bienvenida");
        } catch (IOException ex) {
           System.out.println(ex.getMessage());
        }
    }
    
    @FXML
    public void cancelar(){
        
     }
    
}
