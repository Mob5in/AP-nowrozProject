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
    static final int STEP_ENTITY_CODE = 12;

    @Override
    public int getEntityCode() {
        return STEP_ENTITY_CODE;
    }

    public Step(String title, Task task){
        this.title = title;
        this.status = Status.NotStarted;
        this.taskRef = task.id;
    }
}
