package de.dhbw.goerzig.socket;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer implements Runnable{
	private Socket socket = null;
	private ServerSocket server = null;
	private DataInputStream streamIn = null;
	private Thread thread = null;
	
	public ChatServer(int port){
		try{
			this.server = new ServerSocket(port);
			socket = server.accept();
			open();
			boolean done = false;
			while(!done){
				try{  
					String line = streamIn.readUTF();
					System.out.println(line);
					done = line.equals(".bye");
	            }
	            catch(IOException ioe){  
	            	done = true;
	            }
	        }
		close();
		}
      catch(IOException ioe){  
    	  System.out.println(ioe); 
      }
	}
	
	private void open() throws IOException{
		streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
	}
	public void close() throws IOException{  
		if (socket != null)    socket.close();
	    if (streamIn != null)  streamIn.close();
	}
   public static void main(String args[]){  
	  ChatServer server = null;
      server = new ChatServer(4321);
   }

	public void run() {
		
	}
	public void start(){
		if(thread == null){
			thread = new Thread(this);
			thread.run();
		}
	}
	public void stop(){
		
	}
}

