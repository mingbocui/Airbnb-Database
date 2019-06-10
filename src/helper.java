import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Pair;

public abstract class helper {
	
	public static Scene main_scene;
	public static Scene search_scene;
	public static Scene insertDeleteScene;
	public static Stage main_stage;
	
	public static ArrayList<String> tableColumn(String table){
		ArrayList<String> table_column = new ArrayList<>();
		
		switch (table) {

        case "LISTING":
        	table_column.add("LISTING_ID");
        	table_column.add("HOST_ID");
        	table_column.add("LISTING_NAME");
        	table_column.add("CANCELLATION_POLICY_ID");
        	table_column.add("ROOM_TYPE_ID");
        	table_column.add("BED_TYPE_ID");
        	table_column.add("PROPERTY_TYPE_ID");
        	table_column.add("NEIGHBOURHOOD_ID");
        	table_column.add("REVIEW_SCORES_RATING");
        	table_column.add("CLEANING_FEE");
        	table_column.add("MINIMUM_NIGHTS");
        	table_column.add("SQUARE_FEET");   	
        	table_column.add("REVIEW_SCORES_CLEANLINESS");
        	table_column.add("REVIEW_SCORES_ACCURACY");
        	table_column.add("REVIEW_SCORES_VALUE");
        	table_column.add("REVIEW_SCORES_LOCATION");
        	table_column.add("REQUIRE_GUEST_PHONE_VERIFICATION");
        	table_column.add("PRICE");
        	
        	table_column.add("ACCOMMODATES");
        	
        	table_column.add("SECURITY_DEPOSIT");
        	table_column.add("REVIEW_SCORES_CHECKIN");
        	table_column.add("REVIEW_SCORES_COMMUNICATION");
        	table_column.add("MAXIMUM_NIGHTS");
        	table_column.add("GUESTS_INCLUDED");
        	table_column.add("MONTHLY_PRICE");
        	table_column.add("BATHROOMS");
        	table_column.add("BEDROOMS");
        	table_column.add("WEEKLY_PRICE");
        	table_column.add("EXTRA_PEOPLE");
        	
        	
        	table_column.add("IS_BUSINESS_TRAVEL_READY");
        	table_column.add("LONGITUDE");
        	table_column.add("LATITUDE");
        	table_column.add("HOUSE_RULES");
        	table_column.add("LISTING_SUMMARY");
        	table_column.add("TRANSIT");
        	table_column.add("LISTING_SPACE");
        	table_column.add("LISTING_ACCESS");
        	table_column.add("NEIGHBORHOOD_OVERVIEW");
        	table_column.add("PICTURE_URL");
        	table_column.add("NOTES");
        	
        	table_column.add("LISTING_URL");
        	table_column.add("INTERACTION");
        	table_column.add("DESCRIPTION");
        	table_column.add("BEDS");
        	table_column.add("REQUIRE_GUEST_PROFILE_PICTURE");

            break;
            
        case "LISTING_INSERT":
        	table_column.add("LISTING_ID (Eg. 186598)(Required)");
        	table_column.add("HOST_ID (Eg. 896690)(Required)");
        	table_column.add("LISTING_NAME (Eg. Double room and cozy)(Optional)");
        	table_column.add("CANCELLATION_POLICY_ID (Eg. 2)(Required)");
        	table_column.add("ROOM_TYPE_ID (Eg. 1)(Required)");
        	table_column.add("BED_TYPE_ID (Eg. 0)(Required)");
        	table_column.add("PROPERTY_TYPE_ID ((Eg. 0)(Required)");
        	table_column.add("NEIGHBOURHOOD_ID (Eg. 31)(Required)");
        	table_column.add("REVIEW_SCORES_RATING (Eg. 92)(Optional)");
        	table_column.add("CLEANING_FEE (Eg. 20)(Optional)");
        	table_column.add("MINIMUM_NIGHTS (Eg. 5)(Optional)");
        	table_column.add("SQUARE_FEET (Eg. 350)(Optional)");   	
        	table_column.add("REVIEW_SCORES_CLEANLINESS (Eg. 10 (1-10))(Optional)");
        	table_column.add("REVIEW_SCORES_ACCURACY (Eg. 10 (1-10))(Optional)");
        	table_column.add("REVIEW_SCORES_VALUE (Eg. 10 (1-10))(Optional)");
        	table_column.add("REVIEW_SCORES_LOCATION (Eg. 10 (1-10)))(Optional)");
        	table_column.add("REQUIRE_GUEST_PHONE_VERIFICATION (t or f)(Optional)");
        	table_column.add("PRICE (Eg. 100)(Optional)");
        	
        	table_column.add("ACCOMMODATES (Eg. 1)(Optional)");
        	
        	table_column.add("SECURITY_DEPOSIT (Eg. 100)(Optional)");
        	table_column.add("REVIEW_SCORES_CHECKIN (Eg. 10 (1-10)))(Optional)");
        	table_column.add("REVIEW_SCORES_COMMUNICATION (Eg. 10 (1-10)))(Optional)");
        	table_column.add("MAXIMUM_NIGHTS (Eg. 100)(Optional)");
        	table_column.add("GUESTS_INCLUDED (Eg. 1)(Optional)");
        	table_column.add("MONTHLY_PRICE (Eg. 1000)(Optional)");
        	table_column.add("BATHROOMS (Eg. 1)(Optional)");
        	table_column.add("BEDROOMS (Eg. 1)(Optional)");
        	table_column.add("WEEKLY_PRICE (Eg. 100)(Optional)");
        	table_column.add("EXTRA_PEOPLE (Eg. 10)(Optional)");
        	
        	
        	table_column.add("IS_BUSINESS_TRAVEL_READY (t or f)(Optional)");
        	table_column.add("LONGITUDE (Eg. 2.156)(Optional)");
        	table_column.add("LATITUDE (Eg. 2.156)(Optional)");
        	table_column.add("HOUSE_RULES (Optional)");
        	table_column.add("LISTING_SUMMARY (Optional)");
        	table_column.add("TRANSIT (Optional)");
        	table_column.add("LISTING_SPACE (Optional)");
        	table_column.add("LISTING_ACCESS (Optional)");
        	table_column.add("NEIGHBORHOOD_OVERVIEW (Optional)");
        	table_column.add("PICTURE_URL (Optional)");
        	table_column.add("NOTES (Optional)");
        	
        	table_column.add("LISTING_URL (Optional)");
        	table_column.add("INTERACTION (Optional)");
        	table_column.add("DESCRIPTION (Optional)");
        	table_column.add("BEDS (Eg. 1)(Optional)");
        	table_column.add("REQUIRE_GUEST_PROFILE_PICTURE (t or f)(Optional)");

            break;
     

        case "HOST":
        	table_column.add("HOST_ID");
        	table_column.add("HOST_NAME");
        	table_column.add("HOST_SINCE");
        	table_column.add("HOST_ABOUT");
        	table_column.add("HOST_PICTURE_URL");
        	table_column.add("HOST_THUMBNAIL_URL");
        	table_column.add("HOST_NEIGHBORHOOD");
        	table_column.add("HOST_URL");
        	table_column.add("HOST_RESPONSE_RATE");

            break;
            
        case "HOST_INSERT":
        	table_column.add("HOST_ID (Eg. 186598)(Required)");
        	table_column.add("HOST_NAME (Eg. Oscar)(Optional)");
        	table_column.add("HOST_SINCE (Eg. 22.05.01)(Optional)");
        	table_column.add("HOST_ABOUT (Optional)");
        	table_column.add("HOST_PICTURE_URL (Optional)");
        	table_column.add("HOST_THUMBNAIL_URL (Optional)");
        	table_column.add("HOST_NEIGHBORHOOD (Optional)");
        	table_column.add("HOST_URL (Optional)");
        	table_column.add("HOST_RESPONSE_RATE (Eg. 10 (1-10)))(Optional)");

            break;
            
        case "CALENDARAVAILABLE":
        	table_column.add("LISTING_ID");
        	table_column.add("CALENDAR_DATE");
        	table_column.add("PRICE");
        	table_column.add("AVAILABILITY");

        	break;
        

        case "REVIEWED":
        	table_column.add("REVIEWE_ID");
        	table_column.add("REVIEWER_ID");
        	table_column.add("LISTING_ID"); 
        	table_column.add("REVIEWE_DATE");
            break;
            
        case "REVIEWED_INSERT":
        	table_column.add("REVIEWE_ID (Eg. 186598)(Required)");
        	table_column.add("REVIEWER_ID (Eg. 186598)(Required)");
        	table_column.add("LISTING_ID (Eg. 186598)(Required)"); 
        	table_column.add("REVIEWE_DATE (Eg. 22.05.01)(Optional)");
        	table_column.add("COMMENTS (Optional)");

            break;
            
        case "REVIEWER":
        	table_column.add("REVIEWER_ID");
        	table_column.add("REVIEWER_NAME");
        	table_column.add("COMMENTS");
            break;
            
        case "AMENITIES":
        	table_column.add("AMENITIES_ID");
        	table_column.add("AMENITIES_TYPE");
            break;
            
        case "ROOM_TYPE":
        	table_column.add("ROOM_TYPE_ID");
        	table_column.add("ROOM_TYPE");
            break;
            
        case "BED_TYPE":
        	table_column.add("BED_TYPE_ID");
        	table_column.add("BED_TYPE");
            break;
            
        case "PROPERTY_TYPE":
        	table_column.add("PROPERTY_TYPE_ID");
        	table_column.add("TYPE");
            break;
            
        case "CANCELLATION":
        	table_column.add("CANCELLATION_ID");
        	table_column.add("CANCELLATION_POLICY");
            break;
            
        case "CITY":
        	table_column.add("CITY_ID");
        	table_column.add("COUNTRY_ID");
        	table_column.add("CITY_NAME");
            break;
            
        case "COUNTRY":
        	table_column.add("COUNTRY_ID");
        	table_column.add("COUNTRY_NAME");
            break;
            
        case "NEIGHBOURHOOD":
        	table_column.add("NEIGHBOURHOOD_ID");
        	table_column.add("CITY_ID");
        	table_column.add("NEIGHBOURHOOD_NAME");
            break;
            
        case "HOST_VERIFICATION":
        	table_column.add("VERIFICATION_ID");
        	table_column.add("VERIFICATION_TYPE");
            break;
            
        case "RESPONSE_TIME":
        	table_column.add("RESPONSE_TIME_ID");
        	table_column.add("TYPE");
            break;
    
    }
		return table_column;
}
	
//	public static String queryCreater(String keyWorkd, String table){
//		ArrayList<String> table_column = tableColumn(table);
//		String query = " WHERE";
//		for(int i = 0; i < table_column.size(); i++) {
//
//			query = query + " ";
//			query = query + table_column.get(i);
//			query = query +" LIKE '%";
//			query = query + keyWorkd;
//			query = query +"%' or";
//		}
//
//		return query.substring(0, query.length() -3);
//
//	}
	
