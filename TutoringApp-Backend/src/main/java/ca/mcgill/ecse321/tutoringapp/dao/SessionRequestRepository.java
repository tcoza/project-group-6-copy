package ca.mcgill.ecse321.tutoringapp.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringapp.model.Course;
import ca.mcgill.ecse321.tutoringapp.model.GroupRequest;
import ca.mcgill.ecse321.tutoringapp.model.SessionRequest;
import ca.mcgill.ecse321.tutoringapp.model.Student;
import ca.mcgill.ecse321.tutoringapp.model.Subject;

/**
 * @author Helen
 * 
 */
public interface SessionRequestRepository extends CrudRepository<SessionRequest, Integer> {

	//total session requests
	long count(); 
	
	//find one
	SessionRequest findById(int id);

	//find many
	List<SessionRequest> findAllById(List<Integer> ids);	

	//find by requested course
	List<SessionRequest> findByCourse(Course requestedCourse);
		
	//find by requested subject
	List<SessionRequest> findBySubject(Subject requestedSubject);
	
	//find by requestor
	List<SessionRequest> findByStudent(Student requestor);
	
	//find all scheduled or unscheduled sessions
	List<GroupRequest> findByIsScheduled(boolean isScheduled);
		
	//find all
	List<SessionRequest> findAll();

}
