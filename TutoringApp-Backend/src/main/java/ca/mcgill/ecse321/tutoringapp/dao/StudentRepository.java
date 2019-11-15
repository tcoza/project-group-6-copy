package ca.mcgill.ecse321.tutoringapp.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import ca.mcgill.ecse321.tutoringapp.model.Student;

/**
 * 
 * @author Traian
 *
 */
@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "students", path = "students")
public interface StudentRepository extends CrudRepository<Student, String> {

	boolean existsByUsername(String username);
	Student findByUsername(String username);


}
