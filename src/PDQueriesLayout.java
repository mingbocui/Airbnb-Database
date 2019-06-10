import java.sql.ResultSet;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public abstract class PDQueriesLayout {
	public static BorderPane pdQueriesPane(){
		

		BorderPane pd_queries_borderPane = new BorderPane();
//		
//        //What is the average price for a listing with 8 bedrooms?
		String query1 = "SELECT AVG(price)\n" + 
				"FROM listing\n" + 
				"WHERE listing.bedrooms = 8\n"; 
				
//		
//        //What is the average cleaning review score for listings with TV?
		String query2 = "SELECT AVG(l.REVIEW_SCORES_CLEANLINESS)\n" + 
				"FROM listing l, HASAMENITIES ha\n" + 
				"WHERE l.listing_id = ha.listing_id AND \n" + 
				"      ha.AMENITIES_ID = (SELECT am.AMENITIES_ID FROM AMENITIES am\n" + 
				"      			 WHERE am.AMENITIES_TYPE = 'TV')\n" ;

		
      String query3 ="SELECT * \n" + 
      		"FROM host H\n" + 
      		"WHERE H.host_id IN (SELECT L.host_id \n" + 
      		"                   FROM listing L, calendaravailable C\n" + 
      		"                   WHERE L.listing_id = C.listing_id AND \n" + 
      		"                         C.availability = 't' AND \n" + 
      		"                         C.CALENDAR_DATE >= '01-MAR-19' AND \n" + 
      		"                         C.CALENDAR_DATE <= '30-SEP-19')\n"; 
      		

//        //Print how many listing items exist that are posted by two different hosts but the hosts have the same name.
		String query4 = "SELECT count(*)\n" + 
				"FROM listing l\n" + 
				"WHERE l.host_id in (select h1.host_id\n" + 
				"                from host h1, host h2\n" + 
				"                where h1.host_name = h2.host_name and h1.host_id != h2.host_id)\n"; 
				                             

		
      String query5 = "SELECT c.CALENDAR_DATE\n" + 
      		"FROM listing, calendaravailable c\n" + 
      		"WHERE listing.listing_id = c.listing_id AND\n" + 
      		"c.availability = 't' AND\n" + 
      		"      listing.host_id = (SELECT host_id\n" + 
      		"                         FROM host\n" + 
      		"                         WHERE host.host_name = 'Viajes Eco')\n" ;

      String query6 = "SELECT h.host_id, h.host_name\n" + 
      		"FROM host h \n" + 
      		"WHERE h.host_id IN (SELECT l.host_id\n" + 
      		"                    FROM listing l\n" + 
      		"                    GROUP BY l.host_id \n" + 
      		"                    HAVING COUNT(*) = 1)\n" ;
      		
 
    String query7_1 = "CREATE VIEW with_wifi as (                                          \n" + 
    		"SELECT l.listing_id\n" + 
    		"FROM hasamenities ha, listing l, amenities am\n" + 
    		"WHERE l.listing_id = ha.listing_id AND ha.amenities_id = am.amenities_id AND am.amenities_type = 'Wifi')";
    
    String query7_2 = "SELECT (\n" + 
    		"(SELECT avg(l.price) FROM listing l\n" + 
    		"WHERE l.listing_id in (SELECT * from with_wifi) ) - \n" + 
    		"(SELECT avg(l.price) FROM listing l\n" + 
    		"WHERE l.listing_id not in (SELECT * from with_wifi)) ) \n" + 
    		"AS dif FROM dual\n" ;
      
    String query7_3 = "drop view with_wifi \n";

        //How much more (or less) costly to rent a room with 8 beds in Berlin compared to Madrid on average? 
	
		
		String query8 = "SELECT AVG(L1.price) - AVG(L2.price)\n" + 
				"FROM listing L1, listing L2\n" + 
				"WHERE L1.Bedrooms = 8 AND \n" + 
				"L1.NEIGHBOURHOOD_ID IN \n" + 
				"  (SELECT n.NEIGHBOURHOOD_ID\n" + 
				"         FROM NEIGHBOURHOOD n\n" + 
				"         WHERE n.CITY_ID = (SELECT c.CITY_ID\n" + 
				"                            FROM city c\n" + 
				"                            WHERE c.CITY_NAME = 'Berlin')) AND\n" + 
				"                            L2.Bedrooms = 8 AND \n" + 
				"                            L2.NEIGHBOURHOOD_ID IN \n" + 
				"                              (SELECT n.NEIGHBOURHOOD_ID\n" + 
				"                               FROM NEIGHBOURHOOD n\n" + 
				"                               WHERE n.CITY_ID = (SELECT c.CITY_ID\n" + 
				"                                                  FROM city c\n" + 
				"                                                  WHERE c.CITY_NAME = 'Madrid')) \n" ;
				

//		//Find the top-10 (in terms of the number of listings) hosts (host_ids, host_names) in Spain. 
		String query9 = "SELECT * FROM\n" + 
				"  (SELECT H.host_id, H.host_name\n" + 
				"   FROM listing L, host H\n" + 
				"   WHERE L.NEIGHBOURHOOD_ID IN \n" + 
				"     (SELECT n.NEIGHBOURHOOD_ID\n" + 
				"      FROM NEIGHBOURHOOD n\n" + 
				"      WHERE n.CITY_ID IN (SELECT c.CITY_ID\n" + 
				"                          FROM city c, country ct\n" + 
				"                          WHERE ct.COUNTRY_NAME = 'Spain' AND \n" + 
				"                                c.COUNTRY_ID = ct.COUNTRY_ID))                            \n" + 
				"      AND L.host_id = h.host_id\n" + 
				"      GROUP BY L.host_id, H.host_name, H.host_id\n" + 
				"      ORDER BY COUNT(*) DESC)\n" + 
				"WHERE ROWNUM <=10\n";
				
				
			
//
//
//		//Find the top-10 rated (review_score_rating) apartments (id,name) in Barcelona. 
		String query10 = "SELECT * FROM(\n" + 
				"SELECT L.listing_id,  L.listing_name\n" + 
				"FROM listing l\n" + 
				"WHERE l.NEIGHBOURHOOD_ID IN \n" + 
				"   (SELECT n.NEIGHBOURHOOD_ID\n" + 
				"    FROM NEIGHBOURHOOD n\n" + 
				"    WHERE n.CITY_ID IN \n" + 
				"(SELECT c.CITY_ID\n" + 
				"       FROM city c\n" + 
				"       WHERE c.CITY_NAME = 'Barcelona'))AND\n" + 
				"             l.PROPERTY_TYPE_ID IN  \n" + 
				"               (SELECT pt.property_type_id \n" + 
				"                FROM property_type pt \n" + 
				"                WHERE pt.TYPE = 'Apartment') AND\n" + 
				"             l.REVIEW_SCORES_RATING IS NOT NULL\n" + 
				"             ORDER BY l.REVIEW_SCORES_RATING DESC)\n" + 
				"WHERE rownum <= 10\n";
				
				
		String query11 = "SELECT ct.city_name, count(*) FROM LISTING l1, NEIGHBOURHOOD n, city ct\n" + 
				"WHERE l1.HOST_ID IN (SELECT distinct(l2.HOST_ID) FROM LISTING l2 WHERE     l2.square_feet is not null) AND \n" + 
				"l1.NEIGHBOURHOOD_ID = n.NEIGHBOURHOOD_ID AND \n" + 
				"n.CITY_ID = ct.CITY_ID\n" + 
				"GROUP BY ct.city_name \n" + 
				"ORDER BY ct.city_name ASC\n";
		
		String query12 = "SELECT neighbourhood_name, review_scores_rating FROM( \n" + 
				"SELECT nb.NEIGHBOURHOOD_NAME, l.REVIEW_SCORES_RATING, \n" + 
				"floor((count(all *) over(partition by (l.NEIGHBOURHOOD_ID))+1)/2) AS median_index, \n" + 
				"row_number() over(partition by l.neighbourhood_id ORDER BY l.review_scores_rating DESC) AS rank\n" + 
				"      FROM listing l, neighbourhood nb, city\n" + 
				"WHERE l.neighbourhood_id = nb.neighbourhood_id AND \n" + 
				"      	city.CITY_ID=nb.CITY_ID AND \n" + 
				"      	city.CITY_NAME = 'Madrid' AND \n" + 
				"      	l.REVIEW_SCORES_RATING is not null) medians\n" + 
				"WHERE medians.median_index = medians.rank\n" + 
				"ORDER BY medians.REVIEW_SCORES_RATING DESC\n" + 
				"fetch first 5 rows only\n";
		
		
		String query13 = "SELECT h.host_id, h.host_name FROM\n" + 
				"(SELECT host_id, rank() over(ORDER BY numListingPerHost DESC) AS rank FROM\n" + 
				"(SELECT l.host_id, count(*)AS numListingPerHost FROM \n" + 
				"listing l\n" + 
				"GROUP BY l.host_id)) rankingTable,\n" + 
				"      host h\n" + 
				"WHERE h.host_id = rankingTable.host_id AND rankingTable.rank = 1\n" ;
				
		
		String query14 = "SELECT * FROM\n" + 
				" (SELECT AVG(cal.Price) AS avgPrice, cal.listing_id FROM\n" + 
				"  (SELECT l.listing_id FROM \n" + 
				"    listing l, NEIGHBOURHOOD neigh, city c, property_type p, cancellation canc\n" + 
				"    WHERE neigh.city_id = c.city_id AND \n" + 
				"	    c.city_name = 'Berlin' AND \n" + 
				"	    l.property_type_id = p.property_type_id AND \n" + 
				"	    p.type = 'Apartment' AND \n" + 
				"	    l.beds>=2 AND \n" + 
				"          l.review_scores_location >=8 AND \n" + 
				"          l.neighbourhood_id = neigh.neighbourhood_id AND \n" + 
				"	    canc.cancellation_id = l.cancellation_policy_id AND \n" + 
				"	    canc.cancellation_policy = 'flexible' AND\n" + 
				"	    l.host_id IN \n" + 
				"           (SELECT h.host_id FROM host h, host_verification hv, hasverification hhv\n" + 
				"		WHERE h.host_id = hhv.host_id AND \n" + 
				"                  hv.verification_id = hhv.verification_id AND \n" + 
				"                  hv.verification_type LIKE '%government_id%')) accepted_listing,\n" + 
				" CALENDARAVAILABLE cal\n" + 
				" WHERE cal.CALENDAR_DATE >= '01-MAR-19' AND \n" + 
				"       cal.CALENDAR_DATE <=  '30-APR-19' AND \n" + 
				"	 accepted_listing.listing_id = cal.listing_id AND \n" + 
				"	 cal.AVAILABILITY = 't'\n" + 
				" GROUP BY cal.LISTING_ID\n" + 
				" ORDER BY avgPrice ASC)\n" + 
				"WHERE rownum <= 5\n" ;
			
				
		String query15 = "SELECT * FROM\n" + 
				"  (SELECT accepted_listing.listing_id, l.accommodates,l.review_scores_rating,\n" + 
				"         row_number() over(partition by l.accommodates ORDER BY  \n" + 
				"         l.review_scores_rating DESC) AS row_number\n" + 
				"   FROM (SELECT  hA.listing_id, count(*) AS num FROM hasAmenities hA, Amenities A\n" + 
				"	   WHERE hA.amenities_id = A.amenities_id AND(\n" + 
				"		   A.amenities_type = 'WIFI' OR\n" + 
				"		   A.amenities_type = 'Internet' OR\n" + 
				"		   A.amenities_type = 'TV' OR\n" + 
				"		   A.amenities_type = 'Free street parking') \n" + 
				"	    GROUP BY hA.listing_id) accepted_listing,\n" + 
				"	    LISTING l\n" + 
				"  WHERE l.listing_id = accepted_listing.listing_id AND \n" + 
				"        l.accommodates <=16 AND \n" + 
				"        accepted_listing.num >= 2 AND \n" + 
				"        l.review_scores_rating is not null\n" + 
				"  ORDER BY l.accommodates ASC)\n" + 
				"WHERE row_number <= 5\n" ; 
				
				
		String query16 = "SELECT HOST_ID, LISTING_ID FROM\n" + 
				"  (SELECT HOST_ID, LISTING_ID, ROW_NUMBER() over(partition by HOST_ID \n" + 
				"    ORDER BY NumberOfReviewPerList DESC) AS RANK FROM\n" + 
				"          (SELECT distinct(l.LISTING_ID), l.HOST_ID, \n" + 
				"           count(*) over(partition by l.LISTING_ID) AS NumberOfReviewPerList\n" + 
				"           FROM LISTING l, REVIEWED r\n" + 
				"           WHERE l.LISTING_ID = r.LISTING_ID))\n" + 
				"WHERE RANK <= 3\n" ;
		
		String query17 = "SELECT AMENITIES_TYPE, NEIGHBOURHOOD_NAME FROM\n" + 
				"  (SELECT AMENITIES_TYPE, AMEN_CNT, NEIGHBOURHOOD_NAME,\n" + 
				"          row_number() over(partition by SORTED_AMEN.NEIGHBOURHOOD_NAME \n" + 
				"                            ORDER BY AMEN_CNT DESC) AS SORT \n" + 
				"   FROM (SELECT distinct AMENITIES_TYPE, AMEN_CNT, NEIGHBOURHOOD_NAME FROM\n" + 
				"          (SELECT A.AMENITIES_TYPE, count(AMENITIES_TYPE)over(partition by    \n" + 
				"            NEIGH.NEIGHBOURHOOD_NAME, A.AMENITIES_TYPE) AS AMEN_CNT,   \n" + 
				"            NEIGH.NEIGHBOURHOOD_NAME FROM AMENITIES A, LISTING L, hasAmenities hA,         \n" + 
				"            NEIGHBOURHOOD NEIGH, CITY C, ROOM_TYPE RT\n" + 
				"     WHERE    L.LISTING_ID = hA.LISTING_ID AND \n" + 
				"              hA.AMENITIES_ID = A.AMENITIES_ID AND \n" + 
				"              L.NEIGHBOURHOOD_ID = NEIGH.NEIGHBOURHOOD_ID AND\n" + 
				" 	        NEIGH.city_id = C.city_id AND \n" + 
				"              C.city_name = 'Berlin' AND \n" + 
				"	        L.ROOM_TYPE_ID = RT.ROOM_TYPE_ID AND \n" + 
				"              RT.ROOM_TYPE = 'Private room') AMEN\n" + 
				"	  ORDER BY AMEN.NEIGHBOURHOOD_NAME, AMEN.AMEN_CNT DESC)SORTED_AMEN)final_data\n" + 
				"WHERE final_data.SORT <= 3\n" ; 

		
		String query18_1 = "CREATE VIEW NUM_HOST_VERIFICATIONS AS\n" + 
				"  SELECT H.HOST_ID, COUNT(HASV.VERIFICATION_ID) over(partition by HASV.HOST_ID) AS   \n" + 
				"  VERI_NUM FROM HOST H, HASVERIFICATION HASV, HOST_VERIFICATION HV\n" + 
				"  WHERE H.HOST_ID = HASV.HOST_ID AND HASV.VERIFICATION_ID = HV.VERIFICATION_ID";
		
		String query18_2 = "SELECT AVG(AVG_MOST.AVG_MOST_SCORE-AVG_MIN.AVG_LEAST_SCORE) AS GAP FROM \n" + 
				"  (SELECT coalesce(AVG(L1.REVIEW_SCORES_COMMUNICATION), 0) AS AVG_MOST_SCORE FROM\n" + 
				"    (SELECT filter1.HOST_ID FROM\n" + 
				"       (SELECT NV.HOST_ID FROM NUM_HOST_VERIFICATIONS NV\n" + 
				"	  ORDER BY NV.VERI_NUM DESC)filter1\n" + 
				"	  WHERE rownum = 1) MAX_HOST,\n" + 
				"   LISTING L1\n" + 
				"   WHERE L1.HOST_ID = MAX_HOST.HOST_ID AND \n" + 
				"   L1.REVIEW_SCORES_COMMUNICATION is not null)AVG_MOST,\n" + 
				"\n" + 
				"  (SELECT coalesce(AVG(L2.REVIEW_SCORES_COMMUNICATION), 0) AS AVG_LEAST_SCORE FROM\n" + 
				"    (SELECT filter2.HOST_ID FROM\n" + 
				"       (SELECT NV2.HOST_ID FROM NUM_HOST_VERIFICATIONS NV2\n" + 
				"	  ORDER BY NV2.VERI_NUM ASC)filter2\n" + 
				"	  WHERE rownum = 1) MIN_HOST,\n" + 
				"     LISTING L2\n" + 
				"     WHERE L2.HOST_ID = MIN_HOST.HOST_ID AND \n" + 
				"   	     L2.REVIEW_SCORES_COMMUNICATION is not null)AVG_MIN \n" ;
		
		String query18_3 = "drop view NUM_HOST_VERIFICATIONS \n";
		

				
		String query19 = "SELECT ROOM_TYPE, CITY_NAME FROM\n" + 
				"  (SELECT Filter_Listing.ROOM_TYPE, Filter_Listing.CITY_NAME, rank() over(partition  \n" + 
				"          by Filter_Listing.ROOM_TYPE ORDER BY total) AS rank FROM \n" + 
				"    (SELECT distinct(AVG_ACC.ROOM_TYPE),average_per_room_type,sum(REVIEW_LISTING)    \n" + 
				"      over(partition by CITY_NAME, RT.ROOM_TYPE) AS total, CITY_NAME FROM\n" + 
				"	LISTING L, CITY C, NEIGHBOURHOOD NEIGH, ROOM_TYPE RT,\n" + 
				"	 (SELECT distinct(RVD.LISTING_ID), count(*) \n" + 
				"        over(partition by RVD.LISTING_ID) AS REVIEW_LISTING FROM REVIEWED RVD) R_L,\n" + 
				"          (SELECT L.LISTING_ID, RT.ROOM_TYPE, AVG(L.ACCOMMODATES) \n" + 
				"           over(partition by RT.ROOM_TYPE) AS average_per_room_type\n" + 
				"		FROM LISTING L, ROOM_TYPE RT\n" + 
				"		WHERE L.ROOM_TYPE_ID = RT.ROOM_TYPE_ID)AVG_ACC\n" + 
				"\n" + 
				"	  WHERE R_L.LISTING_ID = AVG_ACC.LISTING_ID AND \n" + 
				"		  average_per_room_type > 3 AND \n" + 
				"		  L.LISTING_ID = AVG_ACC.LISTING_ID AND \n" + 
				"		  L.NEIGHBOURHOOD_ID = NEIGH.NEIGHBOURHOOD_ID AND \n" + 
				"		  NEIGH.CITY_ID = C.CITY_ID AND \n" + 
				"              RT.ROOM_TYPE_ID = L.ROOM_TYPE_ID)Filter_Listing)\n" + 
				"WHERE RANK = 1\n"; 
		
		String query20 = "SELECT nb.NEIGHBOURHOOD_NAME\n" + 
				"FROM (SELECT l.neighbourhood_id, count(distinct l.listing_id) filtered\n" + 
				"      FROM listing l, neighbourhood nb, city, calendaravailable cal, host h\n" + 
				"WHERE l.neighbourhood_id = nb.neighbourhood_id AND \n" + 
				"      nb.city_id = city.city_id AND \n" + 
				"      city.city_name ='Madrid' AND\n" + 
				"      cal.listing_id = l.listing_id AND \n" + 
				"cal.availability = 'f' AND \n" + 
				"CAL.CALENDAR_DATE >= date '2019-01-01' AND\n" + 
				"      	l.host_id = h.host_id AND \n" + 
				"h.host_since <= date '2017-06-01'\n" + 
				"GROUP BY l.neighbourhood_id) filtered,\n" + 
				"(SELECT l.neighbourhood_id, count(distinct l.listing_id) total\n" + 
				"FROM listing l, neighbourhood nb, city\n" + 
				"WHERE l.neighbourhood_id = nb.neighbourhood_id AND \n" + 
				"nb.city_id = city.city_id AND \n" + 
				"city.city_name ='Madrid'\n" + 
				"GROUP BY l.neighbourhood_id) total,\n" + 
				"neighbourhood nb\n" + 
				"WHERE (filtered.filtered/total.total)>=0.5 AND \n" + 
				"filtered.neighbourhood_id = total.neighbourhood_id AND \n" + 
				"      nb.NEIGHBOURHOOD_ID = filtered.neighbourhood_id\n"; 
			
				
		String query21 = "SELECT country.country_name\n" + 
				"FROM (SELECT count(distinct l.listing_id) AS available_amount,\n" + 
				"             country.country_id AS country_id\n" + 
				"      FROM listing l, calendaravailable cal, neighbourhood nbhd, city, country\n" + 
				"WHERE l.listing_id = cal.listing_id AND \n" + 
				"CAL.CALENDAR_DATE >= date '2018-01-01' AND\n" + 
				"CAL.CALENDAR_DATE < date '2019-01-01' AND \n" + 
				"cal.availability = 't' AND\n" + 
				"l.neighbourhood_id = nbhd.neighbourhood_id AND \n" + 
				"nbhd.city_id = city.city_id AND \n" + 
				"city.country_id = country.country_id\n" + 
				"GROUP BY country.country_id) filtered,\n" + 
				"(SELECT count(*) AS total_amount, country.country_id AS country_id\n" + 
				" FROM listing l, neighbourhood nbhd, city, country\n" + 
				"WHERE l.neighbourhood_id = nbhd.neighbourhood_id AND \n" + 
				"nbhd.city_id = city.city_id AND \n" + 
				"city.country_id = country.country_id\n" + 
				"GROUP BY country.country_id) total, \n" + 
				"country\n" + 
				"WHERE total.country_id = filtered.country_id AND \n" + 
				"(filtered.available_amount/ total.total_amount) >= 0.2 AND \n" + 
				"country.country_id = filtered.country_id\n" ;
		
		String query22 = "SELECT nb.neighbourhood_name\n" + 
				"FROM (SELECT l.neighbourhood_id, count(*) filtered\n" + 
				"FROM listing l, neighbourhood nb, city, cancellation cc\n" + 
				"WHERE l.cancellation_policy_id = cc.cancellation_id AND \n" + 
				"      cc.cancellation_policy = 'strict_14_with_grace_period' AND\n" + 
				"     l.neighbourhood_id = nb.neighbourhood_id AND\n" + 
				"     nb.city_id = city.city_id AND \n" + 
				"city.city_name = 'Barcelona'\n" + 
				"GROUP BY l.neighbourhood_id) filtered,\n" + 
				"(SELECT l.neighbourhood_id, count(*) total\n" + 
				" FROM listing l, neighbourhood nb, city\n" + 
				"WHERE l.neighbourhood_id = nb.neighbourhood_id AND\n" + 
				"      nb.city_id = city.city_id AND \n" + 
				" city.city_name = 'Barcelona'\n" + 
				"GROUP BY l.neighbourhood_id) total,\n" + 
				"neighbourhood nb\n" + 
				"WHERE (filtered.filtered/total.total)>0.05 AND \n" + 
				"filtered.neighbourhood_id = total.neighbourhood_id AND\n" + 
				"nb.NEIGHBOURHOOD_ID = filtered.neighbourhood_id\n" ;
		
			
				



		Button q1_button = new Button("Q1");
		Button q2_button = new Button("Q2");
		Button q3_button = new Button("Q3");
		Button q4_button = new Button("Q4");
		Button q5_button = new Button("Q5");
		Button q6_button = new Button("Q6");
		Button q7_button = new Button("Q7");
		Button q8_button = new Button("Q8");
		Button q9_button = new Button("Q9");
		Button q10_button = new Button("Q10");
		Button q11_button = new Button("Q11");
		Button q12_button = new Button("Q12");
		Button q13_button = new Button("Q13");
		Button q14_button = new Button("Q14");
		Button q15_button = new Button("Q15");
		Button q16_button = new Button("Q16");
		Button q17_button = new Button("Q17");
		Button q18_button = new Button("Q18");
		Button q19_button = new Button("Q19");
		Button q20_button = new Button("Q20");
		Button q21_button = new Button("Q21");
		Button q22_button = new Button("Q22");
		
		//add the buttons to the layout
		
		HBox hBox1 = new HBox();
        hBox1.getChildren().addAll(q1_button,q12_button);
        
        HBox hBox2 = new HBox();
        hBox2.getChildren().addAll(q2_button,q13_button);
        
        HBox hBox3 = new HBox();
        hBox3.getChildren().addAll(q3_button,q14_button);
        
        HBox hBox4 = new HBox();
        hBox4.getChildren().addAll(q4_button,q15_button);
        
        HBox hBox5 = new HBox();
        hBox5.getChildren().addAll(q5_button,q16_button);
        
        HBox hBox6 = new HBox();
        hBox6.getChildren().addAll(q6_button,q17_button);
        
        HBox hBox7 = new HBox();
        hBox7.getChildren().addAll(q7_button,q18_button);
        
        HBox hBox8 = new HBox();
        hBox8.getChildren().addAll(q8_button,q19_button);
        
        HBox hBox9 = new HBox();
        hBox9.getChildren().addAll(q9_button,q20_button);
        
        HBox hBox10 = new HBox();
        hBox10.getChildren().addAll(q10_button,q21_button);
        
        HBox hBox11 = new HBox();
        hBox11.getChildren().addAll(q11_button,q22_button);
        
		VBox vbox = new VBox();
		vbox.setSpacing(10);
		vbox.getChildren().addAll(hBox1, hBox2, hBox3, hBox4, hBox5, hBox6, hBox7, hBox8, hBox9, hBox10,hBox11);
		
		pd_queries_borderPane.setLeft(vbox);

//		q1.setLayoutX(40);
//		q1.setLayoutY(100);
		ArrayList<String> columnNames1 = new ArrayList<>();
		columnNames1.add("Average");
		//q1.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> Controllers.executePredefined(query1, b, columnNames1));
		q1_button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//create a scrollpane and insert it in the center of the layout
			      ScrollPane scroll = new ScrollPane();
			      pd_queries_borderPane.setCenter(scroll);
			      
			    //the container will contain the tableview and is inserted into the scrollpane
			      VBox result_vbox = new VBox();
			      scroll.setContent(result_vbox);
			      
			      ResultSet result = helper.executeQuery(query1);
		          Platform.runLater(() -> helper.putResInTable("LISTING", result, result_vbox, columnNames1));
			}
		});

