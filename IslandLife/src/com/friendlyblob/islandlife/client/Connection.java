package com.friendlyblob.islandlife.client;

import java.net.Socket;

public class Connection implements Runnable {
	Socket serverSocket = null;
	
	
	public Connection() {
		try {
			serverSocket = new Socket("127.0.0.1", 8080);
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			System.out.println("WOOHOO");
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}
