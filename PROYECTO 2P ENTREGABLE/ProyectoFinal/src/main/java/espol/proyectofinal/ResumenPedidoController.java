/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package espol.proyectofinal;

import Clases.Pedido;
import Clases.Sabor;
import static espol.proyectofinal.VentanaBienvenidaController.pedidosgenerados;
import static espol.proyectofinal.VentanaBienvenidaController.vpedidos;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import static javafx.scene.layout.HBox.setMargin;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author vv
 */
public class ResumenPedidoController implements Initializable {

    @FXML
    private AnchorPane rootpedido;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnConfirmar;
    @FXML
    private Button btnCancelar;
    @FXML
    private AnchorPane panelMensaje;
    @FXML
    private VBox vbPedido;

    @FXML
    private Label mensaje;
    @FXML
    private Label totalPagar;
    
    ArrayList<HBox> itemsPedido = new ArrayList<>();
    int indiceHB;
    DecimalFormat df = new DecimalFormat("0.00");
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenar();
        for(HBox h:itemsPedido){
            h.setOnMouseClicked(evvb);
        }
        totalPagar.setText("Total a pagar: "+df.format(ElegirBaseController.pedido.calcularTotal()));
        eliminar();
        btnCancelar.setOnMouseClicked(e->ventanaConfirmacion("cancelar Pedido"));
        btnConfirmar.setOnMouseClicked(e->confirmarPedido());
        
             
    }
    
    public void llenar(){
        for(String s:ElegirBaseController.pedido.detallarPedido()){
            HBox hb = new HBox();
            hb.getStyleClass().add("hbPedido");
            HBox.setMargin(hb, new Insets(5, 5, 5, 5));
            Label lb = new Label(s);
            hb.getChildren().add(lb);
            itemsPedido.add(hb);
            vbPedido.getChildren().add(hb);
        }
    }
    //Agrupa todos los vboxes de cada detalle del pedido
    EventHandler evvb = (EventHandler<MouseEvent>) (MouseEvent e) -> {
        mensaje.setText("");
        if(e.getSource() instanceof HBox){
            HBox hbx =(HBox)e.getSource();
            hbx.setStyle("-fx-background-color: skyblue");
            indiceHB = itemsPedido.indexOf(hbx);
            for(HBox h:itemsPedido){            
                if(!h.equals(hbx)){
                    h.setStyle("-fx-background-color: white");
                }
            }
        }
    };
    
    public void eliminar(){
        btnEliminar.setOnMouseClicked((MouseEvent e)->{
            String tipoPedido = ((Label)itemsPedido.get(indiceHB).getChildren().get(0)).getText();
            
            if(tipoPedido.startsWith("Sabor")){
                
                int cantSaboresPedido=0;
                for(HBox h:itemsPedido ){
                String itemP = ((Label)h.getChildren().get(0)).getText();
                    if(itemP.startsWith("Sabor")){
                        cantSaboresPedido++;
                    }
                }
                if(cantSaboresPedido==2){
                    ventanaConfirmacion("eliminar Sabor");
                }
                else{
                    mensaje.setText("No es posible eliminar mas Sabores");
                }    
            }
            
            if(tipoPedido.startsWith("Topping") || tipoPedido.startsWith("Base")){
                mensaje.setText("Solo puede eliminar algun sabor en su pedido");
            }
        });
    }
    
    public void ventanaConfirmacion(String caso){
        Stage stg1 = new Stage();
        VBox vb2= new VBox();
        HBox h2=new HBox();
        Label lb=new Label();
        Button bConfirmar = new Button("Confirmar");
        bConfirmar.setStyle("-fx-text-fill: black;-fx-font-weight: bold;-fx-background-color:#ECDD29");
        Button bCancelar = new Button("Cancelar");
        bCancelar.setStyle("-fx-text-fill: black;-fx-font-weight: bold;-fx-background-color:#ECDD29");
                   
        if(caso=="eliminar Sabor"){
            lb.setText("¿Esta seguro de eliminar el componente?");
            bConfirmar.setOnMouseClicked((MouseEvent e)->{
                ElegirBaseController.pedido.getSabores().remove(indiceHB-1);
                vbPedido.getChildren().remove(indiceHB);
                itemsPedido.remove(indiceHB);
                mensaje.setText("Sabor eliminado de su pedido");
                
                totalPagar.setText("Total a pagar: "+df.format(ElegirBaseController.pedido.calcularTotal()));
                stg1.close();
            });   
        }
        
        if(caso=="cancelar Pedido"){
            lb.setText("¿Esta seguro de cancelar su compra?");
            bConfirmar.setOnMouseClicked((MouseEvent e)->{
                ElegirBaseController.pedido=null;
                try {
                    InicioVentana.cambiarEscenasPedirPedidos("VentanaBienvenida.fxml",VentanaBienvenidaController.stage1,"Ventana Bienvenida");
                } catch (IOException ex) {
                   System.out.println(ex.getMessage());
                }
                stg1.close();
            });   
        }
        
        bCancelar.setOnMouseClicked((MouseEvent e)->{
            stg1.close();
        }); 
        
       h2.getChildren().addAll(bConfirmar,bCancelar);
        h2.setAlignment(Pos.CENTER);
        h2.setSpacing(20);
        h2.setLayoutX(30);
        h2.setLayoutX(48);
        vb2.getChildren().addAll(lb,h2);
        vb2.setAlignment(Pos.CENTER);
        vb2.setStyle("-fx-background-color:#DF9EF1");
        vb2.setSpacing(20);
        Scene sc = new Scene(vb2,300,200);
        stg1.initModality(Modality.APPLICATION_MODAL);
        stg1.setScene(sc);
        stg1.setTitle("Confirmar "+caso);
        stg1.show();
    }
    
    
    public void confirmarPedido(){
        try(BufferedWriter bfr = new BufferedWriter(new FileWriter(InicioVentana.pathFiles+"pedidos.txt"))){ 
        bfr.write(ElegirBaseController.pedido.writePedido()+"\n");
        } catch (IOException ex) {
            System.out.println("FALLO ESCRITURA EN AL ARCHIVO TXT PEDIDOS");
            System.out.println(ex.getMessage());
        }

        Thread th = new Thread(() -> {
            Platform.runLater(() -> {
            // aqui nomas falta agregar un true despues de la cadena pedidos.txt para que la informacion nunca se borre
            // no sabria si ponerlo o no ????

                try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(InicioVentana.pathFiles+"pedido"+ElegirBaseController.pedido.getPedido()+".bin"))){
                    oos.writeObject(ElegirBaseController.pedido);
                    VentanaBienvenidaController.pedidosgenerados.add(ElegirBaseController.pedido);
                }catch (FileNotFoundException fe){
                    System.out.println(fe.getMessage());
                } catch (IOException ex) {
                    System.out.println("FALLO SERIALIZACION DEL PEDIDO");
                    System.out.println(ex.getMessage());
                }
            });
            
        });
        th.setDaemon(true);
        th.start();

        try {
            InicioVentana.cambiarEscenasPedirPedidos("VentanaPago.fxml",VentanaBienvenidaController.stage1,"Ventana de pago");
        } catch (IOException ex) {
           System.out.println(ex.getMessage());
        }
    }
    
    
}
