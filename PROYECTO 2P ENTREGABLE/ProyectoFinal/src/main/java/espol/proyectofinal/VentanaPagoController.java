/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package espol.proyectofinal;

import Clases.Pago;
import Clases.incompleteStageException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
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
   Label ldetalleT = new Label("Ingrese los datos de su tarjeta");
   
   Label lnombreT = new Label();
   Label lnumeroT = new Label();
   Label lfechaT = new Label();
   Label lcvvT = new Label();
   
   TextField txnombreTarjeta = new TextField();
   TextField txnumeroTarjeta = new TextField();
   DatePicker dateTarjeta =new DatePicker();
   TextField txcvvTarjeta = new TextField();

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
                    l.setStyle("-fx-text-fill: black;-fx-font-weight: bold; -fx-font-size: 15");
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
                
                HBox h=new HBox();
                lnombreT.setText("Nombre:");
                lnombreT.setStyle("-fx-text-fill: black;-fx-font-weight: bold; -fx-font-size: 12");
                lnumeroT.setText("Numero:");
                lnumeroT.setStyle("-fx-text-fill: black;-fx-font-weight: bold; -fx-font-size: 12");
                lfechaT.setText("Fecha Caducidad:");
                lfechaT.setStyle("-fx-text-fill: black;-fx-font-weight: bold; -fx-font-size: 12");
                lcvvT.setText("CVV:");
                lcvvT.setStyle("-fx-text-fill: black;-fx-font-weight: bold; -fx-font-size: 12");
                VBox labels=new VBox();
                labels.setSpacing(15);
                labels.getChildren().addAll(lnombreT,lnumeroT,lfechaT,lcvvT);
                VBox textfields=new VBox();
                textfields.setSpacing(5);

                textfields.getChildren().addAll(txnombreTarjeta,txnumeroTarjeta,dateTarjeta,txcvvTarjeta);
                h.getChildren().addAll(labels,textfields);
                ldetalleT.setStyle("-fx-text-fill: #e460bf;-fx-font-weight: bold; -fx-font-size: 15");
                
                hbLabel.getChildren().addAll(ldetalleT,h);
                hbLabel.setSpacing(5);
                
             }
        });
    }
  
    @FXML
    public void confirmar(){
             
        if (trjcredito.isSelected()) {
            String nombreT = txnombreTarjeta.getText();
            String numeroT = txnumeroTarjeta.getText();
            LocalDate fecha = dateTarjeta.getValue();
            String cvv = txcvvTarjeta.getText();
            if (txnombreTarjeta.getText().isEmpty() || txnumeroTarjeta.getText().isEmpty() || txcvvTarjeta.getText().isEmpty()||fecha == null) {
            
                try {
                    throw new incompleteStageException();
                } catch (incompleteStageException inc) {
                    mostrarError(inc.getMessage());
                }
                
            }else{
                // aqui nomas falta agregar un true despues de la cadena pedidos.txt para que la informacion nunca se borre
                // no sabria si ponerlo o no ????
                try ( BufferedWriter br = new BufferedWriter(new FileWriter(InicioVentana.pathFiles + "pagos.txt"))) { 
                    br.write(ElegirBaseController.pedido.generarTransaccionT().writePago() + "\n");
                } catch (IOException e) {
                    System.out.println("FALLO ESCRITURA EN AL ARCHIVO TXT PAGOS");
                    System.out.println(e.getMessage());
                }
                try {
                    InicioVentana.cambiarEscenasPedirPedidos("ventanaCierre.fxml", VentanaBienvenidaController.stage1, "Ventana Cierre");
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
            

        }else if(efectivo.isSelected()){
            
            try ( BufferedWriter br = new BufferedWriter(new FileWriter(InicioVentana.pathFiles + "pagos.txt"))) {
                br.write(ElegirBaseController.pedido.generarTransaccionE().writePago() + "\n");
            } catch (IOException e) {
                System.out.println("FALLO ESCRITURA EN AL ARCHIVO TXT PAGOS");
                System.out.println(e.getMessage());
            }
            try {
                InicioVentana.cambiarEscenasPedirPedidos("ventanaCierre.fxml", VentanaBienvenidaController.stage1, "Ventana Cierre");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            
        }else if (trjcredito.isSelected()== false && efectivo.isSelected()== false){
            try {
                throw new incompleteStageException();
            } catch (incompleteStageException inc) {
                mostrarError(inc.getMessage());
            }
            
        }

    }
    
    private void mostrarError(String mensaje) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    
    @FXML
    public void cancelar(){
        /*
        cuando se da en cancelar al momento de que aparezca esa ventana flotante y le demos en confirmar cancelacion
        despues de que terminen de hacer la codificacion para eliminar ese pedido del archcivo txt y del archivo binario
        pueden poner esto ; para que se cierre todo el programa "Platform.exit()"
        */
     }
    
}