	public static String queryCreater(String keyWorkd, String table,ArrayList<String> columnNames){
		
		ArrayList<String> table_column = new ArrayList<String> ();
		if (columnNames.isEmpty()) {
			System.out.println(165);
			table_column = tableColumn(table);//FIXME data is not correctly displayed in tableviews
		}
		else {
			table_column = columnNames;
			System.out.println(170);
		}
		
		
		String query = " WHERE";
		for(int i = 0; i < table_column.size(); i++) {

			query = query + " ";
			query = query + "LOWER(";
			query = query + table_column.get(i) + ")";
			query = query +" LIKE '%";
			query = query + keyWorkd;
			query = query +"%' or";
		}

		return query.substring(0, query.length() -3);

	}
	
	public static String deleteQuery(ArrayList<String> data, String table){
		ArrayList<String> table_column = tableColumn(table);


		String query = "DELETE FROM "+table+" WHERE ";

        
        for(int i = 0; i < data.size(); i++){

            if(!data.get(i).isEmpty()) {    //only consider nonempty fields

                //TODO for the moment it's exact match => do we need to do more/less than, like etc... ?

                if(isInt(data.get(i))){
                	query  = query  + table_column.get(i) +  " = " + data.get(i) + " AND ";
                }
                else if(isDate(data.get(i))){
                	query  = query  + table_column.get(i) +  " = DATE '" + data.get(i) + " AND ";
              
                }

                else{
                	query  = query  + table_column.get(i) +  " = '" + data.get(i) + "' AND ";
                }
            }

        }

        //remove last "AND"
        return query.substring(0,query.length()-4);

    }
	
