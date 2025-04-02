import db.*;
import dbexeption.*;
import example.*;

import java.util.Date;

public class Main {
    public static void main(String[] args) throws InvalidEntityException {
        Document doc = new Document("Eid Eid Eid");

        Database.add(doc);

        System.out.println("Document added");

        System.out.println("id: " + doc.id);
        System.out.println("content: " + doc.content);
        System.out.println("creation date: " + doc.getCreationDate());
        System.out.println("last modification date: " + doc.getLastModificationDate());
        System.out.println();

        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            System.out.println("Sleep interrupted!");
        }

        doc.content = "This is the new content";

        Database.update(doc);

        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            System.out.println("Sleep interrupted!");
        }
        doc.setLastModificationDate(new Date());
        Document check = (Document) Database.get(1);


        System.out.println("Document updated");
        System.out.println("id: " + doc.id);
        System.out.println("content: " + doc.content);
        System.out.println("creation date: " + check.getCreationDate());
        System.out.println("last modification date: " + check.getLastModificationDate());
    }
}