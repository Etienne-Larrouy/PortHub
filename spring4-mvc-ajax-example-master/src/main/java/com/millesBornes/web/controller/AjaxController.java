package com.millesBornes.web.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.millesBornes.web.jsonview.Views;
import com.millesBornes.web.model.AjaxResponseBody;
import com.millesBornes.web.model.Coords;
import com.millesBornes.web.model.PagesJaunes;

@RestController
public class AjaxController {


	Coords c;
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/search/api/getCLickCoords")
	public AjaxResponseBody getClickCoords(@RequestBody Coords clickCoords) {
		System.out.println(clickCoords.getLat());
		System.out.println(clickCoords.getLng());
		AjaxResponseBody result = new AjaxResponseBody();
		Coords c = new Coords();
		c.setLat(clickCoords.getLat());
		c.setLng(clickCoords.getLng());
		result.setCode("200");
		result.setMsg("Ca marche !!");
		result.setResult(c);
		return result;
	
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
