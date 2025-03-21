package db;
import dbexeption.EntityNotFoundException;
import java.util.ArrayList;


public class Database {


    private static ArrayList<Entity> entities = new ArrayList<>();

    static void add(Entity e){
        entities.add(e);
    }


    static Entity get(int id) throws EntityNotFoundException {
        for(Entity entity: entities){
            if(entity.id == id){
                return entity;
            }
        }
        throw new EntityNotFoundException();
    }


    static void delete(int id) throws EntityNotFoundException{
        for(Entity entity: entities){
            if(entity.id == id){
                entities.remove(entity);
                return;
            }
        }
        throw new EntityNotFoundException();
    }


    static void update(Entity e)throws EntityNotFoundException{
        int i = 0;
        for(Entity entity: entities){
            if(entity.id == e.id){
                entities.set(i, e);
                return;
            }
            i++;
        }
        throw new EntityNotFoundException();
    }


}
