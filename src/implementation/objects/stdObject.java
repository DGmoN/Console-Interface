package implementation.objects;

import implementation.ConsoleCommand;
import implementation.ConsoleObject;

import java.lang.reflect.Method;

public class stdObject extends ConsoleObject {
	
	Class objectClass;
	Object instance;

	public stdObject(String ObjectName, String name, Object... InstanceArgs) {
		
		super(ObjectName, InstanceArgs);
		System.out.println(ObjectName +" "+name);
		try {
			objectClass = Class.forName(name);
			instance = objectClass.newInstance();

			for (Method s : objectClass.getMethods()) {
				regester(new ConsoleCommand(s.getName(), s.getName(), instance));
			}

			created = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// getConstructors()[0].newInstance(parse(objectClass.getConstructors()[0].getParameterTypes(),
	// InstanceArgs));
	private Object[] parse(Class[] targetTypes, Object[] input) {
		Object[] ret = new Object[targetTypes.length];
		int x = 0;
		for (Class ss : targetTypes) {
			if (input[x] != null)
				ret[x] = ss.cast(input[x]);
			x++;
		}
		return ret;
	}
}
