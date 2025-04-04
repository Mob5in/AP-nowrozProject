package todo.entity;

import db.Entity;

public class Step extends Entity {


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

}
