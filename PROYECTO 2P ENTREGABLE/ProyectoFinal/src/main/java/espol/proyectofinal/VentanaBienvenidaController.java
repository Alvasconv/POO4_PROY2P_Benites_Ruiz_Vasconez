/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package espol.proyectofinal;

import Clases.Pedido;
import Clases.incompleteStageException;
import static espol.proyectofinal.ElegirBaseController.pedido;
import static espol.proyectofinal.InicioSesionController.usuario;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Abeni
 */
public class VentanaBienvenidaController implements Initializable {
    // utilizar este arraylist para aqui agregar los pedidos que se vayan generando; recordar que la clase pedido tiene
    // varios constructores y cualquiera de estos puede recibir este arraylist pedidosgenerados
    /**
     * Utilizar este arraylist para aqui agregar los pedidos que se vayan generando.
     */
    public static ArrayList<Pedido> pedidosgenerados = new ArrayList<>();
    /**
     * ListView de los pedidos generados en el programa.
     */
    public static ListView<Pedido> lviewPedidos = new ListView<>();
    /**
     * VBox donde se muestran los pedidos generados.
     */
    public static VBox vpedidos = new VBox ();
    /**
     * Stage de la ventana emergente de los pedidos generados.
     */
    public static Stage g = new Stage();
    //public static int idpedido =0;
    //public static Pane rootNuevo = new Pane();

    @FXML
    private Pane rootBienvenida;
    @FXML
    private Button btlocales;
    @FXML
    private Button btpedido;
    @FXML
    private Label lbienvenida;
    /**
     * Stage usado para abrir la ventana de Elegir Base.
     */
    public static Stage stage1 = new Stage();

    /**
     * Initializes the controller class.
     * @param url url
     * @param rb rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbienvenida.setText(lbienvenida.getText()+", "+usuario);
        
//      estos son solo ejemplos de prueba para ver si mi ventana se actualizaba constantemente
        pedidosgenerados.add(new Pedido("Neymar"));
        pedidosgenerados.add(new Pedido("Iron Man"));
        pedidosgenerados.add(new Pedido("Messi"));
        pedidosgenerados.add(new Pedido("Ronaldo"));
        
        
        
    }    
    /**
     * Muetra los locales
     * @param event Tipo ActionEvent
     */
    @FXML
    private void mostrarLocales(ActionEvent event) {
        btlocales.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("locales.fxml"));
                    Parent root = loader.load();

                    //VentanaBienvenidaController controller = loader.getController();
                    Stage stage = new Stage();
                    stage.setTitle("Ubicaciones");
                    stage.setScene(new Scene(root));

                    // Cerrar la ventana principal
                    //Stage primaryStage = (Stage) btlocales.getScene().getWindow();
                    //primaryStage.close();
                    stage.show();

                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }

        });

    }
    
    /**
     * Da paso al usuario a realizar un pedido dirigiendolo a la venta de Elegir Base.
     * @param event Tipo ActionEvent
     */
    @FXML
    private void realizapedido(ActionEvent event) {
        
        btpedido.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                
                try {

                    InicioVentana.cambiarEscenasPedirPedidos("ElegirBase.fxml",VentanaBienvenidaController.stage1,"Seleccion Base");

                    //Cerrar la ventana principal
                    Stage primaryStage = (Stage) btpedido.getScene().getWindow();
                    primaryStage.close();
                    stage1.show();

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                
                ventanaGenerarPedidos();
                actualizarVentanaPedidos();
            }

        });
        
    }
    
//    -------- este metodo hace que se abra la ventana que se actualiza en vivo cuando llega un nuevo pedido -------
//    -------- y la invoco dentro de mi metodo handle del metodo mostrar pedido de esta clase 
    
    //public void ventanaGenerarPedidos(){
    /**
     * Permite que se abra la ventana emergente que se actualiza en vivo cuando llega un nuevo pedido.
     */
    public static void ventanaGenerarPedidos(){    
        lviewPedidos.setPrefWidth(300);
        lviewPedidos.setPrefHeight(250);
        
        ObservableList <Pedido> items = FXCollections.observableArrayList(pedidosgenerados);
        lviewPedidos.setItems(items);
        lviewPedidos.setDisable(false);
        vpedidos.getChildren().add(lviewPedidos);
        Pane rootNuevo = new Pane();
        rootNuevo.getChildren().addAll(vpedidos); 
      
        Scene s = new Scene(rootNuevo, 300, 250);
        g.setScene(s);
        g.setTitle("Pedidos Generados");
        g.show();
    }
    
    //public void actualizarVentanaPedidos(){
    /**
     * Metodo encargado de realizar la actualizacion de la ventana de pedidos generados.
     */
    public static void actualizarVentanaPedidos(){
        
        Thread backgroundthread = new Thread(new Runnable() {

            public void run() {
                while (true) {
                    try {
                        Thread.sleep(5000); 
                        System.out.println("hola");
                        Platform.runLater(() -> {                         
                            vpedidos.getChildren().clear();
                            ventanaGenerarPedidos();                       
                        });
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
                
            }

        });
        backgroundthread.setDaemon(true);
        backgroundthread.start();
    }
    

}