//		q2.setLayoutX(40);
//		q2.setLayoutY(120);
		ArrayList<String> columnNames2 = new ArrayList<>();
		columnNames2.add("Average");
		//q2.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> Controllers.executePredefined(query2, b, columnNames2));
		q2_button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//create a scrollpane and insert it in the center of the layout
			      ScrollPane scroll = new ScrollPane();
			      pd_queries_borderPane.setCenter(scroll);
			      
			    //the container will contain the tableview and is inserted into the scrollpane
			      VBox result_vbox = new VBox();
			      scroll.setContent(result_vbox);
			      
			      ResultSet result = helper.executeQuery(query2);
		          Platform.runLater(() -> helper.putResInTable("REVIEWS", result, result_vbox, columnNames2));
			}
		});

//		q3.setLayoutX(40);
//		q3.setLayoutY(140);
//		ArrayList<String> columnNames3 = new ArrayList<>();
//		columnNames3.add("host_id");
//		columnNames3.add("host_url");
//		columnNames3.add("host_name");
//		columnNames3.add("host_since");
//		columnNames3.add("host_thumbnail_url");
		//q3.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> Controllers.executePredefined(query3, b, columnNames3));
		ArrayList<String> columnNames3 = new ArrayList<>();
		columnNames3.add("host_id");
		q3_button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//create a scrollpane and insert it in the center of the layout
			      ScrollPane scroll = new ScrollPane();
			      pd_queries_borderPane.setCenter(scroll);
			      
			    //the container will contain the tableview and is inserted into the scrollpane
			      VBox result_vbox = new VBox();
			      scroll.setContent(result_vbox);
			      
			      ResultSet result = helper.executeQuery(query3);
			      System.out.println(result);
		          Platform.runLater(() -> helper.putResInTable("HOST", result, result_vbox,columnNames3));
			}
		});


