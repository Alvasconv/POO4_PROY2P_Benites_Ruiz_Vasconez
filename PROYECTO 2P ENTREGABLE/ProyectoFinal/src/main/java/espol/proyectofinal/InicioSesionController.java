/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package espol.proyectofinal;

import Clases.Usuario;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author Abeni
 */
public class InicioSesionController implements Initializable {

    /**
     * ArrayList de tipo Usuario que almacena todos los usuarios que pueden
     * acceder al programa.
     */
    public static ArrayList<Usuario> lstusuarios = new ArrayList<>();
    /**
     * Nombre del usuario que ha ingresado en el sistema.
     */
    public static String usuario = "";
    
    @FXML
    private TextField txusuario;
    @FXML
    private Button btsesion;
    @FXML
    private PasswordField txcontrasenia;
    @FXML
    private HBox vbaviso;
    @FXML
    private Pane rootsesion;
    
    
    /**
     * Initializes the controller class.
     * @param url url
     * @param rb rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarUsuarios();
        iniciarSesion();
        txusuario.setOnKeyTyped((KeyEvent e)->vbaviso.getChildren().clear());
        txcontrasenia.setOnKeyTyped((KeyEvent e)->vbaviso.getChildren().clear());
    }
    
    /**
     * Lee el archivo usuarios.txt, crea los usuarios y los añade a un ArrayList
     * de tipo Usuario.
     */
    public void cargarUsuarios(){
        try(BufferedReader br = new BufferedReader(new FileReader(InicioVentana.pathFiles+"usuarios.txt",StandardCharsets.UTF_8))){
            String linea= br.readLine();
            while (linea != null) {
                String p[]=linea.split(",");
                lstusuarios.add(new Usuario(p[0].trim(),p[1].trim(),p[2].trim(), p[3].trim()));
                linea= br.readLine();
            }
        }
        catch(FileNotFoundException ex){
            System.out.println("No se pudo encontrar el archivo");
        }
        catch(IOException e){
            System.out.println("ERROOOORRR.......");
        }
    }

    /**
     * compureba que las credenciales ingresadas por el usuario sean las correctas
     * y esten guardadas en el archivo usuarios.txt.
     */
    public void iniciarSesion(){
        
        btsesion.setOnMouseClicked((MouseEvent e)->{
            vbaviso.getChildren().clear();
            boolean entro = false;
            for(Usuario u:lstusuarios){
                if( u.equals( new Usuario(txusuario.getText(),txcontrasenia.getText()) ) ){
                    try {
                        usuario=u.getNombre();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("ventanaBienvenida.fxml"));
                        Parent root = loader.load();
                        VentanaBienvenidaController controller = loader.getController();
                        Stage stage = new Stage();
                        stage.setTitle("Nueva Ventana");
                        stage.setScene(new Scene(root));

                        // Cerrar la ventana principal
                        if(((Stage) btsesion.getScene().getWindow())!=null){   
                            Stage primaryStage = (Stage) btsesion.getScene().getWindow();
                            primaryStage.close();
                            stage.show();
                        }

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    VentanaBienvenidaController.ventanaGenerarPedidos();
                    VentanaBienvenidaController.actualizarVentanaPedidos();
                    entro=true;
                }
                    
            }
            if(entro==false){
                Label aviso = new Label("Datos ingresados erroneos");
                aviso.getStyleClass().add("texto");
                vbaviso.getChildren().add(aviso);
                txcontrasenia.clear();
                txusuario.clear();
            }
        });
        
    }
    


}
