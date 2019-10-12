package ca.mcgill.ecse321.tutoringapp.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringapp.model.Course;

/**
 * 
 * @author Alba
 *
 */
public interface CourseRepository extends CrudRepository<Course,String> {

	Course findCourseByName(String name);	
}