//		q4.setLayoutX(40);
//		q4.setLayoutY(160);
		ArrayList<String> columnNames4 = new ArrayList<>();
		columnNames4.add("Number of listing");
		//q4.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> Controllers.executePredefined(query4, b, columnNames4));
		q4_button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//create a scrollpane and insert it in the center of the layout
			      ScrollPane scroll = new ScrollPane();
			      pd_queries_borderPane.setCenter(scroll);
			      
			    //the container will contain the tableview and is inserted into the scrollpane
			      VBox result_vbox = new VBox();
			      scroll.setContent(result_vbox);
			      
			      ResultSet result = helper.executeQuery(query4);
		          Platform.runLater(() -> helper.putResInTable("HOST", result, result_vbox, columnNames4));
			}
		});


//		q5.setLayoutX(40);
//		q5.setLayoutY(180);
		ArrayList<String> columnNames5 = new ArrayList<>();
		columnNames5.add("calendar_date");
		//q5.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> Controllers.executePredefined(query5, b, columnNames5));
		q5_button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//create a scrollpane and insert it in the center of the layout
			      ScrollPane scroll = new ScrollPane();
			      pd_queries_borderPane.setCenter(scroll);
			      
			    //the container will contain the tableview and is inserted into the scrollpane
			      VBox result_vbox = new VBox();
			      scroll.setContent(result_vbox);
			      
			      ResultSet result = helper.executeQuery(query5);
		          Platform.runLater(() -> helper.putResInTable("HOST", result, result_vbox, columnNames5));
			}
		});

