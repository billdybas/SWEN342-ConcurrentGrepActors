import java.util.*

public class Found {
	private ArrayList<String> foundLines;
	private String filename
	
	public Found(ArrayList<String> foundLines, String filename) {
		this.foundLines = foundLines;
		this.filename = filename;
	}
	
	public String getFilename() {
		return filename;
	}
	
	public ArrayList<String> getFoundLines() {
		return foundLines;
	}

}
