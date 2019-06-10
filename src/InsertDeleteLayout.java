import java.sql.ResultSet;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

public abstract class InsertDeleteLayout {
	static CheckBox checks;
	//layout for insert and delete page 
	public static BorderPane insertDeletePane() {
		BorderPane insertDelete_borderPane = new BorderPane();

		Button insert_button = new Button("I Want To Insert");
		Button delete_button = new Button("I Want To Delete");
		delete_button.setMinWidth(150);
		insert_button.setMinWidth(150);
	
		VBox vbox = new VBox();

		

		vbox.getChildren().addAll(insert_button ,delete_button);
		//vbox.setPadding(new Insets(10, 50, 50, 50));
		vbox.setSpacing(10);
		insertDelete_borderPane.setTop(vbox);
		
		

		VBox vbox1 = new VBox();
		vbox1.setSpacing(5);
		
		
//		VBox vbox2 = new VBox();
//		//vbox2.setSpacing(5);
//		vbox2.setLayoutY(100);
//		Text title = new Text("Table to Insert or Delete");
//	    title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
//	    vbox2.getChildren().add(title);
		
		VBox vbox3 = new VBox();

		CheckBox host = new CheckBox("HOST");
		CheckBox review = new CheckBox("REVIEWED");
		CheckBox listing = new CheckBox("LISTING");
		

		host.setMinWidth(100);
		review.setMinWidth(100);
		listing.setMinWidth(100);
		
		vbox1.getChildren().addAll(host,review,listing);

		vbox1.setLayoutX(50);
		vbox1.setLayoutY(150);

		//insertDelete_borderPane.getChildren().addAll(vbox1);
		//insertDelete_borderPane.getChildren().add(vbox2);
		insertDelete_borderPane.setLeft(vbox1);
				
		//TODO remove System.out.println
		insert_button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				CheckBox checkBox = null;

				//get the request box
				for(int i = 0; i < vbox1.getChildren().size(); i++) {

					if(((CheckBox) vbox1.getChildren().get(i)).isSelected()) {
						if(checkBox != null) {
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("Error");
							alert.setHeaderText(null);
							alert.setContentText("Please select 1 box only!");

							alert.showAndWait();
							return;
						}
						checkBox = ((CheckBox) vbox1.getChildren().get(i));
					}
				}

				if( checkBox == null) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText(null);
					alert.setContentText("Please select a box!");
					alert.showAndWait();
					return;
				}

				String table_name = checkBox.getText();

				System.out.println(checkBox.getText());
				
				BorderPane insert_borderPane = new BorderPane();

				Button submit_button = new Button("INSERT");
				VBox vBox = new VBox();
				vBox.getChildren().add(submit_button);        
				insert_borderPane.setBottom(vBox);

				ScrollPane insert_scrollPane = new ScrollPane();

				VBox fillIn_table = new VBox();
				insert_scrollPane.setContent(fillIn_table);
				
//				Label label1 = new Label("Name:");
//				TextField textField = new TextField ();
//				HBox hb = new HBox();
//				hb.getChildren().addAll(label1, textField);
//				hb.setSpacing(10);
				//insert_scrollPane.setContent(submit_button);
			
				//insert_borderPane.setCenter(insert_scrollPane);
				insertDelete_borderPane.setCenter(insert_scrollPane);
				String label_table;
				
				if(table_name == "HOST") {
					System.out.println(123);
					label_table = "HOST_INSERT";
				}
				else if(table_name == "REVIEWED") {
					label_table = "REVIEWED_INSERT";
				}
				else {
					label_table = "LISTING_INSERT";
				}

				ArrayList<String> insert_column = helper.tableColumn(label_table);
			
				for(int i = 0; i < insert_column.size(); i++) {
					Label label1 = new Label(insert_column.get(i));
					TextField t1 = new TextField();
					HBox hb = new HBox();
					hb.getChildren().addAll(label1, t1);
					hb.setSpacing(10);
					t1.setPrefWidth(550);	
					label1.setPrefWidth(400);	
//					fillIn_table.getChildren().add(t1);
					fillIn_table.getChildren().add(hb);
				}
				fillIn_table.getChildren().add(submit_button);
				
				System.out.println("Here" +fillIn_table.getChildren().size());
				
				


