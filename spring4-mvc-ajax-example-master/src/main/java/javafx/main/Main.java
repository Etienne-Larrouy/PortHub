package javafx.main;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Pane page =  (Pane) FXMLLoader.load(Main.class.getResource("/javafx/view/menu.fxml"));
			Scene scene = new Scene(page);
			primaryStage.setScene(scene);
			scene.setRoot(page);
			primaryStage.setScene(scene);
			
			BackgroundImage myBI= new BackgroundImage(new Image("fond.png",800,800,false,true),
			        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
			          BackgroundSize.DEFAULT);
			page.setBackground(new Background(myBI));
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