	public static String insertQuery(ArrayList<Pair> data, String table){
		 //String table_column = tableColumn(table).toString();
		ArrayList<String> table_column = tableColumn(table);
		 String query = "INSERT INTO "+table+" (";//+table_column.substring(1, table_column.length()-1)+")"+" VALUES (";
		 String tableName = "";
		 int count = 0; 
		 //System.out.println("data.size() " +data.size());
		 for(int i = 0; i < table_column.size(); i++) {
			 //System.out.println("i " +i);
			 if((int)data.get(count).getValue() == i) {
				 
				 //System.out.println("table " +table_column.get(i));
				 tableName = tableName + table_column.get(i) + ", ";	
				 if(count <= data.size()-2) {
					
					 count ++;
					 //System.out.println("count " + count);
				 }
			 }
		 }
		 System.out.println(tableName.substring(0, tableName.length()-2));
		 query = query + tableName.substring(0, tableName.length()-2) +")" +  "VALUES (";
		 
		 System.out.println("data.size() " +data.size());
		 for(int i = 0; i < data.size(); i++) {
			 //if((int)data.get(i).getValue() == i) {
				 //System.out.println(i);
				 String world = (String)(data.get(i).getKey());
				 if(isInt(world)) {
					 System.out.println("Is Int " + world);
					 query = query + world + " ,";
				 }
				 else if(isDate(world)) {
					 query = query + "DATE '" + world + "' ,";
				 }
				 else {
					 query = query + "'" + world + "' ,";
				 }
			 //}
			
			 System.out.println(query);

		}
		 
		return query.substring(0, query.length()-2).concat(")");

    }
	
