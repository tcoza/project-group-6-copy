package ca.mcgill.ecse321.tutoringapp.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import ca.mcgill.ecse321.tutoringapp.model.SmallRoom;

/**
 * 
 * @author Odero
 *
 */
@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "smallrooms", path = "smallrooms")
public interface SmallRoomRepository extends CrudRepository<SmallRoom, Integer> {
	SmallRoom findSmallRoomByStudentCapacity(int studentCapacity);

}
