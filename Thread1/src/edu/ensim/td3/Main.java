package edu.ensim.td3;

public class Main {

	public static void main(String[] args) {
		
		Thread t1 = new Compteur("toto");
		Thread t2 = new Compteur("titi");
		Thread t3 = new Compteur("tata");
		Thread t4 = new Compteur("tutu");
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();

	}

}
