module TextEditor {
	requires java.desktop;
	requires javafx.base;
	requires javafx.controls;
	requires javafx.graphics;
	
	opens textEditor to javafx.base, javafx.controls, javafx.graphics;
}