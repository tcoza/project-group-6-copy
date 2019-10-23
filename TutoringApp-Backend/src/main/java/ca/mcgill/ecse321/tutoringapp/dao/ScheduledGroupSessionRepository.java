package ca.mcgill.ecse321.tutoringapp.dao;

import java.sql.Time;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ca.mcgill.ecse321.tutoringapp.model.ClassRoom;
import ca.mcgill.ecse321.tutoringapp.model.GroupRequest;
import ca.mcgill.ecse321.tutoringapp.model.Room;
import ca.mcgill.ecse321.tutoringapp.model.ScheduledGroupSession;
import ca.mcgill.ecse321.tutoringapp.model.Tutor;

/**
 * 
 * @author Arianit
 *
 */
@RepositoryRestResource(collectionResourceRel = "scheduledgroupsessions", path = "scheduledgroupsessions")
public interface ScheduledGroupSessionRepository extends CrudRepository<ScheduledGroupSession, Integer> {

	ScheduledGroupSession findByRoom (ClassRoom classRoom);
	ScheduledGroupSession findByStartTime (Time startTime);
	ScheduledGroupSession findByAssignedTutor (Tutor tutor);
	List<ScheduledGroupSession> findByGroupRequest (GroupRequest groupRequest);
	List<ScheduledGroupSession> findByIsFull (boolean isFull);
	List<ScheduledGroupSession> findAll();


}
