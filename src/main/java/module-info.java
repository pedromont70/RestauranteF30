module com.example.restaurantef30 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens restaurante30 to javafx.fxml;
    exports restaurante30;
}