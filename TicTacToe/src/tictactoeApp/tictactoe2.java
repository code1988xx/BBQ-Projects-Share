package tictactoeApp;

import java.util.Scanner;
import javax.swing.JOptionPane;

public class tictactoe2 {
	
	//ANSI-Codes -> Text-Styles (Textfarbe, Stil etc.)
	public static final String RESET = "\033[0m";
    public static final String GREEN = "\033[32m";
    public static final String YELLOW = "\033[33m";
    public static final String BLUE = "\033[34m";
    public static final String MAGENTA = "\033[35m";
    public static final String CYAN = "\033[36m";
    public static final String backRED = "\033[41m";
    public static final String UNDERLINE = "\033[4m";
    public static final String BOLD = "\033[1m";
    
	
	static char[] spielfeld = { '1', '2', '3', '4', '5', '6', '7', '8', '9' }; // Spielfeld

	// Spielfeld anzeigen
	static void printFeld() {
		System.out.println(" \n " + spielfeld[0] + " | " + spielfeld[1] + " | " + spielfeld[2]);
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

		// Max. 9 Züge
		for (int zug = 0; zug < 9; zug++) {
		    printFeld();

		    char symbol;
		    if (spieler1) {
		        symbol = 'X';
		    } else {
		        symbol = 'O';
		    }// if

		    if (spieler1) {
		        System.out.print("Spieler " + MAGENTA + "1" + RESET + " (" + MAGENTA + symbol + RESET + ") wähle Feld: ");
		    } else {
		        System.out.print("Spieler " + YELLOW + "2" + RESET + " (" + YELLOW + symbol + RESET + ") wähle Feld: ");
		    }// if

		    int zugPosition = sc.nextInt() - 1;

		    if (zugPosition < 0 || zugPosition > 8 || spielfeld[zugPosition] == 'X' || spielfeld[zugPosition] == 'O') {
		    	JOptionPane.showMessageDialog(null, "Spielfeld belegt -> anderes Feld wählen!");
		        zug--; // Versuch nicht zählen, damit Spiel nicht vorzeitig endet
		        continue; // WICHTIG: Bricht den aktuellen Schleifendurchlauf hier ab und springt direkt zum nächsten Durchlauf (for-Schleife-Anfang)
		    }// if

		    spielfeld[zugPosition] = symbol;

		    if (hatGewonnen(symbol)) {
		        printFeld();
		        System.out.println("Spieler " + (spieler1 ? "1" : "2") + " hat gewonnen!");
		        sc.close();
		        return;
		    }// if

		    // Spieler wechseln
		    spieler1 = !spieler1;
		}// for

		// Kein Sieger nach 9 Zügen → Unentschieden
		printFeld();
		System.out.println("Unentschieden!");
		sc.close();
	
	}// main
}// class