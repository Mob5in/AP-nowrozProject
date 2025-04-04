package todo.service;

import db.Database;
import dbexeption.InvalidEntityException;
import todo.entity.Task;


public class TaskService {


    public static void setAsCompleted(int taskId) throws InvalidEntityException {
        Task task = (Task) Database.get(taskId);
        task.status = Task.Status.Completed;
        Database.update(task);
    }

    public static void setAsInProgress(int taskId) throws InvalidEntityException {
        Task task = (Task) Database.get(taskId);
        task.status = Task.Status.InProgress;
        Database.update(task);
    }

}
