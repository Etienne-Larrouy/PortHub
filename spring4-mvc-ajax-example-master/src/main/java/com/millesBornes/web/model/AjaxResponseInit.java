package com.millesBornes.web.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.millesBornes.web.jsonview.Views;

public class AjaxResponseInit {
	
	@JsonView(Views.Public.class)
	Part result;	

	public void setResult(Part result) {
		this.result = result;
	}
	
	@Override
	public String toString() {
		return "AjaxResponseResult [partie=" + result + "]";
	}
}
