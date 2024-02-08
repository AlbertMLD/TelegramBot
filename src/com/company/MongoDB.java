package com.company;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.BsonInt64;
import org.bson.Document;
import org.bson.conversions.Bson;

public class MongoDB {

    public static void connectToDatabase() {

        String url = "mongodb://localhost:27017"

        try(MongoClient mongoClient= MongoClient.create(url)) {
            mongoClient = mongoClient;
            MongoDatabase mongoDatabase= mongoClient.getDatabase("admin");

            try {
                Bson command = new BsonDocument("ping",new BsonInt64(1));
                Document commandResult = mongoDatabase.runCommand(command);
                System.out.println("Connected successfully tp server");
            }catch (MongoException mongoException) {
                mongoException.printStackTrace();
            }
        }
    }
}
