module Clickablo {

    requires javafx.controls;
    requires javafx.fxml;

    opens app.clickablo to javafx.fxml;

    exports app.clickablo;
}