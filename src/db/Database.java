package db;
import dbexeption.EntityNotFoundException;
import java.util.ArrayList;


public class Database {


    private static ArrayList<Entity> entities = new ArrayList<>();

    public static void add(Entity e){
        e.id = entities.size() + 1;
        entities.add(e.copy());
    }


    public static Entity get(int id) throws EntityNotFoundException {
        for(Entity entity: entities){
            if(entity.id == id){
                return entity.copy();
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
                entities.set(i, e.copy());
                return;
            }
            i++;
        }
        throw new EntityNotFoundException();
    }



}
