package main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

	Scene scene;
	
	BorderPane borderContainer;
	GridPane gridContainer;
	FlowPane flowContainer;
	
	TextField firstnameTF, lastnameTF, usernameTF;
	PasswordField passwordPF;
	
	DatePicker datePicker;
	
	Spinner<Integer> ageSpinner;
	
	CheckBox agreeCB;
	
	Button submitBtn;
	
	Label titleLbl, nameLbl, passwordLbl, usernameLbl;
	
	void initialize() {
		borderContainer = new BorderPane();
		flowContainer = new FlowPane();
		gridContainer = new GridPane();

		firstnameTF = new TextField("John...");
		lastnameTF = new TextField("Doe...");
		usernameTF = new TextField();
		
		passwordPF = new PasswordField();
		
		agreeCB = new CheckBox("Agree to terms and conditions");
		
		titleLbl = new Label("Register Here");
		nameLbl = new Label("Name");
		usernameLbl = new Label("Username");
		passwordLbl = new Label("Password");
		
		ageSpinner = new Spinner<>(13, 60, 13);
		datePicker = new DatePicker();
		
		submitBtn = new Button("Register here");
		
		
		scene = new Scene(borderContainer);
	}
	
	void addComponent() {
		borderContainer.setTop(titleLbl);
		borderContainer.setCenter(gridContainer);
		borderContainer.setBottom(submitBtn);
		
		gridContainer.add(nameLbl, 0, 0);
		gridContainer.add(usernameLbl, 0, 1);
		gridContainer.add(passwordLbl, 0, 2);
		
		flowContainer.getChildren().add(firstnameTF);
		flowContainer.getChildren().add(lastnameTF);
		gridContainer.add(flowContainer, 1, 0);
		gridContainer.add(usernameTF, 1, 1);
		gridContainer.add(passwordPF, 1, 2);
		gridContainer.add(datePicker, 1, 3);
		gridContainer.add(ageSpinner, 1, 4);
		gridContainer.add(agreeCB, 1, 5);
		
		
	}
	
	void arrangeComponent() {
		BorderPane.setAlignment(titleLbl, Pos.CENTER);
		BorderPane.setAlignment(submitBtn, Pos.CENTER);
		BorderPane.setAlignment(gridContainer, Pos.CENTER);
		
		borderContainer.setPadding(new Insets(15));
		gridContainer.setVgap(10);
		flowContainer.setHgap(5);
		
		titleLbl.setPadding(new Insets(0, 0, 20, 0));
		
		titleLbl.setMinWidth(100);
		usernameLbl.setMinWidth(100);
		nameLbl.setMinWidth(100);
		passwordLbl.setMinWidth(100);
		
		firstnameTF.setMaxWidth(200);
		lastnameTF.setMaxWidth(200);
		usernameTF.setMaxWidth(300);
		passwordPF.setMaxWidth(300);
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		initialize();
		addComponent();
		arrangeComponent();
		primaryStage.setTitle("Register Form");
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

}
