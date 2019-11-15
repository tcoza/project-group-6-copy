package ca.mcgill.ecse321.tutoringapp.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.tutoringapp.model.AppUser;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * 
 * @author Traian
 *
 */
@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "appusers", path = "appusers")
public interface AppUserRepository extends CrudRepository<AppUser,String> {
	boolean existsByUsername(String username);
	AppUser findAppUserByUsername(String username);
}
