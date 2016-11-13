import java.util.List;

public class Found {
	private final List<String> foundLines;
	private final String filename;

	public Found(List<String> foundLines, String filename) {
		this.foundLines = foundLines;
		this.filename = filename;
	}

	public String getFilename() {
		return filename;
	}

	public List<String> getFoundLines() {
		return foundLines;
	}

}