	public static String updateQuery(ArrayList<Pair> data, String table){
		 //String table_column = tableColumn(table).toString();
		ArrayList<String> table_column = tableColumn(table);
		 String query = "UPDATE "+table;//+table_column.substring(1, table_column.length()-1)+")"+" VALUES (";
		 ArrayList<String> tableName = new ArrayList<String>();
		 int count = 0; 
		 //System.out.println("data.size() " +data.size());
		 for(int i = 0; i < table_column.size(); i++) {
			 //System.out.println("i " +i);
			 if((int)data.get(count).getValue() == i) {
				 
				 //System.out.println("table " +table_column.get(i));
				 tableName.add(table_column.get(i)) ;	
				 if(count <= data.size()-2) {
					
					 count ++;
					 //System.out.println("count " + count);
				 }
			 }
		 }
		 //System.out.println(tableName.substring(0, tableName.length()-2));
		 query = query + " SET ";
		 
		 System.out.println("data.size() " +data.size());
		 for(int i = 1; i < data.size(); i++) {
			 //if((int)data.get(i).getValue() == i) {
				 //System.out.println(i);
				 String world = (String)(data.get(i).getKey());
				 if(isInt(world)) {
					 System.out.println("Is Int " + world);
					 query = query + tableName.get(i) + " = " + world + " ,";
				 }
				 else if(isDate(world)) {
					 query = query + tableName.get(i) + " = " + "DATE '" + world + "' ,";
				 }
				 else {
					 query = query + tableName.get(i) + " = " + "'" + world + "' ,";
				 }
			 //}
			
			 System.out.println(query);

		}
		 query = query.substring(0, query.length()-2);
		 query = query + " WHERE ";
		 query = query + tableName.get(0) + " = " + (String)(data.get(0).getKey());
		 
		return query ;

   }
	
