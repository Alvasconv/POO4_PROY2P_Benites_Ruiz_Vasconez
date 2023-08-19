/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package espol.proyectofinal;

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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Abeni
 */
public class VentanaBienvenidaController implements Initializable {

    @FXML
    private Pane rootBienvenida;
    @FXML
    private Button btlocales;
    @FXML
    private Button btpedido;
    @FXML
    private Label lbienvenida;

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

    @FXML
    private void realizapedido(ActionEvent event) {
        
        btpedido.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("base.fxml"));
                    Parent root = loader.load();

                    BaseController controller = loader.getController();
                    Stage stage = new Stage();
                    stage.setTitle("Seleccion");
                    stage.setScene(new Scene(root));

                    //Cerrar la ventana principal
                    Stage primaryStage = (Stage) btpedido.getScene().getWindow();
                    primaryStage.close();
                    stage.show();

                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }

        });
        
    }
    
}
