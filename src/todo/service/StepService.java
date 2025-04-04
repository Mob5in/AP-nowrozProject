package todo.service;

import db.Database;
import dbexeption.InvalidEntityException;
import todo.entity.Step;

public class StepService {

    public static void saveStep(Step step) throws InvalidEntityException {
        Database.add(step);
    }

    public static void setAsComplete(){

    }

}
