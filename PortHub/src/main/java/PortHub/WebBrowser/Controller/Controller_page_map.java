package PortHub.WebBrowser.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.javafx.BrowserView;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class Controller_page_map implements Initializable {
	
	@FXML
	private AnchorPane map;

	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		Browser browser = new Browser();
		BrowserView view = new BrowserView(browser);
		 
		
		
		map.getChildren().add(view);
		
		browser.loadURL("http://www.google.com");
		
		
		
		
	}

}
