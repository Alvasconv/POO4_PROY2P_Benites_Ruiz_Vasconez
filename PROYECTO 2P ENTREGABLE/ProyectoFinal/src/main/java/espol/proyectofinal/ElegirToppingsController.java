/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package espol.proyectofinal;

import Clases.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author AngelloVasconez
 */
public class ElegirToppingsController implements Initializable {

    @FXML
    private CheckBox topping1;
    @FXML
    private CheckBox topping2;
    @FXML
    private CheckBox topping3;
    @FXML
    private CheckBox topping4;
    @FXML
    private CheckBox topping5;
    @FXML
    private CheckBox topping6;
    @FXML
    private CheckBox topping7;
    @FXML
    private CheckBox topping8;
    @FXML
    private CheckBox topping9;
    @FXML
    private CheckBox topping10;
    @FXML
    private CheckBox topping11;
    @FXML
    private CheckBox topping12;
    @FXML
    private Label costoTotal;
    @FXML
    private Button btnContinuar;
    
    ArrayList<Topping> toppings= new ArrayList<>();
    List<CheckBox> cbGrupoL;
    ArrayList<CheckBox> cbGrupo;
    
    double sumaTotal=0;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbGrupoL = new ArrayList<>(Arrays.asList(topping1, topping2, topping3, topping4, topping5, topping6,
                                                           topping7, topping8,topping9,topping10,topping11,topping12));
        cbGrupo =(ArrayList<CheckBox>) cbGrupoL;
        rellenarCheckBoxes();
        continuar();
        for(CheckBox ch: cbGrupo) {
            ch.setOnMouseClicked(evChb);
        }
    }    
    
    public void rellenarCheckBoxes(){
        try(BufferedReader archivo = new BufferedReader(new FileReader(InicioVentana.pathFiles+"toppings.txt"))){
            String datos;
            while((datos=archivo.readLine())!=null){
                String[] elementos = datos.split(",");
                Topping t = new Topping(elementos[0],Double.parseDouble(elementos[1]));
                toppings.add(t);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        for(CheckBox cb: cbGrupo){
            cb.setText(toppings.get(cbGrupo.indexOf(cb)).getTopping());
        }
    }
    

    EventHandler evChb = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {  
            if (event.getSource() instanceof CheckBox) {
                CheckBox chb = (CheckBox) event.getSource();
                int indCbox = cbGrupo.indexOf(chb);
                double costo =toppings.get(indCbox).getPrecio();
                DecimalFormat df = new DecimalFormat("0.00");
      
                if(chb.isSelected()){
                    sumaTotal+=costo;                   
                }
                if(!chb.isSelected()){
                    sumaTotal-=costo;   
                }
                if(sumaTotal<0.01){
                    costoTotal.setText("0");
                }else{
                    costoTotal.setText(df.format(sumaTotal));
                }
            }
        }
    };
   
    
    public void continuar(){
        btnContinuar.setOnMouseClicked((MouseEvent e) -> {      
            try {
                ArrayList<Topping> toppingsPedidos= new ArrayList<>();
                ElegirBaseController.pedido.setToppings(toppingsPedidos);
                InicioVentana.setRoot("ResumenPedido.fxml");
            } 
            catch (IOException ex) {
                ex.printStackTrace();
            }   
        });
    }
    
}
