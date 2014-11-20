package implementation;

import implementation.objects.Regestry;
import implementation.objects.stdObject;

import java.util.ArrayList;

import observation.Breaker;
import Formating.Strings;
import Formating.Misc.segment;

public class ConsoleObject extends ConsoleContainer {

	ConsoleObject(String Name) {
		super(Name);
	}

	public ConsoleObject(String Name, Object[] InstanceArgs) {
		super(Name);
		regester(new ConsoleCommand("newExternalObject", "makeObject", this));
		regester(new ConsoleCommand("newStdObject", "makeStdObject", this));
		regester(new ConsoleCommand("Commands", "listCommands", this));
		regester(new ConsoleCommand("Objects", "listObjects", this));
	}

	protected boolean created = false;

	public String getName() {
		return Name;
	}

	public void makeStdObject(String OName, String name, String[] args) {
		this.regester(new stdObject(OName, name, args));
	}

	public void makeObject(String Name) {
		regester(Regestry.getObject(Name));
	}

	public void listCommands() {
		System.out.println("Listing commands : \n");
		for (ConsoleObject ss : Objects) {
			if (ss.toString().contains("Command"))
				System.out.println(" "+ss.getName());
		}
	}

	public void listObjects() {
		System.out.println("Listing objects : ");
		for (ConsoleObject ss : Objects) {
			if (!ss.toString().contains("Command"))
				System.out.println(" "+ss.getName());
		}
	}

	public void invoke(String[] tree) {
		if (Breaker.showDebugData)
			System.out.println("[CONSOLEOBJECT][INVOLKE][" + getName()
					+ "][TREE] : " + getName() + ":\n"
					+ Strings.combine("\n", 1, tree));
		segment<String> TREE = new segment<String>(tree);
		if (TREE.getLenth() > 0) {
			String level = TREE.getNext();
			if (level.charAt(level.length() - 1) == ')') {
				invokeCommand(level.substring(0, level.lastIndexOf('(')),
						getComandArgs(level));
				return;
			} else {
				for (ConsoleObject ss : Objects) {
					if (ss.Name.equals(level)) {
						ss.invoke(Strings.objectArrToStrings(TREE
								.getRemaining()));
						return;
					}
				}
				System.err.println("[CONSOLEOBJECT][INVOLKE][" + getName()
						+ "][OBJECT_INVOLKE] : Object \"" + level
						+ "\" not found");
			}
		}
	}

	protected void invokeCommand(String commandName, String... args) {
		for (ConsoleObject ss : Objects) {
			if (ss.toString().equals("Command_" + commandName)) {
				ss.invoke(args);
				return;
			}
		}
		System.err.println("Command \"" + commandName + "\" not found");
	}

	// test DATA : Bind(Potato, pop, grape)
	private String[] getComandArgs(String ss) {
		String[] ret;
		int index = 1;
		String argsOnly = "";
		if (Breaker.showDebugData)
			System.out.println("[CONSOLEOBJECT][GETARGS][" + getName()
					+ "][DATAIN] : " + ss);
		if (Strings.charCountBetween('(', ')', ss) > 0) {
			argsOnly = Strings.extractBetween('(', ')', ss, 0);
			if (Breaker.showDebugData) {
				System.out.println("[CONSOLEOBJECT][GETARGS][" + getName()
						+ "][INFO] : Has args");
				System.out.println("[CONSOLEOBJECT][GETARGS][" + getName()
						+ "][INFO] : Extracted args = " + argsOnly);
			}

			for (char x : argsOnly.toCharArray()) {
				if (x == ',')
					index++;
			}
		} else
			index = 0;

		if (Breaker.showDebugData)
			System.out.println("[CONSOLEOBJECT][GETARGS][" + getName()
					+ "][INFO] : Arg Count " + index);

		ret = new String[index];

		index = 0;
		while (index < ret.length) {
			if (index == 0)
				ret[index] = Strings.extractBetween(' ', ',', argsOnly, index);
			else
				ret[index] = Strings.extractBetween(',', ',', argsOnly, index-1);
			if (Breaker.showDebugData)
				System.out.println("[CONSOLEOBJECT][GETARGS][" + getName()
						+ "][INFO] : Arg extracted " + ret[index]);
			index++;
		}
		return ret;
	}
}
