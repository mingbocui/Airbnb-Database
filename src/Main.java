import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;



public class Main extends Application{
	
	//public Stage insertDeleteStage = new Stage();
	//Scene search_scene;

	public static void main(String[] args) {
		launch(args); 
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		 helper.main_stage = primaryStage ;
		 
		 //Image image = new Image(new FileInputStream("images.png"));
		
		
		// VBox to vertically contain the button
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(10, 50, 50, 50));
		vbox.setSpacing(10);
		Label title = new Label("MENU");
		title.setFont(Font.font("Amble CN", FontWeight.BOLD, 24));
		
        //user buttons
		Button search_button = new Button("Search");
		Button predefined_queries_button = new Button("Predefined Queries");
		Button insert_delete_button = new Button("Insert/Delete");
		Button update_button = new Button("Update");
		
		ImageView image = new ImageView(new Image("file:///Users/wt.huang/Desktop/UI/image/airbnb-796x357.jpg"));
		
		image.setScaleX(0.8);
		image.setScaleY(0.8);
		// add buttons and title
		
		vbox.getChildren().addAll(image,title, search_button, update_button, insert_delete_button, predefined_queries_button);

		//set scene for primary stage
		helper.main_scene = new Scene(vbox,1000, 700);
		
		helper.main_scene.setFill(Color.BLUE);
		//primaryStage.setX(450);
		//primaryStage.setY(450);
		//helper.main_stage.set
		
		//set primary stage
		helper.main_stage.setTitle("Hello and Welcome to Airbnb!");
		helper.main_stage.setScene(helper.main_scene);
		helper.main_stage.show();
		
		//helper.main_stage.getIcons().add(new Image("file:///Users/wt.huang/Downloads/rbnb_db_project-master-5/UI/src/images.png"));
		
		//change scene to search
		BorderPane search_pane = SearchLayout.searchPane();		
		search_button.setOnAction(e -> {
			if(search_pane.getScene() != null) {				
				helper.main_stage.setScene(search_pane.getScene());
			}
			else {
				helper.search_scene = new Scene(search_pane, 1000, 700);	
				helper.main_stage.setTitle("You Are at the Search Page :)");
				helper.main_stage.setScene(helper.search_scene);
			}
		
			helper.main_stage.show();
	
		});        
		Button search_back_bottom = new Button("Back To Menu");
		search_pane.setBottom(search_back_bottom);
		search_back_bottom.setOnAction(e -> {
			helper.main_stage.setScene(helper.main_scene);
			helper.main_stage.setTitle("Hello and Welcome to Airbnb!");
		});

        
        
        //change scene to insert/delete
		
		
        BorderPane insert_delete_pane = InsertDeleteLayout.insertDeletePane(); 	
        insert_delete_button.setOnAction(e -> {
        	if(insert_delete_pane.getScene() != null) {
        		helper.main_stage.setScene(insert_delete_pane.getScene());
			}
        	else {
        		helper.insertDeleteScene = new Scene(insert_delete_pane, 1000, 700);		
        		helper.main_stage.setTitle("You Are at the Insert/Delete Page");
        		helper.main_stage.setScene(helper.insertDeleteScene);
        	}
        	helper.main_stage.show();

		});        
		Button insert_delete_bottom = new Button("Back To Menu");
		insert_delete_pane.setBottom(insert_delete_bottom);
		insert_delete_bottom.setOnAction(e -> {
			helper.main_stage.setScene(helper.main_scene);
			helper.main_stage.setTitle("Hello and Welcome to Airbnb!");
		});
        
        
        
        
        //change scene to predefined queries
		BorderPane predefined_queries_pane = PDQueriesLayout.pdQueriesPane();	
        predefined_queries_button.setOnAction(e -> {
        	if(predefined_queries_pane.getScene() != null) {
        		helper.main_stage.setScene(predefined_queries_pane.getScene());
			}
        	else {
        		Scene predefinedQueriesScene = new Scene(predefined_queries_pane, 1000, 700);		
        		helper.main_stage.setTitle("You Are at the Predefined Queries Page :)");
        		helper.main_stage.setScene(predefinedQueriesScene);       		
        	}
        	helper.main_stage.show();
	
		});        
		Button predefined_queries_back_bottom = new Button("Back To Menu");
		predefined_queries_pane.setBottom(predefined_queries_back_bottom);
		predefined_queries_back_bottom.setOnAction(e -> {
			helper.main_stage.setScene(helper.main_scene);
			helper.main_stage.setTitle("Hello and Welcome to Airbnb!");
		});
        
		 //change scene to updatePane
		BorderPane update_pane = UpdateLayout.updatePane();	
		update_button.setOnAction(e -> {
        	if(update_pane.getScene() != null) {
        		helper.main_stage.setScene(update_pane.getScene());
			}
        	else {
        		Scene updateScene = new Scene(update_pane, 1000, 700);		
        		helper.main_stage.setTitle("You Are at the Predefined Update Page :)");
        		helper.main_stage.setScene(updateScene);       		
        	}
        	helper.main_stage.show();
	
		});        
		Button update_back_bottom = new Button("Back To Menu");
		update_pane.setBottom(update_back_bottom);
		update_back_bottom.setOnAction(e -> {
			helper.main_stage.setScene(helper.main_scene);
			helper.main_stage.setTitle("Hello and Welcome to Airbnb!");
		});
        
	}
}
