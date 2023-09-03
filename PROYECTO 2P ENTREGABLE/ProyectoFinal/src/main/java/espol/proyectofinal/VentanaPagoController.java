/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package espol.proyectofinal;

import Clases.Pago;
import Clases.Pedido;
import Clases.incompleteStageException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author vv
 */
public class VentanaPagoController implements Initializable {

    Label ldetalleT = new Label("Ingrese los datos de su tarjeta");

    Label lnombreT = new Label();
    Label lnumeroT = new Label();
    Label lfechaT = new Label();
    Label lcvvT = new Label();

    TextField txnombreTarjeta = new TextField();
    TextField txnumeroTarjeta = new TextField();
    DatePicker dateTarjeta = new DatePicker();
    TextField txcvvTarjeta = new TextField();

    @FXML
    private AnchorPane rootPago;

    @FXML
    private AnchorPane rootDetalles;

    @FXML
    private RadioButton efectivo;

    @FXML
    private RadioButton trjcredito;

    @FXML
    private TextField valor;

    @FXML
    private TextField iva;

    @FXML
    private TextField valortrj;

    @FXML
    private TextField total;

    @FXML
    private Button btnConfirmar;

    @FXML
    private Button btnCancelar;

    @FXML
    private VBox hbLabel;
    /**
     * Initialize
     * @param url url
     * @param rb rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        detallarPago();
    }
    /**
     * Detalla el pago generado por el pedido del cliente.
     */
    public void detallarPago() {
        Pago p = ElegirBaseController.pedido.generarTransaccionE();
        valor.setText(Double.toString(Math.round(p.getValorSinAdiciones()*100.0)/100.0));
        iva.setText(Double.toString(Math.round(p.getIva()*100.0)/100.0));
        valortrj.setText(Double.toString(Math.round(p.getValorTrj()*100.0)/100.0));
        total.setText(Double.toString(Math.round(p.getTotalPagar()*100.0)/100.));

        efectivo.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent m) {
                Pago p = ElegirBaseController.pedido.generarTransaccionE();
                valor.clear();
                iva.clear();
                valortrj.clear();
                total.clear();
                valor.setText(Double.toString(Math.round(p.getValorSinAdiciones()*100.0)/100.0));
                iva.setText(Double.toString(Math.round(p.getIva()*100.0)/100.0));
                valortrj.setText(Double.toString(Math.round(p.getValorTrj()*100.0)/100.0));
                total.setText(Double.toString(Math.round(p.getTotalPagar()*100.0)/100.0));
                valor.setEditable(false);
                iva.setEditable(false);
                valortrj.setEditable(false);
                total.setEditable(false);
                hbLabel.getChildren().clear();
                if (efectivo.isSelected()) {
                    Label l = new Label();
                    l.setText("Acercate a caja para que puedas pagar tu pedido");
                    l.setStyle("-fx-text-fill: black;-fx-font-weight: bold; -fx-font-size: 15");
                    hbLabel.getChildren().add(l);
                }
            }
        });

        trjcredito.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent m) {
                Pago p = ElegirBaseController.pedido.generarTransaccionT();
                valor.clear();
                iva.clear();
                valortrj.clear();
                total.clear();
                valor.setText(Double.toString(Math.round(p.getValorSinAdiciones()*100.0)/100.0));
                iva.setText(Double.toString(Math.round(p.getIva()*100.0)/100.0));
                valortrj.setText(Double.toString(Math.round(p.getValorTrj()*100.0)/100.0));
                total.setText(Double.toString(Math.round(p.getTotalPagar()*100.0)/100.0));
                valor.setEditable(false);
                iva.setEditable(false);
                valortrj.setEditable(false);
                total.setEditable(false);
                hbLabel.getChildren().clear();

                HBox h = new HBox();
                lnombreT.setText("Nombre:");
                lnombreT.setStyle("-fx-text-fill: black;-fx-font-weight: bold; -fx-font-size: 12");
                lnumeroT.setText("Numero:");
                lnumeroT.setStyle("-fx-text-fill: black;-fx-font-weight: bold; -fx-font-size: 12");
                lfechaT.setText("Fecha Caducidad:");
                lfechaT.setStyle("-fx-text-fill: black;-fx-font-weight: bold; -fx-font-size: 12");
                lcvvT.setText("CVV:");
                lcvvT.setStyle("-fx-text-fill: black;-fx-font-weight: bold; -fx-font-size: 12");
                VBox labels = new VBox();
                labels.setSpacing(15);
                labels.getChildren().addAll(lnombreT, lnumeroT, lfechaT, lcvvT);
                VBox textfields = new VBox();
                textfields.setSpacing(5);

                textfields.getChildren().addAll(txnombreTarjeta, txnumeroTarjeta, dateTarjeta, txcvvTarjeta);
                h.getChildren().addAll(labels, textfields);
                ldetalleT.setStyle("-fx-text-fill: #e460bf;-fx-font-weight: bold; -fx-font-size: 15");

                hbLabel.getChildren().addAll(ldetalleT, h);
                hbLabel.setSpacing(5);

            }
        });
    }
    /**
     * Metodo encargado de procesar el pago del pedido una vez el cliente de click
     * en el boton confirmar.
     */
    @FXML
    public void confirmar() {

        if (trjcredito.isSelected()) {
            String nombreT = txnombreTarjeta.getText();
            String numeroT = txnumeroTarjeta.getText();
            LocalDate fecha = dateTarjeta.getValue();
            String cvv = txcvvTarjeta.getText();
            if (txnombreTarjeta.getText().isEmpty() || txnumeroTarjeta.getText().isEmpty() || txcvvTarjeta.getText().isEmpty() || fecha == null) {

                try {
                    throw new incompleteStageException();
                } catch (incompleteStageException inc) {
                    mostrarError(inc.getMessage());
                }

            } else {
                // aqui nomas falta agregar un true despues de la cadena pedidos.txt para que la informacion nunca se borre
                // no sabria si ponerlo o no ????
                try (BufferedWriter br = new BufferedWriter(new FileWriter(InicioVentana.pathFiles + "pagos.txt",true))) {
                    br.write(ElegirBaseController.pedido.generarTransaccionT().writePago() + "\n");
                } catch (IOException e) {
                    System.out.println("FALLO ESCRITURA EN AL ARCHIVO TXT PAGOS");
                    System.out.println(e.getMessage());
                }
                try {
                    InicioVentana.cambiarEscenasPedirPedidos("ventanaCierre.fxml", VentanaBienvenidaController.stage1, "Ventana Cierre");
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }

        } else if (efectivo.isSelected()) {

            try (BufferedWriter br = new BufferedWriter(new FileWriter(InicioVentana.pathFiles + "pagos.txt",true))) {
                br.write(ElegirBaseController.pedido.generarTransaccionE().writePago() + "\n");
            } catch (IOException e) {
                System.out.println("FALLO ESCRITURA EN AL ARCHIVO TXT PAGOS");
                System.out.println(e.getMessage());
            }
            try {
                InicioVentana.cambiarEscenasPedirPedidos("ventanaCierre.fxml", VentanaBienvenidaController.stage1, "Ventana Cierre");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

        } else if (trjcredito.isSelected() == false && efectivo.isSelected() == false) {
            try {
                throw new incompleteStageException();
            } catch (incompleteStageException inc) {
                mostrarError(inc.getMessage());
            }

        }

    }
    /**
     * Crea una alerta al usuario e indica que error esta cometiendo.
     * @param mensaje 
     */
    private void mostrarError(String mensaje) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    /**
     * Metodo ligado al boton cancelar. Cierra la ventana de pago y elimina el pedido
     * del archivo pedidos.txt y su archivo .bin.
     */
    @FXML
    public void cancelar() {
        /*
        cuando se da en cancelar al momento de que aparezca esa ventana flotante y le demos en confirmar cancelacion
        despues de que terminen de hacer la codificacion para eliminar ese pedido del archcivo txt y del archivo binario
        pueden poner esto ; para que se cierre todo el programa "Platform.exit()"
         */
        Stage stg1 = new Stage();
        VBox vb2 = new VBox();
        HBox h2 = new HBox();
        Label lb = new Label();
        Button bConfirmar = new Button("Confirmar");
        bConfirmar.setStyle("-fx-text-fill: black;-fx-font-weight: bold;-fx-background-color:#ECDD29");
        Button bCancelar = new Button("Cancelar");
        bCancelar.setStyle("-fx-text-fill: black;-fx-font-weight: bold;-fx-background-color:#ECDD29");

        lb.setText("¿Esta seguro de cancelar su compra?");
        bConfirmar.setOnMouseClicked((MouseEvent e) -> {
            eliminartxt();
            Thread th = new Thread(() -> {
                Platform.runLater(() -> {
                    eliminarbin();
                });
            });
            th.setDaemon(true);
            th.start();

            ElegirBaseController.pedido = null;
            try {
                InicioVentana.cambiarEscenasPedirPedidos("VentanaBienvenida.fxml", VentanaBienvenidaController.stage1, "Ventana Bienvenida");
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            stg1.close();
        });
        bCancelar.setOnMouseClicked((MouseEvent e) -> {
            stg1.close();
        });

        h2.getChildren().addAll(bConfirmar, bCancelar);
        h2.setAlignment(Pos.CENTER);
        h2.setSpacing(20);
        h2.setLayoutX(30);
        h2.setLayoutX(48);
        vb2.getChildren().addAll(lb, h2);
        vb2.setAlignment(Pos.CENTER);
        vb2.setStyle("-fx-background-color:#DF9EF1");
        vb2.setSpacing(20);
        Scene sc = new Scene(vb2, 300, 200);
        stg1.initModality(Modality.APPLICATION_MODAL);
        stg1.setScene(sc);
        stg1.setTitle("Cancelar pedido ");
        stg1.show();
    }
    /**
     * Al ser cancelado un pedido en la ventana pago, lo elimina del archivo pedidos.txt.
     */
    public void eliminartxt() {
        ArrayList<String> lineas = new ArrayList<>();
        try (BufferedReader bfr = new BufferedReader(new FileReader(InicioVentana.pathFiles + "pedidos.txt"))) {
            String linea = bfr.readLine();
            while (linea != null) {
                lineas.add(linea);
                linea = bfr.readLine();
            }
        } catch (IOException ex) {
            System.out.println("FALLO ESCRITURA EN AL ARCHIVO TXT PEDIDOS");
            System.out.println(ex.getMessage());
        }
        lineas.remove(lineas.size() - 1);

        try (BufferedWriter bfr = new BufferedWriter(new FileWriter(InicioVentana.pathFiles + "pedidos.txt"))) {
            for (String linea : lineas) {
                bfr.write(linea + "\n");
            }
            System.out.println("PEDIDO ELIMINADO DE ARCHIVO TXT PEDIDOS");
        } catch (IOException ex) {
            System.out.println("FALLO ESCRITURA EN AL ARCHIVO TXT PEDIDOS");
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Al ser cancelado un pedido en la ventana pago, elimina su archivo serializado tipo .bin.
     */
    public void eliminarbin() {
        ArrayList<Pedido> pediSeri = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(InicioVentana.pathFiles + "pedido" + ElegirBaseController.pedido.getPedido() + ".bin"))) {
            while (true) {
                try {
                    Pedido p = (Pedido) ois.readObject();
                    pediSeri.add(p);
                } catch (EOFException ef) {
                    // Se alcanzó el final del archivo
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException fn) {
            System.out.println(fn.getMessage());
        }

        pediSeri.remove(pediSeri.size() - 1);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(InicioVentana.pathFiles + "pedido" + ElegirBaseController.pedido.getPedido() + ".bin"))) {
            for (Pedido ped : pediSeri) {
                oos.writeObject(ped);
            }
            System.out.println("PEDIDO ELIMINADO DE ARCHIVO BIN");
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

    }
}
