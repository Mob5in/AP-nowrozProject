package todo.entity;

import db.Entity;

public class Step extends Entity {

    @Override
    public int getEntityCode() {
        return 0;
    }

    enum Status{
        NotStarted,
        Complete
    }

    private String title;
    private Status status;
    private int taskRef;


}
