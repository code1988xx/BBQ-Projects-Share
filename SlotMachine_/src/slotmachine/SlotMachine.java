package slotmachine;
// test
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Optional;
import java.util.Random;

public class SlotMachine extends Application {

	ImageView viewBanane, viewKirsche, viewDiamant;
	int credit = 100; // Startguthaben
	Label lblCredit; // Anzeige aktuelles Guthaben
	int erstesBild, zweitesBild, drittesBild; // für Zufallszahlen später

	public static void main(String[] args) {
		launch(args);
	}// main

	@Override
	public void start(Stage stage) throws Exception {

		// Layout
		GridPane gridPane = new GridPane();
		gridPane.setHgap(52); // Abstand zwischen den Bildern
		gridPane.setAlignment(Pos.CENTER); // Zentriert den gesamten Inhalt
		gridPane.setStyle("-fx-background-image: url('file:src/Background2.png'); -fx-background-size: cover;");
		gridPane.setGridLinesVisible(false);

		// Buttons & Labels
		lblCredit = new Label("Credit: " + credit + "€");
		lblCredit.setStyle(
				"-fx-font-size: 20px; -fx-font-weight: bold;-fx-text-fill: gold; -fx-background-color: purple");
		gridPane.add(lblCredit, 1, 2);

		Button btnStart = new Button("Start");
		btnStart.setMinHeight(40);
		btnStart.setMinWidth(115);
		gridPane.add(btnStart, 1, 3); // Button-Position im Grid

		// Banane
		viewBanane = new ImageView(new Image("file:src/Banane.png"));
		viewBanane.setFitHeight(100);
		viewBanane.setFitWidth(100);
		gridPane.add(viewBanane, 0, 0);

		// Kirsche
		viewKirsche = new ImageView(new Image("file:src/Kirsche.png"));
		viewKirsche.setFitHeight(100);
		viewKirsche.setFitWidth(100);
		gridPane.add(viewKirsche, 1, 0);

		// Diamant
		viewDiamant = new ImageView(new Image("file:src/Diamant.png"));
		viewDiamant.setFitHeight(100);
		viewDiamant.setFitWidth(100);
		gridPane.add(viewDiamant, 2, 0);

		// ButtonStart-Action
		btnStart.setOnAction(klick -> {
			erstesBild = (int) (Math.random() * 3) + 1;
			zweitesBild = (int) (Math.random() * 3) + 1;
			drittesBild = (int) (Math.random() * 3) + 1;

			if (erstesBild == 1) {
				viewBanane.setImage(new Image("file:src/Banane.png"));
			} else if (erstesBild == 2) {
				viewBanane.setImage(new Image("file:src/Kirsche.png"));
			} else {
				viewBanane.setImage(new Image("file:src/Diamant.png"));
			} // if erstesBild

			if (zweitesBild == 1) {
				viewKirsche.setImage(new Image("file:src/Banane.png"));
			} else if (zweitesBild == 2) {
				viewKirsche.setImage(new Image("file:src/Kirsche.png"));
			} else {
				viewKirsche.setImage(new Image("file:src/Diamant.png"));
			} // if zweitesBild

			if (drittesBild == 1) {
				viewDiamant.setImage(new Image("file:src/Banane.png"));
			} else if (drittesBild == 2) {
				viewDiamant.setImage(new Image("file:src/Kirsche.png"));
			} else {
				viewDiamant.setImage(new Image("file:src/Diamant.png"));
			} // if drittesBild

			// Prüfung ob gewonnen oder nicht
			if (erstesBild == 1 && (erstesBild == zweitesBild && zweitesBild == drittesBild)) {
				credit += 20;
				Alert alert = new Alert(AlertType.INFORMATION, "SUPER!!! 3x BANANA DU HAST 20€ GEWONNEN!");
				alert.setTitle("GEWONNEN!");
				alert.setHeaderText(null);
				alert.showAndWait(); // damit Alert angezeigt wird

			} else if (erstesBild == 2 && (erstesBild == zweitesBild && zweitesBild == drittesBild)) {
				credit += 30;
				Alert alert = new Alert(AlertType.INFORMATION, "SUPER!!! 3x KIRSCH!! DU HAST 30€ GEWONNEN!");
				alert.setTitle("GEWONNEN!");
				alert.setHeaderText(null);
				alert.showAndWait(); // damit Alert angezeigt wird
			} else if (erstesBild == 3 && (erstesBild == zweitesBild && zweitesBild == drittesBild)) {
				credit += 50;
				Alert alert = new Alert(AlertType.INFORMATION, "SUPER!!! 3x DIAMANT!! DU HAST 50€ GEWONNEN!");
				alert.setTitle("GEWONNEN!");
				alert.setHeaderText(null);
				alert.showAndWait(); // damit Alert angezeigt wird

			} else {
				credit -= 5;
			}
			lblCredit.setText("Credit: " + credit + "€");

			// Prüft ob Credit ausreichend ist -> Abfrage ob aufladen oder nicht
			if (credit < 5) {
				Alert alert = new Alert(AlertType.CONFIRMATION,
						"Dein Credit reicht nicht aus! Willst du 100€ aufladen?", ButtonType.YES, ButtonType.NO);
				alert.setTitle("Credit aufladen?");
				alert.setHeaderText(null);

				Optional<ButtonType> result = alert.showAndWait();

				if (result.isPresent() && result.get() == ButtonType.YES) {
					credit += 100;
					lblCredit.setText("Credit: " + credit + "€");
				} else {
					btnStart.setDisable(true); // Start-Button deaktivieren, wenn nicht aufgeladen werden soll
					btnStart.setText("GAME OVER");
//						btnStart.setStyle("-fx-font-size: 40px; -fx-font-weight: bold;-fx-text-fill: gold; -fx-background-color: purple");
					lblCredit.setVisible(false);
				}
			} // if

		}); // Button-Action

		// stage und scene
		Scene scene = new Scene(gridPane, 900, 900);
		stage.setScene(scene);
		stage.setResizable(false);

		stage.setTitle("Slot-Machine");
		stage.show();

	}// start

}// class