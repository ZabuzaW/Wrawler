package de.zabuza.webcrawler.tools;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Utility tool for slot parsing.
 * @author Zabuza
 *
 */
public final class SlotTypeParseTool {
	/**
	 * Path to the file that contains external data.
	 */
	private static final String FILEPATH = "C:\\Users\\Zabuza\\Desktop\\";
	
	/**
	 * Utility class. No implementation.
	 */
	private SlotTypeParseTool() {

	}
	/**
	 * Gets the content of a file and returns it as list of lines.
	 * @param path Path to the file
	 * @return List of lines from the content
	 * @throws IOException If an I/O-Exception occurs
	 */
	public static List<String> getFileContent(String path) throws IOException {
		BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
		List<String> content = new ArrayList<String>();
		
		String line = file.readLine();
		while (line != null){
			content.add(line);
			line = file.readLine();
		}
		
		file.close();
		return content;
	}
	
	/**
	 * Gets the content of a slot type file and generates code for it.
	 * @param args
	 *            Not supported
	 * @throws IOException 
	 */
	public static void main(final String[] args) throws IOException {
		String filename = "1to62.csv";
		List<String> list = getFileContent(FILEPATH + filename);
		
		String header = "//Generated by SlotParseTool : " + filename;
		System.out.println(header);
		
		Set<String> set = new HashSet<String>(list.size());
		for (String line : list) {
			String[] data = line.split(";");
			if (set.add(data[0])) {
				System.out.println("generatedMap.put(\"" + data[0] + "\", " + "SlotType." + data[1] + ");");
			}
		}
	}
}
