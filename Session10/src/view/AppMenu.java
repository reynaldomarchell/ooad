package view;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class AppMenu extends MenuBar {
	private MenuItem saveMI, openMI;
	private Menu fileMenu, editMenu;
	
	private void init() {
		saveMI = new MenuItem("Save");
        openMI = new MenuItem("Open");
        
        fileMenu = new Menu("File");
        editMenu = new Menu("Edit");
        
	}
	
	private void setLayout () {
		this.getMenus().addAll(fileMenu, editMenu);
		
		fileMenu.getItems().addAll(saveMI, openMI);
	}
	
	public AppMenu() {
		init();
		setLayout();
	}
	
}
