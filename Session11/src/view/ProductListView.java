package view;

import controller.ProductController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import model.Product;

public class ProductListView {
	ListView<Product> productList;
	ProductController controller;
	
	public ProductListView() {
		productList = new ListView<>();
		controller = new ProductController();
		fetchProducts();
	}

	public Scene createProductList() {
		VBox container = new VBox(10);
		TextField nameField = new TextField();
		nameField.setPromptText("Name");
		
		TextField priceField = new TextField();
		priceField.setPromptText("Price");
		
		TextField stockField = new TextField();
		stockField.setPromptText("Stock");
		
		Button addProduct = new Button("Add Product");
		addProduct.setOnMouseClicked(e -> {
			String name = nameField.getText();
			int price = Integer.parseInt(priceField.getText());
			int stock = Integer.parseInt(stockField.getText());
			
			controller.addProduct(name, price, stock);
			fetchProducts();
		});
		
		container.getChildren().addAll(nameField, priceField, stockField, addProduct, productList);
		
		return new Scene(container);
	}

	public void fetchProducts() {
		productList.getItems().clear();
		productList.getItems().addAll(controller.getProducts());
		
		
	}
}
