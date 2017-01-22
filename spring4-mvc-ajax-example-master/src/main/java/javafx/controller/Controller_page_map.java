package javafx.controller;

import javafx.event.ActionEvent;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
//import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.millesBornes.web.model.Card;
import com.millesBornes.web.model.Part;
import com.millesBornes.web.model.Player;
import com.server.ServerThread;
import com.server.Serveur;
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

	private Socket socket;

	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		Browser browser = new Browser();
		BrowserView browserView = new BrowserView(browser);

		browser.loadURL("http://localhost:8080/spring4ajax/#");

		// map.getChildren().add(new Button("tttt"));
		map.getChildren().add(browserView);

		Serveur s = Serveur.getInstance();
		s.setPartie(new Part("localhost", 1));
		s.partie().add_player(new Player("p1"));

		for (int i = 0; i < 7; i++) {
			Card card = s.partie().give_card();
			s.partie().getList_Player().get(0).add_card(card);
		}

		try{
			ServerSocket welcomeSocket = new ServerSocket(3456);
			Socket connectionSocket = welcomeSocket.accept();
			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
			String o = ObjectUtil.toString(s.partie().getList_Player().get(0).getList_card());
			
			outToClient.writeBytes(o);
			connectionSocket.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	@FXML
	public void use_card(ActionEvent event) {
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
