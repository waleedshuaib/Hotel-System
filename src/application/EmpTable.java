package application;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class EmpTable implements Initializable {

	ObservableList<Employee> ol;
	
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
    void exit(ActionEvent event) {

		Platform.exit();
        System.exit(0);
    }

	@FXML
	void menu(ActionEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("Emp_Option.fxml"));
		AnchorPane root = loader.load();

		(Main.window).setTitle("Employee Registration");
		Scene sc = new Scene(root);
		(Main.window).setScene(sc);
		(Main.window).show();

	}

	

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