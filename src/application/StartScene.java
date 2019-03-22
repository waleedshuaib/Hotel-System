package application;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class StartScene {

	@FXML
	private Button login;

	@FXML
	private Button exit;

	@FXML
	void exit(ActionEvent event) {

		Platform.exit();
        System.exit(0);
		
	}

	@FXML
	void login(ActionEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("Emp_Option.fxml"));
		AnchorPane root = loader.load();

		(Main.window).setTitle("Employee Registration");
		Scene sc = new Scene(root);
		(Main.window).setScene(sc);
		(Main.window).show();
		


	}

}
