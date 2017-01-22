package com.millesBornes.web.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.millesBornes.web.jsonview.Views;
import com.millesBornes.web.model.AjaxResponseBody;
import com.millesBornes.web.model.AjaxResponseInit;
import com.millesBornes.web.model.Move;
import com.millesBornes.web.model.Part;
import com.millesBornes.web.model.Player;
import com.millesBornes.web.model.PagesJaunes;
import com.server.Serveur;

@RestController
public class AjaxController{
	Serveur s = Serveur.getInstance();
	
	String proche;

	Move c;
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/search/api/getCLickCoords")

	public AjaxResponseInit getClickCoords(@RequestBody Move clickCoords) {
	
		System.out.println(clickCoords.getLat());
		System.out.println(clickCoords.getLng());
		System.out.println(clickCoords.getDistance());
		System.out.println(clickCoords.getIdPlayer());
		
		s.partie().getList_Player().get(clickCoords.getIdPlayer()).setLat(clickCoords.getLat());
		s.partie().getList_Player().get(clickCoords.getIdPlayer()).setLng(clickCoords.getLng());
		
		s.partie().getList_Player().get(clickCoords.getIdPlayer()).setState(false);
		
		int nextPlayer = clickCoords.getIdPlayer()+1;
		
		if(nextPlayer == (s.partie().getNb_player())){
			nextPlayer = 0;
		}
			
		System.out.println("Au tour de "+nextPlayer);
		s.partie().getList_Player().get(nextPlayer).setState(true);
		
		s.partie().getList_Player().get(0).setDistance(s.partie().getList_Player().get(0).getDistance()+clickCoords.getDistance());
		
		AjaxResponseInit partie = new AjaxResponseInit();
		
		partie.setResult(s.partie());
		return partie;
	
	}
	
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/search/api/getInfo")
	public AjaxResponseInit getInfo() {
		Serveur s = Serveur.getInstance();
		
		AjaxResponseInit partie = new AjaxResponseInit();

		partie.setResult(s.partie());
		return partie;
	}
	
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/search/api/initMap")
	public AjaxResponseInit initMap() {
		Serveur s = Serveur.getInstance();
	System.out.println("Création partie");
		s.setPartie(new Part("", 2));
		Player p1 = new Player("YETI1");
		Player p2 = new Player("YETI2");

		p1.setState(true);
		s.partie().add_player(p1);
		s.partie().add_player(p2);
	
		p1.setLat(s.partie().getDepartLatitude());
		p1.setLng(s.partie().getDepartLongitude());
		
		p2.setLat(s.partie().getDepartLatitude());
		p2.setLng(s.partie().getDepartLongitude());
		
		AjaxResponseInit partie = new AjaxResponseInit();
		
		partie.setResult(s.partie());
		return partie;
	}
	
	// @ResponseBody, not necessary, since class is annotated with @RestController
		// @RequestBody - Convert the json data into object (SearchCriteria) mapped by field name.
		// @JsonView(Views.Public.class) - Optional, limited the json data display to client.
		@JsonView(Views.Public.class)
		@RequestMapping(value = "/search/api/test")
		public PagesJaunes test(@RequestBody Move clickCoords) throws IOException {

			PagesJaunes result = new PagesJaunes();
			result.chargerLocations("cZ"+clickCoords.getLng()+","+clickCoords.getLat());
			
			for(int i =0 ; i < result.getListTotal().size(); i++){
				System.out.println(result.getListTotal().get(i));
			}
			System.out.println(result.getListTotal().size());
			
			return result;

		}
		
		@JsonView(Views.Public.class)
		@RequestMapping(value = "/search/api/proche")
		public Move proche(@RequestBody Move clickCoords) throws IOException {	
			Move result=new Move();
			result.setProche(clickCoords.getProche());
			proche=clickCoords.getProche();
			System.out.println("Proche de "+proche+" ! ");
			return result;
		}

	
}