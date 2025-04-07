import db.*;
import dbexeption.*;
import todo.entity.*;
import todo.validator.*;
import java.util.Scanner;

import static db.Functions.*;
import static todo.service.StepService.*;
import static todo.service.TaskService.*;


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

                case "delete":
                case "3":
                    delete();
                    break;

                case "update":
                case "4":
                    update();
                    break;

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
}