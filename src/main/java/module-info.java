module Clickablo {

    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires com.fasterxml.jackson.databind;

    opens app.clickablo to javafx.fxml;

    exports app.clickablo;
}