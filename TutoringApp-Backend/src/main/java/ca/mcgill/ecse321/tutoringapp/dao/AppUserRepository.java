package ca.mcgill.ecse321.tutoringapp.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.tutoringapp.model.AppUser;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * 
 * @author Traian
 *
 */

@RepositoryRestResource(collectionResourceRel = "appusers", path = "appusers")
public interface AppUserRepository extends CrudRepository<AppUser,String> {
	boolean existsByUsername(String username);
	AppUser findAppUserByUsername(String username);

}
