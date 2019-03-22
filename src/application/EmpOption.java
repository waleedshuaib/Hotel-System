package application;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;

public class EmpOption {

	Alert alert;
	static int updateEmp;
	@FXML
	private Button register;

	@FXML
	private Button viewTable;

	@FXML
	private Button delete;

	@FXML
	private Button updateInfo;
	@FXML
	private MenuItem login;

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
	void login(ActionEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("Start_Scene.fxml"));
		AnchorPane root = loader.load();

		(Main.window).setTitle("Login Page");
		Scene sc = new Scene(root);
		(Main.window).setScene(sc);
		(Main.window).show();

	}

	@FXML
	void menu(ActionEvent event) {

	}

	@FXML
	void delete(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Emp_Delete.fxml"));
		AnchorPane root = loader.load();

		(Main.window).setTitle("Employee Delete");
		Scene sc = new Scene(root);
		(Main.window).setScene(sc);
		(Main.window).show();

	}

	@FXML
	void register(ActionEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("Emp_Reg.fxml"));
		AnchorPane root = loader.load();

		(Main.window).setTitle("Employee Registration");
		Scene sc = new Scene(root);
		(Main.window).setScene(sc);
		(Main.window).show();

	}

	@FXML
	void updateInfo(ActionEvent event) throws IOException, SQLException {

		TextInputDialog td = new TextInputDialog();
		td.setContentText("Enter the employee id that you wish to edit");
		Optional<String> result = td.showAndWait();
		int co = Integer.parseInt(result.get());
		updateEmp = co;

		FXMLLoader loader = new FXMLLoader(getClass().getResource("Emp_Edit.fxml"));
		AnchorPane root = loader.load();

		(Main.window).setTitle("Employee Update");
		Scene sc = new Scene(root);
		(Main.window).setScene(sc);
		(Main.window).show();

	}

	@FXML
	void viewTable(ActionEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("Emp_Table.fxml"));
		AnchorPane root = loader.load();

		(Main.window).setTitle("Employee Table");
		Scene sc = new Scene(root);
		(Main.window).setScene(sc);
		(Main.window).show();

	}

}
