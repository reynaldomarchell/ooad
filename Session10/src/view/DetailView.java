package view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.Student;
import view_controller.ViewController;



public class DetailView extends VBox{
	private Label nameLbl, ageLbl;
	private Button backBtn;
	private Student student;
	
	private void init(Student student) {
		nameLbl = new Label(student.getName());
		ageLbl = new Label(student.getAge().toString());
		backBtn = new Button("Back");
		
		backBtn.setOnMouseClicked(e->{
			ViewController.getInstance(null).back();
        });
		
	}
	
	private void setLayout() {
		this.getChildren().addAll(nameLbl, ageLbl, backBtn);
	}

	public DetailView(Student student) {
		this.student = student;
		init(student);
		setLayout();	
	}

}
