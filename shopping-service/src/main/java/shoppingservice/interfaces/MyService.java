package shoppingservice.interfaces;

import java.util.List;

public interface MyService {
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
     delete entity from database
     */
    void delete(Long id);

    /**
     delete everything in repository
     */
    void empty();
}
