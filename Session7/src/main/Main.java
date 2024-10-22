package main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
	
	Scene scene;
	BorderPane mainContainer;
	
	MenuBar menus;
	
	Menu fileMenu, editMenu;
	MenuItem newFile, undo;
	
	ScrollPane scroll;
	
	VBox names;
	
	TableView<Person> personTable;

	public static void main(String[] args) {
		launch(args);

	}

	void initial() {
		menus = new MenuBar();
		newFile = new MenuItem("New File");
		undo = new MenuItem("Undo");
		
		newFile.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("New File Created");
			}
		});
		
		fileMenu = new Menu("File");
		editMenu = new Menu("Edit");
		
		fileMenu.getItems().add(newFile);
		editMenu.getItems().add(undo);
		
		menus.getMenus().addAll(fileMenu, editMenu);
		
		mainContainer = new BorderPane();
		mainContainer.setTop(menus);
		
		names = new VBox();
		for (int i = 0; i < 100; i++) {
			names.getChildren().add(new Label("Test " + i ));
		}
		
		scroll = new ScrollPane(names);
		
//		mainContainer.setCenter(scroll);
		
		personTable = new TableView<>();
		TableColumn<Person, String> firstColumn = new TableColumn<>("First Name");
		firstColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		
		TableColumn<Person, String> lastColumn = new TableColumn<>("Last Name");
		lastColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		
		personTable.getColumns().addAll(firstColumn, lastColumn);
		personTable.getItems().add(new Person("Bina", "Nusantara"));
		personTable.getItems().add(new Person("John", "Doe"));
		
		mainContainer.setCenter(personTable);
		
		scene = new Scene(mainContainer, 1000, 600);
		
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			
			@Override
            public void handle(KeyEvent event) {
                KeyCode key = event.getCode();
                System.out.println(key);
            }
        });
		
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		initial();
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

}
