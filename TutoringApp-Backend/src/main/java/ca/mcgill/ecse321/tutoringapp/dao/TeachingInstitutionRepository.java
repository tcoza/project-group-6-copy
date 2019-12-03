package ca.mcgill.ecse321.tutoringapp.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import ca.mcgill.ecse321.tutoringapp.model.TeachingInstitution;

/**
 * This interface is the CRUD repository for all TeachingInstitutions in the
 * Tutoring System. It is mapped as a RepositoryRestResource that can be viewed
 * with GET requests at /teachinginstitutions
 * 
 * @author Alba
 */
@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "teachinginstitutions", path = "teachinginstitutions")
public interface TeachingInstitutionRepository extends CrudRepository<TeachingInstitution, String> {

	TeachingInstitution findTeachingInstitutionByName(String name);

	boolean existsByName(String name);

}
