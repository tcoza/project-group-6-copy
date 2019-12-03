package ca.mcgill.ecse321.tutoringapp.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import ca.mcgill.ecse321.tutoringapp.model.Course;
import ca.mcgill.ecse321.tutoringapp.model.GroupRequest;
import ca.mcgill.ecse321.tutoringapp.model.SessionRequest;
import ca.mcgill.ecse321.tutoringapp.model.Student;
import ca.mcgill.ecse321.tutoringapp.model.Subject;

/**
 * This interface is the CRUD repository for all SessionRequests for (group and
 * private) tutoring sessions in the Tutoring System. It is mapped as a
 * RepositoryRestResource that can be viewed with GET requests at
 * /sessionrequests
 * 
 * @author Helen
 */
@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "sessionrequests", path = "sessionrequests")
public interface SessionRequestRepository extends CrudRepository<SessionRequest, Integer> {

	SessionRequest findById(int id);

	// find by requested course
	List<SessionRequest> findByRequestedCourse(Course requestedCourse);

	// find by requested subject
	List<SessionRequest> findByRequestedSubject(Subject requestedSubject);

	// find by requestor
	List<SessionRequest> findByRequestor(Student requestor);

	// find all scheduled or unscheduled sessions
	List<GroupRequest> findByIsScheduled(boolean isScheduled);

	// find all
	List<SessionRequest> findAll();

}
