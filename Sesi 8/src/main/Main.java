package main;


import java.sql.SQLException;
import java.util.Vector;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Cake;
import util.Connect;

public class Main extends Application{

	private Scene scene;
	private TableView<Cake> table;
	private GridPane gp;
	private Label nameLbl, priceLbl;
	private TextField nameTF, priceTF;
	private Button addBtn;
	private VBox vb;
	private Connect connect = Connect.getInstance();
	private Vector<Cake> cakes;
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void init(){
		table = new TableView<Cake>();
		gp = new GridPane();
		nameTF = new TextField();
		priceTF = new TextField();
		nameLbl = new Label("Name: ");
		priceLbl = new Label("Price: ");
		addBtn = new Button("Add");
		vb = new VBox();
		cakes = new Vector<Cake>();
		
		scene = new Scene(vb, 300, 500);
	}
	
	public void setLayout() {
		gp.add(nameLbl, 0, 0);
		gp.add(priceLbl, 0, 1);
		gp.add(nameTF, 1, 0);
		gp.add(priceTF, 1, 1);
		
		vb.getChildren().addAll(gp, addBtn, table);
	}
	
	public void setTable() {
		TableColumn<Cake, String> nameCol = new TableColumn<Cake, String>("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<Cake, String>("name"));
		nameCol.setMinWidth(100);

		TableColumn<Cake, Integer> priceCol = new TableColumn<Cake, Integer>("Price");
		priceCol.setCellValueFactory(new PropertyValueFactory<Cake, Integer>("price"));
		priceCol.setMinWidth(100);
		
		table.getColumns().addAll(nameCol, priceCol);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		init();
		setLayout();
		setTable();
		initAction();
		refreshTable();
		
		primaryStage.setTitle("Manage Cake");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void initAction() {
		addBtn.setOnMouseClicked(e->{
			String name = nameTF.getText();
			Integer price = Integer.valueOf(priceTF.getText());
			
			// PANGGIL ADD DATA
			addCake(name, price);
			
			// REFRESH DATA
			refreshTable();
			
		});
	 }
	
	public void addCake(String name, Integer price) {
		String query = String.format("INSERT INTO cake VALUES(0, '%s', %d)", name, price);
		connect.execUpdate(query);
	}
	
	public void getCake() {
		cakes.removeAllElements();
		String query = "SELECT * FROM cake";
        connect.rs = connect.execQuery(query);
        
        try {
			while(connect.rs.next()) {
				Integer id = connect.rs.getInt("id");
				String name = connect.rs.getString("name");
				Integer price = connect.rs.getInt("price");
				
				cakes.add(new Cake(id, name, price));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void refreshTable() {
		getCake();
		ObservableList<Cake> cakeObs = FXCollections.observableArrayList(cakes);
		table.setItems(cakeObs);
	}

}
