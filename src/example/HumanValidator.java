package example;
import db.Entity;
import db.Validator;
import dbexeption.InvalidEntityException;

public class HumanValidator implements Validator {


    @Override
    public void validate(Entity entity) throws InvalidEntityException {
        if(!(entity instanceof Human)){
            throw new IllegalArgumentException("The entity is not human");
        }

        if ((((Human) entity).name)==null){

            throw new InvalidEntityException("Name cant be null");

        } else if (((Human) entity).age <= 0 ) {

            throw new InvalidEntityException("Age cant be negative");

        }
    }
}