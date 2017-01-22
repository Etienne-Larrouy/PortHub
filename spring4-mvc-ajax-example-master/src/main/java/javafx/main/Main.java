package javafx.main;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			
		
			Pane page =  (Pane) FXMLLoader.load(Main.class.getResource("/javafx/view/menu.fxml"));
			Scene scene = new Scene(page);
			primaryStage.setScene(scene);
			scene.setRoot(page);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
