/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package espol.proyectofinal;

import Clases.Base;
import Clases.Sabor;
import Clases.Topping;
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
//    public static ArrayList<Base> lstbases = new ArrayList<>();
//    public static ArrayList<Sabor> lstsabores = new ArrayList<>();
//    public static ArrayList<Topping> lsttoppings = new ArrayList<>();
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
    
    
    //public static ArrayList<Usuario> lstusuario = new ArrayList<>();
    /**
     * Initializes the controller class.
     * @param url url
     * @param rb rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarUsuarios();
//        cargarBases();
//        cargarSabores();
//        cargarTopping();
        
        
    }   
    /**
     * Lee el archivo usuarios.txt, crea los usuarios y los añade a un ArrayList
     * de tipo Usuario.
     */
    public void cargarUsuarios(){
        try(BufferedReader br = new BufferedReader(new FileReader(InicioVentana.pathFiles+"usuarios.txt",StandardCharsets.UTF_8))){
            String linea= br.readLine();
            while (linea != null) {
                //System.out.println(linea);
                String p[]=linea.split(",");
                lstusuarios.add(new Usuario(p[0].trim(),p[1].trim(),p[2].trim(), p[3].trim()));
                linea= br.readLine();
            }
            
        }catch(FileNotFoundException ex){
            System.out.println("No se pudo encontrar el archivo");
        }catch(IOException e){
            System.out.println("ERROOOORRR.......");
        }
    }
    
//    public void cargarBases(){
//        try(BufferedReader br = new BufferedReader(new FileReader(InicioVentana.pathFiles+"bases.txt",StandardCharsets.UTF_8))){
//            String linea= br.readLine();
//            while (linea != null) {
//                //System.out.println(linea);
//                String p[]=linea.split(",");
//                lstbases.add(new Base(p[0].trim(),Double.valueOf(p[1])));
//                linea= br.readLine();
//            }
//            
//        }catch(FileNotFoundException ex){
//            System.out.println("No se pudo encontrar el archivo");
//        }catch(IOException e){
//            System.out.println("ERROOOORRR.......");
//        }
//    }
//    
//    public void cargarSabores(){
//        try(BufferedReader br = new BufferedReader(new FileReader(InicioVentana.pathFiles+"sabores.txt",StandardCharsets.UTF_8))){
//            String linea= br.readLine();
//            while (linea != null) {
//                //System.out.println(linea);
//                String p[]=linea.split(",");
//                lstsabores.add(new Sabor(p[0].trim(),Double.valueOf(p[1])));
//                linea= br.readLine();
//            }
//            
//        }catch(FileNotFoundException ex){
//            System.out.println("No se pudo encontrar el archivo");
//        }catch(IOException e){
//            System.out.println("ERROOOORRR.......");
//        }
//    }
//    
//    
//    public void cargarTopping(){
//        try(BufferedReader br = new BufferedReader(new FileReader(InicioVentana.pathFiles+"toppings.txt",StandardCharsets.UTF_8))){
//            String linea= br.readLine();
//            while (linea != null) {
//                //System.out.println(linea);
//                String p[]=linea.split(",");
//                lsttoppings.add(new Topping(p[0].trim(),Double.valueOf(p[1])));
//                linea= br.readLine();
//            }
//            
//        }catch(FileNotFoundException ex){
//            System.out.println("No se pudo encontrar el archivo");
//        }catch(IOException e){
//            System.out.println("ERROOOORRR.......");
//        }
//    }

    @FXML
    private void inicaSesion(ActionEvent event) {
        //
        for(Usuario u:lstusuarios){
            //vbaviso.getChildren().clear();
            if (txusuario.getText().equals(u.getUsuario())){
                if(txcontrasenia.getText().equals(u.getContrasenia())){
                    usuario = u.getNombre().toUpperCase();
                    btsesion.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {

                            try {
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

                        }

                    });
                }else{
                    Label aviso = new Label("Contraseña incorrecta");
                    vbaviso.getChildren().add(aviso);
                    txcontrasenia.clear();
                    txusuario.clear();
                    
                }
                break;
            }    
        }
        
       
    }
    
    
    

}
