package ca.mcgill.ecse321.tutoringapp.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringapp.model.Course;

public interface CourseRepository extends CrudRepository<Course,String> {

	Course findCourseByName(String CourseCode);	
	
}
