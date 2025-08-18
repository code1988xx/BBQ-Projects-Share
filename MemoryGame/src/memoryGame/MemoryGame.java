package memoryGame;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;
 
public class MemoryGame extends Application {

	//--- Images die ich verwenden werde (per ChatGPT generiert!) ---
	Image rueckseite = new Image("file:src/rueckseite.png");
	Image arda = new Image("file:src/arda.png");
	Image cristiano = new Image("file:src/cristiano.png");
	Image messi = new Image("file:src/messi.png");
	Image okocha = new Image("file:src/okocha.png");
	Image quaresma = new Image("file:src/quaresma.png");
	Image ronaldinho = new Image("file:src/ronaldinho.png");

	//--- Erste Karte merken, um später Bugs zu vermeiden ---
	ImageView ersteKarte;

	public static void main(String[] args) {
		launch(args);
	}// start()

	@Override
	public void start(Stage stage) {

		// ---- Layout ----
		GridPane spielfeld = new GridPane();
		spielfeld.setVgap(3);
		spielfeld.setHgap(3);                                                                      //Breite Höhe
		spielfeld.setStyle("-fx-background-image: url('file:src/background.png');-fx-background-size: 100% 100%;");

		// --- ImageView um die Bilder in der GUI anzuzeigen ---
		ImageView arda1 = new ImageView(arda);
		ImageView arda2 = new ImageView(arda);
		ImageView cristiano1 = new ImageView(cristiano);
		ImageView cristiano2 = new ImageView(cristiano);
		ImageView messi1 = new ImageView(messi);
		ImageView messi2 = new ImageView(messi);
		ImageView okocha1 = new ImageView(okocha);
		ImageView okocha2 = new ImageView(okocha);
		ImageView quaresma1 = new ImageView(quaresma);
		ImageView quaresma2 = new ImageView(quaresma);
		ImageView ronaldinho1 = new ImageView(ronaldinho);
		ImageView ronaldinho2 = new ImageView(ronaldinho);

		//--- Karten in List packen um gleiche Maße per for-each-Schleife zu setzen und auch später für shuffle (Zeile71)
		List<ImageView> karten = Arrays.asList(arda1, arda2, cristiano1, cristiano2,
				                               messi1, messi2, okocha1, okocha2, quaresma1,
				                               quaresma2, ronaldinho1, ronaldinho2);
		
		//--- Alle Karten auf gleiche Maße setzen
		for (ImageView karte : karten) {
			karte.setFitHeight(300);
			karte.setFitWidth(300);//
		}//for-each

		//--- Karten beim Spielstart zufällig anordnen
		Collections.shuffle(karten);                          // Misch die Reihenfolge in der List (Zeile 60) neu	
		
		int col = 0, row = 0;
		for (ImageView karte : karten) {
			spielfeld.add(karte, col, row);                   // -> gridBezeichnung.add(Element, Spalte/column, Zeile/row)
			col++;
			if (col == 4) { 							      // 4 Karten je Zeile (also 4 Spalten/column), danach in nächste Zeile/row wechseln
				col = 0; 									 // Spalte beginnt wieder bei 0, damit nächste Karte bei col0 - row1 gesetzt wird
				row++; 									     // wechsel in nächste Zeile/row (neue Zeile wird generiert)
			}
		}//for

		
		//--- Karten bei Spielstart nach 3 Sekunden umdrehen ---
		spielfeld.setDisable(true);   						 // Solange der Timer läuft, soll kein klicken möglich sein (Bug verhindern) 
		PauseTransition startDelay = new PauseTransition(Duration.seconds(3));
		startDelay.setOnFinished(delay -> 
		karten.forEach(k -> k.setImage(rueckseite)));
		startDelay.play();            						// startet den Timer (hier 3sek.)
		spielfeld.setDisable(false); 					    // Spielfeld aktivieren (nachdem alle Karten umgedreht wurden)

		
		//--- Event: Karte anklicken ---
		for (ImageView karte : karten) {
			karte.setOnMouseClicked(klick -> {
				// richtiges Bild aufdecken
				if (karte == arda1 || karte == arda2)
					karte.setImage(arda);
				else if (karte == cristiano1 || karte == cristiano2)
					karte.setImage(cristiano);
				else if (karte == messi1 || karte == messi2)
					karte.setImage(messi);
				else if (karte == okocha1 || karte == okocha2)
					karte.setImage(okocha);
				else if (karte == quaresma1 || karte == quaresma2)
					karte.setImage(quaresma);
				else if (karte == ronaldinho1 || karte == ronaldinho2)
					karte.setImage(ronaldinho);

				//--- Prüfen der ausgewählten/angeklickten Karten---
				if (ersteKarte == null) {
					ersteKarte = karte;
				} else if (ersteKarte != karte) { 					   //Doppelklick verhindern! Weiter wenn nächster Klick eine andere Karte ist.
					if (ersteKarte.getImage() == karte.getImage()) {  //Vergleich ob beide gleiches Image-Objekt sind
						Alert alert = new Alert(AlertType.INFORMATION, "Treffer! Paar gefunden!");
						alert.setTitle("TREFFER!!!");
						alert.setHeaderText(null);
						alert.showAndWait(); 					     //Code stoppt hier, bis ich OK klicke, bei alert.show() läuft Code weiter!
					} else {  									    //Falls Karten nicht matchen, wieder umdrehen
						PauseTransition pt = new PauseTransition(Duration.seconds(0.5));
						ImageView tempErsteKarte = ersteKarte;
						pt.setOnFinished(ev -> {
							tempErsteKarte.setImage(rueckseite);
							karte.setImage(rueckseite);
						});
						pt.play();								  //Startet den Timer (hier 0.2sek.)
					}
					ersteKarte = null;							  //Zurücksetzen, da kein Paar gefunden
				}//if
			});//Event-Klick
		}//for

		///---Stage & Scene------------------------------------------------------------------------
		Scene scene = new Scene(spielfeld, 1210, 910);
		stage.setScene(scene);
		stage.setTitle("Memory");
		stage.setResizable(false);
		stage.show();
		
		
		
	}// start()
}// class()