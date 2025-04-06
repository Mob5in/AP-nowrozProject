package todo.entity;

import db.Entity;
import db.Trackable;

import java.util.Date;

public class Step extends Entity implements Trackable {


    enum Status{
        NotStarted,
        Complete
    }

    private String title;
    private Status status;
    private int taskRef;
    public static final int STEP_ENTITY_CODE = 12;

    @Override
    public int getEntityCode() {
        return STEP_ENTITY_CODE;
    }

    public Step(String title, int taskRef){
        this.title = title;
        this.status = Status.NotStarted;
        this.taskRef = taskRef;
    }

    public String getTitle(){
        return title;
    }

    public Status getStatus() {
        return status;
    }

    public int getTaskRef() {
        return taskRef;
    }


    private Date creationDate = new Date();
    public String content;
    private Date lastModifDate = new Date();


    @Override
    public void setCreationDate(Date date) {
        this.creationDate = date;
    }


    @Override
    public Date getCreationDate() {
        return this.creationDate;
    }


    @Override
    public void setLastModificationDate(Date date) {
        this.lastModifDate = date;
    }


    @Override
    public Date getLastModificationDate() {
        return lastModifDate;
    }
}
