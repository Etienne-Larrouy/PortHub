package PortHub.WebBrowser;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;

public class BrowserSample extends Application
{    
	@Override
	public void start(final Stage primaryStage) throws IOException {
		

		
		
		SplitPane page =  (SplitPane) FXMLLoader.load(BrowserSample.class.getResource("/View/page_map.fxml"));
		Scene scene = new Scene(page);
		primaryStage.setScene(scene);
		scene.setRoot(page);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	 
	public static void main(String[] args) {
		launch(args);
	}

}
