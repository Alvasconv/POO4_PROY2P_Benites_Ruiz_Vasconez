/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package espol.proyectofinal;

import Clases.Pago;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author vv
 */
public class VentanaPagoController implements Initializable {

   @FXML
   private AnchorPane rootPago;
   
   @FXML
   private Label lblPedido;
   
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        detallarPago(true);
    }    
    
    
    @FXML
    public void pagoTarjeta(){
        
    }
    
    public void detallarPago(boolean b){
        Pago p=ElegirBaseController.pedido.generarTransacción(b);
        valor.setText(Double.toString(p.getValorSinAdiciones()));
        iva.setText(Double.toString(p.getIva()));
        valortrj.setText(Double.toString(p.getValorTrj()));
        total.setText(Double.toString(p.getTotalPagar()));
        lblPedido.setText("Acercate a caja para que puedas pagar tu pedido");
    }
}
