package todo.service;

import db.Database;
import db.Entity;
import dbexeption.InvalidEntityException;
import todo.entity.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


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

    // 1
    public static void add() throws InvalidEntityException {
        Scanner scn = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);

        String title;
        String descriprion;
        String dateStr;
        Date dueDate = null;


        System.out.println("title:");
        title = scn.nextLine();
        System.out.println("description:");
        descriprion = scn.nextLine();
        System.out.println("Date:");
        dateStr = scn.nextLine();

        try {
            dueDate = dateFormat.parse(dateStr);
        } catch (Exception e) {
            System.out.println("Please enter date correctly(YYYY-MM-DD) ");
            return;
        }

        Task newTask = new Task(title, descriprion, dueDate);
        int id = Database.add(newTask);
        System.out.println("Task saved successfully.");
        System.out.println("Task ID: "+id);
    }


    public static void update() throws InvalidEntityException {

        Scanner scn = new Scanner(System.in);

        System.out.println("ID: ");
        int ID = scn.nextInt();


        System.out.println("Field: ");
        scn.nextLine();
        String purField = scn.nextLine();

        System.out.println("New Value: ");
        String newValue = scn.nextLine();

        System.out.println(purField);
        Task task = (Task) Database.get(ID);
        if(purField.equalsIgnoreCase("title")){
            task.setTitle(newValue);
            System.out.println("title changing...");
        } else if (purField.equalsIgnoreCase("description")) {
            task.setDescription(newValue);
            System.out.println("description changing...");
        }else if (purField.equalsIgnoreCase("status")){
            if(newValue.equalsIgnoreCase("Completed")){
                task.status = Task.Status.Completed;
                System.out.println("status changing...");

                ArrayList<Entity> entities = Database.getAll(12);
                for (Entity entity : entities) {
                    if(((Step) entity).getTaskRef() == ID){
                        Step step = (Step) entity;
                        step.status = Step.Status.Complete;
                        Database.update(step);
                    }
                }


            } else if (newValue.equalsIgnoreCase("InProgress")) {
                task.status = Task.Status.InProgress;
                System.out.println("status changing...");
            }
        }
        try {
            Database.update(task);
            System.out.println("Updated successfully");
        }catch (Exception e){
            System.out.println("Something went wrong");
        }
    }


    public static void getTaskById() {
        Scanner scn = new Scanner(System.in);

        System.out.println("Enter the ID you want: ");
        int id = scn.nextInt();

        Task gettedTask = (Task) Database.get(id);
        System.out.println("Details: ");
        System.out.println("title: "+gettedTask.getTitle());
        System.out.println("description: "+gettedTask.getDescription());
        System.out.println("status: "+gettedTask.getStatus());
        System.out.println("due date: "+gettedTask.getDueDate());
    }


    public static void getAllTasks() {

        ArrayList<Entity> tasks = Database.getAll(Task.TASK_ENTITY_CODE);
        ArrayList<Entity> entities = Database.getAll(Step.STEP_ENTITY_CODE);
        int numberOfTasks = 1;

        for(Entity entity: tasks){
            Task task = (Task) entity;
            System.out.println(numberOfTasks +"_title: " + task.getTitle()+ "-->ID: " + task.id+"(Status:"+task.getStatus()+")");
            numberOfTasks ++;

            System.out.println("Steps:");
            int numberOfSteps=1;
            for (Entity entity1 : entities) {
                if(((Step) entity1).getTaskRef() == task.id){
                    Step step = (Step) entity1;
                    System.out.println("step " + numberOfSteps);
                    System.out.println("title: "+step.getTitle());
                    System.out.println("ID: "+step.id);
                    System.out.println("Status: "+step.getStatus());
                }
                numberOfSteps++;
            }
            if(numberOfSteps==1){
                System.out.println("No Steps");
            }
        }
    }


    public static void getIncompleteTasks() {
        ArrayList<Entity> tasks = Database.getAll(Task.TASK_ENTITY_CODE);
        int numberOfTasks = 1;
        for(Entity entity: tasks){
            Task task = (Task) entity;
            if(task.getStatus() != Task.Status.Completed){
                System.out.println(numberOfTasks +"_title: " + task.getTitle()+ "-->ID: " + task.id);
                numberOfTasks ++;
            }
        }
    }

// this method is similar to delete method in taskService and for not getting error we use them in one class
//    public static void delete(){
//        Scanner scn = new Scanner(System.in);
//
//        System.out.println("Enter the ID you want to delete");
//        int ID = scn.nextInt();
//        try {
//            Database.delete(ID);
//        }catch(EntityNotFoundException e){
//            System.out.println("The ID do not exist");
//            return;
//        }
//        System.out.println("Deleted successfully");
//    }

}
