package ca.mcgill.ecse321.tutoringapp.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringapp.model.Room;

/**
 * 
 * @author Odero
 *
 */
public interface RoomRepository extends CrudRepository<Room, Integer> {

	Room findRoomById(int id);

}
