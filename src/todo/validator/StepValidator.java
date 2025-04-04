package todo.validator;
import db.Database;
import db.Entity;
import db.Validator;
import dbexeption.EntityNotFoundException;
import dbexeption.InvalidEntityException;
import todo.entity.Step;
import todo.entity.Task;

public class StepValidator implements Validator {


    @Override
    public void validate(Entity entity) throws InvalidEntityException {

        if(!(entity instanceof Step)){
            throw new IllegalArgumentException("Entity must be Step");
        }

        if(((Step) entity).getTitle().isEmpty()){
            throw new IllegalArgumentException("Title must not be empty");
        }

        try {
            Database.get(((Step) entity).getTaskRef());
        }catch (EntityNotFoundException e){
            System.out.println("Cant add step to the dataBase, ID not found.");
        }
    }
}
