module WindowWithJavaFx {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;

    exports pwr.edu.pl.window to javafx.graphics, javafx.fxml;
    opens pwr.edu.pl.window to javafx.fxml;

    requires XMLParsers;
    requires javafx.web;

}