//		q6.setLayoutX(40);
//		q6.setLayoutY(200);
		ArrayList<String> columnNames6 = new ArrayList<>();
		columnNames6.add("host_id");
		columnNames6.add("host_name");
		//q6.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> Controllers.executePredefined(query6, b, columnNames6));
		q6_button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//create a scrollpane and insert it in the center of the layout
			      ScrollPane scroll = new ScrollPane();
			      pd_queries_borderPane.setCenter(scroll);
			      
			    //the container will contain the tableview and is inserted into the scrollpane
			      VBox result_vbox = new VBox();
			      scroll.setContent(result_vbox);
			      
			      ResultSet result = helper.executeQuery(query6);
		          Platform.runLater(() -> helper.putResInTable("HOST", result, result_vbox, columnNames6));
			}
		});

//		q7.setLayoutX(40);
//		q7.setLayoutY(220);
		ArrayList<String> columnNames7 = new ArrayList<>();
		columnNames7.add("average price difference");
		//q7.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> Controllers.executePredefined(query7, b, columnNames7));
		q7_button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//create a scrollpane and insert it in the center of the layout
			      ScrollPane scroll = new ScrollPane();
			      pd_queries_borderPane.setCenter(scroll);
			      
			    //the container will contain the tableview and is inserted into the scrollpane
			      VBox result_vbox = new VBox();
			      scroll.setContent(result_vbox);
			      
			      System.out.println();
			      ResultSet result = helper.executeQuery(query7_3);
			      ResultSet result1 = helper.executeQuery(query7_1 );
			      ResultSet result2 = helper.executeQuery(query7_2 );
			      
		          Platform.runLater(() -> helper.putResInTable("HOST", result2, result_vbox, columnNames7));
			}
		});

