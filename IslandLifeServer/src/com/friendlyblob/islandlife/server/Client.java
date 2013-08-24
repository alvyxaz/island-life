package com.friendlyblob.islandlife.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client implements Runnable{
	protected Socket clientSocket;
	
    public Client(Socket clientSocket){
        this.clientSocket = clientSocket;
    }
    
	@Override
	public void run() {
		try {
            InputStream input = clientSocket.getInputStream();
            OutputStream output = clientSocket.getOutputStream();
            output.close();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

}
