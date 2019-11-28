package ca.mcgill.ecse321.tutoringapp.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import ca.mcgill.ecse321.tutoringapp.model.Course;
import ca.mcgill.ecse321.tutoringapp.model.PrivateRequest;
import ca.mcgill.ecse321.tutoringapp.model.Student;
import ca.mcgill.ecse321.tutoringapp.model.Subject;

/**
 *
 * @author Helen
 *
 */
@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "privaterequests", path = "privaterequests")
public interface PrivateRequestRepository extends CrudRepository<PrivateRequest, Integer> {

	PrivateRequest findById(int id);
	//find by requested course
	List<PrivateRequest> findByRequestedCourse(Course requestedCourse);
		
	//find by requested subject
	List<PrivateRequest> findByRequestedSubject(Subject requestedSubject);
	
	//find by requestor
	List<PrivateRequest> findByRequestor(Student requestor);
	
	//find all scheduled or unscheduled sessions
	List<PrivateRequest> findByIsScheduled(boolean isScheduled);
	
	//find all
	List<PrivateRequest> findAll();
}
