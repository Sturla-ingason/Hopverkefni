module com.example.hopverkefni {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.hopverkefni to javafx.fxml;
    exports com.example.hopverkefni;
}