package ca.mcgill.ecse321.tutoringapp.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import ca.mcgill.ecse321.tutoringapp.model.Room;

/**
 * This interface is the CRUD repository for all Rooms (classrooms and
 * smallrooms) in the Tutoring System. It is mapped as a RepositoryRestResource
 * that can be viewed with GET requests at /rooms
 * 
 * @author Odero
 */
@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "rooms", path = "rooms")
public interface RoomRepository extends CrudRepository<Room, Integer> {
	Room findRoomById(int id);
}
