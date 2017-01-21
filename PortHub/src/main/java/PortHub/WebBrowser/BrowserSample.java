package PortHub.WebBrowser;

import java.io.IOException;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.javafx.BrowserView;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class BrowserSample extends Application
{    
	public void start(final Stage primaryStage) throws IOException {
	/*Browser browser = new Browser();
	BrowserView view = new BrowserView(browser);
	 
	Scene scene = new Scene(new BorderPane(view), 700, 500);
	primaryStage.setScene(scene);
	primaryStage.show();
	 
	browser.loadURL("http://www.facebook.com"); 
	*/
		FXMLLoader root =  new FXMLLoader(getClass().getResource("../View/page_map.fxml"));
        Browser browser = new Browser();
        BrowserView view = new BrowserView(browser);

        Scene scene = new Scene(new BorderPane(view), 700, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

        browser.loadURL("http://www.google.com");
	}
	 
	public static void main(String[] args) {
		launch(args);
	}
	/*Stage stage = null;
		Parent root = null;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Task.fxml"));
		
	
		ControllerTask controller = new ControllerTask(s.getObservableListTasks().get(Integer.parseInt(TaskPreview_id.getText())));
	        // Set it in the FXMLLoader
		loader.setController(controller);

		stage = (Stage) ((GridPane)event.getSource()).getScene().getWindow();
		
		root = (Parent)loader.load();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();*/
}
