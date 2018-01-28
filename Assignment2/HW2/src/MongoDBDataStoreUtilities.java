import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

public class MongoDBDataStoreUtilities
{
	static DBCollection myReviews;
	
	public static void getConnection()
	{
		MongoClient mongo;
		mongo = new MongoClient("localhost", 27017);
		
		DB db= mongo.getDB("CustomerReviews");
		myReviews= db.getCollection("myReviews");
	}
	
	public static void insertReview(String ProductName, String productType_1, double price, String retailer, String retailerZip, String retailerCity_1,
										String retailerState, String productOnSale_1, String manufacturer, String manufacturerRebate_1,
										String emailId_1, int userAge, String userGender, String userOccupation, int reviewRating,
										String reviewDate, String reviewText)
	{
		MongoClient mongo;
		mongo = new MongoClient("localhost", 27017);
		
		DB db= mongo.getDB("CustomerReviews");
		myReviews= db.getCollection("myReviews");
		
		BasicDBObject document = new BasicDBObject();
		
		document.put("productName", ProductName);
		document.put("productType",productType_1 );
		document.put("productPrice", price);
		document.put("retailer", retailer);
		document.put("retailerZip", retailerZip);
		document.put("retailerCity", retailerCity_1);
		document.put("retailerState", retailerState);
		document.put("productOnSale", productOnSale_1);
		document.put("manufacturer", manufacturer);
		document.put("manufacturerRebate", manufacturerRebate_1);
		document.put("emailId", emailId_1);
		document.put("userAge", userAge);
		document.put("userGender", userGender);
		document.put("userOccupation", userOccupation);
		document.put("reviewRating", reviewRating);
		document.put("reviewDate", reviewDate);
		document.put("reviewText", reviewText);
		

		myReviews.insert(document);
	}
	
	
	

}