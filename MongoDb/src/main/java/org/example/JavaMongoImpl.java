package org.example;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import java.util.Iterator;

public class JavaMongoImpl {
    public static void main(String[] args) {


// Creating a Mongo client
        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
        System.out.println("Created Mongo Connection successfully");
        MongoDatabase db = mongoClient.getDatabase("socialnetwork");
        System.out.println("Get database is successful");
        //creating collection or get collection if exists.
        MongoCollection<Document> collection= db.getCollection("users");
        System.out.println("collection created ");
//Inserting sample records by creating documents.
        Document doc =new Document("name","Yerdaulet");
        doc.append("id",101);
        doc.append("Subscribers",100);
        doc.append("Interests", "Programming");
        collection.insertOne(doc);
        System.out.println("Insert is completed");

        Document doc2 =new Document("name","NfACTORIAL");
        doc2.append("id",102);
        doc2.append("Subscribers",24000000);
        doc2.append("Interests", "ToAcceptYerdaulet");
        collection.insertOne(doc2);
        System.out.println("Insert is completed");
        FindIterable<Document> iterDoc = collection.find();
        int i = 1;
// Getting the iterator
        System.out.println("Listing All Mongo Documents");
        Iterator it = iterDoc.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
            i++;
        }
//specific document retrieving in a collection
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("name", "Yerdaulet");
        System.out.println("Retrieving specific Mongo Document");
        MongoCursor<Document> cursor = collection.find(searchQuery).iterator();
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
        collection.updateOne(Filters.eq("name", "Yerdaulet"), Updates.set("Interests", "ToGetInvited"));
        System.out.println("Document updated successfully...");
        int j = 1;
// Getting the iterator
        Iterator<Document> itrNew = iterDoc.iterator();
        System.out.println("Document after update...");
        while (itrNew.hasNext()) {
            System.out.println(itrNew.next());
            j++;
        }
        collection.deleteOne(Filters.eq("name", "Yerdaulet"));
        System.out.println("Document deleted successfully...");
        int k = 1;
// Getting the iterator
        Iterator<Document>  it3 = iterDoc.iterator();
        while (it3.hasNext()) {
            System.out.println(it3.next());
            k++;

        }
        collection.drop();
        System.out.println("Collection dropped successfully");

    }

}
