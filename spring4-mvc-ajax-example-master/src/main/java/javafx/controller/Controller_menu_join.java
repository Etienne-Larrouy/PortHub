package javafx.controller;



import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller_menu_join  implements Initializable{

	
	@FXML
	private TextField ip_address;
	@FXML
	private Label state;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	public void connexion_server(){
		
		System.out.println("tentative de connexion");
		String ip = ip_address.getText().toString();
		state.setText("tentative de connexion à "+ip);
		
	}


}
