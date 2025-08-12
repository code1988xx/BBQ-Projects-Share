package textEditor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
 
public class FileIOWriteRead_WIN {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		File file = new File("C:\\Users\\AytunGüler\\eclipse-workspace\\AppFX6\\newU27.txt");

		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String fileData = readFileData(file);
		System.out.println(fileData);
		String data = fileData + "Hi U27!";
		writeDataToFile(file, data);

	}

	private static String readFileData(File file) {
		String data = "";

		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				data = data + scanner.nextLine() + "\n";
				// System.out.println(data);
			}
			return data;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	private static void writeDataToFile(File file, String data) {
		try {
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(data);
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}// main

}// class
