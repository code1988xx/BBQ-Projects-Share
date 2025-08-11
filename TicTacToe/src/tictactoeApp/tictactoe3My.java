package tictactoeApp;

import java.util.Scanner;
import javax.swing.JOptionPane;

public class tictactoe3My {

	// ANSI-Codes -> Text-Styles (Textfarbe, Stil etc.)
	public static final String RESET = "\033[0m";
	public static final String cyanBold = "\033[36m" + "\033[1m";
	public static final String magentaBold = "\033[35m" + "\033[1m";;
	public static final String GREEN = "\033[32m";
	public static final String backRED = "\033[41m";
	public static final String BOLD = "\033[1m";
//	public static final String YELLOW = "\033[33m";
//	public static final String BLUE = "\033[34m";
//	public static final String UNDERLINE = "\033[4m";

	// --- Spielfeld ---
	public static String[] spielfeld = { "1", "2", "3", "4", "5", "6", "7", "8", "9" };

	// --- Gewinnprüfung ---
	public static boolean hatGewonnen(String symbol) {
		int[][] gewinnKombis = { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 }, // Reihen
								 { 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 }, // Spalten
								 { 0, 4, 8 }, { 2, 4, 6 } // Diagonalen
								};

		for (int[] gewinnLinie : gewinnKombis) {
			if (spielfeld[gewinnLinie[0]].contains(symbol) &&  // musste statt .equals mit .contains arbeiten, weil ich Farbcode nutze! So prüft es ob "X" oder "O" irgendwo in dem String ist!
					spielfeld[gewinnLinie[1]].contains(symbol) && 
					spielfeld[gewinnLinie[2]].contains(symbol)) {
				return true;
			}// if
		}// for
		return false;
	}// hatGewonnen()

	// --- Mögliches unentschieden prüfen ---
	public static boolean istUnentschieden() {
		for (int i = 0; i < spielfeld.length; i++) {
			if (spielfeld[i].equals(String.valueOf(i + 1))) { //String.valueOf() wandelt die Zahl in einen String um, damit ich es mit dem spielfeld (ist String) vergleichen kann!
				return false; // es gibt noch freie Felder -> kein unentschieden
			}
		}
		return true; // es gibt keine freien Felder -> unentschieden
	}// istUnentschieden()

	// --- Spielfeld anzeigen ---
	public static void printFeld() {
		System.out.println();
		System.out.println(" " + spielfeld[0] + " | " + spielfeld[1] + " | " + spielfeld[2]);
		System.out.println("---+---+---");
		System.out.println(" " + spielfeld[3] + " | " + spielfeld[4] + " | " + spielfeld[5]);
		System.out.println("---+---+---");
		System.out.println(" " + spielfeld[6] + " | " + spielfeld[7] + " | " + spielfeld[8]);
	}

	// --- Eingabe vom Spieler ---
	public static int spielerEingabe(Scanner sc) {
		return sc.nextInt();
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		printFeld();

		while (true) {
			// Spieler 1
			int eingabe1;
			while (true) {
				System.out.print("Spieler 1 wähle dein Feld: ");
				eingabe1 = spielerEingabe(sc) - 1;

				if (eingabe1 < 0 || eingabe1 > 8) {
					System.out.println(backRED + "Ungültige Eingabe" + RESET);
				} else if (!spielfeld[eingabe1].equals(String.valueOf(eingabe1 + 1))) {
					System.out.println(backRED + "Feld schon belegt" + RESET);
				} else {
					spielfeld[eingabe1] = cyanBold + "X" + RESET;
					printFeld();
					break;
				}
			}

			if (hatGewonnen("X")) {
				System.out.println(GREEN + BOLD + "🎉 Spieler 1 hat gewonnen!");
				break;
			}
			if (istUnentschieden()) {
				System.out.println("Unentschieden!");
				break;
			}

			// Spieler 2
			int eingabe2;
			while (true) {
				System.out.print("Spieler 2 wähle dein Feld: ");
				eingabe2 = spielerEingabe(sc) - 1;

				if (eingabe2 < 0 || eingabe2 > 8) {
					System.out.println(backRED + "Ungültige Eingabe" + RESET);
				} else if (!spielfeld[eingabe2].equals(String.valueOf(eingabe2 + 1))) {
					System.out.println(backRED + "Feld schon belegt" + RESET);
				} else {
					spielfeld[eingabe2] = magentaBold + "O" + RESET;
					printFeld();
					break;
				}
			}

			if (hatGewonnen("O")) {
				System.out.println(GREEN + BOLD + "🎉 Spieler 2 hat gewonnen!");
				break;
			}
			if (istUnentschieden()) {
				System.out.println("Unentschieden!");
				break;
			}
		}

		sc.close();
	}// main
}// class