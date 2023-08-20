/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espol.proyectofinal;

import java.io.FileInputStream;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author Abeni
 */
public class InicioVentana extends Application{
    
    public static String pathFiles = "src/main/resources/Files/";
    public static String pathPhotos = "src/main/resources/photos/";
    
    private static Scene scene;
    
   

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmLoader = new FXMLLoader(InicioVentana.class.getResource("InicioSesion.fxml"));

        Parent root = fxmLoader.load();

        scene = new Scene(root, 600, 400);
        //scene = new Scene(root, 700, 500);

        stage.setScene(scene);
        stage.setTitle("Heladeria JEEZ-FROZ");
        stage.show();

    }
    //Esto solo funciona para las ventanas de la opcion Pedir Pedido
    static void cambiarEscenasPedirPedidos(String nuevaEscena,Stage stage,String titulo) throws IOException {
        FXMLLoader loader = new FXMLLoader(VentanaBienvenidaController.class.getResource(nuevaEscena));
        Parent root = loader.load();            
        //ElegirSaboresController controller = loader.getController();
        stage.setTitle(titulo);
        stage.setScene(new Scene(root,700,520));
    }
    
    public static void insertarImagen(ImageView imgv, String foto, int x, int y){
        try(FileInputStream input = new FileInputStream(InicioVentana.pathPhotos+foto)){
            Image img = new Image(input,x,y,false,false);
            imgv.setImage(img);
        }
        catch(IOException e){
        e.printStackTrace();
        }
    }
    

    public static void main(String[] args) {
        launch();
    }
    
}
