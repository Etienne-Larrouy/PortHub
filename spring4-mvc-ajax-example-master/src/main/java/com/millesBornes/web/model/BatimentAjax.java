package com.millesBornes.web.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.millesBornes.web.jsonview.Views;

public class BatimentAjax {

	
	@JsonView(Views.Public.class)
	List<Batiment> listeBatiment;
	
	public BatimentAjax(){
		listeBatiment=new ArrayList<Batiment>();
	}

	public List<Batiment> getListeBatiment() {
		return listeBatiment;
	}

	public void setListeBatiments(List<Batiment> listeBatiment) {
		this.listeBatiment = listeBatiment;
	}
	
	public void ajouterBatiment(Batiment batiment){
		listeBatiment.add(batiment);
	}



}
