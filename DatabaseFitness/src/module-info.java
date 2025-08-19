module DatabaseFitness {
	requires java.sql;
	requires java.desktop;
	requires javafx.base;
	requires javafx.controls;
	requires javafx.graphics;

	opens app.db.fx to java.sql, javafx.base, javafx.controls, javafx.graphics;

}