package ca.mcgill.ecse321.tutoringapp.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import ca.mcgill.ecse321.tutoringapp.model.Course;

/**
 * This interface is the CRUD repository for all Courses in the Tutoring
 * System. It is mapped as a RepositoryRestResource that can be viewed with GET
 * requests at /courses
 * 
 * @author Alba
 */
@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "courses", path = "courses")
public interface CourseRepository extends CrudRepository<Course,String> {

	Course findCourseByCourseCode(String coursecode);

	boolean existsByCourseCode(String courseCode);	
}

