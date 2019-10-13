package ca.mcgill.ecse321.tutoringapp.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringapp.model.ScheduledGroupSession;
import ca.mcgill.ecse321.tutoringapp.model.ScheduledPrivateSession;
import ca.mcgill.ecse321.tutoringapp.model.ScheduledSession;
import ca.mcgill.ecse321.tutoringapp.model.Tutor;

/**
 * 
 * @author Arianit
 *
 */
public interface ScheduledSessionRepository extends CrudRepository<ScheduledSession, Integer> {
	long count();
	
	ScheduledSession findById(int id);
	
	List<ScheduledSession> findAllById(List<Integer> ids);
	
	List<ScheduledSession> findByTutor (Tutor tutor);
	
	List<ScheduledSession> findAll();
	
	List<ScheduledGroupSession> findByIsFull(boolean isFull);
	
	List <ScheduledPrivateSession> findByIsFree (boolean isFree);

}
