package ca.mcgill.ecse321.tutoringapp.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringapp.model.TeachingInstitution;

/**
 * 
 * @author Alba
 *
 */
public interface TeachingInstitutionRepository extends CrudRepository<TeachingInstitution, String> {
	
	TeachingInstitution findTeachingInstitutionByName(String name);

	boolean existsByName(String name);

}
