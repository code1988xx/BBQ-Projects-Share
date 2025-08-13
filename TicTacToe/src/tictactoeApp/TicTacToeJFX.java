package tictactoeApp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TicTacToeJFX extends Application{

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage stage) throws Exception {
		
		
		// Layout
		GridPane gridPane = new GridPane();

	


		// Scene
		Scene scene = new Scene(gridPane, 600, 500);
		stage.setScene(scene);

		stage.setTitle("TicTacToe"); // FensterTitel
		stage.show();

		
	}

}
