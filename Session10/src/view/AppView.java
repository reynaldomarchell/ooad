package view;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AppView {
	private BorderPane bp;
	private AppMenu appMenu;
	
	private void init() {
		bp = new BorderPane();
		appMenu = new AppMenu();
	
	}
	
	private void setLayout() {
        bp.setTop(appMenu);
    }

	public AppView(Stage stage) {
		init();
		setLayout();
		
		Scene scene = new Scene(bp, 600, 600);
		stage.setScene(scene);
		stage.show();
	}
	
	public BorderPane getContainer() {
		return this.bp;
	}

}
