package ca.mcgill.ecse321.tutoringapp.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringapp.model.SessionRequest;

/**
 * 
 * @author Helen
 *
 */
public interface SessionRequestRepository extends CrudRepository<SessionRequest, Integer> {

}
