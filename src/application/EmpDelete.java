package application;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class EmpDelete implements Initializable {

	@FXML
	private TableView<Employee> table;

	@FXML
	private TableColumn<Employee, Integer> id;

	@FXML
	private TableColumn<Employee, String> firstName;

	@FXML
	private TableColumn<Employee, String> lastName;

	@FXML
	private TableColumn<Employee, String> birthday;

	@FXML
	private TableColumn<Employee, Integer> salary;

	@FXML
	private TableColumn<Employee, String> address;

	@FXML
	private TableColumn<Employee, Integer> phone;

	@FXML
	private TableColumn<Employee, String> email;

	@FXML
	private MenuItem menu;

	@FXML
	private MenuItem exit;

	@FXML
	private Label lbID;

	@FXML
	private Button delete;

	@FXML
	private TextField dID;

	@FXML
	void delete(ActionEvent event) throws SQLException {

		int deleteid = Integer.parseInt(dID.getText());
		PreparedStatement delete = (Main.con).prepareStatement("delete from employee where employeeid = " + deleteid);

		delete.executeUpdate();

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText("ID # " + deleteid + " Has Been Deleted");
		alert.showAndWait();

		ol = FXCollections.observableArrayList();
		ResultSet rs;
		try {
			rs = (Main.con).createStatement().executeQuery("select * from employee");
			while (rs.next()) {
				ol.add(new Employee(rs.getInt("employeeid"), rs.getString("employeefirstname"),
						rs.getString("employeelastname"), rs.getString("employeebirthday"), rs.getInt("employeesalary"),
						rs.getString("employeeaddress"), rs.getString("employeephone"), rs.getString("employeeemail")));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		firstName.setCellValueFactory(new PropertyValueFactory<>("firstname"));
		lastName.setCellValueFactory(new PropertyValueFactory<>("lastname"));
		birthday.setCellValueFactory(new PropertyValueFactory<>("birthday"));
		salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
		address.setCellValueFactory(new PropertyValueFactory<>("address"));
		phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		email.setCellValueFactory(new PropertyValueFactory<>("email"));

		// table.setItems(null);
		table.setItems(ol);

	}

	@FXML
	void exit(ActionEvent event) {

	}

	@FXML
	void menu(ActionEvent event) {

	}

	ObservableList<Employee> ol;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ol = FXCollections.observableArrayList();
		ResultSet rs;
		try {
			rs = (Main.con).createStatement().executeQuery("select * from employee");
			while (rs.next()) {
				ol.add(new Employee(rs.getInt("employeeid"), rs.getString("employeefirstname"),
						rs.getString("employeelastname"), rs.getString("employeebirthday"), rs.getInt("employeesalary"),
						rs.getString("employeeaddress"), rs.getString("employeephone"), rs.getString("employeeemail")));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		firstName.setCellValueFactory(new PropertyValueFactory<>("firstname"));
		lastName.setCellValueFactory(new PropertyValueFactory<>("lastname"));
		birthday.setCellValueFactory(new PropertyValueFactory<>("birthday"));
		salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
		address.setCellValueFactory(new PropertyValueFactory<>("address"));
		phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		email.setCellValueFactory(new PropertyValueFactory<>("email"));

		// table.setItems(null);
		table.setItems(ol);

	}

}
