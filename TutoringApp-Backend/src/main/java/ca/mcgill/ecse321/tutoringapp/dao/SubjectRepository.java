package ca.mcgill.ecse321.tutoringapp.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import ca.mcgill.ecse321.tutoringapp.model.Subject;

/**
 * 
 * @author Alba
 *
 */
@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "subjects", path = "subjects")
public interface SubjectRepository extends CrudRepository<Subject, String> {
	
	Subject findSubjectByName(String name);

}
