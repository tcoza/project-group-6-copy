package ca.mcgill.ecse321.tutoringapp.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import ca.mcgill.ecse321.tutoringapp.model.Tutor;

/**
 * This interface is the CRUD repository for all Tutors in the Tutoring System.
 * It is mapped as a RepositoryRestResource that can be viewed with GET requests
 * at /tutors
 * 
 * @author Traian
 */
@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "tutors", path = "tutors")
public interface TutorRepository extends CrudRepository<Tutor, String> {

	boolean existsByUsername(String username);

	Tutor findTutorByUsername(String username);
}
