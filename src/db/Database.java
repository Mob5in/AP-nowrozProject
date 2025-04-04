package db;
import dbexeption.*;
import example.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class Database {

    private static HashMap<Integer, Validator> validators = new HashMap<>();
    private static ArrayList<Entity> entities = new ArrayList<>();

    public static void add(Entity e) throws InvalidEntityException {

//        zero entity code means it has no validator like document
        if(e.getEntityCode()!=0){
            Validator validator = validators.get(e.getEntityCode());
            validator.validate(e);
        }

        e.id = entities.size() + 1;
        entities.add(e.clone());
    }


    public void getValidators(){

        System.out.println("Validators code:");

        for (int i : validators.keySet()) {
            System.out.println(i);
        }
    }


    public static Entity get(int id) throws EntityNotFoundException {
        for(Entity entity: entities){
            if(entity.id == id){
                return entity.clone();
            }
        }
        throw new EntityNotFoundException(id);
    }


    public static void delete(int id) throws EntityNotFoundException{
        for(Entity entity: entities){
            if(entity.id == id){
                entities.remove(entity);
                return;
            }
        }
        throw new EntityNotFoundException(id);
    }


    public static void update(Entity e) throws EntityNotFoundException, InvalidEntityException {
        int i = 0;

        if(e.getEntityCode()!=0){
            Validator validator = validators.get(e.getEntityCode());
            validator.validate(e);
        }

        for(Entity entity: entities){
            if(entity.id == e.id){
                if(Trackable.class.isAssignableFrom(e.getClass())){
                    ((Trackable) e).setLastModificationDate(new Date());
                }
                entities.set(i, e.clone());
                return;
            }
            i++;
        }
        throw new EntityNotFoundException();
    }

    public static void registerValidator(int entityCode, Validator validator) {

        if(validators.get(entityCode) != null){
            throw new IllegalArgumentException("the entity code already exist");
        }

        validators.put(entityCode, validator);
    }

}
