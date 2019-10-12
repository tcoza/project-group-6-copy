package ca.mcgill.ecse321.tutoringapp.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringapp.model.Tutor;

public interface TutorRepository extends CrudRepository<Tutor, String> {

}
