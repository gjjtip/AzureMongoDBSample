package GetStarted;

import java.util.HashMap;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

/**
 * Simple application that shows how to use Azure Cosmos DB with the MongoDB API and Java.
 *
 */
public class Program {
	
    public static void main(String[] args)
    {
	/*
	* Replace connection string from the Azure Cosmos Portal
        */
        MongoClientURI uri = new MongoClientURI("mongodb://jagongmongodb:zaVWQubr5zIORh6PNYGHbF3PYBi747p0hLiN66KZGjCDtgXjwRC7j7ruijEMook2Hj2ExEW8Kdj5siGnpqZ69g==@jagongmongodb.documents.azure.com:10255/?ssl=true&replicaSet=globaldb");
		
        MongoClient mongoClient = null;
        try {
            mongoClient = new MongoClient(uri);        
            
            // Get database
            MongoDatabase database = mongoClient.getDatabase("db");

            // Get collection
            MongoCollection<Document> collection = database.getCollection("coll");

            // Insert documents
            Document document1 = new Document("fruit", "apple");
            collection.insertOne(document1);
            
            Document document2 = new Document("fruit", "mango");
            collection.insertOne(document2);   
            
            HashMap<String, Object> map=new HashMap<String,Object>();
            map.put("fruit", "peach");
            map.put("color", "pink");
            Document document3 = new Document(map);
            collection.insertOne(document3);   
            
//            public List<GoodsDetail> getGoodsDetails2(int begin, int end) {  
//                Query query = new Query();  
//                query.addCriteria(Criteria.where("goodsSummary").not());  
//                return find(query.limit(end - begin).skip(begin),GoodsDetail.class);  
//            }  
            // Find fruits by name
            Document queryResult = collection.find(Filters.eq("fruit", "apple")).first();
            System.out.println(queryResult.toJson());    	
        	
            System.out.println( "Completed successfully" );  
            
        } finally {
        	if (mongoClient != null) {
        		mongoClient.close();
        	}
        }
    }
}
