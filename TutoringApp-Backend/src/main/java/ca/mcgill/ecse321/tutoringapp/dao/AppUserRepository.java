package ca.mcgill.ecse321.tutoringapp.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringapp.model.AppUser;

/**
 * 
 * @author Traian
 *
 */
public interface AppUserRepository extends CrudRepository<AppUser,String> {
	

}
