package todo.service;

import db.Database;
import todo.entity.Task;


public class TaskService {


    public static void setAsCompleted(int taskId) {
        Task task = (Task) Database.get(taskId);
        task.status = Task.Status.Completed;
    }

    public static void setAsInProgress(int taskId) {
        Task task = (Task) Database.get(taskId);
        task.status = Task.Status.InProgress;
    }

}
