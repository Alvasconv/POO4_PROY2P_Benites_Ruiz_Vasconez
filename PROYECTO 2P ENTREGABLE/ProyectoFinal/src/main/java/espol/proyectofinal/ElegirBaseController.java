/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package espol.proyectofinal;

import Clases.*;
import Clases.incompleteStageException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author AngelloVasconez
 */
public class ElegirBaseController implements Initializable {

    @FXML
    private ToggleButton base1;
    @FXML
    private ToggleButton base2;
    @FXML
    private ToggleButton base3;
    @FXML
    private ImageView imgBase1;
    @FXML
    private ImageView imgBase2;
    @FXML
    private ImageView imgBase3;
    @FXML
    private Label costoBase1;
    @FXML
    private Label costoBase2;
    @FXML
    private Label costoBase3;
    @FXML
    private Label costoTotal;
    @FXML
    private Label mensajeException;
    @FXML
    private Button btnContinuar;
    
    ArrayList<Base> bases = new ArrayList<>();
    ArrayList<ToggleButton> tgBotones = new ArrayList<>();
    Base baseSeleccionada;
    /**
     * Costo del pedido que va mostrando al usuario el precio que posee su pedido
     * hasta el momento dependiendo de sus elecciones.
     */
    public static Double costoPedido=0.00;
    /**
     * Pedido donde se añadiran los componentes escogidos por el usuario.
     */
    public static Pedido pedido=null;
    DecimalFormat df = new DecimalFormat("0.00");

    /**
     *Initialize.
     * @param url url
     * @param rb rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        leerDatosBase();
        for (ToggleButton tg: tgBotones){
            tg.setOnMouseClicked(tgb);
        }
        continuar();
    }    
    
    /**
     * Lee los datos del archivo bases.txt, crea objetos de tipo Base, carga sus
     * valores en los objetos que se muestran en el programa y en un ArrayList de
     * tipo Base.
     */
    public void leerDatosBase(){
        try(BufferedReader archivo = new BufferedReader(new FileReader(InicioVentana.pathFiles+"bases.txt"))){
            String datos;
            while((datos=archivo.readLine())!=null){
                String[] elementos = datos.split(",");
                Base b = new Base(elementos[0],Double.parseDouble(elementos[1]));
                bases.add(b);
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        //texto de las bases
        base1.setText(bases.get(0).getBase());
        base2.setText(bases.get(1).getBase());
        base3.setText(bases.get(2).getBase());
        //costo de las bases
        costoBase1.setText(df.format(bases.get(0).getPrecio()));
        costoBase2.setText(df.format(bases.get(1).getPrecio()));
        costoBase3.setText(df.format(bases.get(2).getPrecio()));
        //imagen de cada base
        InicioVentana.insertarImagen(imgBase1,"yogurt.jpg",90,100);
        InicioVentana.insertarImagen(imgBase2,"baseHelado.png",90,100);
        InicioVentana.insertarImagen(imgBase3,"vegano.png",90,100);
        //ArrayList de bases
        tgBotones.add(base1);
        tgBotones.add(base2);
        tgBotones.add(base3);
    }
    

    EventHandler tgb = new EventHandler<MouseEvent>(){
        @Override
        public void handle(MouseEvent t) {
            if(t.getSource() instanceof ToggleButton){
                mensajeException.setText("");
                ToggleButton tgboton = (ToggleButton) t.getSource();
                int indTgBoton = tgBotones.indexOf(tgboton);
                DecimalFormat df = new DecimalFormat("0.00");
                
                if(tgboton.isSelected()){
                    costoPedido=0.00;
                    costoPedido=costoPedido+bases.get(indTgBoton).getPrecio();
                    costoTotal.setText("Valor a pagar: "+df.format(costoPedido));
                    baseSeleccionada = bases.get(indTgBoton);
                    for (ToggleButton tg2: tgBotones){
                        if(!tg2.equals(tgboton)){
                            tg2.setSelected(false);
                        }
                    }                    
                }
                
                if(!tgboton.isSelected()){
                    costoPedido=costoPedido-bases.get(indTgBoton).getPrecio();
                    costoTotal.setText("Valor a pagar: "+df.format(costoPedido));
                    baseSeleccionada = null;  
                }               
            }
        }
    };
     
    /**
     * Cambia de la escena actual a la de Elegir Sabores y añade la Base seleccionada
     * al pedido.
     */
    public void continuar(){
        btnContinuar.setOnAction((ActionEvent e) -> {
            
            if (!base1.isSelected() && !base2.isSelected() && !base3.isSelected()){
                try{
                    throw (new incompleteStageException());
                }
                catch(incompleteStageException iex){
                    mensajeException.setText(iex.getMessage());
                }
            }
            else{
                try {
                    pedido = new Pedido(baseSeleccionada);
                    pedido.setNombre(InicioSesionController.usuario);
                    InicioVentana.cambiarEscenasPedirPedidos("ElegirSabores.fxml",VentanaBienvenidaController.stage1,"Seleccion Sabores");
                    
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
    }
}
