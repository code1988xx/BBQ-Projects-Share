module SlotMachine {
	requires java.desktop;
	requires javafx.base;
	requires javafx.controls;
	requires javafx.graphics;
	
	opens slotmachine to javafx.base, javafx.controls, javafx.graphics;
}