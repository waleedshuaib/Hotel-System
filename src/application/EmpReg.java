package application;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class EmpReg implements Initializable {
	
	// SQL = "insert into employee (employeeid,employeefirstname,employeelastname)"
			// + "values ('11','tawfiq','essam')";
			// SQL = "Insert into Guests ";
			// PreparedStatement delete = con.prepareStatement("delete from employee where
			// employeeid=13");
			// delete.executeUpdate();
			// PreparedStatement insert = con.prepareStatement("insert into employee
			// (employeeid,employeefirstname,employeelastname)"
			// + "values ('13','tawfiq','essam')");
			// insert.executeUpdate();
	
	//PreparedStatement select = con.prepareStatement("Select * from employee");
//	ResultSet rs = select.executeQuery();
//
//	while (rs.next()) {
//
//		System.out.println("ID: " + rs.getString("employeeid"));
//		System.out.println("First: " + rs.getString("employeefirstname"));
//		System.out.println("Last: " + rs.getString("employeelastname"));
//
//	}
//


	static Employee e;

	static String[] arrMonth = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
			"October", "November", "December" };

	static String add;
	static int[] arrYear = new int[100];

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

		try {

		e = new Employee();
		
		e.firstname = firstName.getText();
		e.lastname = lastName.getText();
		String m = month.getValue();
		int d = day.getValue();
		int y = year.getValue();
		e.birthday = m + " " + d + ", " + y;

		
		e.salary = Integer.parseInt(salary.getText());
		//add = stAdd1.getText()+ ":" + stAdd2.getText() +":"+ postal.getText();
		e.address = stAdd1.getText() + ", " + '\n' + stAdd2.getText() + ", " + '\n' + postal.getText();
		
		e.phone = phone1.getText() + ", " + '\n' + phone2.getText();
		e.email = email.getText();


		PreparedStatement insert = (Main.con)
				.prepareStatement("insert into employee (employeefirstname,employeelastname, employeebirthday, "
						+ "employeesalary, employeeaddress, employeephone, employeeemail)" 
		+ "values ('"
						+ e.firstname + "','" + e.lastname + "','"
		+ e.birthday + "','" + e.salary + "','"+ e.address + "','"+ e.phone + "','"+ e.email + "')");
		
		insert.executeUpdate();
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText("Data Entered to database");
		alert.showAndWait();
		}
		catch (Exception o) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Missing Field or Incorrect Input Type");
			alert.showAndWait();
		}
		
		//(EmpTable.table).getItems().addAll(e);
//		catch (Exception o) {
//			Alert alert = new Alert(AlertType.ERROR);
//			alert.setContentText("Missing Field");
//			alert.showAndWait();
//		}

	}

	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		day.getItems().removeAll(day.getItems());
		for (int i = 1; i <= 31; i++)
			day.getItems().addAll(i);
		
		year.getItems().removeAll(day.getItems());
		for (int i = 2019; i >= 1900; i--)
			year.getItems().addAll(i);
		
		for (int i = 0; i<arrMonth.length; i++)
			month.getItems().addAll(arrMonth[i]);

	}

}
