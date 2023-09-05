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
    
    /**
     * Ruta de la ubicacion de los archivos tipo .txt y .bin usados en el programa.
     */
    public static String pathFiles = "Files/";
    /**
     * Ruta de la ubicaion de las imagenes usadas en el programa.
     */
    public static String pathPhotos = "photos/";
    /**
     * Scene de la ventana Inicio de Sesion.
     */
    private static Scene scene;
    
    /**
     *Metodo start.
     * @param stage stage
     * @throws Exception throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmLoader = new FXMLLoader(InicioVentana.class.getResource("InicioSesion.fxml"));
        Parent root = fxmLoader.load();
        scene = new Scene(root, 700, 500);
        stage.setScene(scene);
        stage.setTitle("Heladeria JEEZ-FROZ");
        stage.show();
    }
    
    /**
     * Cambia las escenas del metodo pedir pedido.
     * @param nuevaEscena nombre del archivo
     * @param stage stage donde se abre la nueva escena
     * @param titulo titulo del stage
     * @throws IOException  throws IOException
     */
    public static void cambiarEscenasPedirPedidos(String nuevaEscena,Stage stage,String titulo) throws IOException {
        FXMLLoader loader = new FXMLLoader(VentanaBienvenidaController.class.getResource(nuevaEscena));
        Parent root = loader.load();
        stage.setTitle(titulo);
        stage.setScene(new Scene(root,700,520));
    }
    
    /**
     * Metodo que permite cargar una imagen en un objeto ImageView previamente
     * creado para ser mostrado en el programa.
     * @param imgv Objeto de javaFx tipo ImageView donde se carga la imagen.
     * @param foto nombre de la foto a ser cargada.
     * @param x tamaño de la foto en el eje horizontal.
     * @param y tamaño de la foto en el eje vertical.
     */
    public static void insertarImagen(ImageView imgv, String foto, int x, int y){
        try(FileInputStream input = new FileInputStream(InicioVentana.pathPhotos+foto)){
            Image img = new Image(input,x,y,false,false);
            imgv.setImage(img);
        }
        catch(IOException e){
        e.printStackTrace();
        }
    }
    
    /**
     * Metodo main que da inicio al programa con el metodo launch().
     * @param args argumentos.
     */
    public static void main(String[] args) {
        launch();
    }
    
}
