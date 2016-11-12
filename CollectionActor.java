import akka.actor.UntypedActor;

public class CollectionActor extends UntypedActor {
	private int count = 0;
	private int numberOfMessages
	
	public CollectionActor(int count, int numberOfMessages) {
		this.numberOfMessages = numberOfMessages;
		this.count = count;
	}
	
	@Override
	public void onReceive(Object message) {
	
		if(message instanceof FileCount) {
			if(fileCount != 0) { 
				
			} else {
				FileCount fileCount = (FileCount)fileCount;
				//TODO get this thing implemented
				count = fileCount.getNumber();
			}
		} else {
			// These messages are instances of Found
			Found found = (Found)message;
			System.out.println(found.getFilename());
			for(String line: found.getLines()) {
				System.out.println(line);
			}
			numberOfMessages ++;
			if(numberOfMessages == count) {
				Actors.registry.shutDownAll();
			}
		}
	}

}
