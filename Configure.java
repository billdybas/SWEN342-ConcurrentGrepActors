import akka.actor.ActorRef;

public class Configure {

	private final String filename;
	private final ActorRef actor;

	public Configure(String filename, ActorRef actor) {
		this.filename = filename;
		this.actor = actor;
	}

	public String getFilename() {
		return filename;
	}

	public ActorRef getActor() {
		return actor;
	}
}
