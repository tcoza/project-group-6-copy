package ca.mcgill.ecse321.tutoringapp.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ca.mcgill.ecse321.tutoringapp.model.TeachingInstitution;

/**
 * 
 * @author Alba
 *
 */
@RepositoryRestResource(collectionResourceRel = "teachinginstitutions", path = "teachinginstitutions")
public interface TeachingInstitutionRepository extends CrudRepository<TeachingInstitution, String> {
	
	TeachingInstitution findTeachingInstitutionByName(String name);

	boolean existsByName(String name);

}
