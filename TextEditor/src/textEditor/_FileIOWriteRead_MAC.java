package textEditor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class _FileIOWriteRead_MAC {

	public static void main(String[] args) {

		
		// --- neue Datei im Wunschpfad erstellen ---
		File file = new File("/Users/a1903/desktop/textDatei.txt");

		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// --- Programmablauf inkl. Methodenaufruf ---
		
		String fileData = readFileData(file);
		System.out.println(fileData);
		String updatedData = fileData + "Hi U27!";
		writeDataToFile(file, updatedData);
		readFileData(file);

	}// main

	private static String readFileData(File file) {
		String textAdd = "";

		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				textAdd = textAdd + scanner.nextLine() + "\n";
				// System.out.println(data);
			}
			return textAdd;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return textAdd;
	}

	private static void writeDataToFile(File file, String data) {
		try {
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(data);
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}// writeDataToFile

}// class
