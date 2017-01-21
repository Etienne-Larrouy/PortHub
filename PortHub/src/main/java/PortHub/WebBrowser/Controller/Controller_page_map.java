package PortHub.WebBrowser.Controller;

import javafx.event.ActionEvent;

//import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.javafx.BrowserView;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Controller_page_map implements Initializable {
	
	@FXML
	private StackPane map;

	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		
		Browser browser = new Browser();
        BrowserView browserView = new BrowserView(browser);

        System.out.println("toto");

        browser.loadURL("facebook.com");
       
       // map.getChildren().add(new Button("tttt"));
       map.getChildren().add(browserView);
         
        
        
       // Stage stage = (Stage)map.getScene().getWindow();
       // Scene scene = (Scene)map.getScene();
        
        
       // stage.setTitle("JxBrowser: JavaFX - Hello World");
       // stage.setScene(scene);
       // stage.show();
		
		
	}
	
/*	@FXML
	public void Choix(ActionEvent event) {
        System.out.println("You clicked me!");

    }*/

}
