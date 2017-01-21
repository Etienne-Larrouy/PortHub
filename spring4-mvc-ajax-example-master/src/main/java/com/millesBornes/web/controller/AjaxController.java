package com.millesBornes.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.millesBornes.web.jsonview.Views;
import com.millesBornes.web.model.AjaxResponseBody;
import com.millesBornes.web.model.Coords;
import com.millesBornes.web.model.SearchCriteria;
import com.millesBornes.web.model.User;

@RestController
public class AjaxController {

	List<User> users;
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
	
}
