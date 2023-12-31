/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package espol.proyectofinal;

import Clases.Sabor;
import Clases.incompleteStageException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author AngelloVasconez
 */
public class ElegirSaboresController implements Initializable {

    @FXML
    private ComboBox<Sabor> sabor1;
    @FXML
    private ComboBox<Sabor> sabor2;
    @FXML
    private Label costoTotal;
    @FXML
    private Label mensajeException;
    @FXML
    private Button btnContinuar;
    
    double costoSabor1=0;
    double costoSabor2=0;
    double sumaTotal=0.00;
    ArrayList<Sabor> sabores= new ArrayList<>();
    ArrayList<ComboBox> cBoxes= new ArrayList<>();
    DecimalFormat df = new DecimalFormat("0.00");
    
    /**
     *Initialize.
     * @param url url
     * @param rb rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        costoTotal.setText("Valor a pagar: "+df.format(ElegirBaseController.costoPedido));
        cBoxes.add(sabor1);
        cBoxes.add(sabor2);
        leerDatosComboBox();
        continuar();
        for(ComboBox cb: cBoxes) {
            cb.setOnAction(evCbox);
        }
        
    }
    
    /**
     * Lee los datos del archivo sabores.txt, crea objetos de tipo Sabor, carga sus
     * valores en los objetos que se muestran en el programa y en un ArrayList de
     * tipo Sabor.
     */
    public void leerDatosComboBox(){
        try(BufferedReader archivo = new BufferedReader(new FileReader(InicioVentana.pathFiles+"sabores.txt"))){
            String datos;
            while ((datos=archivo.readLine())!=null){
                String[] elementos = datos.split(",");
                Sabor s = new Sabor(elementos[0],Double.parseDouble(elementos[1]));
                sabores.add(s);
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        Sabor snull = new Sabor("",0.00);
        sabores.add(snull);
        Collections.sort(sabores);
        sabor1.getItems().addAll(sabores);
        sabor2.getItems().addAll(sabores);
        sabor1.setValue(snull);
        sabor2.setValue(snull);
    }
    
    
    EventHandler evCbox = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) { 
            mensajeException.setText("");
            if (event.getSource() instanceof ComboBox) {
                ComboBox cbx = (ComboBox) event.getSource();

                if(cbx.equals(cBoxes.get(0))){
                        costoSabor1 =sabor1.getValue().getPrecio();
                }
                if(cbx.equals(cBoxes.get(1))){
                        costoSabor2 =sabor2.getValue().getPrecio();
                }

                
                sumaTotal = costoSabor1+costoSabor2;
                costoTotal.setText("Valor a pagar: "+df.format(ElegirBaseController.costoPedido+sumaTotal));
            }   
        }
    };
    
    /**
     * Cambia de la escena actual a la de Elegir Toppings y agrega la lista de sabores al
     * pedido.
     */
    public void continuar(){
        btnContinuar.setOnAction((ActionEvent e) -> {
            
            if ((sabor1.getValue().getSabor().isEmpty() && sabor2.getValue().getSabor().isEmpty()) ){
                try{
                        throw (new incompleteStageException());
                    }
                    catch(incompleteStageException iex){
                        mensajeException.setText(iex.getMessage());
                    }
            }
            else{
                try {
                    ArrayList<Sabor> saboresPedidos= new ArrayList<>();
                    
                    if( !sabor1.getValue().getSabor().isEmpty()){
                        saboresPedidos.add(sabor1.getValue());
                    }
                    if(!sabor2.getValue().getSabor().isEmpty()){
                        saboresPedidos.add(sabor2.getValue());
                    }
                    ElegirBaseController.costoPedido=ElegirBaseController.costoPedido+sumaTotal;
                    ElegirBaseController.pedido.setSabores(saboresPedidos);
                    InicioVentana.cambiarEscenasPedirPedidos("ElegirToppings.fxml",VentanaBienvenidaController.stage1,"Seleccion Toppings");   
                } 
                catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
    }
    
    
}
