package todo.validator;
import db.Entity;
import db.Validator;
import dbexeption.InvalidEntityException;
import todo.entity.Task;


public class TaskValidator implements Validator {

    @Override
    public void validate(Entity entity) throws InvalidEntityException {
        if(!(entity instanceof Task)){
            throw new IllegalArgumentException("Entity must be task");
        }
        if(((Task) entity).getTitle().isEmpty()){
            throw new IllegalArgumentException("Title must not be empty");
        }
    }


}
