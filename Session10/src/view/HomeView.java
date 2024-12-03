package view;





import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import model.Student;
import view_controller.ViewController;

public class HomeView extends VBox implements EventHandler<ActionEvent> {
	private TextField nameTF, ageTF;
	private Button submitBtn;
	private TableView<Student> studentTV;
	
	private void init() {
		nameTF = new TextField();
		ageTF = new TextField();
		submitBtn = new Button("Add");
		submitBtn.setOnAction(this);
		
		studentTV = new TableView<Student>();
		this.studentTV.setOnMouseClicked(e -> {
			Student clickedStudent = this.studentTV.getSelectionModel().getSelectedItem();
			if (clickedStudent != null) {
				ViewController.getInstance(null).navigateToDetail(clickedStudent);
			}
		});
		
	
	}
	
	private void setLayout() {
		this.getChildren().addAll(nameTF, ageTF, submitBtn, studentTV);
	}
	
	private void setTable() {
		TableColumn<Student, String> nameCol = new TableColumn<Student, String>("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
		
		TableColumn<Student, Integer> ageCol = new TableColumn<Student, Integer>("Age");
		ageCol.setCellValueFactory(new PropertyValueFactory<Student, Integer>("age"));
		
		studentTV.getColumns().addAll(nameCol, ageCol);
	}

	public HomeView() {
		init();
        setLayout();
        setTable();
	}
	
	public void addData() {
		Student student = new Student(this.nameTF.getText(), Integer.valueOf(this.ageTF.getText()));
		studentTV.getItems().add(student);
	}

	@Override
	public void handle(ActionEvent event) {
		if (event.getSource() == this.submitBtn) {
			addData();
		}
		
	}

}
