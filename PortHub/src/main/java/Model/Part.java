package Model;

import java.util.ArrayList;

public class Part {
	
	//Attributes
	private ArrayList <Player> list_Player;
	private ArrayList<Card> pack_card;
	private String IP;
	private int nb_player;
	

	public Part(String IP,int nb_player){
		
		this.IP = IP;
		this.setNb_player(nb_player);
		this.list_Player = new ArrayList<>(nb_player);
		init_pack_card();
	}
	
	public void add_player(Player player){
		this.list_Player.add(player);
	}

	public ArrayList <Player> getList_Player() {
		return list_Player;
	}

	public void setList_Player(ArrayList <Player> list_Player) {
		this.list_Player = list_Player;
	}

	public String getIP() {
		return IP;
	}
	
	public void init_pack_card(){
		this.pack_card = new ArrayList<>(85);
		
		
		
		for(int i=0;i<10;i++){
			this.pack_card.add(new Card_distance("25kms", 25));
		}
		for(int i=0;i<10;i++){
			this.pack_card.add(new Card_distance("50kms", 50));
		}
		for(int i=0;i<10;i++){
			this.pack_card.add(new Card_distance("75kms", 75));
		}
		for(int i=0;i<12;i++){
			this.pack_card.add(new Card_distance("100kms", 100));
		}
		for(int i=0;i<4;i++){
			this.pack_card.add(new Card_distance("200kms", 200));
		}
		//46 cartes
		for(int i=0;i<3;i++){
			this.pack_card.add(new Card_Malus("malus", "accidente"));
		}
		for(int i=0;i<3;i++){
			this.pack_card.add(new Card_Malus("malus", "snow"));
		}
		for(int i=0;i<5;i++){
			this.pack_card.add(new Card_Malus("malus", "traffic"));
		}
		for(int i=0;i<3;i++){
			this.pack_card.add(new Card_Malus("malus", "manif"));
		}
		for(int i=0;i<4;i++){
			this.pack_card.add(new Card_Malus("malus", "fast"));
		}
		//64 cartes
		for(int i=0;i<3;i++){
			this.pack_card.add(new Card_bonus("bonus", "pickpocket"));
		}
		for(int i=0;i<6;i++){
			this.pack_card.add(new Card_bonus("bonus", "coffee"));
		}
		for(int i=0;i<6;i++){
			this.pack_card.add(new Card_bonus("bonus", "bouffe"));
		}
		for(int i=0;i<6;i++){
			this.pack_card.add(new Card_bonus("bonus", "car"));
		}
		//85	
		System.out.println("pacquet initialis�");
	}

	public Card give_card(){
		int nb = (int) (Math.random()*(pack_card.size()-1));
		Card card = this.pack_card.get(nb);
		this.pack_card.remove(nb);
		return card;

	}
	
	public int nb_card(){
		
		
		return pack_card.size();
	}
	
	public ArrayList<Card> getList_card() {
		return pack_card;
	}
	
	public Card get_card(int num_card){
		return pack_card.get(num_card);
	}

	public void setList_card(ArrayList<Card> list_card) {
		this.pack_card = list_card;
	}

	public int getNb_player() {
		return nb_player;
	}

	public void setNb_player(int nb_player) {
		this.nb_player = nb_player;
	}

	public ArrayList<Card> getPack_card() {
		return pack_card;
	}

	public void setPack_card(ArrayList<Card> pack_card) {
		this.pack_card = pack_card;
	}
	
	
	

}
