package app.clickablo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ClickabloApplication extends Application {

	@Override
	public void start(Stage primaryStage) {
		Button button = new Button("Click Me");
		button.setOnAction(e -> System.out.println("Hey!"));
		primaryStage.setScene(new Scene(new StackPane(button), 400, 300));
		primaryStage.setTitle("Clickablo");
		primaryStage.show();
	}


	public static void main(String[] args) {
		launch(args);
	}

}