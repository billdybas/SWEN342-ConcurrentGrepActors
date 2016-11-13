import akka.actor.UntypedActor;

public class CollectionActor extends UntypedActor {
	private int count = -1;
	private int numberOfMessages;

	@Override
	public void onReceive(Object message) {
		if (message instanceof FileCount) {
			if (count != -1) {
				throw new IllegalStateException("FileCount has already been received");
			}

			// These messages are instances of FileCount
			FileCount fc = (FileCount)message;
			count = fc.getNumberOfFiles();
		} else if (message instanceof Found) {
			// These messages are instances of Found
			Found f = (Found)message;

			// Print out the filename and the lines found
			System.out.println(f.getFilename() != null ? f.getFilename() : "-");
			for (String line: f.getFoundLines()) {
				System.out.println(line);
			}

			numberOfMessages++;
			// Shutdown the system when all messages have been received
			if (numberOfMessages == count) {
				getContext().system().terminate();
			}
		} else {
			throw new IllegalArgumentException("CollectionActor expects a message of type FileCount or Found");
		}
	}

}
