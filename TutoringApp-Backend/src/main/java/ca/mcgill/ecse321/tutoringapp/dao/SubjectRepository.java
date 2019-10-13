package ca.mcgill.ecse321.tutoringapp.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringapp.model.Subject;

/**
 * 
 * @author Alba
 *
 */
public interface SubjectRepository extends CrudRepository<Subject, String> {
	
	Subject findSubjectByName(String name);

}
