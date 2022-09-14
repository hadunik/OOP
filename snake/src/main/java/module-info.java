module com.example.normalsnake {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.normalsnake to javafx.fxml;
    exports com.example.normalsnake;
}