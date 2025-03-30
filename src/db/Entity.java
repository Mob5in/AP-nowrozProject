package db;

public abstract class Entity {

    public int id = 0;
    public Entity(){
        id ++;
    }

    public abstract Entity copy();

}
