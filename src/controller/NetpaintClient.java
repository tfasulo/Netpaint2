package controller;

import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.EOFException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Shapes.Shape;
import model.Command;
import model.DisconnectCommand;
import view.NetpaintGUI;

public class NetpaintClient{
	
	private String clientName;
	private NetpaintGUI netpaintGUI;
	
	private Socket server;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	private class ServerHandler implements Runnable{

		@Override
		public void run() {

			try{
				while(true){
					Command<NetpaintClient> c = (Command<NetpaintClient>) in.readObject();
					c.execute(NetpaintClient.this);
				}
			}
			catch(SocketException e){
				return;
			}
			catch(EOFException e){
				return;
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public NetpaintClient(){
		
		String host = JOptionPane.showInputDialog("Host address:");
		String port = JOptionPane.showInputDialog("Host port:");
		clientName = JOptionPane.showInputDialog("Username:");
		
		if(host == null || port == null || clientName == null){
			return;
		}
		
		try{
			server = new Socket(host, Integer.parseInt(port));
			out = new ObjectOutputStream(server.getOutputStream());
			in = new ObjectInputStream(server.getInputStream());
			setupGUI();
			
			out.writeObject(clientName);
			
			netpaintGUI.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent ev){
					try{
						out.writeObject(new DisconnectCommand(clientName));
						out.close();
						in.close();
					}
					catch(Exception e){
						e.printStackTrace();
					}
				}
			});
			
			
			
			new Thread(new ServerHandler()).start();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void setupGUI(){
		netpaintGUI = new NetpaintGUI(out);
	}
	

	
	public static void main(String args[]){
		new NetpaintClient();
	}

	public void update(ArrayList<Shape> shapes) {
		netpaintGUI.setShapes(shapes);
	}


}
