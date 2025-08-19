package app.db.fx;

import java.sql.*;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class DBFitnessFX extends Application {

	//--- CustomStyles ---
	Font customFont = Font.font("Verdana", FontWeight.BOLD,16);
	String btnStyle = "-fx-font-family: Verdana;-fx-min-width: 70;-fx-font-weight: BOLD;";
	
	public static void main(String[] args) {

		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

	//--- Create TableView ---
	TableView tableView = new TableView();
	
	//--- Layout ---
	BorderPane borderPane = new BorderPane();

	VBox vBoxCenter = new VBox(tableView);
	borderPane.setCenter(vBoxCenter);
	
	GridPane gridPane = new GridPane();
	gridPane.setHgap(10);
	gridPane.setVgap(1);
	borderPane.setTop(gridPane);
	
	//--- Buttons erstellen --
	Button btnAdd = new Button("Add");
	Button btnDelete = new Button("Delete");
	Button btnUpdate = new Button("Update");
	Button btnLoadData = new Button("Load Data");
	btnAdd.setStyle(btnStyle);
	btnDelete.setStyle(btnStyle + "-fx-text-fill: red");
	btnUpdate.setStyle(btnStyle + "-fx-text-fill: green");
	btnLoadData.setStyle(btnStyle + "-fx-text-fill: blue");
	
	Label lblCustomerId = new Label("MemberID");
	TextField tfCustomerId = new TextField();
	lblCustomerId.setFont(customFont);
	gridPane.add(lblCustomerId, 0, 0); // Spalte(X), Zeile(Y)
	gridPane.add(tfCustomerId, 0, 1);

	Label lblVorname = new Label("Vorname");
	TextField tfVorname = new TextField();
	lblVorname.setFont(customFont);
	gridPane.add(lblVorname, 1, 0); 
	gridPane.add(tfVorname, 1, 1);
	
	Label lblNachname = new Label("Nachname");
	TextField tfNachname = new TextField();
	lblNachname.setFont(customFont);
	gridPane.add(lblNachname, 2, 0); 
	gridPane.add(tfNachname, 2, 1);
	
	Label lblEmail = new Label("Email");
	TextField tfEmail = new TextField();
	lblEmail.setFont(customFont);
	gridPane.add(lblEmail, 3, 0); 
	gridPane.add(tfEmail, 3, 1);
	
	
	//---VBox um die Buttons zu positionieren---
	VBox vBoxRight = new VBox(btnAdd);
	gridPane.add(vBoxRight, 4, 1); 
	
	Label lblSpace = new Label(""); //Um Abstand zwischen Buttons zu generieren
	lblSpace.setMinWidth(523);
	HBox hBoxBottom = new HBox(btnDelete,lblSpace,btnLoadData,btnUpdate);
	borderPane.setBottom(hBoxBottom);
	
	
	///------------🔴AB HIER WEITER🔴️-----------
	//--- Daten & Layout in TableView einbauen---
	TableColumn<Member, Integer> member_id = new TableColumn<>("Member ID");
	TableColumn<Member, String> vorname = new TableColumn<>("Vorname");
	TableColumn<Member, String> nachname = new TableColumn<>("Nachname");
	TableColumn<Member, String> email = new TableColumn<>("Email");
	member_id.setMinWidth(160);
	vorname.setMinWidth(160);
	nachname.setMinWidth(160);
	email.setMinWidth(160);
	tableView.getColumns().addAll(member_id, vorname, nachname, email);
	
	
	


	
	
	//--- Stage & Scene---       (Width(X), Height(Y))
	Scene scene = new Scene(borderPane,755, 470); 	
	stage.setResizable(false);
	stage.setScene(scene);
	stage.setTitle("Fitness Database");
	stage.show();

	
	}//start()

	
}//class()
