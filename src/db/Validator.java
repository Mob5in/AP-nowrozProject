package db;

import dbexeption.InvalidEntityException;

public interface Validator {


    void validate(Entity entity) throws InvalidEntityException;


}
