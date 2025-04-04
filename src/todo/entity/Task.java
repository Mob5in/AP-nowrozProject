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

    static final int TASK_ENTITY_CODE = 4;
    private String title;
    private String description;
    private Date dueDate;
    private Status status;
    private Date creationDate;
    private Date lastModificationDate;

    public Task(String title, String description, Date dueDate){
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = Status.NotStarted;
        setCreationDate(new Date());
        setLastModificationDate(new Date());
    }

    @Override
    public int getEntityCode() {
        return TASK_ENTITY_CODE;
    }


    @Override
    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    @Override
    public Date getCreationDate() {
        return creationDate;
    }


    @Override
    public void setLastModificationDate(Date date) {
        this.lastModificationDate = date;
    }

    @Override
    public Date getLastModificationDate() {
        return lastModificationDate;
    }


}
