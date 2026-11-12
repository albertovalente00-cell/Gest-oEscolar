module com.albertokitala.gestaoescolar {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;


    opens com.albertokitala.gestaoescolar to javafx.fxml;
    exports com.albertokitala.gestaoescolar;
    exports com.albertokitala.gestaoescolar.controller;
    opens com.albertokitala.gestaoescolar.controller to javafx.fxml;
}