import java.sql.ResultSet;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public abstract class SearchLayout {
	//static CheckBox checks;
	

	//layout for search page
	public static BorderPane searchPane(){

		BorderPane search_borderPane = new BorderPane();

		TextField input_query = new TextField("Please Enter Your Query Here");
		input_query.setPrefWidth(100);

		Button search_button = new Button("Search");

		VBox vbox = new VBox();

		vbox.getChildren().addAll(input_query ,search_button);

		vbox.setPadding(new Insets(10, 50, 50, 50));
		vbox.setSpacing(20);
		search_borderPane.setLeft(vbox);

		VBox vbox1 = new VBox();
		vbox1.setSpacing(8);

		CheckBox host = new CheckBox("HOST");
		CheckBox review = new CheckBox("REVIEWED");
		CheckBox reviewer = new CheckBox("REVIEWER");
		CheckBox listing = new CheckBox("LISTING");
		CheckBox calender = new CheckBox("CALENDARAVAILABLE");
		
		CheckBox amenities = new CheckBox("AMENITIES");
		CheckBox ROOM_TYPE = new CheckBox("ROOM_TYPE");
		CheckBox BED_TYPE = new CheckBox("BED_TYPE");
		CheckBox PROPERTY_TYPE = new CheckBox("PROPERTY_TYPE");
		CheckBox CANCELLATION = new CheckBox("CANCELLATION");
		
		CheckBox CITY = new CheckBox("CITY");
		CheckBox COUNTRY = new CheckBox("COUNTRY");
		CheckBox NEIGHBOURHOOD = new CheckBox("NEIGHBOURHOOD");
		
		CheckBox HOST_VERIFICATION = new CheckBox("HOST_VERIFICATION");
		CheckBox RESPONSE_TIME = new CheckBox("RESPONSE_TIME");
		
		Text title = new Text("Table to search");
	    title.setFont(Font.font("Arial", FontWeight.BOLD, 30));
	    vbox.getChildren().add(title);

		host.setMinWidth(500);
		review.setMinWidth(500);
		reviewer.setMinWidth(500);
		listing.setMinWidth(500);
		calender.setMinWidth(500);
		amenities.setMinWidth(500);
		ROOM_TYPE.setMinWidth(500);
		BED_TYPE.setMinWidth(500);
		PROPERTY_TYPE.setMinWidth(500);
		CANCELLATION.setMinWidth(500);
		CITY.setMinWidth(500);
		COUNTRY.setMinWidth(500);
		NEIGHBOURHOOD.setMinWidth(500);
		HOST_VERIFICATION.setMinWidth(500);
		RESPONSE_TIME.setMinWidth(500);
		
		

		vbox1.getChildren().addAll(listing,host,review,reviewer,NEIGHBOURHOOD,CITY,COUNTRY,calender,ROOM_TYPE,BED_TYPE,PROPERTY_TYPE,amenities,CANCELLATION,HOST_VERIFICATION,RESPONSE_TIME);
		vbox1.setLayoutX(50);
		vbox1.setLayoutY(150);

		search_borderPane.getChildren().addAll(vbox1);
		
		search_button.setOnAction(e -> {

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

				BorderPane search2_borderPane = new BorderPane();

				Button submit_button = new Button("submit");
				VBox search2_vBox = new VBox();
				search2_vBox.getChildren().add(submit_button);        
				search2_borderPane.setBottom(search2_vBox);

				VBox search2_vbox1 = new VBox();
				search2_vbox1.setSpacing(5);
				VBox search2_vbox0 = new VBox();
				search2_vbox0.setSpacing(20);
				search2_borderPane.setTop(search2_vbox0);
				
				Text title1 = new Text("Attributes to search");
			    title1.setFont(Font.font("Arial", FontWeight.BOLD, 30));
			    
				
				search2_vbox0.getChildren().add(title1);

				if (table_name == "LISTING") {
					ArrayList<String> table_column = helper.tableColumn(table_name);
					for(int i = 0; i < table_column.size(); i++) {
						
						CheckBox name = new CheckBox(table_column.get(i));
						//name.setMinWidth(50);
						search2_vbox1.getChildren().add(name);
				
					}
					
					

					CheckBox ALL = new CheckBox("ALL");
					search2_vbox1.getChildren().add(ALL);
					
					search2_vbox1.setLayoutX(50);
					search2_vbox1.setLayoutY(150);
					
					ScrollPane list_scrollPane = new ScrollPane();
					
					list_scrollPane.setContent(search2_vbox1);
					
					search2_borderPane.setLeft(list_scrollPane);
					//search2_borderPane.getChildren().add(list_scrollPane);
					
				}
				else  {
					ArrayList<String> table_column = helper.tableColumn(table_name);
					
					for(int i = 0; i < table_column.size(); i++) {
						
						CheckBox name = new CheckBox(table_column.get(i));
						search2_vbox1.getChildren().add(name);
					}

					CheckBox ALL = new CheckBox("ALL");

					search2_vbox1.getChildren().add(ALL);
					search2_vbox1.setLayoutX(50);
					search2_vbox1.setLayoutY(150);
					search2_borderPane.setLeft(search2_vbox1);
					//search2_borderPane.getChildren().add(search2_vbox1);
				}

				
				Scene search2_scene = new Scene(search2_borderPane, 1000, 700);	
				helper.main_stage.setTitle("You Are at the SEARCH Page :)");
				helper.main_stage.setScene(search2_scene);
				
				Button search_back_menu = new Button("Back To Menu");
				Button search_back_search = new Button("Back To Search");
				search2_vBox.getChildren().addAll(search_back_menu,search_back_search); 

				search_back_menu.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						helper.main_stage.setScene(helper.main_scene);
					}
				});
				search_back_search.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						
					
						//helper.main_stage.setScene(helper.main_scene);	
						helper.main_stage.setScene(helper.search_scene);
						System.out.println(123);
					}
				});
				
				submit_button.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						ArrayList<String> columnNames = new ArrayList<>();

						for(int i = 0; i < search2_vbox1.getChildren().size(); i++) {

							if(((CheckBox) search2_vbox1.getChildren().get(i)).isSelected()) {
								if(((CheckBox) search2_vbox1.getChildren().get(i)).getText()=="ALL" && !columnNames.isEmpty()) {
									Alert alert = new Alert(AlertType.ERROR);
									alert.setTitle("Error");
									alert.setHeaderText(null);
									alert.setContentText(" \"ALL Bottom\" can only be selected by itself");

									alert.showAndWait();
									return;
								}
								columnNames.add(((CheckBox) search2_vbox1.getChildren().get(i)).getText());

							}
						}
						
						if( columnNames.isEmpty()) {
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("Error");
							alert.setHeaderText(null);
							alert.setContentText("Please select a box!");
							alert.showAndWait();
							return;
						}



						if(columnNames.get(0)=="ALL") {
							columnNames.clear();
						}
						System.out.println("here");
						System.out.println(columnNames.size());
						//System.out.println(columnNames.get(0));

						ScrollPane search_scrollPane = new ScrollPane();

						VBox result_vbox = new VBox();
						search_scrollPane.setContent(result_vbox);
						search2_borderPane.setCenter(search_scrollPane);

						//build the query
						String select_table = "";
						String query = "";
						if(!columnNames.isEmpty()) {
							for(int i = 0; i < columnNames.size(); i++) {
								select_table  = select_table + columnNames.get(i) + ",";
							}
							System.out.println(select_table);
							System.out.println(select_table.substring(0, select_table.length() -1));
							
							
							query = query +  "SELECT " ;
							query = query + select_table.substring(0, select_table.length() -1);	
							query = query + " FROM " + table_name + helper.queryCreater(input_query.getText().toLowerCase(),table_name,columnNames);
						}
						else {
							query = "SELECT * FROM " + table_name + helper.queryCreater(input_query.getText().toLowerCase(),table_name,columnNames);
						}
						
						
						
						System.out.println(query);

						System.out.println("Search query ready");


						ResultSet result = helper.executeQuery(query);
						System.out.println(result);
						Platform.runLater(() -> helper.putResInTable(table_name, result, result_vbox, columnNames));



					}
				});
			
		});


		return search_borderPane;
	}
}
