package com.millesBornes.web.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.millesBornes.web.jsonview.Views;
import com.millesBornes.web.model.AjaxResponseBody;
import com.millesBornes.web.model.Move;
import com.millesBornes.web.model.PagesJaunes;
import com.server.Server;

@RestController
public class AjaxController {


	Move c;
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/search/api/getCLickCoords")
	public AjaxResponseBody getClickCoords(@RequestBody Move clickCoords) {
		Server s = Server.getInstance();
		
		System.out.println(clickCoords.getLat());
		System.out.println(clickCoords.getLng());
		System.out.println(clickCoords.getDistance());
		
		s.getPlayer().setDistance(s.getPlayer().getDistance()+clickCoords.getDistance());;
		
		AjaxResponseBody move = new AjaxResponseBody();
		Move c = new Move();
		c.setLat(clickCoords.getLat());
		c.setLng(clickCoords.getLng());
		c.setRadius(5000);
		move.setCode("200");
		move.setMsg("Ca marche !!");
		move.setResult(c);
		return move;
	
	}
	
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/search/api/initMap")
	public AjaxResponseBody initMap() {
		Server s = Server.getInstance();
		
		return s.getPartie();
	}
	

	// @ResponseBody, not necessary, since class is annotated with @RestController
	// @RequestBody - Convert the json data into object (SearchCriteria) mapped by field name.
	// @JsonView(Views.Public.class) - Optional, limited the json data display to client.
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/search/api/test")
	public PagesJaunes test() throws IOException {

		PagesJaunes result = new PagesJaunes();
		result.chargerLocations("cZ-4.062577,48.520603");
		
		for(int i =0 ; i < result.getListTotal().size(); i++){
			System.out.println(result.getListTotal().get(i));
		}
		System.out.println(result.getListTotal().size());
		
		return result;

	}
	
}
