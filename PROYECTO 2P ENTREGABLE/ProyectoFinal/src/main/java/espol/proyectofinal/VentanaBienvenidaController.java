/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package espol.proyectofinal;

import Clases.Pedido;
import static espol.proyectofinal.InicioSesionController.usuario;
import static espol.proyectofinal.InicioVentana.icon;
import java.io.File;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Abeni
 */
public class VentanaBienvenidaController implements Initializable {

    /**
     * ListView de los pedidos generados en el programa.
     */
    public static ListView<Pedido> lviewPedidos = new ListView<>();
    /**
     * VBox donde se muestran los pedidos generados.
     */
    public static VBox vpedidos;
    /**
     * Stage de la ventana emergente de los pedidos generados.
     */
    public static Stage g = new Stage();

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
     * PrimaryStage usado para cerrar el Stage actual de esta ventana y dar paso a la
     * ventana Elegir Base.
     */
    public static Stage primaryStage;

    /**
     * Initializes the controller class.
     * @param url url
     * @param rb rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbienvenida.setText(lbienvenida.getText()+", "+usuario);   
    }
    
    /**
     * Muetra la ventana donde se le inidica al usuario la ubicacion y horario de atencion
     * de cada uno de los locales.
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
                    Stage stage = new Stage();
                    stage.setTitle("Ubicaciones");
                    stage.setScene(new Scene(root));
                    File f = new File(InicioVentana.icon);
                    Image ix = new Image(f.toURI().toString());
                    stage.getIcons().add(ix);
                    stage.show();
                } 
                catch (IOException ex) {
                    System.out.println(ex.getMessage());
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
                    if(((Stage) btpedido.getScene().getWindow())!=null){
                        primaryStage = (Stage) btpedido.getScene().getWindow();
                        primaryStage.close();
                    }else{
                        primaryStage = new Stage();
                        primaryStage.close();
                    }
                    stage1.show();
                } 
                catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }); 
    }
    
    /**
     * Permite que se abra la ventana emergente que se actualiza en vivo cuando llega un nuevo pedido.
     */
    public static void ventanaGenerarPedidos(){    
        lviewPedidos.setPrefWidth(300);
        lviewPedidos.setPrefHeight(250);
        
        ObservableList <Pedido> items = FXCollections.observableArrayList(Pedido.pedidosGenerados());
        lviewPedidos.setItems(items);
        lviewPedidos.setDisable(false);
        vpedidos = new VBox ();
        vpedidos.getChildren().add(lviewPedidos);
        Pane rootNuevo = new Pane();
        rootNuevo.getChildren().addAll(vpedidos); 
      
        Scene s = new Scene(rootNuevo, 300, 250);
        
        g.setScene(s);
        g.setTitle("Pedidos Generados");
        g.show();
    }
    
    /**
     * Metodo encargado de realizar la actualizacion de la ventana de pedidos generados.
     */
    public static void actualizarVentanaPedidos(){
        
        Thread backgroundthread = new Thread(new Runnable() {

            public void run() {
                while (true) {
                    try {
                        Thread.sleep(5000);
                        Platform.runLater(() -> {                         
                            vpedidos.getChildren().clear();
                            ventanaGenerarPedidos();
                        });   
                    } 
                    catch (InterruptedException ex) {
                        System.out.println(ex.getMessage());
                    }
                }   
            }
        });
        backgroundthread.setDaemon(true);
        backgroundthread.start();
    }
    

}
