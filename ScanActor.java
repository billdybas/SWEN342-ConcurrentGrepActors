import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

public class ScanActor extends UntypedActor {

	private List<String> matches = new ArrayList<String>();

	@Override
	public void onReceive(Object message) throws IllegalArgumentException {
		if (!(message instanceof Configure)) {
			throw new IllegalArgumentException("ScanActor expects a message of type Configure");
		}

		Configure c = ((Configure)message);
		String filename = c.getFilename();
		ActorRef actor = c.getActor();
		String regex = c.getRegex();

		Scanner scanner = null;

		try {
			if(filename == null) {
				scanner = new Scanner(System.in);
			} else {
				scanner = new Scanner(new File(filename));
			}

			int lineNumber = 1;
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if (Pattern.matches(regex, line)) {
					addOccurance(lineNumber, line);
				}
				lineNumber++;
			}
		} catch (FileNotFoundException e) {
			System.err.println("Can't find file " + filename);
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}

		actor.tell(new Found(matches, filename), actor);
	}

	private void addOccurance(int lineNumber, String line) {
		matches.add(lineNumber + " " + line);
	}
}
