package javafx.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Controller_menu_create  implements Initializable{

	
	@FXML
	private TextField part_name;
	@FXML
	private TextField nb_player;
	
	@FXML 
	private TextField maps;

	@FXML
	private Pane pane;
	
	
	
	static int nbPLayer=0;
	public static int getNbPLayer() {
		return nbPLayer;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	public void create_part() throws IOException{
		System.out.println("Création de la parite"+part_name.getText()+"|"+nb_player.getText()+"|"+maps.getText());
		
		nbPLayer = (Integer.parseInt(nb_player.getText()));
		SplitPane page =  (SplitPane) FXMLLoader.load(Controller_menu_create.class.getResource("/javafx/view/page_map.fxml"));
		Scene scene = new Scene(page);
		Stage stage = (Stage)pane.getScene().getWindow();
		stage.setScene(scene);
		scene.setRoot(page);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		
	}

	
	
}
