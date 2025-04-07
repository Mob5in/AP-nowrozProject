package todo.service;

import db.Database;
import dbexeption.InvalidEntityException;
import todo.entity.Step;
import todo.entity.Task;

public class StepService {

    public static void saveStep(Step step) throws InvalidEntityException {
        Database.add(step);
    }

    public static void setAsCompleted(int stepId) throws InvalidEntityException {
        Step step = (Step) Database.get(stepId);
        step.status = Step.Status.Complete;
        Database.update(step);
    }


}
