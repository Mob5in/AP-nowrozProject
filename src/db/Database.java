package db;
import dbexeption.EntityNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;


public class Database {

    private static HashMap<Integer, Validator> validators = new HashMap<>();
    private static ArrayList<Entity> entities = new ArrayList<>();

    public static void add(Entity e){
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


    public static void update(Entity e)throws EntityNotFoundException{
        int i = 0;
        for(Entity entity: entities){
            if(entity.id == e.id){
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
