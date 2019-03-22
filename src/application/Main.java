package application;

import java.sql.*;
import java.util.Properties;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application{

	static String dbURL;
	static String dbUsername = "root";
	static String dbPassword = "tawfiqrq1998";
	static String URL = "127.0.0.1";
	static String port = "3306";
	static String dbName = "Hotel_Database";
	public static Connection con;
	static String SQL;
	static Stage window;

	public void start(Stage primaryStage) throws Exception {


		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Start_Scene.fxml"));
		AnchorPane root = loader.load();

		primaryStage.setTitle("Login Page");
		Scene sc = new Scene(root);
		primaryStage.setScene(sc);
		primaryStage.show();
		window = primaryStage;
		window.setResizable(false);
		primaryStage.setResizable(false);
		
		dbURL = "jdbc:mysql://" + URL + ":" + port + "/" + dbName + "?verifyServerCertificate=false";

		Properties p = new Properties();
		p.setProperty("user", dbUsername);
		p.setProperty("password", dbPassword);
		p.setProperty("useSSL", "false");
		p.setProperty("autoReconnect", "true");

		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection(dbURL, p);

	

	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		// System.out.println("TEST");

		Application.launch(args);

	}
}
