module Window {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;

    exports pl.edu.pwr.window to javafx.graphics, javafx.fxml;
    opens pl.edu.pwr.window to javafx.fxml;

    //requires Encryptor;
    requires javafx.web;



}