package tictactoeApp;

import java.util.Scanner;

public class tictactoe {

	public static void main(String[] args) {
		char[] spielfeld = { '1', '2', '3', '4', '5', '6', '7', '8', '9' }; // Startfelder
		printSpielfeld(spielfeld);
		Scanner sc = new Scanner(System.in);

		int feld;
		do {
			System.out.print("Wähle ein Feld aus: ");
			feld = sc.nextInt();

			if (feld >= 1 && feld <= 9 && spielfeld[feld - 1] != 'x' && spielfeld[feld - 1] != 'o') {
				spielfeld[feld - 1] = 'x';
			} else {
				System.out.println("Ungültige Eingabe");
			}
			printSpielfeld(spielfeld);
		} while (feld != 0);

		sc.close();
	}// main
	

	// Methode um Feld auszugeben
	public static void printSpielfeld(char[] feld) {
		System.out.println(" " + feld[0] + " | " + feld[1] + " | " + feld[2]);
		System.out.println("---+---+---");
		System.out.println(" " + feld[3] + " | " + feld[4] + " | " + feld[5]);
		System.out.println("---+---+---");
		System.out.println(" " + feld[6] + " | " + feld[7] + " | " + feld[8]);
	}// printBoard

}// class