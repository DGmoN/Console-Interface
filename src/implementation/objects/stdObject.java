package implementation.objects;

import implementation.ConsoleCommand;
import implementation.ConsoleObject;

import java.lang.reflect.Method;

public class stdObject extends ConsoleObject {

	Class objectClass;
	Object instance;

	public stdObject(String ObjectName, String name, Object... InstanceArgs) {
		super(ObjectName, InstanceArgs);
		
	}

	public Class[] getConstructorData(Object[] ss) {
		Class[] ret = new Class[ss.length];
		int x = 0;
		for (Object s : ss) {
			ret[x++] = s.getClass();
		}
		return ret;
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
