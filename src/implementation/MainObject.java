package implementation;

import implementation.objects.Regestry;

import javax.imageio.spi.RegisterableService;

import Formating.Misc.segment;
import Formating.Strings;

public class MainObject extends ConsoleObject {

	public MainObject() {
		super("Main", null);

		regester(new ConsoleCommand("Exit", "exit", this));
		regester(new ConsoleCommand("Help", "help", this));

		created = true;
	}

	public void exit() {
		System.exit(0);
	}

	public void help() {
		System.out
				.println("The simple workings go so : \n You can type \"Comands()\" to see all the commands for the active object. \n The brackets are important seeing as they spesify a referance to a command or an object.");
	}

	@Override
	public String getName() {
		return Name;
	}

}
