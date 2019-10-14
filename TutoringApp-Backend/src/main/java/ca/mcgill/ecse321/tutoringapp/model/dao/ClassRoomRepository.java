package ca.mcgill.ecse321.tutoringapp.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringapp.model.ClassRoom;

/**
 * 
 * @author Odero
 *
 */
public interface ClassRoomRepository extends CrudRepository<ClassRoom,Integer> {
	
	ClassRoom findClassRoomBystudentCapacity(int studentCapacity);

}
