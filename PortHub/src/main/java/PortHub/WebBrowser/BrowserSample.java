package PortHub.WebBrowser;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.javafx.BrowserView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class BrowserSample extends Application
{    
	public void start(final Stage primaryStage) {
	Browser browser = new Browser();
	BrowserView view = new BrowserView(browser);
	 
	Scene scene = new Scene(new BorderPane(view), 700, 500);
	primaryStage.setScene(scene);
	primaryStage.show();
	 
	browser.loadURL("http://www.facebook.com"); 
	
	}
	
	/*public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			GridPane accueil = (GridPane) FXMLLoader.load(Main.class.getResource("../Interface/login.fxml"));
			Scene scene = new Scene(accueil);
			scene.setRoot(accueil);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.setTitle("CTM");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
}*/
	 
	public static void main(String[] args) {
		launch(args);
	}
}
