package main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private VBox userListContainer;
    private TextField nameField, emailField, phoneField;
    private PasswordField passwordField;
    private Spinner<Integer> moneySpinner;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("User Management");

        userListContainer = new VBox();
        userListContainer.setSpacing(10);
        userListContainer.setPadding(new Insets(10));

        Label nameLabel = new Label("Name:");
        nameField = new TextField();
        nameField.setPromptText("Enter name");

        Label emailLabel = new Label("Email:");
        emailField = new TextField();
        emailField.setPromptText("Enter email");

        Label phoneLabel = new Label("Phone:");
        phoneField = new TextField();
        phoneField.setPromptText("Enter phone");

        Label passwordLabel = new Label("Password:");
        passwordField = new PasswordField();
        passwordField.setPromptText("Enter password");

        Label moneyLabel = new Label("Money:");
        moneySpinner = new Spinner<>();
        moneySpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 0, 100));
        moneySpinner.setEditable(true);

        Button addButton = new Button("Add User");
        addButton.setOnAction(e -> addUser());

        VBox inputContainer = new VBox(10, nameLabel, nameField, emailLabel, emailField, phoneLabel, phoneField, passwordLabel, passwordField, moneyLabel, moneySpinner, addButton);
        inputContainer.setPadding(new Insets(10));

        VBox mainLayout = new VBox(10, userListContainer, inputContainer);
        mainLayout.setPadding(new Insets(10));

        Scene scene = new Scene(mainLayout, 400, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

        fetchUserData();
    }

    private void fetchUserData() {
        userListContainer.getChildren().clear();
        // TODO: Fetch user data from database and populate the userListContainer
        String query = "SELECT * FROM users";
        Connect con = Connect.getInstance();
        
        PreparedStatement st = con.prepareStatement(query);
        try {
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				int money = rs.getInt("money");

				HBox userBox = new HBox(10);
				userBox.getChildren().add(new Label("ID: " + id));
				userBox.getChildren().add(new Label("Name: " + name));
				userBox.getChildren().add(new Label("Email: " + email));
				userBox.getChildren().add(new Label("Phone: " + phone));
				userBox.getChildren().add(new Label("Money: " + money));

				Button addMoneyButton = new Button("Add Money");
				addMoneyButton.setOnAction(e -> addMoney(id));
				userBox.getChildren().add(addMoneyButton);

				Button deleteButton = new Button("Delete");
				deleteButton.setOnAction(e -> deleteUser(id));
				userBox.getChildren().add(deleteButton);

				userListContainer.getChildren().add(userBox);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }

    private void addUser() {
        String name = nameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        String password = passwordField.getText();
        int money = moneySpinner.getValue();

        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()) {
            System.out.println("All fields are required.");
            return;
        }

        // TODO: Add user to the database
        String query = "INSERT INTO users (name, email, password, phone, money) VALUES (?, ?, ?, ?, ?)";
        Connect con = Connect.getInstance();
        
        PreparedStatement st = con.prepareStatement(query);
        
		try {
			st.setString(1, name);
			st.setString(2, email);
			st.setString(3, password);
			st.setString(4, phone);
			st.setInt(5, money);
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        nameField.clear();
        emailField.clear();
        phoneField.clear();
        passwordField.clear();
        moneySpinner.getValueFactory().setValue(0);

        fetchUserData();
    }

    private void addMoney(int userId) {
        // TODO: Update the user's money in the database by adding 1000
        fetchUserData();
    }

    private void deleteUser(int userId) {
        // TODO: Delete the user from the database
        fetchUserData();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
