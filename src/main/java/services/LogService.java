package services;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.io.Closeable;
import java.io.IOException;
import java.util.Map;

public class LogService implements Closeable {
    private final MongoClient client;
    private final MongoCollection<Document> logCollection;
    public LogService(){
        client  = MongoClients.create();
        var database = client.getDatabase("syn");
        logCollection = database.getCollection("logs");
    }

    public void add(Map<String, Object> data) {
        logCollection.insertOne(new Document(data));
    }

    @Override
    public void close() throws IOException {
        client.close();
    }
}
