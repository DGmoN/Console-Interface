package observation;

import implementation.MainObject;
import implementation.objects.BasicObject;
import implementation.objects.Regestry;

import java.io.InputStream;
import java.rmi.registry.Registry;
import java.util.Scanner;

import Formating.Strings;
import Formating.Strings.LINES;

/**
 * Standard comand format => command(args...) Object.Command(args...) and so on;
 * 
 * Visibility of objects can be disabled but if they are disabled the will not
 * be enabled again simply
 * 
 * **/

public class Breaker {
	public static Breaker INSTANCE;
	public static boolean showDebugData;

	private static MainObject mainField;

	private Thread fetcher = new Thread() {
		@Override
		public void run() {
			String read = null;
			while (true) {
				read = lineReader.nextLine();
				prosses(read);

			}
		}
	};

	Scanner lineReader;

	// Constructor to initialize with default input stream
	public Breaker() {
		new Breaker(System.in);
	}

	// Constructor to initialize with custom input stream
	public Breaker(InputStream is) {
		System.out.println("[ConsoleControl][Breaker][Init] : Starting...");
		lineReader = new Scanner(is);
		fetcher.start();
		INSTANCE = this;
		mainField = new MainObject();
		System.out.println("[ConsoleControl][Breaker][Init] : Done");
	}

	void prosses(String s) {
		if (showDebugData)
			System.out
					.println("[DEBUG][ConsoleControl][BREAKER][PROSESS] : DataIn = "
							+ s);
		Strings.LINES objects = new LINES(); // Goes deeper the larger the index
		String buffer = "";
		boolean inBrackets = false;
		for (char x : s.toCharArray()) {
			
			if(x=='(')
				inBrackets = true;
			else if(x==')')
				inBrackets = false;
			
			if (Strings.isSymbol(x)) {
				if (x == '.'&&!inBrackets) {
					objects.add(buffer);
					if (showDebugData)
						System.out
								.println("[DEBUG][ConsoleControl][BREAKER][PROSESS] : SegmentFound : "
										+ buffer);
					buffer = "";
				}
			}
			if (x != '.'||inBrackets)
				buffer += x;
		}

		if (!buffer.equals("")) {
			objects.add(buffer);
			if (showDebugData)
				System.out
						.println("[DEBUG][ConsoleControl][BREAKER][PROSESS] : SegmentsFound : "
								+ buffer);
			buffer = "";
		}

		if (showDebugData)
			System.out
					.println("[DEBUG][ConsoleControl][BREAKER][PROSESS] : SegementsDetected : \n"
							+ objects.getAllAsSingle());
		mainField.invoke(objects.getAllLines());

	}

	public static void main(String[] args) {
		if (args.length > 0)
			if (args[0].equals("-d"))
				showDebugData = true;
		System.out.println("DEBUG = " + showDebugData);
		Regestry.regetser(new BasicObject());
		new Breaker();
	}
}
