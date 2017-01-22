package com.millesBornes.web.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.millesBornes.web.jsonview.Views;
import com.server.Serveur;

public class AjaxResponseBody {
	
	@JsonView(Views.Public.class)
	String msg;
	@JsonView(Views.Public.class)
	String code;
	@JsonView(Views.Public.class)
	Move result;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setResult(Move result) {
		this.result = result;
	}
	
	@Override
	public String toString() {
		return "AjaxResponseResult [msg=" + msg + ", code=" + code + ", result=" + result + "]";
	}

}
