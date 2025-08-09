package slotmachine;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.Random;

public class slotMachine2 extends Application {

	private ImageView viewBanane, viewKirsche, viewDiamant;
	private int credit = 100;
	private Label lblCredit;

	private final String[] alleBilder = { "src/Banane.png", "src/Kirsche.png", "src/Diamant.png" };

	public static void main(String[] args) {
		launch(args);
	}

	private Image getRandomImage() {
		int index = new Random().nextInt(alleBilder.length);
		return new Image("file:" + alleBilder[index]);
	}

	@Override
	public void start(Stage stage) {
		GridPane gridPane = new GridPane();
		gridPane.setHgap(20);
		gridPane.setAlignment(Pos.CENTER);

		lblCredit = new Label("Credit: " + credit);
		lblCredit.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
		gridPane.add(lblCredit, 1, 2);

		Button btnStart = new Button("Start");
		btnStart.setMinSize(100, 40);
		gridPane.add(btnStart, 1, 3);

		// Initiale Bilder
		viewBanane = createImageView("Banane.png");
		viewKirsche = createImageView("Kirsche.png");
		viewDiamant = createImageView("Diamant.png");

		gridPane.add(viewBanane, 0, 0);
		gridPane.add(viewKirsche, 1, 0);
		gridPane.add(viewDiamant, 2, 0);

		// Button-Click → Bilder setzen + Credit prüfen
		btnStart.setOnAction(e -> {
			Image img1 = getRandomImage();
			Image img2 = getRandomImage();
			Image img3 = getRandomImage();

			viewBanane.setImage(img1);
			viewKirsche.setImage(img2);
			viewDiamant.setImage(img3);

			String s1 = img1.getUrl();
			String s2 = img2.getUrl();
			String s3 = img3.getUrl();

			if (s1.equals(s2) && s2.equals(s3)) {
				credit += 20;
			} else {
				credit -= 5;
			}

			lblCredit.setText("Credit: " + credit);
		});

		stage.setScene(new Scene(gridPane, 600, 600));
		stage.setTitle("Slot-Machine");
		stage.show();
	}

	private ImageView createImageView(String filename) {
		ImageView view = new ImageView(new Image("file:src/" + filename));
		view.setFitHeight(100);
		view.setFitWidth(100);
		return view;
	}
}// class