package javafx.controller;

import javafx.event.ActionEvent;

//import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.millesBornes.web.model.Card;
import com.millesBornes.web.model.Part;
import com.millesBornes.web.model.Player;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.javafx.BrowserView;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Controller_page_map implements Initializable {
	
	@FXML
	private StackPane map;
	
	@FXML
	private Button card1;
	@FXML
	private Button card2;
	@FXML
	private Button card3;
	@FXML
	private Button card4;
	@FXML
	private Button card5;
	@FXML
	private Button card6;
	@FXML
	private Button card7;
	private ArrayList<Player> list_player = new ArrayList<>();

	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		
		Browser browser = new Browser();
        BrowserView browserView = new BrowserView(browser);

        System.out.println("toto");

        browser.loadURL("http://localhost:8080/spring4ajax/#");
       
       // map.getChildren().add(new Button("tttt"));
       map.getChildren().add(browserView);
         
        
        
       // Stage stage = (Stage)map.getScene().getWindow();
       // Scene scene = (Scene)map.getScene();
        
        
       // stage.setTitle("JxBrowser: JavaFX - Hello World");
       // stage.setScene(scene);
       // stage.show();
	
       ArrayList<Button> playerHand = new ArrayList<Button>();
       
       playerHand.add(card1);
       playerHand.add(card2);
       playerHand.add(card3);
       playerHand.add(card4);
       playerHand.add(card5);
       playerHand.add(card6);
       playerHand.add(card7);
       
       Part partie = new Part("localhost",1);
       
		
	
		list_player.add(new Player("jojo"));


		
		partie.add_player(list_player.get(0));

		
		System.out.println("taille avant distribution "+partie.nb_card());
		
		//beggining part
		//give 7 cars for players
		System.out.println("nb players = "+partie.getNb_player());

		for(int i = 0;i<7;i++){
				Card card = partie.give_card();
				list_player.get(0).add_card(card);
				playerHand.get(i).setText(list_player.get(0).getList_card().get(i).getName());
		}
		
		
		System.out.println("taille apr�s distribution "+partie.nb_card());
		
		//Use a card
		
		//list_player.get(3).display_list_card();
		System.out.println("\n\n");
		list_player.get(0).display_list_card();
		
		
	}
	
	@FXML
	public void use_card(ActionEvent event){
		if (event.getSource() == card1) {
			list_player.get(0).use_card(0);
			
		}
		if (event.getSource() == card2) {
			list_player.get(0).use_card(1);
			
		}
		if (event.getSource() == card3) {
			list_player.get(0).use_card(2);
			
		}
		if (event.getSource() == card4) {
			list_player.get(0).use_card(3);
		
		}
		if (event.getSource() == card5) {
			list_player.get(0).use_card(4);
			
		}
		if (event.getSource() == card6) {
			list_player.get(0).use_card(5);
			
		}
		if (event.getSource() == card7) {
			list_player.get(0).use_card(6);
			
		}
}
	


}
