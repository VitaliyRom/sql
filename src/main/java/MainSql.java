import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import models.Contact;
import models.User;
import org.bson.Document;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainSql {
    public static void main(String[] args) {
        User user1 = new User();
        user1.setId(1);
        user1.setName("Petya");

        Contact cont = new Contact();
        cont.setUser(user1);
        cont.setContactName("Petya cont name");
        cont.setPhone("89131234567");
        cont.setEmail("89131234567@mail.ru");

        var meta = new HashMap<String, Object>();
        meta.put("traceId", "57890jgj0mv0i8");
        meta.put("userId", 1);
        meta.put("time", new Date().getTime());

        var mongoClient = MongoClients.create();
        var database = mongoClient.getDatabase("syn");
        var logCollection = database.getCollection("logs");


        var entityManagerFactory = Persistence.createEntityManagerFactory("some");
        EntityManager em = entityManagerFactory.createEntityManager();

        addContact(em, user1, cont, meta, logCollection);

        em.close();
        entityManagerFactory.close();
        mongoClient.close();
    }

    public static void addContact(EntityManager em, User user, Contact contact, Map<String, Object> meta, MongoCollection<Document> logCollection) {
        contact.setUser(user);

        em.getTransaction().begin();
        em.persist(contact);
        em.getTransaction().commit();

        meta.put("newContactId", contact.getId());
        meta.put("action", "add new contact");
        logCollection.insertOne(new Document(meta));
    }

}
