package PortHub.WebBrowser;

import com.teamdev.jxbrowser.chromium.javafx.BrowserView;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.javafx.BrowserView;
 
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;

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
	 
	public static void main(String[] args) {
		launch(args);
	}
}
