package ca.mcgill.ecse321.tutoringapp.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringapp.model.Student;

public interface StudentReposetory extends CrudRepository<Student, String> {

}
