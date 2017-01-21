package com.millesBornes.web.model;


/**
 * @author jojo-
 *
 */
public class Card_distance extends Card {
	
	private int distance;

	public Card_distance(String name,int distance) {
		super(name);
		// TODO Auto-generated constructor stub
		this.setDistance(distance);
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public void Action(Player player,int dist){
		player.setDistance(dist);	
	}
}
