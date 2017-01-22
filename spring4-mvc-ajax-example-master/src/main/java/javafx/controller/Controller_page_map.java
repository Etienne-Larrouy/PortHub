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
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
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
	@FXML
	private Pane pane;

	private static boolean created = false;

	private static int nbbbbb = 0;

	private ArrayList<Player> list_player = new ArrayList<>();

	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		int nbPLayer = Controller_menu_create.getNbPLayer();
		System.out.println("qsdqsdqsdqsdqsdqsd" + Serveur.getInstance().nb_player + "\n\n\n");

		try {
			Serveur.getInstance().partie();
		} catch (NullPointerException e) {
			created = false;
		}
		if (!created) {
			created = true;
			System.out.println("coiuoucocu");
			Browser browser = new Browser();
			BrowserView browserView = new BrowserView(browser);

			browser.loadURL("http://localhost:8080/spring4ajax?p=" + nbbbbb);

			map.getChildren().add(browserView);

			System.out.println(nbPLayer);

			Serveur.getInstance().setPartie(new Part("localhost", nbPLayer));
			System.out.println(nbPLayer);
			for (int i = 0; i < nbPLayer; i++) {
				Serveur.getInstance().partie().add_player(new Player("Joueur " + (i + 1)));
			}
			System.out.println(Serveur.getInstance().partie().getList_Player().size());

			for (int i = 0; i < nbPLayer; i++) {
				for (int j = 0; j < 7; j++) {
					Serveur.getInstance().partie().getList_Player().get(i)
							.add_card(Serveur.getInstance().partie().give_card());
				}
			}

			if (!Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(0).getUrl()
					.equals("null")) {
				BackgroundImage myBI = new BackgroundImage(
						new Image(Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(0)
								.getUrl(), 100, 100, false, true),
						BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
						BackgroundSize.DEFAULT);
				card1.setBackground(new Background(myBI));
			} else {
				card1.setText(
						Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(0).getName());
			}
			if (!Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(1).getUrl()
					.equals("null")) {
				BackgroundImage myBI = new BackgroundImage(
						new Image(Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(1)
								.getUrl(), 100, 100, false, true),
						BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
						BackgroundSize.DEFAULT);
				card2.setBackground(new Background(myBI));
			} else {
				card2.setText(
						Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(1).getName());
			}
			if (!Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(2).getUrl()
					.equals("null")) {
				BackgroundImage myBI = new BackgroundImage(
						new Image(Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(2)
								.getUrl(), 100, 100, false, true),
						BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
						BackgroundSize.DEFAULT);
				card3.setBackground(new Background(myBI));
			} else {
				card3.setText(
						Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(2).getName());
			}
			if (!Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(3).getUrl()
					.equals("null")) {
				BackgroundImage myBI = new BackgroundImage(
						new Image(Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(3)
								.getUrl(), 100, 100, false, true),
						BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
						BackgroundSize.DEFAULT);
				card4.setBackground(new Background(myBI));
			} else {
				card4.setText(
						Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(3).getName());
			}
			if (!Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(4).getUrl()
					.equals("null")) {
				BackgroundImage myBI = new BackgroundImage(
						new Image(Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(4)
								.getUrl(), 100, 100, false, true),
						BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
						BackgroundSize.DEFAULT);
				card5.setBackground(new Background(myBI));
			} else {
				card5.setText(
						Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(4).getName());
			}
			if (!Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(5).getUrl()
					.equals("null")) {
				BackgroundImage myBI = new BackgroundImage(
						new Image(Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(5)
								.getUrl(), 100, 100, false, true),
						BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
						BackgroundSize.DEFAULT);
				card6.setBackground(new Background(myBI));
			} else {
				card6.setText(
						Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(5).getName());
			}
			if (!Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(6).getUrl()
					.equals("null")) {
				BackgroundImage myBI = new BackgroundImage(
						new Image(Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(6)
								.getUrl(), 100, 100, false, true),
						BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
						BackgroundSize.DEFAULT);
				card7.setBackground(new Background(myBI));
			} else {
				card7.setText(
						Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(6).getName());
			}

			nbbbbb++;

		} else {

			if (!Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(0).getUrl()
					.equals("null")) {
				BackgroundImage myBI = new BackgroundImage(
						new Image(Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(0)
								.getUrl(), 100, 100, false, true),
						BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
						BackgroundSize.DEFAULT);
				card1.setBackground(new Background(myBI));
			} else {
				card1.setText(
						Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(0).getName());
			}
			if (!Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(1).getUrl()
					.equals("null")) {
				BackgroundImage myBI = new BackgroundImage(
						new Image(Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(1)
								.getUrl(), 100, 100, false, true),
						BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
						BackgroundSize.DEFAULT);
				card2.setBackground(new Background(myBI));
			} else {
				card2.setText(
						Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(1).getName());
			}
			if (!Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(2).getUrl()
					.equals("null")) {
				BackgroundImage myBI = new BackgroundImage(
						new Image(Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(2)
								.getUrl(), 100, 100, false, true),
						BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
						BackgroundSize.DEFAULT);
				card3.setBackground(new Background(myBI));
			} else {
				card3.setText(
						Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(2).getName());
			}
			if (!Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(3).getUrl()
					.equals("null")) {
				BackgroundImage myBI = new BackgroundImage(
						new Image(Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(3)
								.getUrl(), 100, 100, false, true),
						BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
						BackgroundSize.DEFAULT);
				card4.setBackground(new Background(myBI));
			} else {
				card4.setText(
						Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(3).getName());
			}
			if (!Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(4).getUrl()
					.equals("null")) {
				BackgroundImage myBI = new BackgroundImage(
						new Image(Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(4)
								.getUrl(), 100, 100, false, true),
						BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
						BackgroundSize.DEFAULT);
				card5.setBackground(new Background(myBI));
			} else {
				card5.setText(
						Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(4).getName());
			}
			if (!Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(5).getUrl()
					.equals("null")) {
				BackgroundImage myBI = new BackgroundImage(
						new Image(Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(5)
								.getUrl(), 100, 100, false, true),
						BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
						BackgroundSize.DEFAULT);
				card6.setBackground(new Background(myBI));
			} else {
				card6.setText(
						Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(5).getName());
			}
			if (!Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(6).getUrl()
					.equals("null")) {
				BackgroundImage myBI = new BackgroundImage(
						new Image(Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(6)
								.getUrl(), 100, 100, false, true),
						BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
						BackgroundSize.DEFAULT);
				card7.setBackground(new Background(myBI));
			} else {
				card7.setText(
						Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(6).getName());
			}

			System.out.println(Serveur.getInstance().partie().getNb_player());

			Browser browser = new Browser();
			BrowserView browserView = new BrowserView(browser);

			browser.loadURL("http://localhost:8080/spring4ajax?p=" + nbbbbb);

			map.getChildren().add(browserView);

			nbbbbb++;
		}

	}

	@FXML
	public void use_card(ActionEvent event) {
		if (event.getSource() == card1) {
			// list_player.get(0).use_card(0);
			if (Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(0).getName()
					.contains("25"))
				Serveur.getInstance().partie().getList_Player().get(nbbbbb).setRadius(25 * 1000);
			if (Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(0).getName()
					.contains("50"))
				Serveur.getInstance().partie().getList_Player().get(nbbbbb).setRadius(50 * 1000);
			if (Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(0).getName()
					.contains("75"))
				Serveur.getInstance().partie().getList_Player().get(nbbbbb).setRadius(75 * 1000);
			if (Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(0).getName()
					.contains("100"))
				Serveur.getInstance().partie().getList_Player().get(nbbbbb).setRadius(100 * 1000);
			if (Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(0).getName()
					.contains("200"))
				Serveur.getInstance().partie().getList_Player().get(nbbbbb).setRadius(200 * 1000);

		}
		if (event.getSource() == card2) {
			list_player.get(0).use_card(1);
			if (Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(1).getName()
					.contains("25"))
				Serveur.getInstance().partie().getList_Player().get(nbbbbb).setRadius(25 * 1000);
			if (Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(1).getName()
					.contains("50"))
				Serveur.getInstance().partie().getList_Player().get(nbbbbb).setRadius(50 * 1000);
			if (Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(1).getName()
					.contains("75"))
				Serveur.getInstance().partie().getList_Player().get(nbbbbb).setRadius(75 * 1000);
			if (Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(1).getName()
					.contains("100"))
				Serveur.getInstance().partie().getList_Player().get(nbbbbb).setRadius(100 * 1000);
			if (Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(1).getName()
					.contains("200"))
				Serveur.getInstance().partie().getList_Player().get(nbbbbb).setRadius(200 * 1000);

		}
		if (event.getSource() == card3) {
			list_player.get(0).use_card(2);
			if (Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(2).getName()
					.contains("25"))
				Serveur.getInstance().partie().getList_Player().get(nbbbbb).setRadius(25 * 1000);
			if (Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(2).getName()
					.contains("50"))
				Serveur.getInstance().partie().getList_Player().get(nbbbbb).setRadius(50 * 1000);
			if (Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(2).getName()
					.contains("75"))
				Serveur.getInstance().partie().getList_Player().get(nbbbbb).setRadius(75 * 1000);
			if (Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(2).getName()
					.contains("100"))
				Serveur.getInstance().partie().getList_Player().get(nbbbbb).setRadius(100 * 1000);
			if (Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(2).getName()
					.contains("200"))
				Serveur.getInstance().partie().getList_Player().get(nbbbbb).setRadius(200 * 1000);

		}
		if (event.getSource() == card4) {
			list_player.get(0).use_card(3);
			if (Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(3).getName()
					.contains("25"))
				Serveur.getInstance().partie().getList_Player().get(nbbbbb).setRadius(25 * 1000);
			if (Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(3).getName()
					.contains("50"))
				Serveur.getInstance().partie().getList_Player().get(nbbbbb).setRadius(50 * 1000);
			if (Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(3).getName()
					.contains("75"))
				Serveur.getInstance().partie().getList_Player().get(nbbbbb).setRadius(75 * 1000);
			if (Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(3).getName()
					.contains("100"))
				Serveur.getInstance().partie().getList_Player().get(nbbbbb).setRadius(100 * 1000);
			if (Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(3).getName()
					.contains("200"))
				Serveur.getInstance().partie().getList_Player().get(nbbbbb).setRadius(200 * 1000);

		}
		if (event.getSource() == card5) {
			list_player.get(0).use_card(4);
			if (Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(4).getName()
					.contains("25"))
				Serveur.getInstance().partie().getList_Player().get(nbbbbb).setRadius(25 * 1000);
			if (Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(4).getName()
					.contains("50"))
				Serveur.getInstance().partie().getList_Player().get(nbbbbb).setRadius(50 * 1000);
			if (Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(4).getName()
					.contains("75"))
				Serveur.getInstance().partie().getList_Player().get(nbbbbb).setRadius(75 * 1000);
			if (Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(4).getName()
					.contains("100"))
				Serveur.getInstance().partie().getList_Player().get(nbbbbb).setRadius(100 * 1000);
			if (Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(4).getName()
					.contains("200"))
				Serveur.getInstance().partie().getList_Player().get(nbbbbb).setRadius(200 * 1000);

		}
		if (event.getSource() == card6) {
			list_player.get(0).use_card(5);
			if (Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(5).getName()
					.contains("25"))
				Serveur.getInstance().partie().getList_Player().get(nbbbbb).setRadius(25 * 1000);
			if (Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(5).getName()
					.contains("50"))
				Serveur.getInstance().partie().getList_Player().get(nbbbbb).setRadius(50 * 1000);
			if (Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(5).getName()
					.contains("75"))
				Serveur.getInstance().partie().getList_Player().get(nbbbbb).setRadius(75 * 1000);
			if (Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(5).getName()
					.contains("100"))
				Serveur.getInstance().partie().getList_Player().get(nbbbbb).setRadius(100 * 1000);
			if (Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(5).getName()
					.contains("200"))
				Serveur.getInstance().partie().getList_Player().get(nbbbbb).setRadius(200 * 1000);

		}
		if (event.getSource() == card7) {
			list_player.get(0).use_card(6);
			if (Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(6).getName()
					.contains("25"))
				Serveur.getInstance().partie().getList_Player().get(nbbbbb).setRadius(25 * 1000);
			if (Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(6).getName()
					.contains("50"))
				Serveur.getInstance().partie().getList_Player().get(nbbbbb).setRadius(50 * 1000);
			if (Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(6).getName()
					.contains("75"))
				Serveur.getInstance().partie().getList_Player().get(nbbbbb).setRadius(75 * 1000);
			if (Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(6).getName()
					.contains("100"))
				Serveur.getInstance().partie().getList_Player().get(nbbbbb).setRadius(100 * 1000);
			if (Serveur.getInstance().partie().getList_Player().get(nbbbbb).getList_card().get(6).getName()
					.contains("200"))
				Serveur.getInstance().partie().getList_Player().get(nbbbbb).setRadius(200 * 1000);
		}
	}

}
