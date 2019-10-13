package ca.mcgill.ecse321.tutoringapp.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringapp.model.Room;
import ca.mcgill.ecse321.tutoringapp.model.ScheduledPrivateSession;
import ca.mcgill.ecse321.tutoringapp.model.ScheduledSession;
import ca.mcgill.ecse321.tutoringapp.model.SmallRoom;
import ca.mcgill.ecse321.tutoringapp.model.Student;
import ca.mcgill.ecse321.tutoringapp.model.Tutor;

/**
 * 
 * @author Arianit
 *
 */
public interface ScheduledPrivateSessionRepository extends CrudRepository<ScheduledPrivateSession, Integer> {
	
	long count();
	
	List<ScheduledPrivateSession> findByAssignedTutor (Tutor tutor);
	List<ScheduledPrivateSession> findByStartTime (String startTime);
	List<ScheduledPrivateSession> findByRoom (Room room);
	
	List<ScheduledPrivateSession> findByIsFull (boolean isFull);
	
	List<ScheduledPrivateSession> findAll();
	

}
