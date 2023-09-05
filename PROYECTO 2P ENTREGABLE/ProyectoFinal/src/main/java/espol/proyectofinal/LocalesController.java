/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package espol.proyectofinal;

import Clases.Local;
import static espol.proyectofinal.LocalesController.cargarLocales;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author Abeni
 */
public class LocalesController implements Initializable {
  
    ImageView inv=null;
    
    @FXML
    private Pane rootlocales;
    /**
     * Initializes the controller class.
     * @param url url
     * @param rb rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        mostrarLocales();
    }    
    /**
     * Metodo que carga los datos de locales.txt y crea un ArrayList de tipo Local
     * y lo devuelve.
     * @return lstlocales
     */
    public static ArrayList<Local> cargarLocales(){
        ArrayList<Local> lstlocales = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(InicioVentana.pathFiles+"locales.txt", StandardCharsets.UTF_8))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                double cX=Double.parseDouble(partes[0]);
                double cY=Double.parseDouble(partes[1]);
                String nombre=partes[2];
                String horario=partes[3];
                Local l=new Local(cX,cY,nombre,horario);
                lstlocales.add(l);
            }
        } 
        catch (IOException e) {
            System.out.println("Ocurrió un error al leer el archivo: " + e.getMessage());
        }
        return lstlocales;
    }
    
    /**
     * Muestra en el mapa una figurita sobre la ubicacion de los locales y crea las
     * ventanas con informacion de cada local.
     */
    public  void mostrarLocales(){
        Thread threadMuestraUbicacion = new Thread(new Runnable() {
            @Override
            public void run() {
                for (Local punto : cargarLocales()) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                FileInputStream input = new FileInputStream(InicioVentana.pathPhotos + "Heladopunto.png");
                                Image image = new Image(input, 25, 35, false, false);
                                inv = new ImageView(image);
                                double x = punto.getCoordenadax();
                                double y = punto.getCoordenadaY();
                                inv.setLayoutX(x);
                                inv.setLayoutY(y);
                            } 
                            catch (IOException e) {
                                System.out.println(e.getMessage());
                            }
                            rootlocales.getChildren().add(inv);
                            
                            //Muestra los detalles de los locales al darle click sobre la imagen
                            inv.setOnMouseClicked(new EventHandler<MouseEvent>() {

                                @Override
                                public void handle(MouseEvent m) {
                                    Stage g = new Stage();
                                    g.initModality(Modality.APPLICATION_MODAL);
                                    Button b1 = new Button("cerrar");
                                    b1.setStyle("-fx-text-fill: black;-fx-font-weight: bold;-fx-background-color:#ECDD29");
                                    b1.setOnAction(new EventHandler<ActionEvent>() {
                                        @Override
                                        public void handle(ActionEvent e) {
                                            Platform.runLater(() -> {
                                                g.close();
                                            });
                                        }
                                    });
                                    Label l1 = new Label("Heladeria 2 Amores");
                                    l1.setStyle("-fx-text-fill: black;-fx-font-weight: bold; -fx-font-size: 15");
                                    Label l4 = new Label("Ubicacion: " + punto.getNombre());
                                    Label l2 = new Label("Horario: " + punto.getHorario());
                                    Label l3 = new Label();

                                    CierreventanaInfo(l3, g);

                                    VBox v1 = new VBox();
                                    v1.setAlignment(Pos.CENTER_LEFT);
                                    v1.setSpacing(15);
                                    v1.setPadding(new Insets(10, 0, 0, 15));

                                    v1.getChildren().addAll(l1, l4, l2, l3, b1);
                                    Pane rootDescrip = new Pane();
                                    rootDescrip.setStyle("-fx-background-color:#DF9EF1");
                                    rootDescrip.getChildren().addAll(v1);
                                    Scene s = new Scene(rootDescrip, 220, 180);
                                    g.setScene(s);
                                    g.setTitle("Detalle Ubicacion");
                                    g.show();
                                }
                            });
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } 
                    catch (InterruptedException ex) {
                        System.out.println("Thread defectuoso");
                    }
                }
            }
        });

        threadMuestraUbicacion.setDaemon(true);
        threadMuestraUbicacion.start();
    }

    /**
     * Realiza un conteo regresivo para que la ventana con informacion del local
     * se cierre despues de 5 segundos.
     * @param l objeto de javaFx tipo Label.
     * @param st Stage donde se abre la nueva ventana.
     */
     public void CierreventanaInfo(Label l,Stage st){
        
        Thread threadconteo=new Thread(new Runnable(){
            @Override
            public void run() {
                
                for (int i = 5; i >= 0; i--) {
                    String status = "Cerrarando en " + i + " segundos...";
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            l.setText(status);
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } 
                    catch (InterruptedException ex) {
                        System.out.println("Thread defectuoso");
                    }
                }
                // esto es para hacer que cuando termine el conteo se cierre solo esa ventana
                Platform.runLater(() -> {
                    st.close();
                });
            }
        });
        threadconteo.setDaemon(true);
        threadconteo.start();
    }
     
}



