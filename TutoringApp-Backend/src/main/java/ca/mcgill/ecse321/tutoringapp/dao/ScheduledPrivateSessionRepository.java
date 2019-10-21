package ca.mcgill.ecse321.tutoringapp.dao;

import java.sql.Time;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ca.mcgill.ecse321.tutoringapp.model.Room;
import ca.mcgill.ecse321.tutoringapp.model.ScheduledPrivateSession;
import ca.mcgill.ecse321.tutoringapp.model.Tutor;

/**
 * 
 * @author Arianit
 *
 */
@RepositoryRestResource(collectionResourceRel = "scheduledprivatesessions", path = "scheduledprivatesessions")
public interface ScheduledPrivateSessionRepository extends CrudRepository<ScheduledPrivateSession, Integer> {
	
	long count();
	
	List<ScheduledPrivateSession> findByAssignedTutor (Tutor tutor);
	List<ScheduledPrivateSession> findByStartTime (Time startTime);
	List<ScheduledPrivateSession> findByRoom (Room room);
	
	List<ScheduledPrivateSession> findByIsFull (boolean isFull);
	
	List<ScheduledPrivateSession> findAll();
	

}
