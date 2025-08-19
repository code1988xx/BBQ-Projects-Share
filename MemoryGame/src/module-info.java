module MemoryGame {
	requires java.desktop;
	requires javafx.base;
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.media;

	opens memoryGame to javafx.base, javafx.controls, javafx.graphics;
}