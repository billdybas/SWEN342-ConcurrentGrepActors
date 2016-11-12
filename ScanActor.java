import java.util.ArrayList;
import java.util.List;

import akka.actor.UntypedActor;

public class ScanActor extends UntypedActor {

	private List<String> matches = new ArrayList<String>();

	@Override
	public void onReceive(Object message) throws IllegalArgumentException {
		if (!(message instanceof Configure)) {
			throw new IllegalArgumentException("ScanActor expects a message of type Configure");
		}

		Configure c = ((Configure)message);

		if (c.getFilename() == null) {
			// TODO: Read from stdin
		} else {
			// TODO: Read from file
		}

		Object f = new Found(matches, c.getFilename());
		c.getActor().tell(f, c.getActor());
	}
}