	 public static boolean isInt(String s){

	        char[] c = s.toCharArray();

	        for(int i = 0; i < c.length; ++i){

	            if(!Character.isDigit(c[i])){
	                return false;
	            }

	        }

	        return true;
	    }


	    public static boolean isDate(String s){

	        char[] c = s.toCharArray();

	        for(int i = 0; i < c.length; ++i){

	            if(!Character.isDigit(c[i]) && c[i] != '-'){
	                return false;
	            }

	        }

	        return true;
	    }
	
	public static ResultSet executeQuery(String query){
		
		String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@cs322-db.epfl.ch:1521:ORCLCDB";
        String username = "C##DB2019_G13";
        String password = "DB2019_G13";
        
        Connection connection = null;

        try {

            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);

        } catch(Exception e) {
        	System.out.println("exxeption");
            e.printStackTrace();
            Alert submit_alert = new Alert(AlertType.ERROR);
			 submit_alert.setTitle("Error");
			 submit_alert.setHeaderText(null);
			 submit_alert.setContentText("Something Went Wrong");

			 submit_alert.showAndWait();
			 
        }

   
        ResultSet result = null;

        try {
            Statement s = connection.createStatement();
            System.out.println("Executing query...");            //TODO remove sysout
            result = s.executeQuery(query);
            System.out.println("result"); 
            
        }
        catch (SQLException e){
            e.printStackTrace();
            System.out.println("exxeption");
           
            Alert submit_alert = new Alert(AlertType.ERROR);
			 submit_alert.setTitle("Error");
			 submit_alert.setHeaderText(null);
			 submit_alert.setContentText("Something Went Wrong");

			 submit_alert.showAndWait();
        }

        return result;
    }
	
	public static void putResInTable(String table, ResultSet res, VBox container, ArrayList<String> columnNames){ 
		ArrayList<String> table_column = new ArrayList<String> ();
		if (columnNames.isEmpty()) {
			table_column = tableColumn(table);//FIXME data is not correctly displayed in tableviews
		}
		else {
			table_column = columnNames;
		}
		


        //create result table for each table
        TableView<ObservableList<String>> new_table = new TableView<>();
		
        new_table.setMinWidth(500);

        //List where the result data will be stored
        ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
       
       

        //create columns, read result and insert data into the table
        try {


           for(int i = 0; i < table_column.size(); ++i){

               final int j = i;

               TableColumn col = new TableColumn(table_column.get(i));

               //define what to do when receiving data from observable list
               col.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param ->{
                   if(param.getValue().size() > j){
                       Object o = param.getValue().get(j);  //in case the value is NULL in DB
                       if(o != null) {
                           return new SimpleStringProperty(o.toString());
                       }
                       else{
                           return null;
                       }
                   }
                   else{
                       return null;
                   }
               }

               );

               new_table.getColumns().add(col);

           }


            //retrieve data from resulset
            while (res.next()) {

             //get data from result set by row
             ObservableList<String> row = FXCollections.observableArrayList();
            

             for(int i = 1; i < res.getMetaData().getColumnCount() + 1; ++i){


                 row.add(res.getString(i));
  
  
                
             }

             data.add(row);

            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }


        //put the data into table view
        //tableView.setItems(data);
        new_table.setItems(data);


        //add table view to the container
        container.getChildren().add(new_table);

        System.out.println("View updated !");

    }
	


}