//		q8.setLayoutX(40);
//		q8.setLayoutY(240);
		ArrayList<String> columnNames8 = new ArrayList<>();
		columnNames8.add("price difference");
		//q8.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> Controllers.executePredefined(query8, b, columnNames8));
		q8_button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//create a scrollpane and insert it in the center of the layout
			      ScrollPane scroll = new ScrollPane();
			      pd_queries_borderPane.setCenter(scroll);
			      
			    //the container will contain the tableview and is inserted into the scrollpane
			      VBox result_vbox = new VBox();
			      scroll.setContent(result_vbox);
			      
			      ResultSet result = helper.executeQuery(query8);
		          Platform.runLater(() -> helper.putResInTable("HOST", result, result_vbox, columnNames8));
			}
		});

//		q9.setLayoutX(40);
//		q9.setLayoutY(260);
		ArrayList<String> columnNames9 = new ArrayList<>();
		columnNames9.add("host_id");
		columnNames9.add("host_name");
		//q9.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> Controllers.executePredefined(query9, b, columnNames9));
		q9_button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//create a scrollpane and insert it in the center of the layout
			      ScrollPane scroll = new ScrollPane();
			      pd_queries_borderPane.setCenter(scroll);
			      
			    //the container will contain the tableview and is inserted into the scrollpane
			      VBox result_vbox = new VBox();
			      scroll.setContent(result_vbox);
			      
			      ResultSet result = helper.executeQuery(query9);
		          Platform.runLater(() -> helper.putResInTable("HOST", result, result_vbox, columnNames9));
			}
		});

