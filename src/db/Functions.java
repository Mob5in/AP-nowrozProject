package db;
import dbexeption.EntityNotFoundException;
import dbexeption.InvalidEntityException;
import todo.entity.Step;
import todo.entity.Task;
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
        title = scn.next();
        System.out.println("description:");
        descriprion = scn.next();
        System.out.println("Date:");
        dateStr = scn.next();

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
        title = scn.next();
        System.out.println("Task ID:");
        taskRef = scn.nextInt();

        Step newStep = new Step(title, taskRef);
        int id = Database.add(newStep);
        System.out.println("Step saved successfully");
        System.out.println("ID: "+id);
        System.out.println("Creation Date: "+((Step)Database.get(id)).getCreationDate());
    }

    //3
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

    public static void update() throws InvalidEntityException {

        Scanner scn = new Scanner(System.in);

        System.out.println("ID: ");
        int ID = scn.nextInt();

        System.out.println("Field: ");
        String purField = scn.next();

        System.out.println("New Value: ");
        String newValue = scn.next();

        Task task = (Task) Database.get(ID);
        if(purField.equalsIgnoreCase("title")){
            task.setTitle(newValue);
        } else if (purField.equalsIgnoreCase("description")) {
            task.setDescription(newValue);
        }else if (purField.equalsIgnoreCase("status")){
            if(newValue.equalsIgnoreCase("Completed")){
                task.status = Task.Status.Completed;
            } else if (newValue.equalsIgnoreCase("InProgress")) {
                task.status = Task.Status.InProgress;
            }
        }
        Database.update(task);


    }


    public static void updateStep() {
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
        int numberOfTasks = 1;
        for(Entity entity: tasks){
            Task task = (Task) entity;
            System.out.println(numberOfTasks +"_title: " + task.getTitle()+ "-->ID: " + task.id+"(Status:"+task.getStatus()+")");
            numberOfTasks ++;
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

}
