module espol.proyectofinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens espol.proyectofinal to javafx.fxml;
    exports espol.proyectofinal;
}