				submit_button.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						
						ArrayList<Pair> textFields1 = new ArrayList<>();	
						ArrayList<String> textFields = new ArrayList<>();			
						 for(int i = 0; i < fillIn_table.getChildren().size()-1; i++) {
						
							 HBox hb = (HBox)fillIn_table.getChildren().get(i);
							 System.out.println(((TextField) (hb.getChildren().get(1))).getText());
							 if((!((TextField) (hb.getChildren().get(1))).getText().isEmpty())) {
								 //textFields.add(((TextField) (hb.getChildren().get(1))).getText());
								 Pair p1 = new Pair(((TextField) (hb.getChildren().get(1))).getText(),i); 
								 textFields1.add(p1);
							 }
//							 textFields.add(((TextField) (fillIn_table.getChildren().get(i))).getText());
							
							 
						 }
						 System.out.println("size" + textFields1.size());
						 //System.out.println(textFields1.get(1));
						 

						 ResultSet rs = null;
						 try {
							 String query = helper.insertQuery(textFields1, table_name);
							 System.out.println(query);
							 rs = helper.executeQuery(query);

						 }catch(Exception e){
							 //e.printStackTrace();
							 Alert submit_alert = new Alert(AlertType.ERROR);
							 submit_alert.setTitle("Error");
							 submit_alert.setHeaderText(null);
							 submit_alert.setContentText("Something Went Wrong");

							 submit_alert.showAndWait();
							 return;
						 }
						 
						 if(rs!= null) {
							 Alert submit_alert = new Alert(AlertType.INFORMATION);
							 submit_alert.setTitle("Information Dialog");
							 submit_alert.setHeaderText(null);
							 submit_alert.setContentText("Your record has been instered!");

							 submit_alert.showAndWait();
						 }
						 
						 
					}
				});
			}
		});
		
		delete_button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				CheckBox checkBox = null;

				//get the request box
				for(int i = 0; i < vbox1.getChildren().size(); i++) {
					
					if(((CheckBox) vbox1.getChildren().get(i)).isSelected()) {
						if(checkBox != null) {
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("Error");
							alert.setHeaderText(null);
							alert.setContentText("Please select 1 box only!");

							alert.showAndWait();
							return;
						}
						checkBox = ((CheckBox) vbox1.getChildren().get(i));
					}
				}
				
				if( checkBox == null) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText(null);
					alert.setContentText("Please select a box!");

					alert.showAndWait();
					return;
				}
								
				String table_name = checkBox.getText();

				System.out.println(checkBox.getText());
					
				BorderPane delete_borderPane = new BorderPane();

				Button submit_button = new Button("DELETE");
				VBox vBox = new VBox();
				vBox.getChildren().add(submit_button);        
				delete_borderPane.setBottom(vBox);

				ScrollPane delete_scrollPane = new ScrollPane();

				VBox fillIn_table = new VBox();
				delete_scrollPane.setContent(fillIn_table);
				insertDelete_borderPane.setCenter(delete_scrollPane);

				ArrayList<String> insert_column = helper.tableColumn(table_name);
				for(int i = 0; i < 1; i++) {
					TextField t1 = new TextField(insert_column.get(i));
					t1.setPrefWidth(1000);			
					fillIn_table.getChildren().add(t1);
				}
				fillIn_table.getChildren().add(submit_button);


		        
				submit_button.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {				
						ArrayList<String> textFields = new ArrayList<>();						 
						 for(int i = 0; i < fillIn_table.getChildren().size()-1; i++) {
							 textFields.add(((TextField) fillIn_table.getChildren().get(i)).getText());
						 }
						 
						 try {
							 String query = helper.deleteQuery(textFields, table_name);
							 System.out.println(query);
							 helper.executeQuery(query);
						 }catch(Exception e){
							 Alert submit_alert = new Alert(AlertType.ERROR);
							 submit_alert.setTitle("Error");
							 submit_alert.setHeaderText(null);
							 submit_alert.setContentText("Something Went Wrong");

							 submit_alert.showAndWait();
							 return;
						 }

						

						 
						 Alert submit_alert = new Alert(AlertType.INFORMATION);
						 submit_alert.setTitle("Information Dialog");
						 submit_alert.setHeaderText(null);
						 submit_alert.setContentText("Your record has been deleted!");

						 submit_alert.showAndWait();
					
					}
				});
			}
		});
		return insertDelete_borderPane;
	}
}

