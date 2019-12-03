package ca.mcgill.ecse321.tutoringapp.dao;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import ca.mcgill.ecse321.tutoringapp.model.ClassRoom;
import ca.mcgill.ecse321.tutoringapp.model.GroupRequest;
import ca.mcgill.ecse321.tutoringapp.model.Room;
import ca.mcgill.ecse321.tutoringapp.model.ScheduledGroupSession;
import ca.mcgill.ecse321.tutoringapp.model.Tutor;

/**
 * This interface is the CRUD repository for all ScheduledGroupSessions in the
 * Tutoring System. It is mapped as a RepositoryRestResource that can be viewed
 * with GET requests at /scheduledgroupsessions
 * 
 * @author Arianit
 */
@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "scheduledgroupsessions", path = "scheduledgroupsessions")
public interface ScheduledGroupSessionRepository extends CrudRepository<ScheduledGroupSession, Integer> {

	ScheduledGroupSession findByRoom(ClassRoom classRoom);

	ScheduledGroupSession findByStartTime(Time startTime);

	ScheduledGroupSession findByAssignedTutor(Tutor tutor);

	ScheduledGroupSession deleteByAssignedTutorAndRoomAndStartTimeAndDate(Tutor tutor, ClassRoom classRoom,
			Time startTime, Date date);

	ScheduledGroupSession findByAssignedTutorAndRoomAndStartTimeAndDate(Tutor tutor, ClassRoom classRoom,
			Time startTime, Date date);

	List<ScheduledGroupSession> findByGroupRequest(GroupRequest groupRequest);

	List<ScheduledGroupSession> findByIsFull(boolean isFull);

	List<ScheduledGroupSession> findAll();

}
