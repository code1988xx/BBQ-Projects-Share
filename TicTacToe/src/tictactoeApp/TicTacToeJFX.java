package tictactoeApp;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TicTacToeJFX extends Application {
	
	private boolean spieler1 = true; // true = X, false = O
	private Button[][] spielfeld = new Button[3][3]; // Spielfeld mit Hilfe von Button-Array erstellt
	private int zuege = 0; // Anzahl gespielte Züge

	public static void main(String[] args) {
		launch(args);
	}// main

	@Override
	public void start(Stage stage) {
		GridPane gridPane = new GridPane();

		// -- Buttons und 3x3 Spielfeld erstellen --
		for (int zeile = 0; zeile < 3; zeile++) {
			for (int spalte = 0; spalte < 3; spalte++) {
				Button btn = new Button("-");
				btn.setMinSize(100, 100);
				spielfeld[zeile][spalte] = btn; // Button ins Array speichern
				gridPane.add(btn, spalte, zeile); // Button ins GridPane einfügen
			}
		} // for

		for (int zeile = 0; zeile < 3; zeile++) {
			for (int spalte = 0; spalte < 3; spalte++) {
				Button btn = spielfeld[zeile][spalte];

				btn.setOnAction(event -> {
					if (btn.getText().equals("-")) {
						btn.setText(spieler1 ? "X" : "O");
						zuege++;

						if (hatGewonnen(btn.getText())) {
							zeigeMeldung("Spieler " + (spieler1 ? "1" : "2") + " hat gewonnen!");
							reset();
							return;
						}

						if (zuege == 9) {
							zeigeMeldung("Unentschieden!");
							reset();
							return;
						}

						spieler1 = !spieler1; // Spieler wechseln
					}
				});// btn-event
			} // for
		} // for

		// -- Scene & Stage --
		Scene scene = new Scene(gridPane, 300, 300);
		stage.setScene(scene);
		stage.setTitle("TicTacToe");
		stage.setResizable(false);
		stage.show();
	}

	// -- Gewinnprüfung --
	private boolean hatGewonnen(String symbol) {
		// Reihen prüfen
		for (int i = 0; i < 3; i++) {
			if (spielfeld[i][0].getText().equals(symbol) && spielfeld[i][1].getText().equals(symbol)
					&& spielfeld[i][2].getText().equals(symbol)) {
				return true;
			}
		}
		// Spalten prüfen
		for (int i = 0; i < 3; i++) {
			if (spielfeld[0][i].getText().equals(symbol) && spielfeld[1][i].getText().equals(symbol)
					&& spielfeld[2][i].getText().equals(symbol)) {
				return true;
			}
		}
		// Diagonalen prüfen
		if (spielfeld[0][0].getText().equals(symbol) && spielfeld[1][1].getText().equals(symbol)
				&& spielfeld[2][2].getText().equals(symbol)) {
			return true;
		}
		if (spielfeld[0][2].getText().equals(symbol) && spielfeld[1][1].getText().equals(symbol)
				&& spielfeld[2][0].getText().equals(symbol)) {
			return true;
		}

		return false;
	}

	private void zeigeMeldung(String text) {
	    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	    alert.setTitle("Spielende");
	    alert.setHeaderText(null);
	    alert.setContentText(text + "\nMöchten Sie weiterspielen?");

	    alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

	    alert.showAndWait().ifPresent(response -> {
	        if (response == ButtonType.YES) {
	            reset();
	        } else {
	            Platform.exit();   // Beendet alle JavaFX-Fenster; 
	            	               // stage.close() schließt nur ein Fenster und Programm könnte weiterlaufen;
	                  			   // System.exit(0) erzwingt sofort das Ende der gesamten JVM
	        }
	    });
	}

	// -- Methode um Spielfeld zu reseten --
	private void reset() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				spielfeld[i][j].setText("-");
			}
		}
		spieler1 = true;
		zuege = 0;
	}// reset()
}// class