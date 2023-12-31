/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package espol.proyectofinal;

import Clases.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
    DecimalFormat df = new DecimalFormat("0.00");
    double sumaTotal=0;
    
    /**
     *Initialize.
     * @param url url
     * @param rb rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        costoTotal.setText("Valor a pagar: "+df.format(ElegirBaseController.costoPedido));
        cbGrupoL = new ArrayList<>(Arrays.asList(topping1, topping2, topping3, topping4, topping5, topping6,
                                                           topping7, topping8,topping9,topping10,topping11,topping12));
        cbGrupo =(ArrayList<CheckBox>) cbGrupoL;
        rellenarCheckBoxes();
        continuar();
        for(CheckBox ch: cbGrupo) {
            ch.setOnMouseClicked(evChb);
        }
    }    
    
    /**
     * Lee los datos del archivo toppings.txt, crea objetos de tipo Topping, carga sus
     * valores en los objetos que se muestran en el programa y en un ArrayList de
     * tipo Topping.
     */
    public void rellenarCheckBoxes(){
        try(BufferedReader archivo = new BufferedReader(new FileReader(InicioVentana.pathFiles+"toppings.txt"))){
            String datos;
            while((datos=archivo.readLine())!=null){
                String[] elementos = datos.split(",");
                Topping t = new Topping(elementos[0],Double.parseDouble(elementos[1]));
                toppings.add(t);
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        for(CheckBox cb: cbGrupo){
            cb.setText(toppings.get(cbGrupo.indexOf(cb)).toString());
        }
    }
    

    EventHandler evChb = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {  
            if (event.getSource() instanceof CheckBox) {
                CheckBox chb = (CheckBox) event.getSource();
                int indCbox = cbGrupo.indexOf(chb);
                double costo =toppings.get(indCbox).getPrecio();
      
                if(chb.isSelected()){
                    sumaTotal+=costo;                   
                }
                if(!chb.isSelected()){
                    sumaTotal-=costo;   
                }

                costoTotal.setText("Valor a pagar: "+df.format(ElegirBaseController.costoPedido+sumaTotal));
                
            }
        }
    };
   
    /**
     * Cambia de la escena actual a la de Resumen Pedido y añade la lista de Toppings
     * al pedido.
     */
    public void continuar(){
        btnContinuar.setOnAction((ActionEvent e) -> {
            try {
                ArrayList<Topping> toppingsPedidos= new ArrayList<>();
                for(CheckBox cb: cbGrupo){
                    if(cb.isSelected()){
                        int ind = cbGrupo.indexOf(cb);
                        toppingsPedidos.add(toppings.get(ind));
                    }
                }
                
                ElegirBaseController.pedido.setToppings(toppingsPedidos);
                InicioVentana.cambiarEscenasPedirPedidos("ResumenPedido.fxml",VentanaBienvenidaController.stage1,"Resumen del Pedido");
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        });
        
    }
    
    
}
