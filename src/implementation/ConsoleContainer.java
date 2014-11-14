package implementation;

import java.util.ArrayList;

import observation.Breaker;

public abstract class ConsoleContainer {
	
	protected  ArrayList<ConsoleObject> Objects = new ArrayList<ConsoleObject>();
	
	public String Name;
		
	public void regester(ConsoleObject a){
		if (a.created){
		for(ConsoleObject e:Objects){
			if(e.getName().equals(a.getName())){
				System.err.println("The console object \""+a.getName()+"\" is regesterd");
				return;
			}
		}
		if(Breaker.showDebugData)
			System.out.println("[ConsoleRegesty][Regester] : Regsesterd ConsoleObject : "+a.getName());

		Objects.add(a);
		}else{
			System.err.println("[ConsoleRegesty][Regester] : Object not created proporly : "+a.getName());
		}
	}
	
	ConsoleContainer(String Name){
		this.Name = Name;
	}
	
}
