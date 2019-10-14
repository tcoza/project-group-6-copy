package ca.mcgill.ecse321.tutoringapp.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringapp.model.Course;
import ca.mcgill.ecse321.tutoringapp.model.GroupRequest;
import ca.mcgill.ecse321.tutoringapp.model.Student;
import ca.mcgill.ecse321.tutoringapp.model.Subject;

/**
 * 
 * @author Helen
 *
 */
public interface GroupRequestRepository extends CrudRepository<GroupRequest, Integer> {

	//total group requests
	long count(); 
	
	//find one
	GroupRequest findById(int id);

	//find many
	List<GroupRequest> findAllById(List<Integer> ids);	

	//find by requested course
	List<GroupRequest> findByRequestedCourse(Course requestedCourse);
		
	//find by requested subject
	List<GroupRequest> findByRequestedSubject(Subject requestedSubject);
	
	//find by requestor
	List<GroupRequest> findByRequestor(Student requestor);
	
	//find all scheduled or unscheduled sessions
	List<GroupRequest> findByIsScheduled(boolean isScheduled);
	
	//find all
	List<GroupRequest> findAll();
}

