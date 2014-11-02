package controller;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Shapes.Shape;
import model.Command;
import model.DisconnectCommand;
import model.UpdateClientCommand;

public class NetpaintServer {
	private ServerSocket socket;
	
	private ArrayList<Shape> shapes;	
	private HashMap<String, ObjectOutputStream> outputs; // map of all connected users' output streams
	
	/**
	 *	This thread reads and executes commands sent by a client
	 */
	private class ClientHandler implements Runnable{
		private ObjectInputStream input; // the input stream from the client
		
		public ClientHandler(ObjectInputStream input){
			this.input = input;
		}
		
		public void run() {
			try{
				while(true){
					// read a command from the client, execute on the server
					Command<NetpaintServer> command = (Command<NetpaintServer>)input.readObject();
					command.execute(NetpaintServer.this);
					
					// terminate if client is disconnecting
					if (command instanceof DisconnectCommand){
						input.close();
						return;
					}
				}
			} catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	/**
	 *	This thread listens for and sets up connections to new clients
	 */
	private class ClientAccepter implements Runnable{
		public void run() {
			try{
				while(true){
					// accept a new client, get output & input streams
					Socket s = socket.accept();
					ObjectOutputStream output = new ObjectOutputStream(s.getOutputStream());
					ObjectInputStream input = new ObjectInputStream(s.getInputStream());
					
					// read the client's name
					String clientName = (String)input.readObject();
					
					// map client name to output stream
					outputs.put(clientName, output);
					
					// spawn a thread to handle communication with this client
					new Thread(new ClientHandler(input)).start();
					
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public NetpaintServer(){
		this.shapes = new ArrayList<Shape>(); // create the list of shapes
		this.outputs = new HashMap<String, ObjectOutputStream>(); // setup the map
		
		try{
			// start a new server on port 9001
			socket = new ServerSocket(9001);
			System.out.println("NetpaintServer started on port 9001");
			
			// spawn a client accepter thread
			new Thread(new ClientAccepter()).start();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public void disconnect(String clientName) {
		try {
			outputs.get(clientName).close();
			outputs.remove(clientName);
		
		} catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void addShape(Shape shape){
		shapes.add(shape);
		updateClients();
	}
	
	public void updateClients(){
		UpdateClientCommand update = new UpdateClientCommand(shapes);
		try{
			for (ObjectOutputStream out : outputs.values()){
				out.writeObject(update);
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]){
		new NetpaintServer();
	}
	
}
