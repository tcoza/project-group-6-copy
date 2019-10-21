package ca.mcgill.ecse321.tutoringapp.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ca.mcgill.ecse321.tutoringapp.model.Tutor;

/**
 * 
 * @author Traian
 *
 */
@RepositoryRestResource(collectionResourceRel = "tutors", path = "tutors")
public interface TutorRepository extends CrudRepository<Tutor, String> {

	boolean existsByUsername(String username);
	Tutor findTutorByUsername(String username);
}
