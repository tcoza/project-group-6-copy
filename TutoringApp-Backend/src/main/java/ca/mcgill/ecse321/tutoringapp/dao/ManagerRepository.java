package ca.mcgill.ecse321.tutoringapp.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ca.mcgill.ecse321.tutoringapp.model.Manager;

/**
 * 
 * @author Traian
 *
 */
@RepositoryRestResource(collectionResourceRel = "managers", path = "managers")
public interface ManagerRepository extends CrudRepository<Manager, String> {

	boolean existsByUsername(String username);
	Manager findManagerByUsername(String username);
}
