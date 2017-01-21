package Model;

import java.util.ArrayList;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Part partie = new Part("localhost",4);
		ArrayList<Player> list_player = new ArrayList<>();
		
	
		list_player.add(new Player("jojo"));
		list_player.add(new Player("antoine"));
		list_player.add(new Player("clement"));
		list_player.add(new Player("etienne"));

		
		partie.add_player(list_player.get(0));
		partie.add_player(list_player.get(1));
		partie.add_player(list_player.get(2));
		partie.add_player(list_player.get(3));
		
		System.out.println("taille avant distribution "+partie.nb_card());
		
		//beggining part
		//give 7 cars for players
		System.out.println("nb players = "+partie.getNb_player());
		for(int j=0;j<partie.getNb_player();j++){
			System.out.println("-----------------------------Player "+j);
			for(int i = 0;i<7;i++){
				Card card = partie.give_card();
				list_player.get(j).add_card(card);
				
			}
		}
		
		System.out.println("taille apr�s distribution "+partie.nb_card());
		
		//Use a card
		
		list_player.get(0).display_list_card();
		System.out.println("\n\n");
		list_player.get(0).use_card(2);
		
		
		
		
		
	}

}
