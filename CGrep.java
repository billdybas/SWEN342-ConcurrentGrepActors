import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class CGrep {

	public static void main(String[] args) {
		// Display usage info if no args
		if (args.length == 0) {
			System.err.println("Usage: java CGrep pattern [file...]");
			return;
		}

		// Get the regex to match
		String regex = args[0];

		// Create an ActorSystem
		ActorSystem system = ActorSystem.create("CGrep");
		// Create the CollectionActor
		ActorRef collectionActor = system.actorOf(Props.create(CollectionActor.class));
		// Create the ScanActor
		ActorRef scanActor = system.actorOf(Props.create(ScanActor.class));

		if (args.length > 1) {
			List<String> files = new ArrayList<String>(Arrays.asList(args));
			files.remove(0); // Remove pattern argument

			// Tell the CollectionActor the number of files to read from
			collectionActor.tell(new FileCount(files.size()), ActorRef.noSender());

			// Tell the ScanActor the files to read
			for (String file: files) {
				scanActor.tell(new Configure(file, collectionActor, regex), ActorRef.noSender());
			}
		} else {
			// Tell the ScanActor to read from stdin
			scanActor.tell(new Configure(null, collectionActor, regex), ActorRef.noSender());
		}
	}
}
