import akka.actor.ActorRef;

public class Configure {

	private final String filename;
	private final ActorRef actor;
	private final String regex;

	public Configure(String filename, ActorRef actor, String regex) {
		this.filename = filename;
		this.actor = actor;
		this.regex = regex;
	}

	public String getFilename() {
		return filename;
	}

	public ActorRef getActor() {
		return actor;
	}

	public String getRegex() {
		return regex;
	}
}
