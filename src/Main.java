import db.*;
import dbexeption.*;
import todo.entity.Step;
import todo.entity.Task;
import todo.validator.StepValidator;
import todo.validator.TaskValidator;

import javax.xml.transform.Source;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;



public class Main {
    public static void main(String[] args) throws InvalidEntityException {

        // Register validator
        Database.registerValidator(Task.TASK_ENTITY_CODE, new TaskValidator());
        Database.registerValidator(Step.STEP_ENTITY_CODE, new StepValidator());
        Scanner scn = new Scanner(System.in);
        System.out.println("Hello and Welcome to Our To-Do list");




        while (true){

            System.out.println("Command List \n1-add task\n2-add step\n3-delete\n4-update\n5-update step\n6-get task-by-id" +
                    "\n7-get all-tasks\n8-get incomplete-tasks\n9-exit\n" +
                    "please only enter the number or the Command");

            String command = scn.next();


            switch (command.toLowerCase()) {  // Convert to lowercase for case-insensitive matching
                case "add":
                case "1":
                    add();
                    break;

                case "add step":
                case "2":
                    addStep();
                    break;

//                case "delete":
//                case "3":
//                    delete();
//                    break;
//
//                case "update":
//                case "4":
//                    update();
//                    break;

                case "update step":
                case "5":
                    updateStep();
                    break;

                case "get task-by-id":
                case "6":
                    getTaskById();
                    break;

                case "get all-tasks":
                case "7":
                    getAllTasks();
                    break;

                case "get incomplete-tasks":
                case "8":
                    getIncompleteTasks();
                    break;

                case "exit":
                case "9":
                    System.out.println("Exiting program...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid command! Please try again.");
                    break;
            }
        }

    }

    private static void getIncompleteTasks() {
    }


    private static void getAllTasks() {
    }


    private static void getTaskById() {
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


    private static void updateStep() {
    }


    private static void addStep() {
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