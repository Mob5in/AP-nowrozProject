package todo.service;

import db.Database;
import dbexeption.EntityNotFoundException;
import dbexeption.InvalidEntityException;
import todo.entity.Step;
import todo.entity.Task;

import java.util.Scanner;

public class StepService {

    public static void saveStep(Step step) throws InvalidEntityException {
        Database.add(step);
    }

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


    public static void setAsCompleted(int stepId) throws InvalidEntityException {
        Step step = (Step) Database.get(stepId);
        step.status = Step.Status.Complete;
        Database.update(step);
    }


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
}
