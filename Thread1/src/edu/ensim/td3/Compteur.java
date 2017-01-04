package edu.ensim.td3;

import java.util.Random;

public class Compteur extends Thread {
	
	Random r = new Random();
	private String name = "vide";
	public Compteur(String nom) {
		name = nom;
	}
	public void run() {
		for (int i =0;i < 10;i++) {
			System.out.println(name + " : " + i);
			try {
				sleep(r.nextInt(5000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		System.out.println(name + " a fini de compter jusqu'a 10");
	}

}
