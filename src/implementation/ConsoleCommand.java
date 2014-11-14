package implementation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import observation.Breaker;
import Formating.Strings;

public class ConsoleCommand extends ConsoleObject {

	private final String Command;

	private Method Target = null;
	private Object TargetInstance;

	public ConsoleCommand(String Command, String MethodName, Object TargetClass) {
		super(MethodName);
		this.Command = Command;
		TargetInstance = TargetClass;
		for (Method s : TargetClass.getClass().getMethods()) {
			if (s.getName().equals(MethodName)) {
				Target = s;
				created = true;
			}
		}
		if(Target==null)
			System.err.println("[CONSOLECOMMAND][INIT][ERR] : Method does not exist");
		
	}

	@Override
	public void invoke(String[] args) {
		if (Breaker.showDebugData)
			System.out.println("[CONSOLECOMMAND]["+getName()+"][INVOLKE]");
		Class[] params =Target.getParameterTypes();
		Object[] Args = new Object[params.length];
		
		if(args.length<Args.length){
			System.err.println("Insufitient args");
			System.out.print("The propper syntax is : \n"+Command);
			for(Class s:params)
				System.out.print(s.getSimpleName()+" ");
			return;
		}
		int x = 0;
		while(x<Args.length){
			if(params[x].equals(String.class))
				Args[x] = args[x];
			if(params[x].equals(Integer.class))
				Args[x] = Integer.parseInt(args[x]);
			x++;
		}
		try {
			Target.invoke(TargetInstance, Args);
		} catch (Exception e) {
			System.out.println("[CONSOLECOMMAND]["+getName()+"][INVOLKE][ERROR] : ");
			e.printStackTrace();
		}
	}

	@Override
	public String getName() {
		return Command;
	}

	public String toString() {
		return "Command_" + Command;
	}

}
