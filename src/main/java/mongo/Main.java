package mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        try(var mongoClient = MongoClients.create()) {

            var database = mongoClient.getDatabase("syn");

            var todoCollection = database.getCollection("todo");
            todoCollection.find().forEach((Consumer<Document>) System.out::println);
            todoCollection.updateOne(new Document("_id", new ObjectId("64111560dcc1d43dacbc5742")),
                    new Document(Map.of("$set", new Document("done", true),
                            "$currentDate", new Document("dateDone", true),
                            "$unset", new Document("dateCreated", true))));
            todoCollection.deleteOne(new Document("_id", new ObjectId("641116ccdcc3f82a2056a961")));
            todoCollection.find().forEach((Consumer<Document>) System.out::println);

           /* var todoDocument = new Document(Map.of(
                    "_id", new ObjectId(),
                    "task", "Drink some coffee",
                    "dateCreated", LocalDateTime.now(),
                    "done", false));
            todoCollection.insertOne(todoDocument);*/

        };
    }
}
