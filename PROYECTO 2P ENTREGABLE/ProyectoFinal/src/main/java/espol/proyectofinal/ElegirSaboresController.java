/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package espol.proyectofinal;

import Clases.Pedido;
import Clases.Sabor;
import Clases.incompleteStageException;
import static espol.proyectofinal.ElegirBaseController.pedido;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author AngelloV
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
    ArrayList<Sabor> sabores= new ArrayList<>();
    ArrayList<ComboBox> cBoxes= new ArrayList<>();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cBoxes.add(sabor1);
        cBoxes.add(sabor2);
        leerDatosComboBox();
        continuar();
        for(ComboBox cb: cBoxes) {
            cb.setOnAction(evCbox);
        }
    }
    
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
            ex.printStackTrace();
        }
        Sabor snull = null;
        sabores.add(snull);
        sabor1.getItems().addAll(sabores);
        sabor2.getItems().addAll(sabores);
    }
    
    
    EventHandler evCbox = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) { 
            mensajeException.setText("");
            if (event.getSource() instanceof ComboBox) {
                ComboBox cbx = (ComboBox) event.getSource();
                
                try{
                if(cbx.equals(cBoxes.get(0))){
                        costoSabor1 =sabor1.getValue().getPrecio();
                    }
                }
                catch(Exception ex){
                    costoSabor1 =0;
                }
                
                try{
                if(cbx.equals(cBoxes.get(1))){
                        costoSabor2 =sabor2.getValue().getPrecio();
                    }
                }
                catch(Exception ex){
                    costoSabor2 =0;
                }

                
                double sumaTotal = costoSabor1+costoSabor2;
                costoTotal.setText(""+sumaTotal);
            }   
        }
    };
    
    
    public void continuar(){
        btnContinuar.setOnAction((ActionEvent e) -> {
            
            if (sabor1.getValue()==null && sabor2.getValue()==null){
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
                    if(sabor1.getValue()!=null){
                        saboresPedidos.add(sabor1.getValue());
                    }
                    if(sabor2.getValue()!=null){
                        saboresPedidos.add(sabor2.getValue());
                    }
                    ElegirBaseController.pedido.setSabores(saboresPedidos);
                    
                    
                    InicioVentana.cambiarEscenasPedirPedidos("ElegirToppings.fxml",VentanaBienvenidaController.stage1,"Seleccion Toppings");   
                } 
                catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
    
    
}
