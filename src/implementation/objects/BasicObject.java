package implementation.objects;

import implementation.ConsoleCommand;
import implementation.ConsoleObject;

public class BasicObject extends ConsoleObject{

	public BasicObject() {
		super("Basic", null);
		regester(new ConsoleCommand("Works", "works", this));
		created = true;
	}

	public void works(){
		System.out.println("WORKS!");
	}
	
	
}
