package model;

import Shapes.Shape;
import controller.NetpaintServer;

public class AddObjectCommand extends Command<NetpaintServer>{
	private Shape shape;
	
	public AddObjectCommand(Shape shape){
		this.shape = shape;
	}

	@Override
	public void execute(NetpaintServer executeOn) {
		executeOn.addShape(shape);
	}
	
	
}
