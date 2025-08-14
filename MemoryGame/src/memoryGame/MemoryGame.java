package memoryGame;

import java.util.Arrays;
import java.util.List;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MemoryGame extends Application {

	private ImageView viewArda, viewCristiano, 
	                   viewMessi, viewOkocha, viewRonaldinho, viewQuaresma,
	                   viewRueckseite;
	
	
	
	public static void main(String[] args) {
		launch(args);
	}// main

	@Override
	public void start(Stage stage) throws Exception {


		// Layout
		GridPane gridPane = new GridPane();
		gridPane.setVgap(5);
		gridPane.setHgap(5);
		gridPane.setStyle("-fx-background-color:#4169E1; -fx-opacity:1;");
		
		//-- Images --
		viewRueckseite = new ImageView(new Image("file:src/rueckseite.png"));
		viewRueckseite.setFitHeight(300);
		viewRueckseite.setFitWidth(300);

		viewArda = new ImageView(new Image("file:src/arda.png"));
		viewArda.setFitHeight(300);
		viewArda.setFitWidth(300);
		gridPane.add(viewArda, 0, 0);
		
		viewCristiano = new ImageView(new Image("file:src/cristiano.png"));
		viewCristiano.setFitHeight(300);
		viewCristiano.setFitWidth(300);
		gridPane.add(viewCristiano, 1, 0);
		
		viewMessi = new ImageView(new Image("file:src/messi.png"));
		viewMessi.setFitHeight(300);
		viewMessi.setFitWidth(300);
		gridPane.add(viewMessi, 2, 0);
		
		viewQuaresma = new ImageView(new Image("file:src/quaresma.png"));
		viewQuaresma.setFitHeight(300);
		viewQuaresma.setFitWidth(300);
		gridPane.add(viewQuaresma, 3, 0);

		viewOkocha = new ImageView(new Image("file:src/okocha.png"));
		viewOkocha.setFitHeight(300);
		viewOkocha.setFitWidth(300);
		gridPane.add(viewOkocha, 0, 1);
		
		
		viewRonaldinho = new ImageView(new Image("file:src/ronaldinho.png"));
		viewRonaldinho.setFitHeight(300);
		viewRonaldinho.setFitWidth(300);
		gridPane.add(viewRonaldinho, 1, 1);
		
	
		//-- Event Karte anklicken --
		viewArda.setOnMouseClicked(klick -> {
			viewArda.setImage(new Image("file:src/arda.png"));
		});
		
		
		//-- Karten umdrehen --
		Image rueckseite = viewRueckseite.getImage();
		List<ImageView> karten = Arrays.asList(viewArda, viewQuaresma, viewCristiano, viewMessi, viewOkocha, viewRonaldinho);

		// Nach 3 Sekunden auf Karten umdrehen
		PauseTransition delay = new PauseTransition(Duration.seconds(3));
		delay.setOnFinished(event -> {
			karten.forEach(ev -> ev.setImage(rueckseite));
		}
			
				);
		delay.play();
		
		
		
		// region stage & scene
		Scene scene = new Scene(gridPane, 1210, 1024);
		stage.setScene(scene);
		stage.setTitle("Memory-Game");
		stage.show();
		stage.setResizable(false);
		// endregion
	}// start

}// class
