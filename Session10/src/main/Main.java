package main;

import javafx.application.Application;
import javafx.stage.Stage;
import view_controller.ViewController;

public class Main extends Application {
	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		ViewController viewController = ViewController.getInstance(primaryStage);
		viewController.navigateToHome();
		
	}

}
