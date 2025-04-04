package todo.entity;
import db.Entity;
import db.Trackable;
import java.util.Date;

public class Task extends Entity implements Trackable {



    enum Status {
        NotStarted,
        InProgress,
        Completed
    }

    private String title;
    private String description;
    private Date dueDate;
    private Status status;



    @Override
    public int getEntityCode() {
        return 0;
    }


    @Override
    public void setCreationDate(Date date) {

    }

    @Override
    public Date getCreationDate() {
        return null;
    }


    @Override
    public void setLastModificationDate(Date date) {

    }

    @Override
    public Date getLastModificationDate() {
        return null;
    }


}
