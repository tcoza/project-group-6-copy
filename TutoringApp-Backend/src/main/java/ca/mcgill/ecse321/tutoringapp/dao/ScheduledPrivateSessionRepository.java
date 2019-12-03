package ca.mcgill.ecse321.tutoringapp.dao;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import ca.mcgill.ecse321.tutoringapp.model.Room;
import ca.mcgill.ecse321.tutoringapp.model.ScheduledPrivateSession;
import ca.mcgill.ecse321.tutoringapp.model.SmallRoom;
import ca.mcgill.ecse321.tutoringapp.model.Tutor;

/**
 * This interface is the CRUD repository for all ScheduledPrivateSessions in the
 * Tutoring System. It is mapped as a RepositoryRestResource that can be viewed
 * with GET requests at /scheduledprivatesessions
 * 
 * @author Arianit
 */
@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "scheduledprivatesessions", path = "scheduledprivatesessions")
public interface ScheduledPrivateSessionRepository extends CrudRepository<ScheduledPrivateSession, Integer> {
	ScheduledPrivateSession findByAssignedTutor(Tutor tutor);

	List<ScheduledPrivateSession> findByStartTime(Time startTime);

	List<ScheduledPrivateSession> findByRoom(SmallRoom smallRoom);

	ScheduledPrivateSession deleteByAssignedTutorAndStartTimeAndRoomAndDate(Tutor tutor, Time startTime,
			SmallRoom smallRoom, Date date);

	List<ScheduledPrivateSession> findByIsFull(boolean isFull);

	List<ScheduledPrivateSession> findAll();

}
