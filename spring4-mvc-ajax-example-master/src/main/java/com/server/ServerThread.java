package com.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javafx.controller.ObjectUtil;

public class ServerThread implements Runnable {
	private Socket socketCient;

	public ServerThread(Socket clientSocket, Serveur s) {
		this.socketCient = clientSocket;
	}

	@Override
	public void run() {

		
		
		try {
				System.out.println("afdnqlsdf");
				DataOutputStream outToClient = new DataOutputStream(socketCient.getOutputStream());
				outToClient.writeBytes("ta mere");
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

