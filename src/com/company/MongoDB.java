package com.company;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import org.bson.BsonDocument;
import org.bson.BsonInt64;
import org.bson.Document;
import org.bson.conversions.Bson;

public class MongoDB {

    private static MongoClient mongoClient;

    public static void connectToDatabase() {

        String url = "mongodb://localhost:27017";

        mongoClient = MongoClients.create(url);
        MongoDatabase mongoDatabase = mongoClient.getDatabase("telegram");

        try {
            Bson command = new BsonDocument("ping", new BsonInt64(1));
            Document commandResult = mongoDatabase.runCommand(command);
            System.out.println("Connected successfully tp server");
        } catch (MongoException mongoException) {
            mongoException.printStackTrace();
        }
    }

    public static void insertNewUserID(String userID) {

        MongoDatabase mongoDatabase = mongoClient.getDatabase("telegram");
        MongoCollection<Document> customerCollection = mongoDatabase.getCollection("customers");

        try {

            InsertOneResult result = customerCollection.insertOne(new Document().append("_id", userID));
            System.out.println(result.getInsertedId());
        } catch (MongoException mongoException) {
            mongoException.printStackTrace();
        }

    }

}
