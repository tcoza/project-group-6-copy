package ca.mcgill.ecse321.tutoringapp.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ca.mcgill.ecse321.tutoringapp.model.SmallRoom;

/**
 * 
 * @author Odero
 *
 */
@RepositoryRestResource(collectionResourceRel = "smallrooms", path = "smallrooms")
public interface SmallRoomRepository extends CrudRepository<SmallRoom, Integer> {
	SmallRoom findSmallRoomByStudentCapacity(int studentCapacity);

}
