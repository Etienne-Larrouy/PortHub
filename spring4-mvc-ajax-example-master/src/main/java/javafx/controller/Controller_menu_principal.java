package javafx.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Controller_menu_principal implements Initializable{
	
	@FXML
	private Pane pane;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

	
	@FXML
	public void create_part() throws IOException{
		Pane page =  (Pane) FXMLLoader.load(Controller_menu_principal.class.getResource("/javafx/view/menu_create.fxml"));
		Scene scene = new Scene(page);
		Stage stage = (Stage)pane.getScene().getWindow();
		stage.setScene(scene);
		scene.setRoot(page);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		
	}
	
	@FXML
	public void join_part() throws IOException{
		Pane page =  (Pane) FXMLLoader.load(Controller_menu_principal.class.getResource("/javafx/view/menu_join.fxml"));
		Scene scene = new Scene(page);
		Stage stage = (Stage)pane.getScene().getWindow();
		stage.setScene(scene);
		scene.setRoot(page);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		
	}
}
