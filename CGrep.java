import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CGrep {

	public static void main(String[] args) {
		// Display usage info if no args
		if (args.length == 0) {
			System.err.println("Usage: java CGrep pattern [file...]");
			return;
		}
		
		// Get the regex to match
		String regex = args[0];
		
		Map<String, InputStream> streams = new HashMap<String, InputStream>();

		// Map between filenames and InputStreams that can read those files
		if (args.length == 1) {
			// If there's no file names listed, we're using stdin

			// Use the empty string as the file name since actual files must have a non-empty name
			streams.put("", System.in);
		} else {
			// Otherwise, the rest of args is file names

			// Convert the file args to a List
			List<String> files = new ArrayList<String>(Arrays.asList(args));
			// Remove the Pattern argument
			files.remove(0);

			// Make FileInputStreams for each file
			for (String s: files) {
				try {
					streams.put(s, new FileInputStream(s));
				} catch (FileNotFoundException e) {
					System.err.println("Can't find file " + s + ". Skipping it.");
				}
			}
		}
		
		// Go through every stream and read its contents
		for (String name: streams.keySet()) {
			BufferedReader b = new BufferedReader(new InputStreamReader(streams.get(name)));

			List<String> contents = new ArrayList<String>();
			String line;
			try {
				while ((line = b.readLine()) != null) {
					contents.add(line);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			// TODO: ScanActor for this stream?
		}	
	}
}
