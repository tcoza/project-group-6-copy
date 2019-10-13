package ca.mcgill.ecse321.tutoringapp.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringapp.model.Student;

/**
 * 
 * @author Traian
 *
 */
public interface StudentRepository extends CrudRepository<Student, String> {

	boolean existsByUsername(String username);

}
