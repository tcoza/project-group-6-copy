package ca.mcgill.ecse321.tutoringapp.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringapp.model.Subject;

public interface SubjectReposetory extends CrudRepository<Subject, String> {

}
