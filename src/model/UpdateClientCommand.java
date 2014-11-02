package model;

import java.util.ArrayList;

import Shapes.Shape;
import controller.NetpaintClient;

public class UpdateClientCommand extends Command<NetpaintClient>{
	private ArrayList<Shape> shapes;

	public UpdateClientCommand(ArrayList<Shape> shapes){
		this.shapes = new ArrayList<Shape>(shapes);
	}

	@Override
	public void execute(NetpaintClient executeOn) {
		// TODO Auto-generated method stub
		executeOn.update(shapes);
	}
	
	

}
