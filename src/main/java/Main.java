import Controllers.MainController;
import models.Contact;
import models.User;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        var controller = new MainController();
        User user1 = new User();
        user1.setId(1);
        user1.setName("Petya");

        Contact cont = new Contact();
        cont.setUser(user1);
        cont.setContactName("Petunya cont name");
        cont.setPhone("11111111111");
        cont.setEmail("april@mail.ru");

        var meta = new HashMap<String, Object>();
        meta.put("traceId", "sonyaaaaaa");
        meta.put("userId", 1);
        meta.put("time", new Date().getTime());

        controller.addContact(user1, cont, meta);
        controller.close();
    }



}