//		q10.setLayoutX(40);
//		q10.setLayoutY(280);
		ArrayList<String> columnNames10 = new ArrayList<>();
		columnNames10.add("listing_id");
		columnNames10.add("listing_name");
		//q10.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> Controllers.executePredefined(query10, b, columnNames10));
		q10_button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//create a scrollpane and insert it in the center of the layout
			      ScrollPane scroll = new ScrollPane();
			      pd_queries_borderPane.setCenter(scroll);
			      
			    //the container will contain the tableview and is inserted into the scrollpane
			      VBox result_vbox = new VBox();
			      scroll.setContent(result_vbox);
			      
			      ResultSet result = helper.executeQuery(query10);
		          Platform.runLater(() -> helper.putResInTable("HOST", result, result_vbox, columnNames10));
			}
		});
		
		ArrayList<String> columnNames11 = new ArrayList<>();
		columnNames11.add("number_of_host");
		
		q11_button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//create a scrollpane and insert it in the center of the layout
			      ScrollPane scroll = new ScrollPane();
			      pd_queries_borderPane.setCenter(scroll);
			      
			    //the container will contain the tableview and is inserted into the scrollpane
			      VBox result_vbox = new VBox();
			      scroll.setContent(result_vbox);
			      
			      ResultSet result = helper.executeQuery(query11);
		          Platform.runLater(() -> helper.putResInTable("HOST", result, result_vbox, columnNames11));
			}
		});
		
		ArrayList<String> columnNames12 = new ArrayList<>();
		columnNames12.add("NIGHOURHOOD_NAME");
		columnNames12.add("REVIEW_SCORES_RATING");
		
		q12_button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//create a scrollpane and insert it in the center of the layout
			      ScrollPane scroll = new ScrollPane();
			      pd_queries_borderPane.setCenter(scroll);
			      
			    //the container will contain the tableview and is inserted into the scrollpane
			      VBox result_vbox = new VBox();
			      scroll.setContent(result_vbox);
			      
			      ResultSet result = helper.executeQuery(query12);
		          Platform.runLater(() -> helper.putResInTable("HOST", result, result_vbox, columnNames12));
			}
		});
		
		
		ArrayList<String> columnNames13 = new ArrayList<>();
		columnNames13.add("host_id");
		columnNames13.add("host_name");
		
		q13_button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//create a scrollpane and insert it in the center of the layout
			      ScrollPane scroll = new ScrollPane();
			      pd_queries_borderPane.setCenter(scroll);
			      
			    //the container will contain the tableview and is inserted into the scrollpane
			      VBox result_vbox = new VBox();
			      scroll.setContent(result_vbox);
			      
			      ResultSet result = helper.executeQuery(query13);
		          Platform.runLater(() -> helper.putResInTable("HOST", result, result_vbox, columnNames13));
			}
		});
		
		ArrayList<String> columnNames14 = new ArrayList<>();
		columnNames14.add("Average Price");
		columnNames14.add("listing_id");
		
		q14_button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//create a scrollpane and insert it in the center of the layout
			      ScrollPane scroll = new ScrollPane();
			      pd_queries_borderPane.setCenter(scroll);
			      
			    //the container will contain the tableview and is inserted into the scrollpane
			      VBox result_vbox = new VBox();
			      scroll.setContent(result_vbox);
			      
			      ResultSet result = helper.executeQuery(query14);
		          Platform.runLater(() -> helper.putResInTable("HOST", result, result_vbox, columnNames14));
			}
		});
		
		ArrayList<String> columnNames15 = new ArrayList<>();
		columnNames15.add("listing_id");
		columnNames15.add("accommodates");
		columnNames15.add("review");
		
		q15_button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//create a scrollpane and insert it in the center of the layout
			      ScrollPane scroll = new ScrollPane();
			      pd_queries_borderPane.setCenter(scroll);
			      
			    //the container will contain the tableview and is inserted into the scrollpane
			      VBox result_vbox = new VBox();
			      scroll.setContent(result_vbox);
			      
			      ResultSet result = helper.executeQuery(query15);
		          Platform.runLater(() -> helper.putResInTable("HOST", result, result_vbox, columnNames15));
			}
		});
		
		
		ArrayList<String> columnNames16 = new ArrayList<>();
		columnNames16.add("host_id");
		columnNames16.add("listing_id");
		
		q16_button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//create a scrollpane and insert it in the center of the layout
			      ScrollPane scroll = new ScrollPane();
			      pd_queries_borderPane.setCenter(scroll);
			      
			    //the container will contain the tableview and is inserted into the scrollpane
			      VBox result_vbox = new VBox();
			      scroll.setContent(result_vbox);
			      
			      ResultSet result = helper.executeQuery(query16);
		          Platform.runLater(() -> helper.putResInTable("HOST", result, result_vbox, columnNames16));
			}
		});
		
		ArrayList<String> columnNames17 = new ArrayList<>();
		columnNames17.add("AMENITIES_TYPE");
		columnNames17.add("NEIHOURHOOD_NAME");
		
		q17_button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//create a scrollpane and insert it in the center of the layout
			      ScrollPane scroll = new ScrollPane();
			      pd_queries_borderPane.setCenter(scroll);
			      
			    //the container will contain the tableview and is inserted into the scrollpane
			      VBox result_vbox = new VBox();
			      scroll.setContent(result_vbox);
			      
			      ResultSet result = helper.executeQuery(query17);
		          Platform.runLater(() -> helper.putResInTable("HOST", result, result_vbox, columnNames17));
			}
		}); 
		
		ArrayList<String> columnNames18 = new ArrayList<>();
		columnNames18.add("GAP");
		
		
		q18_button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//create a scrollpane and insert it in the center of the layout
			      ScrollPane scroll = new ScrollPane();
			      pd_queries_borderPane.setCenter(scroll);
			      
			    //the container will contain the tableview and is inserted into the scrollpane
			      VBox result_vbox = new VBox();
			      scroll.setContent(result_vbox);
			      
			      ResultSet result = helper.executeQuery(query18_3);
			      ResultSet result1 = helper.executeQuery(query18_1 );
			      ResultSet result2 = helper.executeQuery(query18_2 );
			      
		          Platform.runLater(() -> helper.putResInTable("HOST", result2, result_vbox, columnNames18));
			}
		}); 
		
		ArrayList<String> columnNames19 = new ArrayList<>();
		columnNames19.add("ROME_TYPE");
		columnNames19.add("CITY_NAME");
		
		q19_button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//create a scrollpane and insert it in the center of the layout
			      ScrollPane scroll = new ScrollPane();
			      pd_queries_borderPane.setCenter(scroll);
			      
			    //the container will contain the tableview and is inserted into the scrollpane
			      VBox result_vbox = new VBox();
			      scroll.setContent(result_vbox);
			      
			      ResultSet result = helper.executeQuery(query19);
		          Platform.runLater(() -> helper.putResInTable("HOST", result, result_vbox, columnNames19));
			}
		}); 
		
		ArrayList<String> columnNames20 = new ArrayList<>();
		
		columnNames20.add("NEIGHBOURHOOD_NAME");
		
		q20_button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//create a scrollpane and insert it in the center of the layout
			      ScrollPane scroll = new ScrollPane();
			      pd_queries_borderPane.setCenter(scroll);
			      
			    //the container will contain the tableview and is inserted into the scrollpane
			      VBox result_vbox = new VBox();
			      scroll.setContent(result_vbox);
			      
			      ResultSet result = helper.executeQuery(query20);
		          Platform.runLater(() -> helper.putResInTable("HOST", result, result_vbox, columnNames20));
			}
		}); 
		
		ArrayList<String> columnNames21 = new ArrayList<>();
		
		columnNames21.add("COUNTRY_NAME");
		
		q21_button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//create a scrollpane and insert it in the center of the layout
			      ScrollPane scroll = new ScrollPane();
			      pd_queries_borderPane.setCenter(scroll);
			      
			    //the container will contain the tableview and is inserted into the scrollpane
			      VBox result_vbox = new VBox();
			      scroll.setContent(result_vbox);
			      
			      ResultSet result = helper.executeQuery(query21);
		          Platform.runLater(() -> helper.putResInTable("HOST", result, result_vbox, columnNames21));
			}
		}); 
		ArrayList<String> columnNames22 = new ArrayList<>();
		
		columnNames22.add("NEIGHBOURHOOD_NAME");
		
		q22_button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//create a scrollpane and insert it in the center of the layout
			      ScrollPane scroll = new ScrollPane();
			      pd_queries_borderPane.setCenter(scroll);
			      
			    //the container will contain the tableview and is inserted into the scrollpane
			      VBox result_vbox = new VBox();
			      scroll.setContent(result_vbox);
			      
			      ResultSet result = helper.executeQuery(query22);
		          Platform.runLater(() -> helper.putResInTable("HOST", result, result_vbox, columnNames22));
			}
		}); 
		
		
		


		return pd_queries_borderPane;
		
		

	}

}
