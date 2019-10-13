package ca.mcgill.ecse321.tutoringapp.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringapp.model.ClassRoom;
import ca.mcgill.ecse321.tutoringapp.model.GroupRequest;
import ca.mcgill.ecse321.tutoringapp.model.PrivateRequest;
import ca.mcgill.ecse321.tutoringapp.model.Room;
import ca.mcgill.ecse321.tutoringapp.model.ScheduledGroupSession;
import ca.mcgill.ecse321.tutoringapp.model.Tutor;

/**
 * 
 * @author Arianit
 *
 */
public interface ScheduledGroupSessionRepository extends CrudRepository<ScheduledGroupSession, Integer> {
	long count();
	
	ScheduledGroupSession findById (List<Integer> ids);
	
	
	List<ScheduledGroupSession> findByRoom (Room room);
	
	List<ScheduledGroupSession> findByAssignedTutor (Tutor tutor);
	List<ScheduledGroupSession> findByGroupRequest (GroupRequest groupRequest);
	List<ScheduledGroupSession> findByIsFull (boolean isFull);
	List<ScheduledGroupSession> findAll();

}
