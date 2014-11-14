package implementation.objects;

import implementation.ConsoleObject;

import java.util.ArrayList;

public abstract class Regestry {

	private static ArrayList<ConsoleObject> Objects = new ArrayList<ConsoleObject>();

	public static ConsoleObject getObject(String name) {
		for (ConsoleObject s : Objects) {
			System.out.println(s.Name +" "+name);
			if (s.Name.equals(name)) {
				try {
					ConsoleObject d = s.getClass().newInstance();
					return d;
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	public static void regetser(ConsoleObject a){
		for(ConsoleObject s: Objects){
			if(s.getName().equals(a.getName())){
				System.out.println("Object exists");
				return;
			}
		}
		System.out.println("Object registerd");
		Objects.add(a);
	}

}
