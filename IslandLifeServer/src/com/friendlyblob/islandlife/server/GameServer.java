package com.friendlyblob.islandlife.server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameServer extends Thread implements ActionListener{
	private int serverPort = 8080;
    private ServerSocket serverSocket = null;
    private boolean isStopped = false;
    private ExecutorService threadPool;
    
    public GameServer(int port){
    	this.setName("Island Life Game Server");
        this.serverPort = port;
        threadPool = Executors.newFixedThreadPool(1000);
    }
    
	@Override
	public void run() {
	    openServerSocket();
        while(! isStopped){

            Socket clientSocket = null;
            
            try {
                clientSocket = this.serverSocket.accept();
                this.serverSocket.accept();
            } catch (IOException e){
                if (isStopped){
                    System.out.println("Server Stopped.");
                    return;
                }
                throw new RuntimeException(
                        "Error accepting client connection", e);
            }
            
            this.threadPool.execute(
                new Client(clientSocket));

        }
        this.threadPool.shutdown();
        
	}
	
	private void openServerSocket(){
        try {
            this.serverSocket = new ServerSocket(this.serverPort);
        } catch (IOException e) {
            throw new RuntimeException("Cannot open port" + this.serverPort, e);
        }
    }
	
	public static void main(String [] args){
		GameServer server = new GameServer(8080);
		GUI gui = new GUI(server);
		server.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.isStopped = true;
	}

}
