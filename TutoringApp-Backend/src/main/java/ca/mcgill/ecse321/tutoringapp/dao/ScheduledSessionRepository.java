package ca.mcgill.ecse321.tutoringapp.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringapp.model.ScheduledSession;

/**
 * 
 * @author Arianit
 *
 */
public interface ScheduledSessionRepository extends CrudRepository<ScheduledSession, Integer> {

}
