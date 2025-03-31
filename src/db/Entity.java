package db;

public abstract class Entity implements Cloneable{

    public int id = 0;
    public Entity(){
        id ++;
    }

    @Override
    public Entity clone() {
        try {
            return (Entity) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Clone not supported");
        }
    }
}
