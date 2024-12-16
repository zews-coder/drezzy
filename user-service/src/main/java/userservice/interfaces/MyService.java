package userservice.interfaces;

import java.util.List;

public interface MyService {
    /**
    find entity in table with provided email
     */
    MyEntity getByEmail(String email);

    /**
     * get all entities from table
     */
    List<MyEntity> get();

    /**
    add new entity into table
     */
    MyEntity add(MyDto myDto);

    /**
    update existing entity
     */
    MyEntity update(MyDto myDto);

    /**
    set entity's field "active" to be true
     */
    void activate(Long id);

    /**
    set entity's field "active" to be false
     */
    void deactivate(Long id);

    /**
    delete entity from database
     */
    void delete(Long id);

    /**
    delete everything in repository
     */
    void empty();
}
