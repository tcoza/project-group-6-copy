package ca.mcgill.ecse321.tutoringapp.dao;

import java.sql.Time;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.tutoringapp.model.ScheduledSession;
import ca.mcgill.ecse321.tutoringapp.model.Tutor;

/**
 * 
 * @author Arianit
 *
 */
public interface ScheduledSessionRepository extends CrudRepository<ScheduledSession, Integer> {
	
	long count();
	List<ScheduledSession> findByStartTime(Time startTime);
	List<ScheduledSession> findByAssignedTutor (Tutor tutor);
	List<ScheduledSession> findAll();
	


}
