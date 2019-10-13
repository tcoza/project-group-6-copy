package ca.mcgill.ecse321.tutoringapp.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringapp.model.Course;
import ca.mcgill.ecse321.tutoringapp.model.PrivateRequest;
import ca.mcgill.ecse321.tutoringapp.model.Student;
import ca.mcgill.ecse321.tutoringapp.model.Subject;

/**
 *
 * @author Helen
 *
 */
public interface PrivateRequestRepository extends CrudRepository<PrivateRequest, Integer> {

	//total group requests
	long count(); 
	
	//find one
	PrivateRequest findById(int id);

	//find many
	List<PrivateRequest> findAllById(List<Integer> ids);	

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
