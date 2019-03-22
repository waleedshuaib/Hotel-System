package application;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class EmpEdit implements Initializable {

	static String[] arrMonth = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
			"October", "November", "December" };
	static Employee e;
	@FXML
	private TextField firstName;

	@FXML
	private ComboBox<String> month;

	@FXML
	private ComboBox<Integer> year;

	@FXML
	private ComboBox<Integer> day;

	@FXML
	private TextField stAdd1;

	@FXML
	private TextField stAdd2;

	@FXML
	private TextField postal;

	@FXML
	private TextField email;

	@FXML
	private TextField phone1;

	@FXML
	private TextField phone2;

	@FXML
	private TextField lastName;

	@FXML
	private Button btSave;

	@FXML
	private Button btReset;

	@FXML
	private Button btExit;

	@FXML
	private TextField salary;

	@FXML
	void btExit(ActionEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("Emp_Option.fxml"));
		AnchorPane root = loader.load();

		(Main.window).setTitle("Employee Registration");
		Scene sc = new Scene(root);
		(Main.window).setScene(sc);
		(Main.window).show();

	}

	@FXML
	void btReset(ActionEvent event) {

		firstName.clear();
		lastName.clear();
		month.getSelectionModel().clearSelection();
		day.getSelectionModel().clearSelection();
		year.getSelectionModel().clearSelection();
		stAdd1.clear();
		stAdd2.clear();
		postal.clear();
		email.clear();
		phone1.clear();
		phone2.clear();
		salary.clear();

	}

	@FXML
	void btSave(ActionEvent event) throws SQLException {

		/*
		 * UPDATE Customers SET ContactName = 'Alfred Schmidt', City= 'Frankfurt' WHERE
		 * CustomerID = 1;
		 */
		e = new Employee();
		e.firstname = firstName.getText();
		e.lastname = lastName.getText();
		String m = month.getValue();
		int d = day.getValue();
		int y = year.getValue();
		e.birthday = m + " " + d + ", " + y;

		e.salary = Integer.parseInt(salary.getText());
		e.address = stAdd1.getText() + ", " + '\n' + stAdd2.getText() + ", " + '\n' + postal.getText();
		e.phone = phone1.getText() + ", " + '\n' + phone2.getText();
		e.email = email.getText();

		String up = "update employee set employeefirstname = '" + e.firstname + "', employeelastname = '" + e.lastname
				+ "', employeebirthday = '" + e.birthday + "', employeesalary = '" + e.salary + "', employeeaddress = '"
				+ e.address + "', employeephone= '" + e.phone + "', employeeemail = '" + e.email
				+ "' where employeeid = " + EmpOption.updateEmp;

		PreparedStatement insert = (Main.con).prepareStatement(up);

		insert.executeUpdate();

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText("Data Entry For " + e.firstname + " " + e.lastname + " Updated In database");
		alert.showAndWait();

	}

	ObservableList<Employee> ol;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {
			day.getItems().removeAll(day.getItems());
			for (int i = 1; i <= 31; i++)
				day.getItems().addAll(i);

			year.getItems().removeAll(day.getItems());
			for (int i = 2019; i >= 1900; i--)
				year.getItems().addAll(i);

			for (int i = 0; i < arrMonth.length; i++)
				month.getItems().addAll(arrMonth[i]);

			ol = FXCollections.observableArrayList();
			ResultSet rs;

			Employee e = new Employee();
			String s = "select * from employee where employeeid = " + EmpOption.updateEmp;
			rs = (Main.con).createStatement().executeQuery(s);
			while (rs.next()) {
				e.id = rs.getInt("employeeid");
				e.firstname = rs.getString("employeefirstname");
				e.lastname = rs.getString("employeelastname");
				e.birthday = rs.getString("employeebirthday");
				e.salary = rs.getInt("employeesalary");
				e.address = rs.getString("employeeaddress");
				e.phone = rs.getString("employeephone");
				e.email = rs.getString("employeeemail");

			}

			firstName.setText(e.firstname);
			lastName.setText(e.lastname);

			String[] birth = e.birthday.split("\\s*(=>|,|\\s)\\s*");
			month.setValue(birth[0]);
			day.setValue(Integer.parseInt(birth[1]));
			year.setValue(Integer.parseInt(birth[2]));

			String[] addr = e.address.split(",");
			for (int i = 0; i < addr.length; i++) {
				System.out.println(addr[i]);
			}

			stAdd1.setText(addr[0]);
			if (addr[1] != null)
				stAdd2.setText(addr[1]);
			
			
			String[] phone = e.phone.split(",");
			
			phone1.setText(phone[0]);
			phone2.setText(phone[1]);
			postal.setText(addr[2]);
			salary.setText(e.salary + "");
			email.setText(e.email);

		}

		catch (Exception o) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Employee ID " + EmpOption.updateEmp + " Not Found");
			alert.showAndWait();

		}
	}

}
