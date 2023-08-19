/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espol.proyectofinal;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Abeni
 */
public class InicioVentana extends Application{
    
    public static String pathFiles = "src/main/resources/Files/";
    
    private static Scene scene;
    
   

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmLoader = new FXMLLoader(InicioVentana.class.getResource("InicioSesion.fxml"));

        Parent root = fxmLoader.load();

        scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.setTitle("Heladeria JEEZ-FROZ");
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
    
}
