package javafx.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class Controller_menu_create  implements Initializable{

	
	@FXML
	private TextField part_name;
	@FXML
	private TextField nb_player;
	
	@FXML 
	private TextField maps;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	public void create_part(){
		System.out.println("Création de la parite"+part_name.getText()+"|"+nb_player.getText()+"|"+maps.getText());
	}


	
}
