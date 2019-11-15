package ca.mcgill.ecse321.tutoringapp.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ca.mcgill.ecse321.tutoringapp.model.ClassRoom;

/**
 * 
 * @author Odero
 *
 */
@RepositoryRestResource(collectionResourceRel = "classrooms", path = "classrooms")
public interface ClassRoomRepository extends CrudRepository<ClassRoom,Integer> {
	ClassRoom findClassRoomBystudentCapacity(int studentCapacity);
}
