module com.example.ti2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.management;


    opens com.example.ti2 to javafx.fxml;
    exports com.example.ti2;
    exports com.example.ti2.control;
    opens com.example.ti2.control to javafx.fxml;
}