module com.example.encoding {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires spring.security.core;
    requires java.desktop;


    opens com.example.encoding to javafx.fxml;
    exports com.example.encoding;
}