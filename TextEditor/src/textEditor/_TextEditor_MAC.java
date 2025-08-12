package textEditor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class _TextEditor_MAC extends Application {
	 
	private static File selectedFile;
	private static String fileDataString;
	private File currentFile;
 
	// -- Methode Datei-Inhalt lesen und anzeigen --
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
	}// readFileData()
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}// main
	

	@Override
	public void start(Stage stage) throws Exception {
		
		// -- Beim Start direkt eine neue Textdatei im Wunschpfad erstellen --
		File file = new File("/Users/a1903/desktop/textDatei.txt");
		
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}


		// Elements
		Button btnOpen = new Button("Open File");
		Button btnWrite = new Button("Write to File");
		Button btnCloe = new Button("Close");

		// MenueBar
		MenuBar menuBar = new MenuBar();

		Menu mFile = new Menu("File");
		Menu mAbout = new Menu("About");

		Menu mSubMenu = new Menu("Help");
		mAbout.getItems().add(mSubMenu);

		MenuItem itemOpen = new MenuItem("Open File");
		MenuItem itemWrite = new MenuItem("Write to File");
		MenuItem itemClose = new MenuItem("Exit");
		MenuItem itemAbout = new MenuItem("About");

		MenuItem itemContactItem = new MenuItem("Contact");

		mFile.getItems().add(itemAbout);
		mFile.getItems().add(itemOpen);
		mFile.getItems().add(itemWrite);
		mFile.getItems().add(new SeparatorMenuItem());
		mFile.getItems().add(itemClose);

		mAbout.getItems().add(itemAbout);
		mSubMenu.getItems().add(itemContactItem);

		menuBar.getMenus().add(mFile);
		menuBar.getMenus().add(mAbout);

		TextArea tArea = new TextArea();
		Label statusLabel = new Label("Status: ");

		
		// -- FileChooser um Dateiauswahlmaske zu önnfen --
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open JFX File");
		fileChooser.setInitialFileName("jfxText.txt");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Files", "*.*.*"),
				new FileChooser.ExtensionFilter("Text Files", "*.text"),
				new FileChooser.ExtensionFilter("HTML Files", "*.text"));


		
		// -- Event: Open File --
		itemOpen.setOnAction(event -> {
			File selectedFile = fileChooser.showOpenDialog(stage);
			if (selectedFile != null) {
				currentFile = selectedFile;
				String filePath = selectedFile.getAbsolutePath();
				statusLabel.setText("Status: OpenFile, Path" + filePath);
				
				// -- Datei lesen und anzeigen --
				String dateiInhalt = readFileData(selectedFile);
				tArea.setText(dateiInhalt);	   
			}
		});// itemOpen()

		
		
		// -- Event: Write to File --
		itemWrite.setOnAction(event -> {		
		    if (currentFile == null) return; // keine Datei geöffnet -> nichts tun
		    try (FileWriter fileWriter = new FileWriter(currentFile)) {
		        fileWriter.write(tArea.getText());
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		});
		
		
		// -- Event: About klick --
		mAbout.setOnAction(event -> {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("About JX Editor");
			alert.setHeaderText(null); // muss auf null sein, wenn ich keine Meldung anzeigen will (sonst kommt nämlich
			// default-Meldung)
			alert.setContentText("JFX Text Editor\nVersion:0.0.1");
			alert.showAndWait();
			
		});// mAbout()
		
		// -- Event: Exit klick --
		itemClose.setOnAction(event -> {
			stage.close();
		});

		// Layout
		BorderPane borderPane = new BorderPane();

		borderPane.setTop(menuBar);
		borderPane.setCenter(tArea);
		borderPane.setBottom(statusLabel);


		// Scene
		Scene scene = new Scene(borderPane, 600, 500);
		stage.setScene(scene);

		stage.setTitle("TextEditor"); // FensterTitel
		stage.show();

	}

}// class


//		GridPane gridPane = new GridPane();
//		gridPane.add(btnOpen, 0, 0);
//		gridPane.add(btnWrite, 1, 0);
//		gridPane.add(btnCloe, 2, 0);
//		gridPane.setHgap(10);
//		gridPane.setVgap(5);
//
//		gridPane.add(tArea, 0, 1, 3, 1);
//		gridPane.setGridLinesVisible(true);