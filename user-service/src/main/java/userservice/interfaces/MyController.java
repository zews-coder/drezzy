package userservice.interfaces;

import org.springframework.http.ResponseEntity;

public interface MyController {
    /**
    get all entities
     */
    ResponseEntity<?> getAll();

    /**
    create new entity
     */
//    ResponseEntity<?> createOne(MyDto myDto);

    /**
    update existing entity
     */
//    ResponseEntity<?> updateOne(MyDto myDto);

    /**
    activate entity with provided id
     */
    ResponseEntity<?> activeOne(Long id);

    /**
    deactivate entity with provided id
     */
    ResponseEntity<?> deactivateOne(Long id);

    /**
    delete one entity with provided id
     */
    ResponseEntity<?> deleteOne(Long id);

    /**
    delete all entities
     */
    ResponseEntity<?> deleteAll();

    /**
    return message which function failed
     */
    ResponseEntity<?> badRequest(String message);
}
