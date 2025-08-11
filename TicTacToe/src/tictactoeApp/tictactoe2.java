package tictactoeApp;
//2
import java.util.Scanner;

public class tictactoe2 {
	
	static char[] spielfeld = { '1', '2', '3', '4', '5', '6', '7', '8', '9' }; // Spielfeld

	// Spielfeld anzeigen
	static void printFeld() {
		System.out.println(" " + spielfeld[0] + " | " + spielfeld[1] + " | " + spielfeld[2]);
		System.out.println("---+---+---");
		System.out.println(" " + spielfeld[3] + " | " + spielfeld[4] + " | " + spielfeld[5]);
		System.out.println("---+---+---");
		System.out.println(" " + spielfeld[6] + " | " + spielfeld[7] + " | " + spielfeld[8]);
	}

	// Bedingungen um zu gewinnen
	static boolean hatGewonnen(char s) {
		int[][] gewonnen = { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 }, // Reihen (horizontal)
				{ 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 }, // Spalten (vertikal)
				{ 0, 4, 8 }, { 2, 4, 6 } // Diagonal
		};
		for (int[] a : gewonnen)
			if (spielfeld[a[0]] == s && spielfeld[a[1]] == s && spielfeld[a[2]] == s) // prüfen ob alle drei Felder das Symbol "s" haben
				return true; // Spieler hat gewonnen weil Gewinnbedingung passt
		return false; // Spieler hat (noch) nicht gewonnen
	}// hatGewonnen

	// Unentschieden prüfen
	static boolean istUnentschieden() {
		for (char feld : spielfeld)
			if (feld != 'X' && feld != 'O') // es gibt noch freie Felder -> kein unentschieden
				return false;
		return true; // es gibt keine freien Felder -> unentschieden
	}// istUnentschieden

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean spieler1 = true; // true = Spieler 1 (X), false = Spieler 2 (O)

		while (true) {
			printFeld();
			char symbol = spieler1 ? 'X' : 'O'; // falls nicht spieler1, dann Symbol auf "O" switchen
			System.out.print("Spieler " + (spieler1 ? "\u001B[32m 1 \u001B[0m" : "\u001B[31m 2 \u001B[0m") + " (" + symbol + ") wähle Feld: ");

			int zugPosition = sc.nextInt() - 1; // Eingabefeld und -> -1, weil Index bei 0 startet ich aber Feld[0] 1 gesetzt habe optisch

			if (zugPosition < 0 || zugPosition > 8 || spielfeld[zugPosition] == 'X' || spielfeld[zugPosition] == 'O') {
				System.out.println("Ungültige Eingabe!");
				continue; // zurück zum Schleifenanfang, erneut fragen
			} // if

			spielfeld[zugPosition] = symbol; // Wer am Zug ist

			if (hatGewonnen(symbol)) {
				printFeld();
				System.out.println("Spieler " + (spieler1 ? "1" : "2") + " hat gewonnen!");
				break;
			} // if

			if (istUnentschieden()) {
				printFeld();
				System.out.println("Unentschieden!");
				break;
			} // if

			spieler1 = !spieler1; // Spieler wechseln
		}// while

		sc.close();
	}// main
}// class