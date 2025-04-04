package db;
import dbexeption.InvalidEntityException;
import todo.entity.Task;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Functions {

    public static void getAllTasks() {
        ArrayList<Entity> tasks = Database.getAll(Task.TASK_ENTITY_CODE);
        int numberOfTasks = 1;
        for(Entity entity: tasks){
            Task task = (Task) entity;
            System.out.println(numberOfTasks +"_title: " + task.getTitle()+ "-->ID: " + task.id);
            numberOfTasks ++;
        }
    }


    public static void getIncompleteTasks() {
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


    public static void updateStep() {
    }


    public static void addStep() {
    }


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
        Database.add(newTask);
    }


}
