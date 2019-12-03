package ca.mcgill.ecse321.tutoringapp.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import ca.mcgill.ecse321.tutoringapp.model.ClassRoom;

/**
 * This interface is the CRUD repository for all Classrooms in the Tutoring
 * System. It is mapped as a RepositoryRestResource that can be viewed with GET
 * requests at /classrooms
 * 
 * @author Odero
 */
@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "classrooms", path = "classrooms")
public interface ClassRoomRepository extends CrudRepository<ClassRoom,Integer> {
	ClassRoom findClassRoomBystudentCapacity(int studentCapacity);
}
