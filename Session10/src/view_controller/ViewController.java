package view_controller;

import java.util.Stack;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Student;
import view.AppView;
import view.DetailView;
import view.HomeView;

public class ViewController {
	private Stage stage;
	private AppView appView;
	private Stack<VBox> pages;
	
	public static ViewController instance;
	
	public static ViewController getInstance(Stage stage) {
		if (instance == null && stage != null) {
			instance = new ViewController(stage);
		}
		return instance;
	}
	
	private void init() {
		this.appView = new AppView(this.stage);
		this.pages = new Stack<VBox>();
	}
	
	private ViewController(Stage stages) {
        this.stage = stage;
        init();
		
	}
	
	public void navigateToHome() {
		HomeView home = new HomeView();
		this.pages.add(home);
		appView.getContainer().setCenter(home);
	}
	
	public void navigateToDetail(Student student) {
		DetailView detail = new DetailView(student);
		this.pages.add(detail);
		appView.getContainer().setCenter(detail);
	}
	
	public void back() {
		pages.pop();
		appView.getContainer().setCenter(this.pages.peek());
	}

}
