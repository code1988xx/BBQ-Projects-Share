/**
 * 
 */
/**
 * 
 */
module TicTacToe {
	requires java.desktop;
	requires javafx.base;
	requires javafx.controls;
	requires javafx.graphics;
	
	opens tictactoeApp to javafx.base, javafx.controls, javafx.graphics;
}