package ca.mcgill.ecse321.tutoringapp.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import ca.mcgill.ecse321.tutoringapp.model.Manager;

/**
 * This interface is the CRUD repository for all Managers in the Tutoring
 * System. It is mapped as a RepositoryRestResource that can be viewed with GET
 * requests at /managers
 * 
 * @author Traian
 */
@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "managers", path = "managers")
public interface ManagerRepository extends CrudRepository<Manager, String> {

	boolean existsByUsername(String username);
	Manager findManagerByUsername(String username);
}
