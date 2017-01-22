package javafx.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
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

		
		 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/javafx/view/menu_create.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         Stage stage = new Stage();
         stage.setTitle("create party");
         stage.setScene(new Scene(root1));  
         stage.show();
		
	}
	
	@FXML
	public void join_part() throws IOException{
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/javafx/view/page_map.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        
        stage.setTitle("create party");
        stage.setScene(new Scene(root1));  
        stage.show();
        
		
		
	}
}
