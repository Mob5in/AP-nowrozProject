package db;
import dbexeption.EntityNotFoundException;
import dbexeption.InvalidEntityException;
import todo.entity.Step;
import todo.entity.Task;
import todo.service.StepService;
import todo.service.TaskService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Functions {


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


    // 2
    public static void addStep() throws InvalidEntityException {
        Scanner scn = new Scanner(System.in);
        String title;
        int taskRef;

        System.out.println("title:");
        title = scn.nextLine();
        System.out.println("Task ID:");
        taskRef = scn.nextInt();

        Step newStep = new Step(title, taskRef);
        int id = Database.add(newStep);
        System.out.println("Step saved successfully");
        System.out.println("ID: "+id);
        System.out.println("Creation Date: "+((Step)Database.get(id)).getCreationDate());
    }

    // 3
    public static void delete(){
        Scanner scn = new Scanner(System.in);

        System.out.println("Enter the ID you want to delete");
        int ID = scn.nextInt();
        try {
            Database.delete(ID);
        }catch(EntityNotFoundException e){
            System.out.println("The ID do not exist");
            return;
        }
        System.out.println("Deleted successfully");
    }

    // 4
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

    // 5
    public static void updateStep() throws InvalidEntityException {

        Scanner scn = new Scanner(System.in);

        System.out.println("ID: ");
        int ID = scn.nextInt();

        System.out.println("Field: ");
        String purField = scn.nextLine();

        System.out.println("New Value: ");
        String newValue = scn.nextLine();


        Step step = (Step) Database.get(ID);
        if(purField.equalsIgnoreCase("title")){
            step.setTitle(newValue);
        } else if (purField.equalsIgnoreCase("task ID")) {
            step.setTaskRef(Integer.parseInt(newValue));
        }else if (purField.equalsIgnoreCase("status")){
            if(newValue.equalsIgnoreCase("Completed")){
                StepService.setAsCompleted(ID);
            } else{
                System.out.println("wrong status");
            }
        }

        Database.update(step);

    }


    //6
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



    //7
    public static void getAllTasks() {
        ArrayList<Entity> tasks = Database.getAll(Task.TASK_ENTITY_CODE);
        int numberOfTasks = 1;
        for(Entity entity: tasks){
            Task task = (Task) entity;
            System.out.println(numberOfTasks +"_title: " + task.getTitle()+ "-->ID: " + task.id+"(Status:"+task.getStatus()+")");
            numberOfTasks ++;
        }
    }

    //8
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

}
