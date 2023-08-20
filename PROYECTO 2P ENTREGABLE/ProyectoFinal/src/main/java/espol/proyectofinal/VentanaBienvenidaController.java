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
import java.util.ResourceBundle;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Abeni
 */
public class VentanaBienvenidaController implements Initializable {
    
    public static ListView<String> lviewPedidos = new ListView<>();
    public static int idpedido =0;

    @FXML
    private Pane rootBienvenida;
    @FXML
    private Button btlocales;
    @FXML
    private Button btpedido;
    @FXML
    private Label lbienvenida;
    public static Stage stage1 = new Stage();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbienvenida.setText(lbienvenida.getText()+", "+usuario);
    }    

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
    
//    public void continuar(){
//        btpedido.setOnMouseClicked((MouseEvent e) -> {     
//            try {
//                InicioVentana.setRoot("ElegirBase.fxml");
//            } 
//            catch (IOException ex) {
//                ex.printStackTrace();
//            }
//            
//        });
//    }

    @FXML
    private void realizapedido(ActionEvent event) {
        
        btpedido.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                
                try {
//                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Elegirbase.fxml"));
//                    Parent root = loader.load();
//
//                    ElegirBaseController controller = loader.getController();
//                    stage1 = new Stage();
//                    stage1.setTitle("Seleccion Base");
//                    stage1.setScene(new Scene(root));
                    InicioVentana.cambiarEscenasPedirPedidos("ElegirBase.fxml",VentanaBienvenidaController.stage1,"Seleccion Base");

                    //Cerrar la ventana principal
                    Stage primaryStage = (Stage) btpedido.getScene().getWindow();
                    primaryStage.close();
                    stage1.show();

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                
                ventanaGenerarPedidos();
            }

        });
        
    }
    
//    -------- este metodo hace que se abra la ventana que se actualiza en vivo cuando llega un nuevo pedido -------
//    -------- y la invoco dentro de mi metodo handle del metodo mostrar pedido de esta clase 
    
    public void ventanaGenerarPedidos(){
        Stage g = new Stage();

        lviewPedidos.setPrefWidth(300);
        lviewPedidos.setPrefHeight(250);
        Pane rootNuevo = new Pane();
        rootNuevo.getChildren().addAll(lviewPedidos);
//        ObservableList <String> items = FXCollections.observableArrayList(lpedidos);
//        lviewPedidos.setItems(items);
//        lviewPedidos.setDisable(false);
        

        Scene s = new Scene(rootNuevo, 300, 250);
        g.setScene(s);
        g.setTitle("Pedidos Generados");
        g.show();
      
    }
    
}
